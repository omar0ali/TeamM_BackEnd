package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.OrderedItemBean;

public class OrderedItemDAO {
	private Connection con = null;
	public OrderedItemDAO() throws NamingException {
		try {
			DatabaseConnection db = DatabaseConnection.getInstance();
			con = db.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// returns a list of all ordered items
	public List<OrderedItemBean> getOrderedItems() throws SQLException{
		ArrayList<OrderedItemBean> orders = new ArrayList<OrderedItemBean>();
		Connection con = this.con;
		PreparedStatement p = con.prepareStatement("select * from tbl_ordered_item");
		ResultSet rquery = p.executeQuery();
		while (rquery.next()) {
			long id = rquery.getLong("item_id");
			long order_id = rquery.getLong("order_id");
			String item_name = rquery.getString("item_name");
			int quantity = rquery.getInt("quantity");
			String type = rquery.getString("item_type");
			String brand = rquery.getString("brand");
			float price = rquery.getFloat("price");
			OrderedItemBean ub = new OrderedItemBean(id, order_id, item_name, quantity, type, brand, price);
			orders.add(ub);
		}
		rquery.close();
		p.close();
		return orders;
	}
	
	// inserts an item with specified attributes 
	public int insert(long order_id, long item_id, String item_name, int quantity, String type, String brand, float price)
			throws SQLException, NamingException {
	
		String preparedStatement = "insert into tbl_ordered_item values(default,?,?,?,?,?,?,?)";
		Connection con = this.con;
		PreparedStatement stmt = con.prepareStatement(preparedStatement);
		stmt.setLong(1, order_id);
		stmt.setLong(2, item_id);
		stmt.setString(3, item_name);
		stmt.setInt(4, quantity);
		stmt.setString(5, type);
		stmt.setString(6, brand);
		stmt.setFloat(7, price);
		return stmt.executeUpdate();
	}
	
	// deletes an item from the order
	public int delete(long order_id, long item_id)
			throws SQLException, NamingException {
	
		String preparedStatement = "delete from tbl_ordered_item where order_id=? and item_id=?";
		Connection con = this.con;
		PreparedStatement stmt = con.prepareStatement(preparedStatement);
		stmt.setLong(1, order_id);
		stmt.setLong(2, item_id);
		return stmt.executeUpdate();
	}
}
