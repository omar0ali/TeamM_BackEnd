package bean;

import com.google.gson.Gson;

public class OrderedItemBean {
	private long item_id;
	private long order_id;
	private String item_name;
	private int quantity;
	private String type;
	private String brand;
	private float price;

	// creates an ordered item bean
	public OrderedItemBean(long item_id, long order_id, String item_name, int quantity, String type, String brand,
			float price) {
		super();
		this.item_id = item_id;
		this.order_id = order_id;
		this.item_name = item_name;
		this.quantity = quantity;
		this.type = type;
		this.brand = brand;
		this.price = price;
	}

	// getters and setters
	public long getItem_id() {
		return item_id;
	}
	public void setItem_id(long item_id) {
		this.item_id = item_id;
	}
	public long getOrder_id() {
		return order_id;
	}
	public void setOrder_id(long order_id) {
		this.order_id = order_id;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
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
		return json.toJson(this, OrderedItemBean.class);
	}
	
}
