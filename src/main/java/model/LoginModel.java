package model;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import com.google.common.hash.Hashing;

import bean.UserBean;
import dao.UserDAO;

public class LoginModel {
	private UserDAO user_dao = null;
	private static LoginModel login_instance = null;

	private LoginModel() {
	}

	// gets singleton instance
	public static LoginModel getInstance() throws NamingException {
		if (login_instance == null) {
			login_instance = new LoginModel();
			login_instance.user_dao = new UserDAO();
		}
		return login_instance;
	}

	/*
	 * Get users json code will be returned
	 * 
	 * Only admin user can access and see all users
	 */

	public String getUsers() {
		// only if admin was logged in can access all these data
		StringBuilder sb = new StringBuilder();
		try {
			List<UserBean> result = user_dao.getUsers();
			sb.append("[");
			int length = result.size();
			int counter = 0;
			for (UserBean user : result) {
				sb.append("{");
				sb.append("\"id\":\"" + user.getUser_id() + "\",");
				sb.append("\"first_name\":\"" + user.getFirst_name() + "\",");
				sb.append("\"last_name\":\"" + user.getLast_name() + "\",");
				sb.append("\"street\":\"" + user.getStreet() + "\",");
				sb.append("\"city\":\"" + user.getCity() + "\",");
				sb.append("\"zip\":\"" + user.getZip() + "\",");
				sb.append("\"phone\":\"" + user.getPhone() + "\",");
				sb.append("\"password\":\"" + user.getPassword() + "\",");
				sb.append("\"user_typ\":\"" + user.getUser_type() + "\",");
				sb.append("\"admin\":\"" + user.isAdmin() + "\"");
				if (counter == length - 1) {
					sb.append("}");
				} else {
					sb.append("},");
				}
				counter++;
			}
			sb.append("]");
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	/*
	 * User can get own information
	 */
	public UserBean getUser(String email, String password) {
		UserBean currentUser = null;
		try {
			password = Hashing.sha256()
					  .hashString(password, StandardCharsets.UTF_8)
					  .toString();
			List<UserBean> result = user_dao.getUsers();
			for (UserBean user : result) {
				if (user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password)) {
					currentUser = user;
					return currentUser;
				}
			}
		} catch (NumberFormatException | SQLException e) {
			e.printStackTrace();
		}
		return currentUser;
	}
	
	/*
	 * delete account
	 */

	public String delete(String email, String password, String last_name) {
		int code = -1;
		try {
			code = user_dao.delete(email, password, last_name);
		} catch (Exception e) {
			return code + " " +e.getMessage();
		}
		
		return code + " Account deleted successfully!";
	}
}
