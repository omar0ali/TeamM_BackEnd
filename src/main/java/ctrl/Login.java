package ctrl;

import java.io.IOException;
import java.io.Writer;

import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.UserBean;
import model.LoginModel;
/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String MODEL_NAME = "login_model";
	private final String LOGIN_SESSION = "login";
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		LoginModel model = null;
		try {
			model = LoginModel.getInstance();
		} catch ( NamingException e) {
			e.printStackTrace();
		}
		if(model!=null) {
			this.getServletContext().setAttribute(MODEL_NAME, model);
		}
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * If there is no params provided, then need to dispatch the user to login page.
	 * params i.e {email=address&password=password123}
	 * content will be saved in the session to check if the user logged in, user shouldn't login every time.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Writer out = response.getWriter();
		HttpSession session = request.getSession();
		
		//save login user information in a session
		if(session.getAttribute(LOGIN_SESSION) != null) {
			UserBean user = (UserBean) session.getAttribute(LOGIN_SESSION);
			if(request.getParameter("cart")!=null) {
				String cart = request.getParameter("cart");
				if(cart.equals("true")) {
					out.write(user.getCart());
					out.flush();
					return;
				}
				return;
			}
			
			out.write("User logged in as: \n");
			out.write(user.getEmail());
			out.flush();
			
			return;
		}
		
		LoginModel model = (LoginModel) this.getServletContext().getAttribute(MODEL_NAME);
		//try login
		if(request.getParameter("email") != null && request.getParameter("password")!=null) {
			if(session.getAttribute("login") != null) {
				UserBean user = (UserBean) session.getAttribute("login");
				out.write(user.toString());
				out.flush();
				return;
			}
			String email = request.getParameter("email");
			String pass = request.getParameter("password");
			UserBean user = model.getUser(email, pass);
			if(user == null) {
				//log in failed.
				out.write("Login failed.");
				return;
			}
			session.setAttribute("login", user);
			//now we have the user data
			out.write(user.toString());
			out.flush();
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
