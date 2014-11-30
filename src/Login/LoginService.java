package Login;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import Classes.User;
import DBInteractor.JDBCMySQLConnection;
import QueryEngine.QueryBuilder;

public class LoginService {
	Connection connection;
	Statement statement; 
	String query;
	public LoginService()
	{
		connection = JDBCMySQLConnection.getConnection();
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Boolean createNewUser(User user)
	{
		String [] fields = {"ID","NAME","MOBILE","PASSWORD","CITY"};
		query = "INSERT INTO USER (ID,NAME,PASSWORD,MOBILE,CITY) VALUES ("+QueryBuilder.getValueinQuotes(user.getUserId())+
				" , "+QueryBuilder.getValueinQuotes(user.getName())+" , "+QueryBuilder.getValueinQuotes(user.getPassword())
						+" , "+QueryBuilder.getValueinQuotes(user.getMobile())+" , "+QueryBuilder.getValueinQuotes(user.getCity())+")";
		//QueryBuilder.getInsertQuery("USER", fields, [user.getUserId()]);
		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		//code to insert user in DB return false if username already exists
	}
	
	Boolean isUserIDAlreadyExists(String userId)
	{
		return null;
		
	}
	public Boolean isUserValid(String userid , String password)
	{
		ResultSet rs = null;
		query = "SELECT * FROM USER WHERE ID="+QueryBuilder.getValueinQuotes(userid);
		try {
			rs =statement.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if(rs.next())
			{
				
				
			}
			if((userid.equals(rs.getString("ID") )) && (password.equals(rs.getString("PASSWORD"))))
			{
				return true;
			}
			else
			{
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return null;
		//check from db is user credentials are correct 
		
	}
	public Boolean checkIfUserIdUnique(String userid)
	{
		return false;
		//check if user id unique or not
	}
	public Boolean isUserAdmin(String userid)
	{
		return false;
	}
	

}
