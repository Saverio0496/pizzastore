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
		return entityManager.createQuery("from Pizza", Pizza.class).getResultList();
	}

	@Override
	public Optional<Pizza> findOne(Long id) throws Exception {
		Pizza result = entityManager.find(Pizza.class, id);
		return result != null ? Optional.of(result) : Optional.empty();
	}

	@Override
	public void update(Pizza pizzaInput) throws Exception {
		if (pizzaInput == null) {
			throw new Exception("Problema valore in input");
		}
		pizzaInput = entityManager.merge(pizzaInput);
	}

	@Override
	public void insert(Pizza pizzaInput) throws Exception {
		if (pizzaInput == null) {
			throw new Exception("Problema valore in input");
		}

		entityManager.persist(pizzaInput);
	}

	@Override
	public void delete(Pizza pizzaInput) throws Exception {
	}

	@Override
	public List<Pizza> findByExample(Pizza example) throws Exception {
		return null;
	}

}
