package model;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import bean.PaymentMethodBean;
import dao.PaymentMethodDAO;


public class PaymentMethodModel {
	private PaymentMethodDAO pay_dao = null;
	private static PaymentMethodModel pm_instance = null;

	// gets singleton instance
	public static PaymentMethodModel getInstance() throws NamingException {
		if (pm_instance == null) {
			pm_instance = new PaymentMethodModel();
			pm_instance.pay_dao = new PaymentMethodDAO();
		}
		return pm_instance;
	}
	
	// gets payment methods
	public String getPaymentMethods() {
		StringBuilder sb = new StringBuilder();
		try {
			List<PaymentMethodBean> result = pay_dao.getPayMethods();
			sb.append("[");
			int length = result.size();
			int counter = 0;
			for (PaymentMethodBean pm : result) {
				sb.append("{");
				sb.append("\"id\":\"" + pm.getP_method_id() + "\",");
				sb.append("\"user_id\":\"" + pm.getUser_id() + "\",");
				sb.append("\"card_number\":\"" + pm.getCard_number() + "\",");
				sb.append("\"cvv\":\"" + pm.getCVV() + "\",");
				sb.append("\"card_holder\":\"" + pm.getCard_holder() + "\",");
				sb.append("\"street\":\"" + pm.getStreet() + "\",");
				sb.append("\"city\":\"" + pm.getCity() + "\",");
				sb.append("\"zip\":\"" + pm.getZip() + "\",");
				sb.append("\"phone\":\"" + pm.getPhone() + "\",");
				sb.append("\"exp_date\":\"" + pm.getExpiration_date() + "\"");
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
	
	// gets payment methods by id
	public String getPaymentMethods(long id) {
		StringBuilder sb = new StringBuilder();
		try {
			List<PaymentMethodBean> result = pay_dao.getPayMethods();
			sb.append("[");
			int length = result.size();
			int counter = 0;
			for (PaymentMethodBean pm : result) {
				if(pm.getUser_id() != id) {
					continue;
				}
				sb.append("{");
				sb.append("\"id\":\"" + pm.getP_method_id() + "\",");
				sb.append("\"user_id\":\"" + pm.getUser_id() + "\",");
				sb.append("\"card_number\":\"" + pm.getCard_number() + "\",");
				sb.append("\"cvv\":\"" + pm.getCVV() + "\",");
				sb.append("\"card_holder\":\"" + pm.getCard_holder() + "\",");
				sb.append("\"street\":\"" + pm.getStreet() + "\",");
				sb.append("\"city\":\"" + pm.getCity() + "\",");
				sb.append("\"zip\":\"" + pm.getZip() + "\",");
				sb.append("\"phone\":\"" + pm.getPhone() + "\",");
				sb.append("\"exp_date\":\"" + pm.getExpiration_date() + "\"");
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
	
	// inserts a payment method
	public String insert(long user_id, String card_number, String cvv, String card_holder, String street, String city, String zip, String phone, Date date) {
		int code = -1;
		try {
			code = pay_dao.insert(user_id, card_number, cvv, card_holder, street, city, zip, phone, date);
			return code + " Payment method inserted sucessfully. \nCard Holder: " + card_holder;
		} catch (Exception e) {
			return code + " " + e.getMessage();
		}
	}
}
