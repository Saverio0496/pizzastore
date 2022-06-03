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
		return null;
	}

	@Override
	public Optional<Cliente> findOne(Long id) throws Exception {
		return null;
	}

	@Override
	public void update(Cliente input) throws Exception {
	}

	@Override
	public void insert(Cliente input) throws Exception {
	}

	@Override
	public void delete(Cliente input) throws Exception {
	}

	@Override
	public List<Cliente> findByExample(Cliente example) throws Exception {
		return null;
	}
}
