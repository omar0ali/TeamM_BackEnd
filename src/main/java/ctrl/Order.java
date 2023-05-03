package ctrl;

import java.io.IOException;
import java.io.Writer;
import java.sql.Date;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.CartBean;
import bean.UserBean;
import model.OrderedItemModel;

/**
 * Servlet implementation class Order
 */
@WebServlet("/orders")
public class Order extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String MODEL_NAME = "ordered_model";
	private final String LOGIN_SESSION = "login";

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		OrderedItemModel model = null;
		try {
			model = OrderedItemModel.getInstance();
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
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Writer out = response.getWriter();
		HttpSession session = request.getSession();
		OrderedItemModel model = (OrderedItemModel) this.getServletContext().getAttribute(MODEL_NAME);
		if (session.getAttribute(LOGIN_SESSION) != null) {
			UserBean user = (UserBean) session.getAttribute(LOGIN_SESSION);
			if (request.getParameter("purchase") != null) {
				String purchase = request.getParameter("purchase");
				if (purchase.equals("true")) {
					if (!user.isCartEmpty()) { // we should also check if payment methods and choose which payment to
												// purchase with, not in here tho
						List<CartBean> items = user.getCartItemsAsArray();
						try {
							out.write(model.makePurchase(items, user)); // will insert rows to order and ordered_item
							out.write("\nPurchase has been successful.");
							out.flush();
							// empty cart
							if (!user.isCartEmpty()) {
								user.emptyCart();
							}
						} catch (Exception e) {
							e.printStackTrace();
							out.write(e.getMessage());
							out.flush();
						}
						return;
					} else {
						out.write("There is no item in your cart.");
						out.flush();
						return;
					}
				}
			}

			if (request.getParameter("history") != null) {
				String purchase = request.getParameter("history");
				if (purchase.equals("true")) {
					out.write(model.getOrdersByUserId(user.getUser_id()));
					out.flush();
					return;
				}
			}

			if (!user.isAdmin()) {
				out.write("Only admin account can access items.");
				out.flush();
				return;
			}
			out.write(model.getOrderedItems());
			out.flush();
			return;
		} else {
			out.write("Please login first to see ordered items.");
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
