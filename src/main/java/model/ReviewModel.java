package model;

import java.sql.Date;
import java.sql.Time;

import javax.naming.NamingException;
import dao.ReviewItemDAO;

public class ReviewModel {
	private ReviewItemDAO review_dao = null;
	private static ReviewModel review_instance = null;
	
	// gets singleton instance
	public static ReviewModel getInstance() throws NamingException {
		if (review_instance == null) {
			review_instance = new ReviewModel();
			review_instance.review_dao = new ReviewItemDAO();
		}
		return review_instance;
	}
	
	
	/*
	 * We need to insert a review to item_id
	 */
	
	public String insert(long item_id, long user_id, String user_name, String comment, Date review_date, Time review_time, int review_stars) {
		int code = -1;
		try {
			code = review_dao.insert(item_id, user_id, user_name, comment, review_date, review_time, review_stars);
			return code + " Review inserted sucessfully. \nComment: " + comment;
		} catch (Exception e) {
			return code + " " + e.getMessage();
		}
	}
	
}
