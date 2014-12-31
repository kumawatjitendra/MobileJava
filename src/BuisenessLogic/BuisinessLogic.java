package BuisenessLogic;

import QueryEngine.JSONInterface;
import QueryEngine.QueryBuilder;
import Classes.PointsPair;
import Classes.UserTravelInfo;
import DBInteractor.JDBCMySQLConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BuisinessLogic {
	static long allocationId = 260689;
	static   int THRESHOLD_DISTANCE = 20;
	 Map<Integer,String> sourcePoints = new HashMap<Integer,String>();
	 Map<Integer,ArrayList<String>> userPointsForSource = new HashMap<Integer,ArrayList<String>>();
	 Map<Integer,ArrayList<String>> userPointsForDestination = new HashMap<Integer,ArrayList<String>>();
	 Map<String,String> userToSource = new HashMap<String,String>();
	 Map<String,String> userToDistination = new HashMap<String,String>();
	 Map<Integer,String> distinationPoints = new HashMap<Integer,String>();
	 float [][] distanceMatrixForSource = new float[100][100];
	 float [][] distanceMatrixForDistination = new float[100][100];
	 ArrayList<Classes.PointsPair> sourcePointsWithinThreshold;
	 ArrayList<Classes.PointsPair> distPointsWithinThreshold;
	public ArrayList<Classes.PointsPair> commonPoints;
	 ArrayList<Classes.UserTravelInfo> usersTravelInfo;
	 Connection connection;
		Statement statement; 
		String query;
		public BuisinessLogic() {
			connection = JDBCMySQLConnection.getConnection();
			try {
				statement = connection.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// TODO Auto-generated constructor stub
		}
	//this function updates DB with allocation ID for all enteries "at the given EPOCH"
	public void processForEpoch(long epoch) throws SQLException {
		// TODO Auto-generated method stub
		 JSONInterface ji=new JSONInterface();
			populateUserTravelInfos(epoch);
			userToSource = getUserToSourceMap(usersTravelInfo);
			 userToDistination = getUserToDistinationMap(usersTravelInfo);
			sourcePoints = getAllSourcePoints(usersTravelInfo);
			userPointsForSource = getUserPoints(userToSource,sourcePoints);
			distinationPoints = getAllDistinationPoints(usersTravelInfo);
			userPointsForDestination = getUserPoints(userToDistination, distinationPoints);
			distanceMatrixForSource = ji.getDistanceMatrix(getSourcePointString(sourcePoints),getSourcePointString(sourcePoints));
			distanceMatrixForDistination = ji.getDistanceMatrix(getSourcePointString(distinationPoints), getSourcePointString(distinationPoints));
			sourcePointsWithinThreshold = getAllPairsWhithinThreshold(distanceMatrixForSource,THRESHOLD_DISTANCE,userPointsForSource.size(),userPointsForSource);
			distPointsWithinThreshold = getAllPairsWhithinThreshold(distanceMatrixForDistination,THRESHOLD_DISTANCE,userPointsForDestination.size(),userPointsForDestination);
			commonPoints =getCommonPoints(sourcePointsWithinThreshold,distPointsWithinThreshold);
			populateAllcocationId();
		
	}
	private void populateAllcocationId() throws SQLException {
		query = "UPDATE REQUESTS SET ALLOCID =";
		for (int i = 0; i < commonPoints.size(); i++) {
			statement.executeUpdate(query + (++allocationId)+" where email = "+QueryBuilder.getValueinQuotes(commonPoints.get(i).getPointA()));
			statement.executeUpdate(query + (allocationId)+" where email = "+QueryBuilder.getValueinQuotes(commonPoints.get(i).getPointB()));
			
			
		}
		// TODO Auto-generated method stub
		
	}
	@SuppressWarnings("unused")
	private void populateUserTravelInfos(long epoch) throws SQLException {
		// TODO Auto-generated method stub
		usersTravelInfo = new ArrayList<UserTravelInfo>();
		ResultSet rs ;
		query = "select * from requests where epoch ="+epoch;
		rs = statement.executeQuery(query);
		while(rs.next())
		{
			UserTravelInfo userTravleInfo = new UserTravelInfo();
			userTravleInfo.setUserName(rs.getString("name"));
			userTravleInfo.setSource(rs.getString("source"));
			userTravleInfo.setDestination(rs.getString("destinations"));
			userTravleInfo.setGender(rs.getString("gender"));
			userTravleInfo.setEmail(rs.getString("email"));
			userTravleInfo.setMobile(rs.getString("mobile"));
			userTravleInfo.setEpoch(rs.getLong("epoch"));
			usersTravelInfo.add(userTravleInfo);
		}
		
	}
	@SuppressWarnings("unused")
	private  Map<Integer, ArrayList<String>> getUserPoints(
			Map<String, String> userToSource2,
			Map<Integer, String> sourcePoints2) {
		 Map<Integer,ArrayList<String>> ret = new HashMap<Integer,ArrayList<String>>();
		 for (int i = 0; i < sourcePoints2.size(); i++) {
			 ArrayList<String> users = new ArrayList<String>();
			 users = getKeys(userToSource2,sourcePoints2.get(i));
			 ret.put(i, users);
			
		}
		 return ret;
		
	}
	private  ArrayList<String> getKeys(Map<String, String> userToSource2,
			String source) {
		 ArrayList<String> ret = new ArrayList<String>();
		for (String user : userToSource2.keySet()) {
			if(userToSource2.get(user).toString()==source)
				ret.add(user);
			
		}
		return ret;
	}
	private  String getSourcePointString(Map<Integer, String> sourcePoints2) {
		// TODO Auto-generated method stub
		String ret = new String();
		ret=sourcePoints2.get(0);
		for (int i = 1; i < sourcePoints2.size(); i++) {
			ret=ret+"|"+sourcePoints2.get(i);
		}
		return ret;
	}
	private  ArrayList<UserTravelInfo> getUsersTravelInfo() throws IOException {
		ArrayList<UserTravelInfo> ret = new ArrayList<UserTravelInfo>();
		int N =0;
		System.out.print("\nEnter number of user\n");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			try {
				N = Integer.parseInt(br.readLine());
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (int i = 0; i < N; i++) {
				UserTravelInfo uti = new UserTravelInfo();
				System.out.print((i+1)+": Enter userName : \n");
				String uName =br.readLine();
				System.out.print("Enter source : \n");
				String source = br.readLine();
				System.out.print("Enter dist : \n");
				String dist = br.readLine();
				uti.setUserName( uName);
				uti.setSource(source);
				uti.setDestination(dist);
				ret.add(uti);
				
			}
		return ret;
	}
	private  Map<String, String> getUserToDistinationMap(ArrayList<UserTravelInfo> usersTravelInfo2) {
		Map<String, String> ret = new HashMap<String, String>();
		for (UserTravelInfo userTravelInfo : usersTravelInfo2) {
			ret.put(userTravelInfo.getEmail(), userTravelInfo.getDestination());
			
		}
		return ret;
	}
	private  Map<String, String> getUserToSourceMap(ArrayList<UserTravelInfo> usersTravelInfo2) {
		
		Map<String, String> ret = new HashMap<String, String>();
		for (UserTravelInfo userTravelInfo : usersTravelInfo2) {
			ret.put(userTravelInfo.getEmail(), userTravelInfo.getSource());
			
		}
		return ret;
	}
	private  ArrayList<PointsPair> getCommonPoints(
			ArrayList<PointsPair> sourcePointsWithinThreshold2,
			ArrayList<PointsPair> distPointsWithinThreshold2) {
		ArrayList<PointsPair> ret = new ArrayList<PointsPair>();
		for (PointsPair pointsPairD : distPointsWithinThreshold2) {
			for (PointsPair pointsPairS : sourcePointsWithinThreshold2) {
				if(pointsPairD.isEqual(pointsPairS))
					ret.add(pointsPairD);
				
			}
		}
		return ret;
	}
	private  ArrayList<PointsPair> getAllPairsWhithinThreshold(
			float[][] distanceMatrixForSource2, int tHRESHOLD_DISTANCE2,
			int size,Map<Integer,ArrayList<String>> points) {
		ArrayList<PointsPair> ret = new ArrayList<PointsPair>();
		for (int i = 0; i < size; i++) {
			for (int j = i; j < size; j++) {
				if(distanceMatrixForSource2[i][j] <tHRESHOLD_DISTANCE2 && i!=j )
				{
					if(points.get(i).size() > 0 && points.get(j).size() > 0)
					{
					PointsPair p = new PointsPair();
					
					p.setPointA(points.get(i).get(0));
					p.setPointB(points.get(j).get(0));
					ret.add(p);
					}
				}
			}
		}
		return ret;
		// TODO Auto-generated method stub
		
	}
	@SuppressWarnings("unchecked")
	private  Map<Integer,String> getAllDistinationPoints(ArrayList<UserTravelInfo> usersTravelInfo2) {
		Map<Integer,String> ret = new HashMap<Integer, String>();
		for (int i = 0; i < usersTravelInfo2.size(); i++) {
			ret.put(i, usersTravelInfo2.get(i).getDestination());
		}
		return ret;
//		.populateDistMap(start_d.toString());
	}
	private  Map<Integer,String> getAllSourcePoints(ArrayList<UserTravelInfo> usersTravelInfo2) {
		Map<Integer,String> ret = new HashMap<Integer, String>();
		for (int i = 0; i < usersTravelInfo2.size(); i++) {
			ret.put(i, usersTravelInfo2.get(i).getSource());
		}
		return ret;
	}

}
