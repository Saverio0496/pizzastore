package it.prova.pizzastore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.pizzastore.model.Utente;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Utente utenteInstance = (Utente) request.getSession().getAttribute("userInfo");

		if (utenteInstance.isAdmin()) {
			request.getRequestDispatcher("/utente/indexAdmin.jsp").forward(request, response);
		} else if (utenteInstance.isPizzaiolo()) {
			request.getRequestDispatcher("/utente/indexPizzaiolo.jsp").forward(request, response);
		} else if (utenteInstance.isFattorino()) {
			request.getRequestDispatcher("/utente/indexFattorino.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Utente utenteInstance = (Utente) request.getSession().getAttribute("userInfo");

		if (utenteInstance.isAdmin()) {
			request.getRequestDispatcher("/utente/indexAdmin.jsp").forward(request, response);
		} else if (utenteInstance.isPizzaiolo()) {
			request.getRequestDispatcher("/utente/indexPizzaiolo.jsp").forward(request, response);
		} else if (utenteInstance.isFattorino()) {
			request.getRequestDispatcher("/utente/indexFattorino.jsp").forward(request, response);
		}

	}
}
