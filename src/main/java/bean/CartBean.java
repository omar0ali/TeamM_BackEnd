package bean;

import com.google.gson.Gson;

public class CartBean {
	private long item_id;
	private String item_name;
	private int quantity;
	private float price;

	// creates a cart bean
	public CartBean(long item_id, String item_name, int quantity, float price) {
		super();
		this.item_id = item_id;
		this.item_name = item_name;
		this.quantity = quantity;
		this.price = price;
	}

	// getters and setters
	public long getItem_id() {
		return item_id;
	}
	public void setItem_id(long item_id) {
		this.item_id = item_id;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	@Override
	public String toString() {
		Gson json = new Gson();
		return json.toJson(this, CartBean.class);
	}
	
}
