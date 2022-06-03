package it.prova.pizzastore.web.servlet.pizza;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.pizzastore.model.Pizza;
import it.prova.pizzastore.service.MyServiceFactory;
import it.prova.pizzastore.utility.UtilityForm;

@WebServlet("/ExecuteInsertPizzaServlet")
public class ExecuteInsertPizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// estraggo input
		String descrizioneParam = request.getParameter("descrizione");
		String ingredientiParam = request.getParameter("ingredienti");
		String prezzoBaseParam = request.getParameter("prezzobase");

		// preparo un bean (che mi serve sia per tornare in pagina
		// che per inserire) e faccio il binding dei parametri
		Pizza pizzaInstance = UtilityForm.createPizzaFromParams(descrizioneParam, ingredientiParam, prezzoBaseParam);
		pizzaInstance.setAttivo(true);

		try {
			// se la validazione non risulta ok
			if (!UtilityForm.validatePizzaBean(pizzaInstance)) {
				request.setAttribute("insert_pizza_attr", pizzaInstance);
				request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
				request.getRequestDispatcher("home").forward(request, response);
				return;
			}

			// se sono qui i valori sono ok quindi posso creare l'oggetto da inserire
			// occupiamoci delle operazioni di business
			MyServiceFactory.getPizzaServiceInstance().inserisciNuovo(pizzaInstance);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		// andiamo ai risultati
		// uso il sendRedirect con parametro per evitare il problema del double save on
		// refresh
		response.sendRedirect("ExecuteListPizzaServlet?operationResult=SUCCESS");

	}

}
