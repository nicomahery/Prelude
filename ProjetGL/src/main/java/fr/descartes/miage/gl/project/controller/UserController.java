package fr.descartes.miage.gl.project.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fr.descartes.miage.gl.project.dao.AddressRepository;
import fr.descartes.miage.gl.project.dao.UserRepository;
import fr.descartes.miage.gl.project.entities.Address;
import fr.descartes.miage.gl.project.entities.User;

@Controller
@RequestMapping(value="/user")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AddressRepository addressRepository;
	
	@RequestMapping(value="/subscribePage", method=RequestMethod.GET)
	public String create(Model model){
		model.addAttribute("user", new User());
		model.addAttribute("address", new Address());
		return "userSubForm";
	}
	
	@RequestMapping(value="/subscribe", method=RequestMethod.POST)
	public String subscribe(Model model,@Valid Address address,@Valid User user, BindingResult bindingResult){
		System.out.println("Parameters: username:"+user.getPassword());
		if(bindingResult.hasErrors())
			return "userSubForm";
		addressRepository.save(address);
		user.setAddress(address);
		userRepository.save(user);
		return "userSuccess";
	}
	
	@RequestMapping(value="/modifyPage", method=RequestMethod.GET)
	public String modifyPage(Model model, HttpSession session){
		User user = userRepository.findOne((Long)session.getAttribute("userId"));
		model.addAttribute("user", user);
		model.addAttribute("address", user.getAddress());
		return "userModifyForm";
	}
	
	@RequestMapping(value="/connect", method=RequestMethod.POST)
	public String connect(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session){
		System.out.println("Parameters: username:"+username+" password" +password);
		for(User u:userRepository.findAll()){
			System.out.println("id:"+u.getId()+ " username:"+u.getUsername()+ " password:"+u.getPassword());
			if(u.getUsername().equals(username) && u.getPassword().equals(password)) {
				session.setAttribute("userId", u.getId());
				session.setAttribute("userUsername", u.getUsername());
				return "redirect:../";
			}
		}
		return "redirect:../";
	}
	
	
	
	@RequestMapping(value="/disconnect", method=RequestMethod.GET)
	public String disconnect(HttpSession session){
		session.removeAttribute("userId");
		session.removeAttribute("userUsername");
		return "redirect:../";
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public String modify(Model model, User user, Address address){
		System.out.println(address.getId());
		User newUser = userRepository.findOne(user.getId());
		Address newAddress = addressRepository.findOne(address.getId());
		newAddress.setId(address.getId());
		newAddress.setStreet(address.getStreet());
		newAddress.setCity(address.getCity());
		newAddress.setZip(address.getZip());
		newAddress.setCountry(address.getCountry());
		newUser.setId(user.getId());
		newUser.setUsername(user.getUsername());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setPassword(user.getPassword());
		newUser.setMailAddress(user.getMailAddress());
		newUser.setPhoneNumber(user.getPhoneNumber());
		newUser.setAddress(address);
		userRepository.save(newUser);
		addressRepository.save(newAddress);
		return "userSuccess";
	}
	
	
}
