package it.prova.pizzastore.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import it.prova.pizzastore.model.Cliente;
import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.model.Pizza;

public class UtilityForm {

	public static Pizza createPizzaFromParams(String descrizioneInputParam, String ingredientiInputParam,
			String prezzoBaseInputParam) {

		Pizza result = new Pizza(descrizioneInputParam, ingredientiInputParam);
		if (NumberUtils.isCreatable(prezzoBaseInputParam)) {
			result.setPrezzoBase(Integer.parseInt(prezzoBaseInputParam));
		}
		return result;
	}

	public static boolean validatePizzaBean(Pizza pizzaToBeValidated) {
		if (StringUtils.isBlank(pizzaToBeValidated.getDescrizione())
				|| StringUtils.isBlank(pizzaToBeValidated.getIngredienti())
				|| pizzaToBeValidated.getPrezzoBase() <= 0) {
			return false;
		}
		return true;
	}

	public static Cliente createClienteFromParams(String nomeInputParam, String cognomeInputParam,
			String indirizzoInputParam) {

		Cliente result = new Cliente(nomeInputParam, cognomeInputParam, indirizzoInputParam);
		return result;
	}

	public static Cliente createClienteFromParams(String nomeParam, String cognomeParam, String indirizzoParam,
			boolean attivoParam) {
		Cliente result = new Cliente(nomeParam, cognomeParam, indirizzoParam, attivoParam);
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

	public static Ordine createOrdineFromParams(String dataInputParam, String codiceInputParam,
			String costoTotaleOrdineInputParam) {

		Ordine result = new Ordine(codiceInputParam);
		result.setData(parseDateFromString(dataInputParam));
		if (NumberUtils.isCreatable(costoTotaleOrdineInputParam)) {
			result.setCostoTotaleOrdine(Integer.parseInt(costoTotaleOrdineInputParam));
		}
		return result;
	}

	public static boolean validateOrdineBean(Ordine ordineToBeValidated) {
		if (ordineToBeValidated.getData() == null || StringUtils.isBlank(ordineToBeValidated.getCodice())
				|| ordineToBeValidated.getCostoTotaleOrdine() <= 0) {
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
