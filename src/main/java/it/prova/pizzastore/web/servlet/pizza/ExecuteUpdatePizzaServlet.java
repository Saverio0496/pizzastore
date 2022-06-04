package it.prova.pizzastore.web.servlet.pizza;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.pizzastore.model.Pizza;
import it.prova.pizzastore.service.MyServiceFactory;
import it.prova.pizzastore.utility.UtilityForm;

@WebServlet("/ExecuteUpdatePizzaServlet")
public class ExecuteUpdatePizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idPizzaParam = request.getParameter("idPizza");

		if (!NumberUtils.isCreatable(idPizzaParam)) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		String descrizioneParam = request.getParameter("descrizione");
		String ingredientiParam = request.getParameter("ingredienti");
		String prezzoBaseParam = request.getParameter("prezzobase");
		String attivoParam = request.getParameter("attivo");

		Pizza pizzaInstance = UtilityForm.createPizzaFromParams(descrizioneParam, ingredientiParam, prezzoBaseParam,
				Boolean.parseBoolean(attivoParam));

		pizzaInstance.setId(Long.parseLong(idPizzaParam));

		if (!UtilityForm.validatePizzaBean(pizzaInstance)) {
			request.setAttribute("update_pizza_attr", pizzaInstance);
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		try {
			MyServiceFactory.getPizzaServiceInstance().aggiorna(pizzaInstance);
			request.setAttribute("pizza_list_attribute",
					MyServiceFactory.getPizzaServiceInstance().listAllElements());
			request.setAttribute("successMessage", "Operazione effettuata con successo");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		request.getRequestDispatcher("/pizza/list.jsp").forward(request, response);
	}

}
