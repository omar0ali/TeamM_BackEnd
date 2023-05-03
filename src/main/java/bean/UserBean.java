package bean;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class UserBean {
	// every user will have a cart
	List<CartBean> cart;
	private long user_id; // primary key
	private String first_name;
	private String last_name;
	private String street;
	private String city;
	private String zip;
	private String phone;
	private String email;
	private String password;// This should be hashed before saved in the database.
	private String user_type;
	private String token;
	private boolean admin = false;

	// creates a user bean
	public UserBean(long user_id, String first_name, String last_name, String street, String city, String zip,
			String phone, String email, String password, String user_type) {
		this.user_id = user_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.street = street;
		this.city = city;
		this.zip = zip;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.user_type = user_type;
		generateToken();
		cart = new ArrayList<CartBean>();
	}

	// getters and setters
	public long getUser_id() {
		return user_id;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getZip() {
		return this.zip;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	private void generateToken() {
		SecureRandom random = new SecureRandom();
		byte bytes[] = new byte[100];
		random.nextBytes(bytes);
		this.token = bytes.toString();
	}

	public String getToken() {
		if (this.token.isEmpty()) {
			generateToken();
		}
		return this.token;
	}

	// adds an item bean and its amount to card
	public boolean addToCart(ItemBean item, int quantity) {
		// this takes an item and we need to add the item into the cart
		if(item == null) {
			return false;
		}
		cart.add(new CartBean(item.getItem_id(), item.getItem_name(), quantity, item.getPrice()));
		return true;
	}

	// removes a specific item from the card
	public CartBean removeFromCart(int item_id) {
		CartBean old = null;
		for (CartBean cur : cart) {
			if (cur.getItem_id() == item_id) {
				old = cur;
				cart.remove(cur);
				return cur;
			}
		}
		return null;
	}
	
	public boolean isCartEmpty() {
		return cart.isEmpty();
	}
	
	public void emptyCart() {
		this.cart.clear();
	}

	public List<CartBean> getCartItemsAsArray(){
		return cart;
	}

	// returns the entire cart's contents
	public String getCart() {
		StringBuilder sb = new StringBuilder();
		try {
			sb.append("[");
			int length = cart.size();
			int counter = 0;
			for (CartBean item : cart) {
				sb.append("{");
				sb.append("\"item_id\":\"" + item.getItem_id() + "\",");
				sb.append("\"item_name\":\"" + item.getItem_name() + "\",");
				sb.append("\"quantity\":\"" + item.getQuantity() + "\",");
				sb.append("\"price\":\"" + item.getPrice() + "\"");
				if (counter == length - 1) {
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

	@Override
	public String toString() {
		Gson json = new Gson();
		return json.toJson(this, UserBean.class);
	}

}
