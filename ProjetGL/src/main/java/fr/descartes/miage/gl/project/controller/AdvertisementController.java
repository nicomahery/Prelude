package fr.descartes.miage.gl.project.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fr.descartes.miage.gl.project.dao.AddressRepository;
import fr.descartes.miage.gl.project.dao.AdvertisementRepository;
import fr.descartes.miage.gl.project.dao.CategoryRepository;
import fr.descartes.miage.gl.project.dao.UserRepository;
import fr.descartes.miage.gl.project.entities.Address;
import fr.descartes.miage.gl.project.entities.Advertisement;
import fr.descartes.miage.gl.project.entities.Category;
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

	@RequestMapping(value="/createAdvertisementPage", method=RequestMethod.GET)
	public String create(Model model, HttpSession session){
		User user = userRepository.findOne((Long)session.getAttribute("userId"));
		model.addAttribute("user", user);
		model.addAttribute("address", user.getAddress());
		List<Category> listCategory = categoryRepository.findAll();
		model.addAttribute("categories", listCategory);
		return "createAdvertisement";
	}

	@RequestMapping(value="/createAdvertisement", method=RequestMethod.POST)
	public String createAdvetisement(Model model,@Valid Address address, @RequestParam("categories") String category, @RequestParam("title") String title ,@RequestParam("description") String description, HttpSession session){
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
			/*List<Address> listAd = addressRepository.findAll();
			boolean find = false;
			long idAdFind=0;
			for ( int i =0; i< listAd.size(); ++i)
				if (   listAd.get(i).getCity().equals(address.getCity())
					&& listAd.get(i).getCountry().equals(address.getCountry())	
					&& listAd.get(i).getStreet().equals(address.getStreet())	
					&& listAd.get(i).getZip().equals(address.getZip())){
					find=true;
					idAdFind=listAd.get(i).getId();
				}
			if (find)
				advertisement.setAddress(addressRepository.findOne(idAdFind));
			else{*/
				addressRepository.save(address);	
			    advertisement.setAddress(address);
			  // }
		}
		advertisementRepository.save(advertisement);
		model.addAttribute("advertisement", advertisement);
		model.addAttribute("user",userRepository.findOne((Long)session.getAttribute("userId")));
		
		return "advertisementSuccess";
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
			/*List<Address> listAd = addressRepository.findAll();
			boolean find = false;
			long idAdFind=0;
			for ( int i =0; i< listAd.size(); ++i)
				if (   listAd.get(i).getCity().equals(address.getCity())
					&& listAd.get(i).getCountry().equals(address.getCountry())	
					&& listAd.get(i).getStreet().equals(address.getStreet())	
					&& listAd.get(i).getZip().equals(address.getZip())){
					find=true;
					idAdFind=listAd.get(i).getId();
				}
			if (find)
				advertisement.setAddress(addressRepository.findOne(idAdFind));
			else{*/
				addressRepository.save(address);	
			    advertisement.setAddress(address);
			  // }
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
