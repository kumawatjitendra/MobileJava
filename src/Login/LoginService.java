package Login;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import Classes.Result;
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
	public Result isUserValid(String userid , String password)
	{
		ResultSet rs = null;
		Result result = new Result();
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
			if((rs.getRow() !=0) &&((userid.equals(rs.getString("ID") )) && (password.equals(rs.getString("PASSWORD")))))
			{

				result.setIsResultValid(true);//return true;
			}
			else
			{
				result.setIsResultValid(false);//return false;
			}
			return result;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return result;
		//check from db is user credentials are correct 
		
	}
	public Boolean checkIfUserIdUnique(String userid)
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
			if((rs.getRow() !=0 && userid.equals(rs.getString("ID") )))
			{
				return false;
			}
			else
			{
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//check if user id unique or not
		return true;
	}
	public Boolean isUserAdmin(String userid)
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if(rs.getRow() !=0 && rs.getBoolean("ISADMIN"))
			{
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public ArrayList<String> getAllUsers()
	{
		ArrayList<User> list = new ArrayList<User>();
		
		return null;
		
	}
	public User getUserInfo(String userId)
	{
		return null;
	}
	public ArrayList<String> getAllDestinations() 
	{
		return null;
	}
}
