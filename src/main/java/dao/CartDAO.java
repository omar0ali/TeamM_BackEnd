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
import bean.CartBean;

// unused
public class CartDAO {
//	private DataSource ds = null;
//	
//	
//	public CartDAO() throws NamingException {
//		ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
//	}
//	
//	public List<CartBean> getCartItems() throws SQLException{
//		ArrayList<CartBean> cartItems = new ArrayList<CartBean>();
//		Connection con = this.ds.getConnection();
//		PreparedStatement p = con.prepareStatement("select * from tbl_order");
//		ResultSet rquery = p.executeQuery();
//		while (rquery.next()) {
//			long id = rquery.getLong("cart_id");
//			long item_id = rquery.getLong("item_id");
//			String item_name = rquery.getString("item_name");
//			int quantity = rquery.getInt("quantity");
//			float price = rquery.getFloat("price");
//			CartBean ub = new CartBean(id, item_id, item_name, quantity, price);
//			cartItems.add(ub);
//		}
//		rquery.close();
//		p.close();
//		con.close();
//		return cartItems;
//	}
//	
//	public int insert(long item_id, String item_name, int quantity, float price)
//			throws SQLException, NamingException {
//	
//		String preparedStatement = "insert into tbl_cart values(?,?,?,?)";
//		Connection con = this.ds.getConnection();
//		PreparedStatement stmt = con.prepareStatement(preparedStatement);
//		stmt.setLong(1, item_id);
//		stmt.setString(2, item_name);
//		stmt.setInt(3, quantity);
//		stmt.setFloat(4, price);
//		return stmt.executeUpdate();
//	}
//	
//	public int delete(long cart_id)
//			throws SQLException, NamingException {
//	
//		String preparedStatement = "delete from tbl_cart where cart_id=?";
//		Connection con = this.ds.getConnection();
//		PreparedStatement stmt = con.prepareStatement(preparedStatement);
//		stmt.setLong(1, cart_id);
//		return stmt.executeUpdate();
//	}
}
