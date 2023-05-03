package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.UserBean;

public class UserDAO {

	//private DataSource ds= null;
	Connection con = null;
	public UserDAO() throws NamingException {
		//ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/db_teammdb");
		try {
			DatabaseConnection db = DatabaseConnection.getInstance();
			con = db.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public List<UserBean> getUsers() throws SQLException {
		ArrayList<UserBean> users = new ArrayList<UserBean>();
		Connection con = this.con;
		PreparedStatement p = con.prepareStatement("select * from tbl_user");
		ResultSet rquery = p.executeQuery();
		while (rquery.next()) {
			long id = rquery.getInt("user_id");
			String first_name = rquery.getString("first_name");
			String last_name = rquery.getString("last_name");
			String street = rquery.getString("street");
			String city = rquery.getString("city");
			String zip = rquery.getString("zip");
			String phone = rquery.getString("phone");
			String email = rquery.getString("email");
			String password = rquery.getString("password");
			String user_type = rquery.getString("user_type");
			boolean admin = rquery.getBoolean("admin");
			UserBean ub = new UserBean(id, first_name, last_name, street, city, zip, phone, email, password, user_type);
			ub.setAdmin(admin);
			users.add(ub);
		}
		rquery.close();
		p.close();
		return users;
	}

	public List<UserBean> customSQL(String statement) throws SQLException{
		ArrayList<UserBean> users = new ArrayList<UserBean>();
		Connection con = this.con;
		PreparedStatement p = con.prepareStatement(statement);
		ResultSet rquery = p.executeQuery();
		while (rquery.next()) {
			long id = rquery.getInt("user_id");
			String first_name = rquery.getString("first_name");
			String last_name = rquery.getString("last_name");
			String street = rquery.getString("street");
			String city = rquery.getString("city");
			String zip = rquery.getString("zip");
			String phone = rquery.getString("phone");
			String email = rquery.getString("email");
			String password = rquery.getString("password");
			String user_type = rquery.getString("user_type");
			boolean admin = rquery.getBoolean("admin");
			UserBean ub = new UserBean(id, first_name, last_name, street, city, zip, phone, email, password, user_type);
			ub.setAdmin(admin);
			users.add(ub);
		}
		rquery.close();
		p.close();
		return users;
	}

	
	/*
	 * registering a new user account
	 */
	public int insert(String first_name, String last_name, String street, String city, String zip, String phone, String email, String password,
			String user_type, boolean admin)
			throws SQLException, NamingException {
	
		String preparedStatement = "insert into tbl_user values(default,?,?,?,?,?,?,?,?,?,?)";
		Connection con = this.con;
		PreparedStatement stmt = con.prepareStatement(preparedStatement);
		stmt.setString(1, first_name);
		stmt.setString(2, last_name);
		stmt.setString(3, street);
		stmt.setString(4, city);
		stmt.setString(5, zip);
		stmt.setString(6, phone);
		stmt.setString(7, email);
		stmt.setString(8, password);
		stmt.setString(9, user_type);
		stmt.setBoolean(10, admin);
		return stmt.executeUpdate();
	}
	
	
	public int delete(String email, String password, String last_name)
			throws SQLException, NamingException {
	
		String preparedStatement = "delete from tbl_user where email=? and password=? and last_name=?";
		Connection con = this.con;
		PreparedStatement stmt = con.prepareStatement(preparedStatement);
		stmt.setString(1, email);
		stmt.setString(2, password);
		stmt.setString(3, last_name);
		return stmt.executeUpdate();
	}
	

}
