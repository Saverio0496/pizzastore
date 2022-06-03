package it.prova.pizzastore.service;

import java.util.List;

import it.prova.pizzastore.dao.PizzaDAO;
import it.prova.pizzastore.model.Pizza;

public class PizzaServiceImpl implements PizzaService {

	private PizzaDAO pizzaDAO;

	@Override
	public void setPizzaDAO(PizzaDAO pizzaDAO) {
		this.pizzaDAO = pizzaDAO;
	}

	@Override
	public List<Pizza> listAllElements() throws Exception {
		return null;
	}

	@Override
	public Pizza caricaSingoloElemento(Long id) throws Exception {
		return null;
	}

	@Override
	public void aggiorna(Pizza pizzaInstance) throws Exception {
	}

	@Override
	public void inserisciNuovo(Pizza pizzaInstance) throws Exception {
	}

	@Override
	public void rimuovi(Long idPizzaToRemove) throws Exception {
	}

	@Override
	public List<Pizza> findByExample(Pizza example) throws Exception {
		return null;
	}

}
