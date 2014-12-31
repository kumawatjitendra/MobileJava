package Classes;
import java.sql.Date;
import java.sql.Time;


public class UserTravelInfo {
	String userName;
	String email;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	String mobile;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	String TravellerName;
	public String getTravellerName() {
		return TravellerName;
	}
	public void setTravellerName(String travellerName) {
		TravellerName = travellerName;
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
	long epoch ;
	public long getEpoch() {
		return epoch;
	}
	public void setEpoch(long epoch) {
		this.epoch = epoch;
	}
	Date travelDate;
	public Date getTravelDate() {
		return travelDate;
	}
	public void setTravelDate(Date travelDate) {
		this.travelDate = travelDate;
	}
	Time travelTime;
	public Time getTravelTime() {
		return travelTime;
	}
	public void setTravelTime(Time travelTime) {
		this.travelTime = travelTime;
	}
	String gender;
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

}
