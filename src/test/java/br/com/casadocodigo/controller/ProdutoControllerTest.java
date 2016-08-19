package br.com.casadocodigo.controller;

import javax.servlet.Filter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.casadocodigo.conf.DataSourceConfigurationTest;
import br.com.casadocodigo.configuration.AppWebConfiguration;
import br.com.casadocodigo.configuration.JPAConfiguration;
import br.com.casadocodigo.configuration.SecurityConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { AppWebConfiguration.class, JPAConfiguration.class, DataSourceConfigurationTest.class, SecurityConfiguration.class })
@ActiveProfiles("test")
public class ProdutoControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private Filter springSecurityFilterChain;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
								.addFilter(springSecurityFilterChain).build();
	}

	@Test
	public void deveRetornarParaHomeComLivros() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/"))
					.andExpect(MockMvcResultMatchers.model().attributeExists("produtos"))
					.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/home.jsp"));
	}
	
	@Test
	public void somenteAdminAcessaFormDeProdutos() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/produtos/form")
							.with(SecurityMockMvcRequestPostProcessors
									.user("usuario@casadocodigo.com.br")
									.password("123456")
									.roles("USUARIO")))
						.andExpect(MockMvcResultMatchers.status().is(403));
	}
}
