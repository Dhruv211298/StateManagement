package in.ac.adit.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String username = null;
	private String password = null;
	private String newPassword = null;
	private RequestDispatcher requestDispatcher = null;

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		username = request.getParameter("username");
		password = request.getParameter("password");

		StringBuffer stringBuffer = new StringBuffer(password);
		newPassword = stringBuffer.reverse().toString();
		
		if (username != null && password != null) {
			if (username.equals(newPassword)) {
				Cookie cookie = new Cookie("AUTHENTICATE", username);
				response.addCookie(cookie);
				requestDispatcher = request.getRequestDispatcher("home.jsp");
			} else {
				request.setAttribute("MESSAGE", "Invalid Username / Password");
				requestDispatcher = request.getRequestDispatcher("login.jsp");
			}
		} else {
			request.setAttribute("MESSAGE", "PLEASE ENTER USERNAME / PASSWORD");
			requestDispatcher = request.getRequestDispatcher("login.jsp");
		}

		requestDispatcher.forward(request, response);
	}
}