package br.com.casadocodigo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.dao.ProdutoDAO;
import br.com.casadocodigo.model.CarrinhoCompras;
import br.com.casadocodigo.model.CarrinhoItem;
import br.com.casadocodigo.model.TipoPreco;

@Controller
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
@RequestMapping("/carrinho")
public class CarrinhoComprasController {

	private static final String VIEW_CARRINHO_COMPRAS = "/carrinho/itens";

	@Autowired
	private CarrinhoCompras carrinho;

	@Autowired
	private ProdutoDAO produtoDAO;

	@RequestMapping("/add")
	public ModelAndView add(Long produtoId, TipoPreco tipoPreco) {
		ModelAndView modelAndView = new ModelAndView("redirect:/carrinho");

		CarrinhoItem item = new CarrinhoItem(produtoDAO.find(produtoId), tipoPreco);
		carrinho.add(item);

		return modelAndView;
	}

	@RequestMapping(value = { "", "/" })
	public ModelAndView carrinho() {
		return new ModelAndView(VIEW_CARRINHO_COMPRAS);
	}
	
	@RequestMapping("/remover")
	public ModelAndView remover(Long produtoId, TipoPreco tipoPreco){
	    carrinho.remover(produtoId, tipoPreco);
	    return new ModelAndView("redirect:/carrinho");
	}
}
