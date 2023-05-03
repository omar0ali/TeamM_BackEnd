package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.ReviewItemBean;


public class ReviewItemDAO {
	private Connection con = null;
	public ReviewItemDAO() throws NamingException {
		//ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		try {
			DatabaseConnection db = DatabaseConnection.getInstance();
			con = db.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<ReviewItemBean> getReviews() throws SQLException{
		ArrayList<ReviewItemBean> reviews = new ArrayList<ReviewItemBean>();
		Connection con = this.con;
		PreparedStatement p = con.prepareStatement("select * from tbl_review_item");
		ResultSet rquery = p.executeQuery();
		while (rquery.next()) {
			long id = rquery.getLong("review_id");
			long order_id = rquery.getLong("item_id");
			long user_id = rquery.getLong("user_id");
			String user_name = rquery.getString("user_name");
			String comment = rquery.getString("comment");
			Date brand = rquery.getDate("review_date");
			Time time = rquery.getTime("review_time");
			int stars = rquery.getInt("review_stars");
			ReviewItemBean ub = new ReviewItemBean(id, order_id, user_id, user_name, comment, brand, time, stars);
			reviews.add(ub);
		}
		rquery.close();
		p.close();
		return reviews;
	}
	
	/*
	 * Adding a review to an item
	 */
	public int insert(long item_id, long user_id, String user_name, String comment, Date review_date, Time review_time, int review_stars)
			throws SQLException, NamingException {
		String preparedStatement = "insert into tbl_review_item values(default,?,?,?,?,?,?,?)";
		Connection con = this.con;
		PreparedStatement stmt = con.prepareStatement(preparedStatement);
		stmt.setLong(1, item_id);
		stmt.setLong(2, user_id);
		stmt.setString(3, user_name);
		stmt.setString(4, comment);
		stmt.setDate(5, review_date);
		stmt.setTime(6, review_time);
		stmt.setInt(7, review_stars);
		return stmt.executeUpdate();
	}
	
	
	
	/*
	 * Deleting a review
	 */
	public int delete(long review_id, long user_id)
			throws SQLException, NamingException {
		String preparedStatement = "delete from tbl_reivew_item where review_id=? and user_id";
		Connection con = this.con;
		PreparedStatement stmt = con.prepareStatement(preparedStatement);
		stmt.setLong(1, review_id);
		stmt.setLong(2, user_id);
		return stmt.executeUpdate();
	}
	
}
