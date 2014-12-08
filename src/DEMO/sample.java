package DEMO;

import Classes.Result;
import Classes.User;
import Login.LoginService;

public class sample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		User u = new User();
		u.setUserId("check11");
		u.setName("name1");
		u.setPassword("passwd");
		u.setMobile("8860572779");
		u.setCity("muz");
		// TODO Auto-generated method stub
		LoginService login = new LoginService();
//		login.createNewUser();
		Result rs = login.isUserValid(u.getUserId(), "ASS");
		
		Boolean b =login.isUserAdmin("001");
//		login.checkIfUserIdUnique("DASS");
	}

}
