package it.prova.pizzastore.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import it.prova.pizzastore.model.Ordine;

public class OrdineDAOImpl implements OrdineDAO {

	private EntityManager entityManager;

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Ordine> list() throws Exception {
		return null;
	}

	@Override
	public Optional<Ordine> findOne(Long id) throws Exception {
		return null;
	}

	@Override
	public void update(Ordine input) throws Exception {
	}

	@Override
	public void insert(Ordine input) throws Exception {
	}

	@Override
	public void delete(Ordine input) throws Exception {
	}

	@Override
	public List<Ordine> findByExample(Ordine example) throws Exception {
		return null;
	}

}
