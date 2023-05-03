package ctrl;

import java.io.IOException;
import java.io.Writer;
import java.sql.Date;
import java.sql.Time;

import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import bean.UserBean;
import model.ReviewModel;

/**
 * Servlet implementation class Review
 */
@WebServlet("/review")
public class Review extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String MODEL_NAME = "review_model";
	private final String LOGIN_SESSION = "login";

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ReviewModel model = null;
		try {
			model = ReviewModel.getInstance();
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
	public Review() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		ReviewModel rmodel = (ReviewModel) this.getServletContext().getAttribute(MODEL_NAME);
		UserBean user = null;
		Writer out = response.getWriter();
		if (session.getAttribute(LOGIN_SESSION) != null) {
			user = (UserBean) session.getAttribute(LOGIN_SESSION);
			if (request.getParameter("comment") != null && request.getParameter("item_id") != null
					&& request.getParameter("stars") != null) {
				String comment = request.getParameter("comment");
				int stars = Integer.valueOf(request.getParameter("stars"));
				long item_id = Long.valueOf(request.getParameter("item_id"));
				String result = rmodel.insert(item_id, user.getUser_id(),
						user.getLast_name() + ", " + user.getFirst_name(), comment,
						new Date(System.currentTimeMillis()), new Time(System.currentTimeMillis()), stars);
				out.write(result);
				return;
			}else {
				out.write("There is no item select to write a review for.");
				out.flush();
			}
		} else {
			out.write("Please you must be logged in to write a review.");
			out.flush();
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
