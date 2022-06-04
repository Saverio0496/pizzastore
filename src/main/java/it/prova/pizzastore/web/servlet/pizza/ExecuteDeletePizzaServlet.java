package it.prova.pizzastore.web.servlet.pizza;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.pizzastore.exceptions.ElementNotFoundException;
import it.prova.pizzastore.service.MyServiceFactory;

@WebServlet("/ExecuteDeletePizzaServlet")
public class ExecuteDeletePizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idPizzaParam = request.getParameter("idPizza");

		if (!NumberUtils.isCreatable(idPizzaParam)) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		try {
			MyServiceFactory.getPizzaServiceInstance().rimuovi(Long.parseLong(idPizzaParam));
		} catch (ElementNotFoundException e) {
			request.getRequestDispatcher("ExecuteListPizzaServlet?operationResult=NOT_FOUND").forward(request,
					response);
			return;
		} catch (Exception e) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		response.sendRedirect("ExecuteListPizzaServlet?operationResult=SUCCESS");
	}

}
