package br.com.casadocodigo.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.dao.ProdutoDAO;
import br.com.casadocodigo.dao.UsuarioDAO;
import br.com.casadocodigo.model.Role;
import br.com.casadocodigo.model.Usuario;

@Controller
public class HomeController {

	private static final String HOMEPAGE_VIEW = "home";

	@Autowired
	private ProdutoDAO produtoDAO;

	@Autowired
	private UsuarioDAO usuarioDAO;

	@Cacheable(value = "produtosHome")
	@RequestMapping("/")
	public ModelAndView homePage() {
		ModelAndView modelAndView = new ModelAndView(HOMEPAGE_VIEW);

		modelAndView.addObject("produtos", produtoDAO.listar());

		return modelAndView;
	}

	@ResponseBody
	@Transactional
	@RequestMapping("/url-malucaSDIFMQEQF40dfm3m59wdDFYT3242135ASREE132REDR")
	public String urlMalucaXCVASDM442D3RZSE() {
		Usuario usuario = new Usuario();
		usuario.setEmail("admin@admin.com.br");
		usuario.setNome("Admin");
		usuario.setSenha("$2a$10$wmVg8sYJviuxh.DMQg0.euOFTuGzqardH7ljuEc/W/2s9iQqqJ5HO");
		usuario.setRoles(Arrays.asList(new Role("ROLE_ADMIN")));

		usuarioDAO.gravar(usuario);

		return "admin cadastrado!";
	}
}