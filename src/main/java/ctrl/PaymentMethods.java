package ctrl;

import java.io.IOException;
import java.io.Writer;
import java.sql.Date;

import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.UserBean;
import model.PaymentMethodModel;

/**
 * Servlet implementation class PaymentMethods
 */
@WebServlet("/pay_methods")
public class PaymentMethods extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String MODEL_NAME = "pm_model";
	private final String LOGIN_SESSION = "login";

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		PaymentMethodModel model = null;
		try {
			model = PaymentMethodModel.getInstance();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		if (model != null) {
			this.getServletContext().setAttribute(MODEL_NAME, model);
		}
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PaymentMethods() {
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
		Writer out = response.getWriter();
		HttpSession session = request.getSession();
		PaymentMethodModel model = (PaymentMethodModel) this.getServletContext().getAttribute(MODEL_NAME);
		if (session.getAttribute(LOGIN_SESSION) != null) {
			UserBean user = (UserBean) session.getAttribute(LOGIN_SESSION);
			// if there are parameters were provided, we add a new pm.
			if (request.getParameter("card_number") != null && request.getParameter("cvv") != null
					&& request.getParameter("card_holder") != null && request.getParameter("street") != null
					&& request.getParameter("city") != null && request.getParameter("zip") != null
					&& request.getParameter("phone") != null && request.getParameter("exp_date")!=null) {
				String card_number = request.getParameter("card_number");
				String cvv = request.getParameter("cvv");
				String card_holder = request.getParameter("card_holder");
				String street = request.getParameter("street");
				String city = request.getParameter("city");
				String zip = request.getParameter("zip");
				String phone =request.getParameter("phone");
				Date exp_date = Date.valueOf(request.getParameter("exp_date"));
				String result = model.insert(user.getUser_id(), card_number, cvv, card_holder, street, city, zip, phone, exp_date);
				out.write(result+"\n");
			}
			out.write(model.getPaymentMethods(user.getUser_id()));
			out.flush();
			return;
		} else {
			out.write("Please login first to see all your payment methods.");
			out.flush();
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
