package br.com.casadocodigo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	private static final String HOMEPAGE_VIEW = "home";

	@RequestMapping("/")
	public String homePage(){
		return HOMEPAGE_VIEW;
	}
}