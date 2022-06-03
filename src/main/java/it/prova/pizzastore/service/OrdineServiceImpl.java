package it.prova.pizzastore.service;

import java.util.List;

import it.prova.pizzastore.dao.OrdineDAO;
import it.prova.pizzastore.model.Ordine;

public class OrdineServiceImpl implements OrdineService {

	private OrdineDAO ordineDAO;

	public void setOrdineDAO(OrdineDAO Ordine) {
		this.ordineDAO = ordineDAO;
	}

	public List<Ordine> listAllElements() throws Exception {
		return null;
	}

	public Ordine caricaSingoloElemento(Long id) throws Exception {
		return null;
	}

	public void aggiorna(Ordine ordineInstance) throws Exception {
	}

	public void inserisciNuovo(Ordine ordineInstance) throws Exception {
	}

	public void rimuovi(Long idOrdineToRemove) throws Exception {
	}

	public List<Ordine> findByExample(Ordine example) throws Exception {
		return null;
	}

}
