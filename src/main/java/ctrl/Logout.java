package ctrl;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.UserBean;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/logout")
public class Logout extends HttpServlet {
	private final String LOGIN_SESSION = "login";
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Logout() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Writer out = response.getWriter();
		HttpSession session = request.getSession();
		// save login user information in a session
		if (request.getParameter("email") != null) {
			String currentEmail = request.getParameter("email");
			if (session.getAttribute(LOGIN_SESSION) != null) {
				UserBean user = (UserBean) session.getAttribute(LOGIN_SESSION);
				if (currentEmail.equals(user.getEmail())) {
					session.setAttribute(LOGIN_SESSION, null);
					out.write(user.getEmail() + " Logged off successfully.");
					out.flush();
				} else {
					out.write(user.getEmail() + "- Inccorrect email. Please type the email again.");
					out.flush();
				}
				return;
			} else {
				out.write("No account is logged in.");
				out.flush();
				return;
			}
		} else {
			if (session.getAttribute(LOGIN_SESSION) != null) {
				UserBean user = (UserBean) session.getAttribute(LOGIN_SESSION);
				session.setAttribute(LOGIN_SESSION, null);
				out.write(user.getEmail() + " Logged off successfully.");
				out.flush();
				return;
			} else {
				out.write("No account is logged in.");
				out.flush();
				return;
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
