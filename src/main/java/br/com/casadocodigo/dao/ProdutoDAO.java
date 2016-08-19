package br.com.casadocodigo.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.casadocodigo.model.Produto;
import br.com.casadocodigo.model.TipoPreco;

@Transactional
@Repository
public class ProdutoDAO {
	@PersistenceContext
	private EntityManager manager;

	public void add(Produto produto) {
		manager.persist(produto);
	}

	public List<Produto> listar() {
		return manager.createQuery("select p from Produto p").getResultList();
	}

	public Produto find(Long id) {
		return manager.createQuery("select distinct(p) from Produto p "
									+ "join fetch p.precos prc "
									+ "where p.id = :id", Produto.class)
				.setParameter("id", id)
				.getSingleResult();
	}

	public BigDecimal somaProdutoPorTipoPreco(TipoPreco tipoPreco){
		TypedQuery<BigDecimal> query = manager.createQuery("select sum(prc.valor) from Produto p"
				+ " join p.precos prc where prc.tipo = :tipoPreco", BigDecimal.class);
		query.setParameter("tipoPreco", tipoPreco);
		
		return query.getSingleResult(); 
	}
	
}
