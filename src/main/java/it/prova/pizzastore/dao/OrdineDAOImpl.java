package it.prova.pizzastore.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.model.StatoUtente;

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
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("select o from Ordine o where o.id = o.id ");

		if (example.getCliente() != null && example.getCliente().getId() != null && example.getCliente().getId() > 0
				&& example.getCliente().isAttivo()) {
			whereClauses.add(" o.cliente_id = :cliente_id ");
			paramaterMap.put("cliente_id", example.getCliente().getId());
		}
		if (example.getData() != null) {
			whereClauses.add(" o.data = :data ");
			paramaterMap.put("data", example.getData());
		}
		if (example.getUtente() != null && example.getUtente().getId() != null && example.getUtente().getId() > 0
				&& example.getUtente().getStato() == StatoUtente.ATTIVO) {
			whereClauses.add(" o.utente_id = :utente_id ");
			paramaterMap.put("utente_id", example.getUtente().getId());
		}
		if (StringUtils.isNotEmpty(example.getCodice())) {
			whereClauses.add(" o.codice  like :codice ");
			paramaterMap.put("codice", "%" + example.getCodice() + "%");
		}
		if (example.getCostoTotaleOrdine() != null && example.getCostoTotaleOrdine() > 0) {
			whereClauses.add(" o.costoTotaleOrdine = :costoTotaleOrdine ");
			paramaterMap.put("costoTotaleOrdine", example.getCostoTotaleOrdine());
		}
		queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Ordine> typedQuery = entityManager.createQuery(queryBuilder.toString(), Ordine.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
	}

	@Override
	public Optional<Ordine> findOneEager(Long id) throws Exception {
		return entityManager.createQuery(
				"from Ordine o left join fetch o.cliente left join fetch o.utente left join fetch o.pizze where o.id=:idOrdine",
				Ordine.class).setParameter("idOrdine", id).getResultList().stream().findFirst();
	}

	@Override
	public Integer calculateOrderPrice(Ordine ordineInput) throws Exception {
		TypedQuery<Long> typedQuery = entityManager.createQuery(
				"select sum(p.prezzoBase) from Ordine o join o.pizze p where o.codice = :codice", Long.class);
		return typedQuery.setParameter("codice", ordineInput.getCodice()).getFirstResult();
	}
	
	@Override
	public List<Ordine> listFattorini(Long idFattorino) throws Exception {
		return entityManager
				.createQuery("select o FROM Ordine o where o.closed = 'false' and o.utente.id = ?1", Ordine.class)
				.setParameter(1, idFattorino).getResultList();
	}
}
