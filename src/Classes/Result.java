package Classes;

import java.io.Serializable;

public class Result implements Serializable{

	 Boolean isResultValid = false;
	 String message="success";
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Boolean getIsResultValid() {
		return isResultValid;
	}
	public void setIsResultValid(Boolean isResultValid) {
		this.isResultValid = isResultValid;
	}
	Object data = null;
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}

}
