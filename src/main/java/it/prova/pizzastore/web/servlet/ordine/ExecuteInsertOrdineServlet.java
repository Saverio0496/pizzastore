package it.prova.pizzastore.web.servlet.ordine;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.service.MyServiceFactory;
import it.prova.pizzastore.utility.UtilityForm;

@WebServlet("/ExecuteInsertOrdineServlet")
public class ExecuteInsertOrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String codiceInput = request.getParameter("codice");
		String dataInput = request.getParameter("data");
		String utenteInput = request.getParameter("utente.id");
		String clienteInput = request.getParameter("cliente.id");
		String[] pizzeInput = request.getParameterValues("pizze.id");

		Ordine ordineInstance = new Ordine();

		try {
			ordineInstance = UtilityForm.createOrdineFromParams(codiceInput, dataInput, clienteInput, utenteInput);

			if (!UtilityForm.validateOrdineBean(ordineInstance)) {

				request.setAttribute("ordine_insert_att", ordineInstance);
				request.setAttribute("utente_list_attribute",
						MyServiceFactory.getUtenteServiceInstance().listSoloFattorini());
				request.setAttribute("pizza_list_att", MyServiceFactory.getPizzaServiceInstance().listAllElements());
				request.setAttribute("cliente_list_att",
						MyServiceFactory.getClienteServiceInstance().listAllElements());
				request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
				
				request.getRequestDispatcher("home").forward(request, response);
				return;
			}

			MyServiceFactory.getOrdineServiceInstance().inserisciNuovo(ordineInstance);
			UtilityForm.setPizzeAdOrdine(ordineInstance, pizzeInput);
			MyServiceFactory.getOrdineServiceInstance().aggiorna(ordineInstance);
			Integer costoTotaleOrdine = MyServiceFactory.getOrdineServiceInstance().calcolaPrezzoOrdine(ordineInstance);
			ordineInstance.setCostoTotaleOrdine(Integer.parseInt(costoTotaleOrdine.toString()));

			MyServiceFactory.getOrdineServiceInstance().aggiorna(ordineInstance);

			request.setAttribute("sommaTotale", costoTotaleOrdine);

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}
		response.sendRedirect("ExecuteListOrdineServlet?operationResult=SUCCESS");

	}
}
