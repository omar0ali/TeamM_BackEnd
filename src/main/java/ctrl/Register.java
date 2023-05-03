package ctrl;

import java.io.IOException;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.RegisterModel;

/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String MODEL_NAME = "register_model";
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		RegisterModel model = null;
		try {
			model = RegisterModel.getInstance();
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
	public Register() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		// all the information
		/*
		 * firstname, lastname, List<CartBean> cart; private long user_id; //primary key
		 * private String first_name; private String last_name; private String street;
		 * private String city; private String zip; private String phone; private String
		 * email; private String password;// This should be hashed before saved in the
		 * database. private String user_type; private String token; private boolean
		 * admin = false;
		 */

		
		if (request.getParameter("fn") != null && request.getParameter("ln") != null
				&& request.getParameter("email") != null && request.getParameter("password") != null) {
			String first_name = request.getParameter("fn");
			String last_name = request.getParameter("ln");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			//checking password, must be larger than 8 Chars
			if(password.length()<8) {
				response.getWriter().write("Password entered must be 8 or greater than 8 characters.");
				return;
			}
			RegisterModel rmodel = (RegisterModel) this.getServletContext().getAttribute(MODEL_NAME);
			String result = rmodel.insert(first_name, last_name, "", "", "", "", email, password, "", false);
			response.getWriter().write("Result: " + result);
			return;
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
