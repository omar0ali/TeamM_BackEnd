package model;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.naming.NamingException;

import com.google.common.hash.Hashing;

import bean.UserBean;
import dao.UserDAO;

public class RegisterModel {
	private UserDAO user_dao = null;
	private static RegisterModel register_instance = null;

	// gets singleton instance
	public static RegisterModel getInstance() throws NamingException {
		if (register_instance == null) {
			register_instance = new RegisterModel();
			register_instance.user_dao = new UserDAO();
		}
		return register_instance;
	}

	/*
	 * insert user
	 */

	public String insert(String first_name, String last_name, String street, String city, String zip, String phone,
			String email, String password, String user_type, boolean admin) {
		
		int code = -1;
		try {
			password = Hashing.sha256()
					  .hashString(password, StandardCharsets.UTF_8)
					  .toString();
			//String sha256hex = DigestUtils.sha256Hex(originalString);
			List<UserBean> users = this.user_dao.getUsers();
			for (UserBean user : users) {
				if (email.equals(user.getEmail())) {
					return code + " Email already registered.";
				}
			}
			code = user_dao.insert(first_name, last_name, street, city, zip, phone, email, password, user_type, admin);
		} catch (Exception e) {
			return code + " " + e.getMessage();
		}

		return code + " Registered successfully!";
	}

}
