package it.prova.pizzastore.service;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.pizzastore.dao.OrdineDAO;
import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.web.listener.LocalEntityManagerFactoryListener;

public class OrdineServiceImpl implements OrdineService {

	private OrdineDAO ordineDAO;

	public void setOrdineDAO(OrdineDAO Ordine) {
		this.ordineDAO = ordineDAO;
	}

	public List<Ordine> listAllElements() throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			ordineDAO.setEntityManager(entityManager);

			return ordineDAO.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
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
