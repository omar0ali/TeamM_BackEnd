package bean;

import java.sql.Date;

import com.google.gson.Gson;

public class PaymentMethodBean {
	private long method_id; //pk
	private long user_id; //fk
	private String card_number;
	private String CVV;
	private String card_holder;
	//billing address
	private String street;
	private String city;
	private String zip;
	private String phone;
	private Date expiration_date;
	
	// creates a payment method bean
	public PaymentMethodBean(long p_method_id, long user_id, String card_number, String cVV, String card_holder,
			String street, String city, String zip, String phone, Date expiration_date) {
		super();
		this.method_id = p_method_id;
		this.user_id = user_id;
		this.card_number = card_number;
		this.CVV = cVV;
		this.card_holder = card_holder;
		this.street = street;
		this.city = city;
		this.zip = zip;
		this.phone = phone;
		this.expiration_date = expiration_date;
	}
	
	// getters and setters
	public long getP_method_id() {
		return method_id;
	}
	public void setP_method_id(long p_method_id) {
		this.method_id = p_method_id;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public String getCard_number() {
		return card_number;
	}
	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}
	public String getCVV() {
		return CVV;
	}
	public void setCVV(String cVV) {
		CVV = cVV;
	}
	public String getCard_holder() {
		return card_holder;
	}
	public void setCard_holder(String card_holder) {
		this.card_holder = card_holder;
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
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getExpiration_date() {
		return expiration_date;
	}
	public void setExpiration_date(Date expiration_date) {
		this.expiration_date = expiration_date;
	}
	@Override
	public String toString() {
		Gson json = new Gson();
		return json.toJson(this, PaymentMethodBean.class);
	}
}
