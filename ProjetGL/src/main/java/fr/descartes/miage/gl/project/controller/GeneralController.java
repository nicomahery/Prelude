package fr.descartes.miage.gl.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.descartes.miage.gl.project.dao.CategoryRepository;

@Controller
public class GeneralController {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@RequestMapping("/")
	public String welcome(Model model){
		model.addAttribute("categories", categoryRepository.findAll());
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
	
	@RequestMapping("/back")
	public String backPage(){
		return "view/back";
	}
}
