package bean;

import java.sql.Date;
import java.sql.Time;

import com.google.gson.Gson;

public class ReviewItemBean {
	private long review_id;
	private long item_id;
	private long user_id;
	private String user_name;
	private String comment;
	private Date date;
	private Time time;
	private int stars;

	// creates a reviewitem bean
	public ReviewItemBean(long review_id, long item_id, long user_id, String user_name, String comment, Date date,
			Time time, int stars) {
		super();
		this.review_id = review_id;
		this.item_id = item_id;
		this.user_id = user_id;
		this.user_name = user_name;
		this.comment = comment;
		this.date = date;
		this.time = time;
		this.stars = stars;
	}

	// getters and setters
	public long getReview_id() {
		return review_id;
	}
	public void setReview_id(long review_id) {
		this.review_id = review_id;
	}
	public long getItem_id() {
		return item_id;
	}
	public void setItem_id(long item_id) {
		this.item_id = item_id;
	}
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	public int getStars() {
		return stars;
	}
	public void setStars(int stars) {
		this.stars = stars;
	}
	@Override
	public String toString() {
		Gson json = new Gson();
		return json.toJson(this, ReviewItemBean.class);
	}
	
}
