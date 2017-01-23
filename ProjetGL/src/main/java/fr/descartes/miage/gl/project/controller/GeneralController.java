package fr.descartes.miage.gl.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GeneralController {
	
	@RequestMapping("/")
	public String welcome(){
		return "view/index";
	}
	
	@RequestMapping("/about")
	public String about(){
		return "view/about";
	}
	
	@RequestMapping("/information")
	public String information(){
		return "view/informations";
	}
	
	@RequestMapping("/research")
	public String research(){
		return "view/research";
	}
	
	@RequestMapping("/product")
	public String product(){
		return "view/product";
	}
	
	@RequestMapping("/addAdvert")
	public String addAdvert(){
		return "view/addAdvert";
	}
	
	@RequestMapping("/modifyAdvert")
	public String modifyAdvert(){
		return "view/modifyAdvert";
	}
	
}
