package it.prova.pizzastore.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import it.prova.pizzastore.model.Ordine;

public class OrdineDAOImpl implements OrdineDAO {

	private EntityManager entityManager;

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Ordine> list() throws Exception {
		return entityManager.createQuery("from Ordine", Ordine.class).getResultList();
	}

	@Override
	public Optional<Ordine> findOne(Long id) throws Exception {
		Ordine result = entityManager.find(Ordine.class, id);
		return result != null ? Optional.of(result) : Optional.empty();
	}

	@Override
	public void update(Ordine ordineInput) throws Exception {
	}

	@Override
	public void insert(Ordine ordineInput) throws Exception {
		if (ordineInput == null) {
			throw new Exception("Problema valore in input");
		}

		entityManager.persist(ordineInput);
	}

	@Override
	public void delete(Ordine ordineInput) throws Exception {
		if (ordineInput == null) {
			throw new Exception("Problema valore in input");
		}
		ordineInput.setClosed(true);
		entityManager.merge(ordineInput);
	}

	@Override
	public List<Ordine> findByExample(Ordine example) throws Exception {
		return null;
	}

	@Override
	public Optional<Ordine> findOneEager(Long id) throws Exception {
		return entityManager.createQuery(
				"from Ordine o left join fetch o.cliente left join fetch o.utente left join fetch o.pizze where o.id=:idOrdine",
				Ordine.class).setParameter("idOrdine", id).getResultList().stream().findFirst();
	}

	@Override
	public Integer calculateOrderPrice(Ordine ordineInput) throws Exception {
		return entityManager
				.createQuery("select sum(p.prezzoBase) from Ordine o join o.pizze p where o.codice = :codice",
						Long.class)
				.setParameter("codice", ordineInput.getCodice()).getFirstResult();
	}
}
