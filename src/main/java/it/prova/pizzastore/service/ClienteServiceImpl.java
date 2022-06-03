package it.prova.pizzastore.service;

import java.util.List;

import it.prova.pizzastore.dao.ClienteDAO;
import it.prova.pizzastore.model.Cliente;

public class ClienteServiceImpl implements ClienteService {

	private ClienteDAO clienteDAO;

	@Override
	public void setClienteDAO(ClienteDAO clienteDAO) {
		this.clienteDAO = clienteDAO;
	}

	@Override
	public List<Cliente> listAllElements() throws Exception {
		return null;
	}

	@Override
	public Cliente caricaSingoloElemento(Long id) throws Exception {
		return null;
	}

	@Override
	public void aggiorna(Cliente clienteInstance) throws Exception {
	}

	@Override
	public void inserisciNuovo(Cliente clienteInstance) throws Exception {
	}

	@Override
	public void rimuovi(Long idClienteToRemove) throws Exception {
	}

	@Override
	public List<Cliente> findByExample(Cliente example) throws Exception {
		return null;
	}
}
