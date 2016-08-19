package br.com.casadocodigo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Profile("prod")
@EnableTransactionManagement
public class JPAProductionConfiguration {

	@Autowired
	private Environment env;

}
