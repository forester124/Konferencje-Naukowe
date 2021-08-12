package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LoginDao;
import model.Uzytkownik;
import model.Organizator;
import model.Recenzent;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginDao loginDao;

	public void init() {
		loginDao = new LoginDao();
	}

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (username.charAt(0) == '#') {
			Organizator organizer = new Organizator();
			organizer.setUsername(username);
			organizer.setPassword(password);
			try {
				if (loginDao.validate(organizer)) {

					response.sendRedirect("listKonfOrg");
				} else {
					response.sendRedirect("login.jsp");
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else if (username.charAt(0) == '^') {
			Recenzent recenzent = new Recenzent();
			recenzent.setUsername(username);
			recenzent.setPassword(password);
			try {
				if (loginDao.validate(recenzent)) {
					response.sendRedirect("listKonfRec");
				} else {
					response.sendRedirect("login.jsp");
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			Uzytkownik user = new Uzytkownik();
			user.setUsername(username);
			user.setPassword(password);
			try {
				if (loginDao.validate(user)) {

					response.sendRedirect("listKonfUser");
				} else {

					response.sendRedirect("login.jsp");
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

}
