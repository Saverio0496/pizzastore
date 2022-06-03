package it.prova.pizzastore.service;

import java.util.List;

import it.prova.pizzastore.dao.OrdineDAO;
import it.prova.pizzastore.model.Ordine;

public interface OrdineService {

	public List<Ordine> listAllElements() throws Exception;

	public Ordine caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(Ordine ordineInstance) throws Exception;

	public void inserisciNuovo(Ordine ordineInstance) throws Exception;

	public void rimuovi(Long idOrdineToRemove) throws Exception;

	public List<Ordine> findByExample(Ordine example) throws Exception;

	// per injection
	public void setOrdineDAO(OrdineDAO Ordine);

}
