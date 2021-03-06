package it.prova.pizzastore.service;

import java.util.List;

import it.prova.pizzastore.dao.OrdineDAO;
import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.model.Pizza;

public interface OrdineService {

	public List<Ordine> listAllElements() throws Exception;

	public Ordine caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(Ordine ordineInstance) throws Exception;

	public void inserisciNuovo(Ordine ordineInstance) throws Exception;

	public void rimuovi(Long idOrdineToRemove) throws Exception;

	public List<Ordine> findByExample(Ordine example) throws Exception;
	
	public Ordine caricaSingoloElementoEager(Long id) throws Exception;
	
	public Integer calcolaPrezzoOrdine(Ordine ordineInstance) throws Exception;
	
	public List<Ordine> listAllOrdiniAperti(Long idFattorino) throws Exception;
	
	public void aggiungiPizze(Ordine ordineInstance, Pizza pizzaDaAggiungere) throws Exception;

	// per injection
	public void setOrdineDAO(OrdineDAO Ordine);

}
