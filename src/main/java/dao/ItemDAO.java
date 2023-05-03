package dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.ItemBean;

public class ItemDAO {

	private Connection con = null;
	
	public ItemDAO () throws NamingException {
		try {
			DatabaseConnection db = DatabaseConnection.getInstance();
			this.con = db.getConnection();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// returns a list of all items
	public List<ItemBean> getItems() throws SQLException{
		ArrayList<ItemBean> items = new ArrayList<ItemBean>();
		Connection con = this.con;
		PreparedStatement p = con.prepareStatement("select * from tbl_item");
		ResultSet rquery = p.executeQuery();
		while (rquery.next()) {
			long id = rquery.getInt("item_id");
			String item_name = rquery.getString("item_name");
			int quantity = rquery.getInt("quantity");
			String type = rquery.getString("item_type");
			String brand = rquery.getString("brand");
			float price = rquery.getFloat("price");
			String description = rquery.getString("item_description");
			Blob image = rquery.getBlob("item_image");
			ItemBean ub = new ItemBean(id, item_name, quantity, type, brand, price, description, image);
			items.add(ub);
		}
		rquery.close();
		p.close();
		return items;
	}
	
	// inserts an item with specified attributes
	public int insert(String item_name, int quantity, String item_type, String brand, float price, String description, Blob image)
			throws SQLException, NamingException {
	
		String preparedStatement = "insert into students values(?,?,?,?,?,?,?)";
		Connection con = this.con;
		PreparedStatement stmt = con.prepareStatement(preparedStatement);
		stmt.setString(1, item_name);
		stmt.setInt(2, quantity);
		stmt.setString(3, item_type);
		stmt.setString(4, brand);
		stmt.setFloat(5, price);
		stmt.setString(6, description);
		stmt.setBlob(7, image);
		return stmt.executeUpdate();
	}
	
	// amount of an item
	public int purchaseCountOfItem(long item_id, int quantity) throws SQLException, NamingException{
		String preparedStatement = "update tbl_item set quantity = quantity - ? where quantity > 0 and item_id = ?";
		Connection con = this.con;
		PreparedStatement stmt = con.prepareStatement(preparedStatement);
		stmt.setInt(1, quantity);
		stmt.setLong(2, item_id);
		return stmt.executeUpdate();
	}
	
	// deletes an item by name and id
	public int delete(int item_id, String item_name)
			throws SQLException, NamingException {
	
		String preparedStatement = "delete from tbl_item where item_id=? and item_name=?";
		Connection con = this.con;
		PreparedStatement stmt = con.prepareStatement(preparedStatement);
		stmt.setInt(1, item_id);
		stmt.setString(2, item_name);
		return stmt.executeUpdate();
	}
	
}
