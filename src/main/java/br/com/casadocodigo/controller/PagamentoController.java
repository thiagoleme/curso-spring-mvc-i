package br.com.casadocodigo.controller;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.model.CarrinhoCompras;
import br.com.casadocodigo.model.DadosPagamento;
import br.com.casadocodigo.model.Usuario;

@Controller
@RequestMapping("/pagamento")
public class PagamentoController {

	@Autowired
	CarrinhoCompras carrinho;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	private MailSender sender;

	@RequestMapping(value = "/finalizar", method = RequestMethod.POST)
	public Callable<ModelAndView> finalizar(@AuthenticationPrincipal Usuario usuario, RedirectAttributes model) {
		return () -> {
			String uri = "http://book-payment.herokuapp.com/payment";

			try {
				String response = restTemplate.postForObject(uri, new DadosPagamento(carrinho.getTotal()),
						String.class);
				model.addFlashAttribute("message", response);

				enviaEmailPedido(usuario);

			} catch (HttpClientErrorException e) {
				e.printStackTrace();
				model.addFlashAttribute("message", "Valor maior que o permitido");
			}

			return new ModelAndView("redirect:/produtos");
		};

	}

	private void enviaEmailPedido(Usuario usuario) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom("webapp.java.test@gmail.com");
		mailMessage.setTo("webapp.java.test@gmail.com"); // usuario.getEmail();
		mailMessage.setSubject("Compra finalizada com Sucesso!");
		mailMessage.setText("Compra aprovada no valor de R$" + carrinho.getTotal());

		sender.send(mailMessage);
	}

}
