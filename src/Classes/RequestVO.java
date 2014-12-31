package Classes;

import java.io.Serializable;
import java.sql.Date;

public class RequestVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String requestUser;
	 public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getEpoch() {
		return epoch;
	}
	public void setEpoch(long epoch) {
		this.epoch = epoch;
	}
	String email;
	 long epoch;
	public String getRequestUser() {
		return requestUser;
	}
	public void setRequestUser(String requestUser) {
		this.requestUser = requestUser;
	}
	String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	String mobile;
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	String gender;
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	String source;
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	String destination;
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	Date date;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	int time;
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}

}
