package it.prova.pizzastore.web.servlet.ordine;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.pizzastore.model.Utente;
import it.prova.pizzastore.service.MyServiceFactory;

@WebServlet("/ExecuteDeleteOrdiniAttiviServlet")
public class ExecuteDeleteOrdiniAttiviServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idOrdineParam = request.getParameter("idOrdine");

		if (!NumberUtils.isCreatable(idOrdineParam)) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		Long idFattorino = ((Utente) request.getSession().getAttribute("userInfo")).getId();

		try {

			MyServiceFactory.getOrdineServiceInstance().rimuovi(Long.parseLong(idOrdineParam));
			request.setAttribute("ordine_list_attribute",
					MyServiceFactory.getOrdineServiceInstance().listAllOrdiniAperti(idFattorino));
			request.setAttribute("successMessage", "Operazione effettuata con successo");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		request.getRequestDispatcher("/ordine/listOrdiniAperti.jsp").forward(request, response);
	}
}
