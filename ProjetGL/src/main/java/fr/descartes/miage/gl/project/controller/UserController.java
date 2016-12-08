package fr.descartes.miage.gl.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
		return "subForm";
	}
	
	@RequestMapping(value="/subscribe", method=RequestMethod.POST)
	public String subscribe(Model model, Address address, User user){
		addressRepository.save(address);
		user.setAddress(address);
		userRepository.save(user);
		return "subSuccess";
	}
}