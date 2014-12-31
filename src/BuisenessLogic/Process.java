package BuisenessLogic;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import Classes.RequestVO;
import DBInteractor.JDBCMySQLConnection;
import QueryEngine.QueryBuilder;

public class Process {
	 Connection connection;
		Statement statement; 
		String query;
	public Process() {
		connection = JDBCMySQLConnection.getConnection();
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	BuisinessLogic logic = new BuisinessLogic();
	public void submitRequest(RequestVO rvo) throws SQLException
	{
		query = "INSERT into REQUESTS(ID,NAME,SOURCE,DESTINATIONS,EPOCH,EMAIL,GENDER,MOBILE) VALUES ("+QueryBuilder.getValueinQuotes(rvo.getRequestUser())+","+
	QueryBuilder.getValueinQuotes(rvo.getName())+","+QueryBuilder.getValueinQuotes(rvo.getSource())+","+
				QueryBuilder.getValueinQuotes(rvo.getDestination())+","+rvo.getEpoch()+","+QueryBuilder.getValueinQuotes(rvo.getEmail())+
				","+QueryBuilder.getValueinQuotes(rvo.getGender())+","+QueryBuilder.getValueinQuotes(rvo.getMobile())+" )";
		statement.executeUpdate(query);
		execute(rvo.getEpoch());
		sendMailToCommonPoints();
				
				
	}
	private void sendMailToCommonPoints() {
		
		
	}
	public void execute(long epoch)
	{
		try {
			logic.processForEpoch(epoch);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
