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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		        // estraggo input
		        String clienteParam = request.getParameter("cliente_id");
				String [] pizzeParam = request.getParameterValues("pizza_id");
				String dataParam = request.getParameter("data");
				String codiceParam = request.getParameter("codice");
				String utenteParam = request.getParameter("utente_id");

				Ordine ordineInstance = new Ordine();

				try {
					
					 ordineInstance = UtilityForm.createOrdineFromParams(clienteParam, pizzeParam, dataParam, codiceParam, utenteParam);
					// se la validazione non risulta ok
					if (!UtilityForm.validateOrdineBean(ordineInstance)) {
						request.setAttribute("insert_ordine_attr", ordineInstance);
						request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
						request.getRequestDispatcher("home").forward(request, response);
						return;
					}
					
				    MyServiceFactory.getOrdineServiceInstance().calcolaPrezzoOrdine(ordineInstance);
					
					

					// se sono qui i valori sono ok quindi posso creare l'oggetto da inserire
					// occupiamoci delle operazioni di business
					MyServiceFactory.getOrdineServiceInstance().inserisciNuovo(ordineInstance);
				} catch (Exception e) {
					e.printStackTrace();
					request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
					request.getRequestDispatcher("home").forward(request, response);
					return;
				}

				// andiamo ai risultati
				// uso il sendRedirect con parametro per evitare il problema del double save on
				// refresh
				response.sendRedirect("ExecuteListOrdineServlet?operationResult=SUCCESS");

			}
}


