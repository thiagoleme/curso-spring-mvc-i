package br.com.casadocodigo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role implements GrantedAuthority {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String nome;

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String getAuthority() {
		return nome;
	}
}
