package bean;

import java.sql.Blob;

import com.google.gson.Gson;

public class ItemBean {
	private long item_id;
	private String item_name;
	private int quantity;
	private String type;
	private String brand;
	private float price;
	private String description;
	private Blob image;

	// creates an item bean
	public ItemBean(long item_id, String item_name, int quantity, String type, String brand, float price,
			String description, Blob image) {
		super();
		this.item_id = item_id;
		this.item_name = item_name;
		this.quantity = quantity;
		this.type = type;
		this.brand = brand;
		this.price = price;
		this.description = description;
		this.image = image;
	}
	@Override
	public String toString() {
		Gson json = new Gson();
		return json.toJson(this, ItemBean.class);
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Blob getImage() {
		return image;
	}
	public void setImage(Blob image) {
		this.image = image;
	}
}
