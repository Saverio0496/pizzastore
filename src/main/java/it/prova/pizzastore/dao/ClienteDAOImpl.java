package it.prova.pizzastore.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

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
	}

	@Override
	public List<Cliente> findByExample(Cliente example) throws Exception {
		return null;
	}
}
