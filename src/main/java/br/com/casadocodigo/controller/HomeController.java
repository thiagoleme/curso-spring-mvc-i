package br.com.casadocodigo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.dao.ProdutoDAO;

@Controller
public class HomeController {

	private static final String HOMEPAGE_VIEW = "home";

	@Autowired
	private ProdutoDAO produtoDAO;

	@Cacheable(value = "produtosHome")
	@RequestMapping("/")
	public ModelAndView homePage() {
		ModelAndView modelAndView = new ModelAndView(HOMEPAGE_VIEW);

		modelAndView.addObject("produtos", produtoDAO.listar());

		return modelAndView;
	}
}