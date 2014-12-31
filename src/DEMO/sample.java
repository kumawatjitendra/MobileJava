package DEMO;

import java.sql.SQLException;

import BuisenessLogic.BuisinessLogic;
import BuisenessLogic.Process;
import Classes.RequestVO;
import Classes.Result;
import Classes.User;
import Login.LoginService;

public class sample {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
//		User u = new User();
//		u.setUserId("check11");
//		u.setName("name1");
//		u.setPassword("passwd");
//		u.setMobile("8860572779");
//		u.setCity("muz");
//		// TODO Auto-generated method stub
		LoginService login = new LoginService();
////		login.createNewUser();
		Result rs = login.isUserValid("ALOK", "SUNNY");
		
//		BuisinessLogic bl = new BuisinessLogic();
//		try {
//			bl.processForEpoch(1419272791);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		Process p = new Process();
	RequestVO rvo = new RequestVO();
	rvo.setRequestUser("alok");
	rvo.setName("sunny22");
	rvo.setSource("delhi");
	rvo.setDestination("patna");
	rvo.setEmail("bit1.srivastava@gmail.com");
	rvo.setMobile("8860572779");
	rvo.setEpoch(1419930551);
		p.submitRequest(rvo);
//		Boolean b =login.isUserAdmin("001");
//		login.checkIfUserIdUnique("DASS");
	}

}
