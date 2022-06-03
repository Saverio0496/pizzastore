package it.prova.pizzastore.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import it.prova.pizzastore.model.Cliente;

public class ClienteDAOImpl implements ClienteDAO {

	private EntityManager entityManager;

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Cliente> list() throws Exception {
		return entityManager.createQuery("from Cliente", Cliente.class).getResultList();
	}

	@Override
	public Optional<Cliente> findOne(Long id) throws Exception {
		Cliente result = entityManager.find(Cliente.class, id);
		return result != null ? Optional.of(result) : Optional.empty();
	}

	@Override
	public void update(Cliente clienteInput) throws Exception {
		if (clienteInput == null) {
			throw new Exception("Problema valore in input");
		}
		clienteInput = entityManager.merge(clienteInput);
	}

	@Override
	public void insert(Cliente clienteInput) throws Exception {
		if (clienteInput == null) {
			throw new Exception("Problema valore in input");
		}

		entityManager.persist(clienteInput);
	}

	@Override
	public void delete(Cliente clienteInput) throws Exception {
		if (clienteInput == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(clienteInput));
	}

	@Override
	public List<Cliente> findByExample(Cliente example) throws Exception {
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("select c from Cliente c where c.id = c.id ");

		if (StringUtils.isNotEmpty(example.getNome())) {
			whereClauses.add(" c.nome  like :nome ");
			paramaterMap.put("nome", "%" + example.getNome() + "%");
		}
		if (StringUtils.isNotEmpty(example.getCognome())) {
			whereClauses.add(" c.cognome like :cognome ");
			paramaterMap.put("cognome", "%" + example.getCognome() + "%");
		}
		if (StringUtils.isNotEmpty(example.getIndirizzo())) {
			whereClauses.add(" c.indirizzo like :indirizzo ");
			paramaterMap.put("indirizzo", "%" + example.getIndirizzo() + "%");
		}

		queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Cliente> typedQuery = entityManager.createQuery(queryBuilder.toString(), Cliente.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
	}
}
