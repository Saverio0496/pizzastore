package it.prova.pizzastore.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

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
		if (pizzaInput == null) {
			throw new Exception("Problema valore in input");
		}
		pizzaInput.setAttivo(false);
		entityManager.merge(pizzaInput);
	}

	@Override
	public List<Pizza> findByExample(Pizza example) throws Exception {
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("select p from Pizza p where p.id = p.id ");

		if (StringUtils.isNotEmpty(example.getDescrizione())) {
			whereClauses.add(" p.descrizione  like :descrizione ");
			paramaterMap.put("descrizione", "%" + example.getDescrizione() + "%");
		}
		if (StringUtils.isNotEmpty(example.getIngredienti())) {
			whereClauses.add(" p.ingredienti like :ingredienti ");
			paramaterMap.put("ingredienti", "%" + example.getIngredienti() + "%");
		}
		if (example.getPrezzoBase() != null && example.getPrezzoBase() > 0) {
			whereClauses.add("p.prezzoBase =:prezzoBase ");
			paramaterMap.put("prezzoBase", example.getPrezzoBase());
		}

		queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Pizza> typedQuery = entityManager.createQuery(queryBuilder.toString(), Pizza.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
	}

}
