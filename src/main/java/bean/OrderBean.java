package bean;

import java.sql.Date;

import com.google.gson.Gson;

public class OrderBean {
	private long order_id;
	private long user_id;
	private String user_name;
	private String to_street;
	private String to_city;
	private String to_zip;
	private Date order_date;
	private Date ship_date;

	// getters and setters
	public long getOrder_id() {
		return order_id;
	}
	public void setOrder_id(long order_id) {
		this.order_id = order_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public String getTo_street() {
		return to_street;
	}
	public void setTo_street(String to_street) {
		this.to_street = to_street;
	}
	public String getTo_city() {
		return to_city;
	}
	public void setTo_city(String to_city) {
		this.to_city = to_city;
	}
	public String getTo_zip() {
		return to_zip;
	}
	public void setTo_zip(String to_zip) {
		this.to_zip = to_zip;
	}
	public Date getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}
	public Date getShip_date() {
		return ship_date;
	}
	public void setShip_date(Date ship_date) {
		this.ship_date = ship_date;
	}

	// creates an order bean
	public OrderBean(long order_id, long user_id, String user_name, String to_street, String to_city, String to_zip,
			Date order_date, Date ship_date) {
		super();
		this.order_id = order_id;
		this.user_name = user_name;
		this.to_street = to_street;
		this.to_city = to_city;
		this.to_zip = to_zip;
		this.order_date = order_date;
		this.ship_date = ship_date;
		this.user_id = user_id;
	}
	@Override
	public String toString() {
		Gson json = new Gson();
		return json.toJson(this, OrderBean.class);
	}
	
}
