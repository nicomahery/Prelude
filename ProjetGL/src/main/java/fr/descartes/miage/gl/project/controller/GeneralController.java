package fr.descartes.miage.gl.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GeneralController {
	
	@RequestMapping("/")
	public String welcome(){
		return "index";
	}
}
