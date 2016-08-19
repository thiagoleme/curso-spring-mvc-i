package br.com.casadocodigo.dao;

import java.math.BigDecimal;
import java.util.List;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.com.casadocodigo.builders.ProdutoBuilder;
import br.com.casadocodigo.conf.DataSourceConfigurationTest;
import br.com.casadocodigo.configuration.JPAConfiguration;
import br.com.casadocodigo.model.Produto;
import br.com.casadocodigo.model.TipoPreco;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={JPAConfiguration.class, ProdutoDAO.class, DataSourceConfigurationTest.class})
@ActiveProfiles("test")
public class ProdutoDAOTest {

	@Autowired
	private ProdutoDAO produtoDAO;

	@Test
	@Transactional
	public void deveSomarPrecosPorLivro(){
		List<Produto> livrosImpressos = ProdutoBuilder.newProduto(TipoPreco.IMPRESSO, BigDecimal.TEN)
					.more(3).buildAll();
		
		List<Produto> livrosEbook = ProdutoBuilder.newProduto(TipoPreco.EBOOK, BigDecimal.TEN)
				.more(3).buildAll();
		
//		livrosImpressos.stream().forEach(produtoDAO::add);
		for (Produto produto : livrosImpressos) {
			produtoDAO.add(produto);
		}
		
//		livrosEbook.stream().forEach(produtoDAO::add);
		for (Produto produto : livrosEbook) {
			produtoDAO.add(produto);
		}
		
		BigDecimal valor = produtoDAO.somaProdutoPorTipoPreco(TipoPreco.EBOOK);
		
		Assert.assertEquals(new BigDecimal(40).setScale(2), valor);
	}
}
