package DEMO;

import Classes.User;
import Login.LoginService;

public class sample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		User u = new User();
		u.setUserId("check1");
		u.setName("name1");
		u.setPassword("passwd");
		u.setMobile("8860572779");
		u.setCity("muz");
		// TODO Auto-generated method stub
		LoginService login = new LoginService();
//		login.createNewUser(u);
		login.isUserValid(u.getUserId(), "ASS");
	}

}
