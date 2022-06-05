package it.prova.pizzastore.dao;

import java.util.List;

import it.prova.pizzastore.model.Ordine;

public interface OrdineDAO extends IBaseDAO<Ordine> {

	public List<Ordine> findByExample(Ordine example) throws Exception;

	public Ordine findOneEager(Long id) throws Exception;

	public Integer calculateOrderPrice(Ordine ordineInput) throws Exception;

	public List<Ordine> listFattorini(Long idFattorino) throws Exception;

}
