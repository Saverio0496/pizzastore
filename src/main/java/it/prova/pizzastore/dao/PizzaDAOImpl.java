package it.prova.pizzastore.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import it.prova.pizzastore.model.Pizza;

public class PizzaDAOImpl implements PizzaDAO {

	private EntityManager entityManager;

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Pizza> list() throws Exception {
		return null;
	}

	@Override
	public Optional<Pizza> findOne(Long id) throws Exception {
		return null;
	}

	@Override
	public void update(Pizza input) throws Exception {
	}

	@Override
	public void insert(Pizza input) throws Exception {
	}

	@Override
	public void delete(Pizza input) throws Exception {
	}

	@Override
	public List<Pizza> findByExample(Pizza example) throws Exception {
		return null;
	}

}
