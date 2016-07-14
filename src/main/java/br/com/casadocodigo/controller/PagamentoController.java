package br.com.casadocodigo.controller;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.model.CarrinhoCompras;
import br.com.casadocodigo.model.DadosPagamento;

@Controller
@RequestMapping("/pagamento")
public class PagamentoController {

	@Autowired
	CarrinhoCompras carrinho;

	@Autowired
	RestTemplate restTemplate;

	@RequestMapping(value = "/finalizar", method = RequestMethod.POST)
	public Callable<ModelAndView> finalizar(RedirectAttributes model) {
		return new Callable<ModelAndView>() {
			private RedirectAttributes model;

			public Callable<ModelAndView> setModel(RedirectAttributes model) {
				this.model = model;
				return this;
			}

			@Override
			public ModelAndView call() throws Exception {
				ModelAndView modelAndView = new ModelAndView("redirect:/produtos");
				String response;
				try {
					String uri = "http://book-payment.herokuapp.com/payment";
					response = restTemplate.postForObject(uri, new DadosPagamento(carrinho.getTotal()), String.class);
					model.addFlashAttribute("message", response);
				} catch (HttpClientErrorException e) {
					e.printStackTrace();
					model.addFlashAttribute("message", "Valor maior que o permitido");
				}
				return modelAndView;
			}

		}.setModel(model);

	}
}
