package it.prova.pizzastore.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import it.prova.pizzastore.model.Cliente;
import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.model.Pizza;
import it.prova.pizzastore.model.Utente;
import it.prova.pizzastore.service.MyServiceFactory;

public class UtilityForm {

	public static Pizza createPizzaFromParams(String descrizioneInputParam, String ingredientiInputParam,
			String prezzoBaseInputParam) {

		Pizza result = new Pizza(descrizioneInputParam, ingredientiInputParam);
		if (NumberUtils.isCreatable(prezzoBaseInputParam)) {
			result.setPrezzoBase(Integer.parseInt(prezzoBaseInputParam));
		}
		return result;
	}

	public static Pizza createPizzaFromParams(String descrizioneInputParam, String ingredientiInputParam,
			String prezzoBaseInputParam, boolean attivoInputParam) {
		Pizza result = new Pizza(descrizioneInputParam, ingredientiInputParam, attivoInputParam);
		if (NumberUtils.isCreatable(prezzoBaseInputParam)) {
			result.setPrezzoBase(Integer.parseInt(prezzoBaseInputParam));
		}
		return result;
	}

	public static boolean validatePizzaBean(Pizza pizzaToBeValidated) {
		if (StringUtils.isBlank(pizzaToBeValidated.getDescrizione())
				|| StringUtils.isBlank(pizzaToBeValidated.getIngredienti())
				|| pizzaToBeValidated.getPrezzoBase() == 0) {
			return false;
		}
		return true;
	}

	public static Cliente createClienteFromParams(String nomeInputParam, String cognomeInputParam,
			String indirizzoInputParam) {

		Cliente result = new Cliente(nomeInputParam, cognomeInputParam, indirizzoInputParam);
		return result;
	}

	public static Cliente createClienteFromParams(String nomeInputParam, String cognomeInputParam,
			String indirizzoInputParam, boolean attivoInputParam) {
		Cliente result = new Cliente(nomeInputParam, cognomeInputParam, indirizzoInputParam, attivoInputParam);
		return result;
	}

	public static boolean validateClienteBean(Cliente clienteToBeValidated) {
		if (StringUtils.isBlank(clienteToBeValidated.getNome())
				|| StringUtils.isBlank(clienteToBeValidated.getCognome())
				|| StringUtils.isBlank(clienteToBeValidated.getIndirizzo())) {
			return false;
		}
		return true;
	}

	public static Ordine createOrdineFromParams(String clienteInputParam, String[] pizzeInputParam,
			String dataInputParam, String codiceInputParam, String utenteInputParam)
			throws NumberFormatException, Exception {

		Ordine result = new Ordine(codiceInputParam);
		if (NumberUtils.isCreatable(clienteInputParam)) {
			Cliente cliente = new Cliente();
			cliente.setId(Long.parseLong(clienteInputParam));
			result.setCliente(cliente);
		}
		Set<Pizza> elencoPizzePerOrdine = new HashSet<Pizza>();
		if (pizzeInputParam == null || pizzeInputParam.length == 0) {
			result.setPizze(null);
		} else {
			for (String pizzaItem : pizzeInputParam) {
				if (NumberUtils.isCreatable(pizzaItem)) {
				elencoPizzePerOrdine.add(
						MyServiceFactory.getPizzaServiceInstance().caricaSingoloElemento(Long.parseLong(pizzaItem)));
			    }
		    }
		}
		result.setPizze(elencoPizzePerOrdine);
		result.setData(parseDateFromString(dataInputParam));
		if (NumberUtils.isCreatable(utenteInputParam)) {
			Utente utente = new Utente();
			utente.setId(Long.parseLong(utenteInputParam));
			result.setUtente(utente);
		}
		return result;
	}

	public static boolean validateOrdineBean(Ordine ordineToBeValidated) {
		if (ordineToBeValidated.getData() == null || StringUtils.isBlank(ordineToBeValidated.getCodice())
				|| ordineToBeValidated.getCliente() == null || ordineToBeValidated.getCliente().getId() == null
				|| ordineToBeValidated.getCliente().getId() < 1 || ordineToBeValidated.getUtente() == null
				|| ordineToBeValidated.getUtente().getId() < 1) {
			return false;
		}
		return true;
	}

	public static Date parseDateFromString(String dataStringParam) {
		if (StringUtils.isBlank(dataStringParam))
			return null;

		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(dataStringParam);
		} catch (ParseException e) {
			return null;
		}
	}

}
