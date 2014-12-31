package Login;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import Classes.RequestVO;
import Classes.Result;
import DBInteractor.JDBCMySQLConnection;

public class MakeRequestService {

	/**
	 * @param args
	 */
	Connection connection;
	Statement statement; 
	String query;
	public MakeRequestService() {
		// TODO Auto-generated constructor stub
		connection = JDBCMySQLConnection.getConnection();
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Result createRequest(RequestVO requestVO)
	{
//		query = "INSERT INTO "
		return null;
	}
	public Result deleteRequest(int requestID)
	{
		return null;
	}
	
}
