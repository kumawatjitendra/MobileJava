package Login;

import Classes.User;

public class LoginService {
	Boolean createNewUser(User user)
	{
		return false;
		//code to insert user in DB return false if username already exists
	}
	Boolean isUserValid(String userid , String password)
	{
		return null;
		//check from db is user credentials are correct 
		
	}
	Boolean checkIfUserIdUnique(String userid)
	{
		return false;
		//check if user id unique or not
	}
	
	

}
