package ctrl;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.CartBean;
import bean.ItemBean;
import bean.UserBean;
import model.BrowseModel;

/**
 * Servlet implementation class Browse
 */
@WebServlet("/browse")
public class Browse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final String MODEL_NAME = "browse_model";
	// Now we can use if the user logged in they can add items to the cart.
	// otherwise they can't add item.
	private final String LOGIN_SESSION = "login";

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		BrowseModel model = null;
		try {
			model = BrowseModel.getInstance();
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
	public Browse() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// if id was provided example /browse?id=4 then we only return the item of that
		// specific id, otherwise we return everything
		BrowseModel rmodel = (BrowseModel) this.getServletContext().getAttribute(MODEL_NAME);
		HttpSession session = request.getSession();
		if (request.getParameter("id") != null && request.getParameter("review") != null) {
			int id = Integer.valueOf(request.getParameter("id"));
			String review = request.getParameter("review");
			if (review.equals("true")) {
				try {
					String result = rmodel.getReviewsByItemId(id);
					response.getWriter().write(result);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;
			}
		}
		if (request.getParameter("id") != null && request.getParameter("add") != null) {
			if(request.getParameter("quantity") == null && request.getParameter("add").equals("true")) {
				response.getWriter().write("Please add &quantity=#.");
				response.getWriter().flush();
				return;
			}
			UserBean user = (UserBean) session.getAttribute(LOGIN_SESSION);
			if (user == null) {
				response.getWriter().write("Please you must be logged in to add an item to cart.");
				response.getWriter().flush();
				return;
			}
			int id = Integer.valueOf(request.getParameter("id"));
			String add = request.getParameter("add");
			if (add.equals("true")) {
				int quantity = Integer.valueOf(request.getParameter("quantity"));
				// add this item
				try {
					//we need to check if there is enough items.
					if(quantity>10) {
						response.getWriter().write("Can't have more 10 of the same product.");
						return;
					}
					boolean pass = user.addToCart(rmodel.getItemById(id, quantity), quantity);
					if(!pass) {
						response.getWriter().write("Item couldn't be added due lack of resources.");
						return;
					}
					response.getWriter()
							.write("Item has been added successfully. " + rmodel.getItemById(id).getItem_name());
					response.getWriter().flush();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else if (add.equals("false")) {
				// remove this item
				CartBean item = user.removeFromCart(id);
				if (item == null) {
					response.getWriter().write("Item selected does not exist in the cart.");
					response.getWriter().flush();
				} else {
					response.getWriter().write("Item has been removed " + item.getItem_name());
					response.getWriter().flush();
				}
			}
			return;
		}
		if (request.getParameter("id") != null) {
			int id = Integer.valueOf(request.getParameter("id"));
			try {
				ItemBean item = rmodel.getItemById(id);
				response.getWriter().write(item.toString()); // json code
			} catch (SQLException e) {
				response.getWriter().write("Error: " + e.getMessage());
				e.printStackTrace();
			}
			return;
		}
		String result = rmodel.getItems();
		response.getWriter().write(result);// json code array
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
