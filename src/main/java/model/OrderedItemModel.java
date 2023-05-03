package model;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import org.apache.jasper.tagplugins.jstl.core.Out;

import bean.CartBean;
import bean.OrderBean;
import bean.OrderedItemBean;
import bean.UserBean;
import dao.ItemDAO;
import dao.OrderDAO;
import dao.OrderedItemDAO;

public class OrderedItemModel {
	private OrderedItemDAO oi_dao = null;
	private OrderDAO o_dao = null;
	private ItemDAO i_dao = null;
	private static OrderedItemModel model_instance;

	// gets singleton instance
	public static OrderedItemModel getInstance() throws NamingException {
		if (model_instance == null) {
			model_instance = new OrderedItemModel();
			model_instance.oi_dao = new OrderedItemDAO();
			model_instance.o_dao = new OrderDAO();
			model_instance.i_dao = new ItemDAO();
		}
		return model_instance;
	}

	// gets all ordered items
	public String getOrderedItems() {
		StringBuilder sb = new StringBuilder();
		try {
			List<OrderedItemBean> result = oi_dao.getOrderedItems();
			sb.append("[");
			int length = result.size();
			int counter = 0;
			for (OrderedItemBean orders : result) {
				sb.append("{");
				sb.append("\"item_id\":\"" + orders.getItem_id() + "\",");
				sb.append("\"order_id\":\"" + orders.getOrder_id() + "\",");
				sb.append("\"item_name\":\"" + orders.getItem_name() + "\",");
				sb.append("\"quantity\":\"" + orders.getQuantity() + "\",");
				sb.append("\"type\":\"" + orders.getType() + "\",");
				sb.append("\"brand\":\"" + orders.getBrand() + "\",");
				sb.append("\"price\":\"" + orders.getPrice() + "\"");
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

	// gets all orders
	public String getOrders() {
		StringBuilder sb = new StringBuilder();
		try {
			List<OrderBean> result = o_dao.getOrders();
			sb.append("[");
			int length = result.size();
			int counter = 0;
			for (OrderBean orders : result) {
				sb.append("{");
				sb.append("\"order_id\":\"" + orders.getOrder_id() + "\",");
				sb.append("\"user_id\":\"" + orders.getUser_id() + "\",");
				sb.append("\"user_name\":\"" + orders.getUser_name() + "\",");
				sb.append("\"to_street\":\"" + orders.getTo_street() + "\",");
				sb.append("\"to_city\":\"" + orders.getTo_city() + "\",");
				sb.append("\"to_zip\":\"" + orders.getTo_zip() + "\",");
				sb.append("\"order_date\":\"" + orders.getOrder_date() + "\",");
				sb.append("\"ship_date\":\"" + orders.getShip_date() + "\"");
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

	// purchases items in cart
	public String makePurchase(List<CartBean> items, UserBean user) throws SQLException {
		StringBuilder sb = new StringBuilder();
		for (CartBean item : items) {
			String i = insertOrder(user.getUser_id(), user.getLast_name(), user.getStreet(), user.getCity(),
					user.getZip(), new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis() + 2000));
			sb.append(i + "\n");
			OrderBean order = o_dao.getOrderByUserId(user.getUser_id());
			if (order != null) {
				i = insertOrderedItem(order.getOrder_id(), item.getItem_id(), item.getItem_name(), item.getQuantity(),
						"", "", item.getPrice());
			}
			itemQuantityPurchased(item.getItem_id(), item.getQuantity());
			sb.append(i);
		}
		return sb.toString();
	}

	// inserts ordered item
	public String insertOrderedItem(long item_id, long order_id, String item_name, int quantity, String type,
			String brand, float price) {
		int code = -1;
		try {
			code = oi_dao.insert(order_id, item_id, item_name, quantity, type, brand, price);
			return code + " Ordered item inserted sucessfully. \nItem: " + item_name;
		} catch (Exception e) {
			return code + " " + e.getMessage();
		}
	}

	// insert order
	public String insertOrder(long user_id, String user_last_name, String to_street, String to_city, String to_zip,
			Date order_date, Date ship_date) {
		int code = -1;
		try {
			code = o_dao.insert(user_id, user_last_name, to_street, to_city, to_zip, order_date, ship_date);
			System.out.println("ORDER CODE:"+code);
			return code + " Order item inserted sucessfully. \nShip date: " + ship_date+"\n";
		} catch (Exception e) {
			return code + " " + e.getMessage();
		}
	}

	// We need to update Item table (decrement quantity) once a purchase made.
	public void itemQuantityPurchased(long item_id, int quantity) {
		try {
			i_dao.purchaseCountOfItem(item_id, quantity);
		} catch (SQLException | NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// gets all users id
	public String getOrdersByUserId(long user_id) {
		StringBuilder sb = new StringBuilder();
		try {
			List<OrderBean> result = o_dao.getOrders();
			sb.append("[");
			int length = result.size();
			int counter = 0;
			for (OrderBean orders : result) {
				if (orders.getUser_id() != user_id) {
					continue;
				}
				sb.append("{");
				sb.append("\"order_id\":\"" + orders.getOrder_id() + "\",");
				sb.append("\"user_id\":\"" + orders.getUser_id() + "\",");
				sb.append("\"user_name\":\"" + orders.getUser_name() + "\",");
				sb.append("\"to_street\":\"" + orders.getTo_street() + "\",");
				sb.append("\"to_city\":\"" + orders.getTo_city() + "\",");
				sb.append("\"to_zip\":\"" + orders.getTo_zip() + "\",");
				sb.append("\"order_date\":\"" + orders.getOrder_date() + "\",");
				sb.append("\"ship_date\":\"" + orders.getShip_date() + "\"");
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
}
