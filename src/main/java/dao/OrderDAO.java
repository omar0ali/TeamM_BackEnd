package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

import bean.OrderBean;

public class OrderDAO {
	private Connection con= null;
	public OrderDAO() throws NamingException {
		try {
			DatabaseConnection db = DatabaseConnection.getInstance();
			this.con = db.getConnection();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public List<OrderBean> getOrders() throws SQLException{
		ArrayList<OrderBean> orders = new ArrayList<OrderBean>();
		Connection con = this.con;
		PreparedStatement p = con.prepareStatement("select * from tbl_order");
		ResultSet rquery = p.executeQuery();
		while (rquery.next()) {
			long id = rquery.getLong("order_id");
			long user_id = rquery.getLong("user_id");
			String user_name = rquery.getString("user_last_name");
			String to_street = rquery.getString("to_street");
			String to_city = rquery.getString("to_city");
			String to_zip = rquery.getString("to_zip");
			Date order_date = rquery.getDate("order_date");
			Date ship_date = rquery.getDate("ship_date");
			OrderBean ub = new OrderBean(id, user_id, user_name, to_street, to_city, to_zip, order_date, ship_date);
			orders.add(ub);
		}
		rquery.close();
		p.close();
		return orders;
	}
	
	// gets user by provided id
	public OrderBean getOrderByUserId(long userId) throws SQLException{
		ArrayList<OrderBean> orders = (ArrayList<OrderBean>) getOrders();
		for(OrderBean order:orders) {
			System.out.println(order);
			if(order.getUser_id()==userId) {
				return order;
			}
		}
		return null;
	}
	
	// adds an order with specified attributes
	public int insert(long user_id, String user_last_name, String to_street, String to_city, String to_zip, Date order_date, Date ship_date)
			throws SQLException, NamingException {
	
		String preparedStatement = "insert into tbl_order values(default,?,?,?,?,?,?,?)";
		Connection con = this.con;
		PreparedStatement stmt = con.prepareStatement(preparedStatement, java.sql.Statement.RETURN_GENERATED_KEYS);
		stmt.setLong(1, user_id);
		stmt.setString(2, user_last_name);
		stmt.setString(3, to_street);
		stmt.setString(4, to_city);
		stmt.setString(5, to_zip);
		stmt.setDate(6, order_date);
		stmt.setDate(7, ship_date);
		ResultSet rs = stmt.getGeneratedKeys();
		if(rs.next()) {
			stmt.executeUpdate();
			return (int) rs.getLong(1);
		}
		return stmt.executeUpdate();
	}
	
	// removes an order 
	public int delete(long order_id)
			throws SQLException, NamingException {
	
		String preparedStatement = "delete from tbl_order where order_id=?";
		Connection con = this.con;
		PreparedStatement stmt = con.prepareStatement(preparedStatement);
		stmt.setLong(1, order_id);
		return stmt.executeUpdate();
	}
}
