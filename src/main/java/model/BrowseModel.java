package model;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import bean.ItemBean;
import bean.ReviewItemBean;
import dao.ItemDAO;
import dao.ReviewItemDAO;

public class BrowseModel {
	private ItemDAO item_dao = null;
	private ReviewItemDAO review_dao = null;
	private static BrowseModel browse_instance = null;

	// get singleton instance
	public static BrowseModel getInstance() throws NamingException {
		if (browse_instance == null) {
			browse_instance = new BrowseModel();
			browse_instance.item_dao = new ItemDAO();
			browse_instance.review_dao = new ReviewItemDAO();
		}
		return browse_instance;
	}
	
	// gets all items
	public String getItems() {
		StringBuilder sb = new StringBuilder();
		try {
			List<ItemBean> result = item_dao.getItems();
			sb.append("[");
			int length = result.size();
			int counter = 0;
			for (ItemBean item : result) {
				sb.append("{");
				sb.append("\"id\":\"" + item.getItem_id() + "\",");
				sb.append("\"item_name\":\"" + item.getItem_name() + "\",");
				sb.append("\"quantity\":\"" + item.getQuantity() + "\",");
				sb.append("\"item_type\":\"" + item.getType() + "\",");
				sb.append("\"brand\":\"" + item.getBrand() + "\",");
				sb.append("\"price\":\"" + item.getPrice() + "\",");
				sb.append("\"description\":\"" + item.getDescription() + "\",");
				sb.append("\"image\":\"" + item.getImage() + "\"");
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

	// gets item by id and amount
	public ItemBean getItemById(long id, int quantity) throws SQLException {
		List<ItemBean> result = item_dao.getItems();
		for(ItemBean item : result) {
			if(item.getItem_id() == id) {
				if(item.getQuantity()<quantity) {
					return null;
				}
				return item;
			}
		}
		return null;
	}
	
	// gets item by id
	public ItemBean getItemById(long id) throws SQLException {
		List<ItemBean> result = item_dao.getItems();
		for(ItemBean item : result) {
			if(item.getItem_id() == id) {
				
				return item;
			}
		}
		return null;
	}
	
	// gets an items reviews by id
	public String getReviewsByItemId(long item_id) throws SQLException {
		List<ReviewItemBean> result = review_dao.getReviews();
		List<ReviewItemBean> newresult = new ArrayList<ReviewItemBean>();
		for(ReviewItemBean review : result) {
			if(review.getItem_id() == item_id) {
				newresult.add(review);
			}
		}
		StringBuilder sb = new StringBuilder();
		try {
			sb.append("[");
			int length = newresult.size();
			int counter = 0;
			for (ReviewItemBean item : newresult) {
				sb.append("{");
				sb.append("\"review_id\":\"" + item.getReview_id() + "\",");
				sb.append("\"item_id\":\"" + item.getItem_id() + "\",");
				sb.append("\"user_id\":\"" + item.getUser_id() + "\",");
				sb.append("\"user_name\":\"" + item.getUser_name() + "\",");
				sb.append("\"comment\":\"" + item.getComment() + "\",");
				sb.append("\"review_date\":\"" + item.getDate() + "\",");
				sb.append("\"review_time\":\"" + item.getTime() + "\",");
				sb.append("\"review_stars\":\"" + item.getStars() + "\"");
				if (counter == length-1) {
					sb.append("}");
				} else {
					sb.append("},");
				}
				counter++;
			}
			sb.append("]");
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	/*
	 * insert user
	 */

	/*
	 * private long item_id; private String item_name; private int quantity; private
	 * String type; private String brand; private float price; private String
	 * description; private Blob image;
	 */

	public String insert(String item_name, int quantity, String item_type, String brand, float price, String description,
			Blob image) throws SQLException, NamingException {
		int code = -1;
		try {
			List<ItemBean> items = this.item_dao.getItems();
			for (ItemBean item : items) {
				if (item_name.equals(item.getItem_name()) && brand.equals(item.getBrand())) {
					return code + " Item already registered.";
				}
			}
			code = item_dao.insert(item_name, quantity, item_type, brand, price, description, image);
			return code + " Item inserted sucessfully. \nItem_name: " + item_name;
		} catch (Exception e) {
			return code + " " + e.getMessage();
		}

	}
}
