package fr.descartes.miage.gl.project.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import fr.descartes.miage.gl.project.dao.AddressRepository;
import fr.descartes.miage.gl.project.dao.AdvertisementRepository;
import fr.descartes.miage.gl.project.dao.CategoryRepository;
import fr.descartes.miage.gl.project.dao.PhotoRepository;
import fr.descartes.miage.gl.project.dao.UserRepository;
import fr.descartes.miage.gl.project.entities.Address;
import fr.descartes.miage.gl.project.entities.Advertisement;
import fr.descartes.miage.gl.project.entities.Category;
import fr.descartes.miage.gl.project.entities.Photo;
import fr.descartes.miage.gl.project.entities.User;

@Controller
@RequestMapping(value="/advertisement")
public class AdvertisementController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private AdvertisementRepository advertisementRepository;
	@Autowired
	private PhotoRepository photoRepository;

	@RequestMapping(value="/createAdvertisementPage", method=RequestMethod.GET)
	public String create(Model model, HttpSession session){
		User user = userRepository.findOne((Long)session.getAttribute("userId"));
		model.addAttribute("user", user);
		model.addAttribute("address", user.getAddress());
		List<Category> listCategory = categoryRepository.findAll();
		model.addAttribute("categories", listCategory);
		model.addAttribute("adv", new Advertisement());
		return "view/addAdvert";
	}

	@RequestMapping(value="/createAdvertisement", method=RequestMethod.POST)
	public String createAdvetisement(Model model,@Valid Address address, @RequestParam("categories") String category, @RequestParam("title") String title ,@RequestParam("description") String description, HttpSession session, @RequestParam("file") MultipartFile[] files){
		Advertisement advertisement = new Advertisement();
		advertisement.setTitle(title);
		advertisement.setDescription(description);
		advertisement.setCategory(categoryRepository.findOne(Long.valueOf(category)));
		advertisement.setDate(new Timestamp(System.currentTimeMillis()));
		advertisement.setOwner(userRepository.findOne((Long)session.getAttribute("userId")));
		advertisement.setSold(false);
		
		
		//si ad==ad user 
		if(		address.getCity().equals(userRepository.findOne((Long)session.getAttribute("userId")).getAddress().getCity()) 
			&&  address.getCountry().equals(userRepository.findOne((Long)session.getAttribute("userId")).getAddress().getCountry())
			&&  address.getStreet().equals(userRepository.findOne((Long)session.getAttribute("userId")).getAddress().getStreet())
			&&  address.getZip().equals(userRepository.findOne((Long)session.getAttribute("userId")).getAddress().getZip())) 
			advertisement.setAddress(userRepository.findOne((Long)session.getAttribute("userId")).getAddress());
		else{
				addressRepository.save(address);	
			    advertisement.setAddress(address);
		}
		advertisementRepository.save(advertisement);

		//if(file.isEmpty())
		
		//else
		for (MultipartFile file : files) {
			try {
				Photo photo = new Photo();
				photo.setAdvertisement(advertisement);
				photo.setData(file.getBytes());
				photoRepository.save(photo);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		model.addAttribute("advertisement", advertisement);
		model.addAttribute("user",userRepository.findOne((Long)session.getAttribute("userId")));
		
		return "advertisementSuccess";
	}
	
	@RequestMapping(value="/photoSearch/{imageId}", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<byte[]> getImage(@PathVariable("imageId") Long imageId){
		byte[] imageContent = photoRepository.findOne(imageId).getData();
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		return new ResponseEntity<byte[]>(imageContent, headers, HttpStatus.OK);
	}
	
	@RequestMapping(value="/userAdvertisementsPage", method=RequestMethod.GET)
	public String userAdvertisements(Model model, HttpSession session){
		User user = userRepository.findOne((Long)session.getAttribute("userId"));
		model.addAttribute("advertisements", advertisementRepository.findByOwner_idAndSold((Long)session.getAttribute("userId"), false));
		//model.addAttribute("advertisement", new Advertisement());
		return "userAdvertisements";
	}
	
	
	@RequestMapping(value="/modifyAdvertisementPage", method=RequestMethod.GET)
	public String mofifyAdvertisementPage(Model model, @RequestParam("advertisementId") String advertisementId, HttpSession session){
		//User user = userRepository.findOne((Long)session.getAttribute("userId"));
		Advertisement a=advertisementRepository.findOne(Long.valueOf(advertisementId));
		model.addAttribute("advertisementModify", a);
		model.addAttribute("address", a.getAddress() );
		List<Category> listCategory = categoryRepository.findAll();
		model.addAttribute("categories", listCategory);
		//model.addAllAttributes("address", addressRepository.)
		return "view/modifyAdvertissement";
	}
	
	@RequestMapping(value="/getAdvertisement", method=RequestMethod.GET)
	public String getAdvertisement(Model model, @RequestParam("id") Long id){
		Advertisement adv = advertisementRepository.findOne(id);
		model.addAttribute("adv", adv);
		model.addAttribute("advAdd", adv.getAddress());
		model.addAttribute("advOwn", adv.getOwner());
		model.addAttribute("photos", this.getAllImage(adv));
		return "view/product";
	}
	
	@RequestMapping(value="/research", method=RequestMethod.POST)
	public String research(Model model, @RequestParam("researchTxt") String titleReseach){
		List<Advertisement> adv = advertisementRepository.findAll();
		HashMap<Long,Advertisement> advFinal= new HashMap <Long, Advertisement>();
		
		List <Long>listImg= new ArrayList <Long>();
		
		for(Advertisement a : adv){
			if(a.getTitle().contains(titleReseach)){
				advFinal.put(this.getOneImage(a), a);
			}
		}
		model.addAttribute("advlist", advFinal);
		return "view/research";
	}
	

	
	public List<Long> getAllImage(Advertisement adv){
		List<Long> res = new ArrayList<Long>();
		for(Photo p: photoRepository.findByAdvertisement(adv))
			res.add(p.getId());
		return res;
	}
	
	public long getOneImage(Advertisement adv){
		List<Long> res = new ArrayList<Long>();
		for(Photo p: photoRepository.findByAdvertisement(adv))
			res.add(p.getId());
		return res.get(0);
	}
	
	@RequestMapping(value="/modifyAdvertisement", method=RequestMethod.POST)
	public String modifyAdvetisement(Model model,@RequestParam("advertisementId") String advertisementId, @Valid Address address, @RequestParam("categories") String category, @RequestParam("title") String title ,@RequestParam("description") String description, HttpSession session){
		Advertisement advertisement = advertisementRepository.findOne(Long.valueOf(advertisementId));
		advertisement.setTitle(title);
		advertisement.setDescription(description);
		advertisement.setCategory(categoryRepository.findOne(Long.valueOf(category)));
		//si ad==ad advert 
		if(		address.getCity().equals(advertisement.getAddress().getCity()) 
			&&  address.getCountry().equals(advertisement.getAddress().getCountry())
			&&  address.getStreet().equals(advertisement.getAddress().getStreet())
			&&  address.getZip().equals(advertisement.getAddress().getZip())) 
			;
		else{
				addressRepository.save(address);	
			    advertisement.setAddress(address);
		}
		advertisementRepository.save(advertisement);
		model.addAttribute("advertisement", advertisement);
		model.addAttribute("user",userRepository.findOne((Long)session.getAttribute("userId")));
		
		return "advertisementSuccess";
	}

	@RequestMapping(value="/deleteAdvertisement", method=RequestMethod.GET)
	public String deleteAdvertisementPage(Model model, @RequestParam("advertisementId") String advertisementId, HttpSession session){
		Advertisement a=advertisementRepository.findOne(Long.valueOf(advertisementId));
		a.setSold(true);
		advertisementRepository.save(a);
		return  "redirect:../";
	}
	
	@RequestMapping(value="/advertisementPage", method=RequestMethod.GET)
	public String advertisementPage(Model model, @RequestParam("advertisementId") String advertisementId, HttpSession session){
		//User user = userRepository.findOne((Long)session.getAttribute("userId"));
		Advertisement a=advertisementRepository.findOne(Long.valueOf(advertisementId));
		model.addAttribute("advertisement", a);
		model.addAttribute("user",userRepository.findOne((Long)session.getAttribute("userId")));
		//model.addAllAttributes("address", addressRepository.)
		return "advertisementSuccess";
	}
	


	
}
