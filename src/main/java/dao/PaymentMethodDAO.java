package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;


import bean.PaymentMethodBean;

public class PaymentMethodDAO {
	private Connection con= null;
	public PaymentMethodDAO() throws NamingException {
		try {
			DatabaseConnection db = DatabaseConnection.getInstance();
			con = db.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<PaymentMethodBean> getPayMethods() throws SQLException{
		ArrayList<PaymentMethodBean> paymentMethods = new ArrayList<PaymentMethodBean>();
		Connection con = this.con;
		PreparedStatement p = con.prepareStatement("select * from tbl_payment_method");
		ResultSet rquery = p.executeQuery();
		while (rquery.next()) {
			long id = rquery.getLong("method_id");
			long user_id = rquery.getLong("user_id");
			String card_number = rquery.getString("card_number");
			String cvv = rquery.getString("cvv");
			String card_holder = rquery.getString("card_holder");
			String street = rquery.getString("street");
			String city = rquery.getString("city");
			String zip = rquery.getString("zip");
			String phone = rquery.getString("phone");
			Date exp_date = rquery.getDate("exp_date");
			PaymentMethodBean ub = new PaymentMethodBean(id, user_id, card_number, cvv, card_holder, street, city, zip, phone, exp_date);
			paymentMethods.add(ub);
		}
		rquery.close();
		p.close();
		return paymentMethods;
	}
	
	/*
	 * Adding a new payment method for a user
	 */
	public int insert(long user_id, String card_number, String cvv, String card_holder, String street, String city, String zip, String phone, Date exp_date)
			throws SQLException, NamingException {
		String preparedStatement = "insert into tbl_payment_method values(default,?,?,?,?,?,?,?,?,?)";
		Connection con = this.con;
		PreparedStatement stmt = con.prepareStatement(preparedStatement);
		stmt.setLong(1, user_id);
		stmt.setString(2, card_number);
		stmt.setString(3, cvv);
		stmt.setString(4, card_holder);
		stmt.setString(5, street);
		stmt.setString(6, city);
		stmt.setString(7, zip);
		stmt.setString(8, phone);
		stmt.setDate(9, exp_date);
		return stmt.executeUpdate();
	}
	
	
	
	/*
	 * Deleting a payment method for a user
	 */
	public int delete(long method_id)
			throws SQLException, NamingException {
		String preparedStatement = "delete from tbl_payment_method where method_id=?";
		Connection con = this.con;
		PreparedStatement stmt = con.prepareStatement(preparedStatement);
		stmt.setLong(1, method_id);
		return stmt.executeUpdate();
	}
}
