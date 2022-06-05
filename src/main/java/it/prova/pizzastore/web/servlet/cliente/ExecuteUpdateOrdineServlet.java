package it.prova.pizzastore.web.servlet.cliente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.service.MyServiceFactory;
import it.prova.pizzastore.utility.UtilityForm;

@WebServlet("/ExecuteUpdateOrdineServlet")
public class ExecuteUpdateOrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idOrdineParam = request.getParameter("idOrdine");

		if (!NumberUtils.isCreatable(idOrdineParam)) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		String codiceParam = request.getParameter("codice");
		String dataParam = request.getParameter("data");
		String clienteParam = request.getParameter("cliente.id");
		String utenteParam = request.getParameter("utente.id");
		String[] pizzeParam = request.getParameterValues("pizze.id");

		Ordine ordineInstance = new Ordine();
		try {
			ordineInstance = UtilityForm.createOrdineFromParams(clienteParam, pizzeParam, dataParam, codiceParam,
					utenteParam);
		} catch (Exception e1) {

			e1.printStackTrace();
		}

		ordineInstance.setId(Long.parseLong(idOrdineParam));

		if (!UtilityForm.validateOrdineBean(ordineInstance)) {
			request.setAttribute("update_ordine_attr", ordineInstance);
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		try {
			MyServiceFactory.getOrdineServiceInstance().aggiorna(ordineInstance);
			request.setAttribute("ordine_list_attribute",
					MyServiceFactory.getOrdineServiceInstance().listAllElements());
			request.setAttribute("successMessage", "Operazione effettuata con successo");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		request.getRequestDispatcher("/ordine/list.jsp").forward(request, response);
	}
}
