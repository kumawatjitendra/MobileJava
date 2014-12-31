package QueryEngine;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.json.*;


public class JSONInterface 
{


 private static String readAll(Reader rd) throws IOException 
 {
     StringBuilder sb = new StringBuilder();
     int cp;
     while ((cp = rd.read()) != -1) 
     {
       sb.append((char) cp);
     }
     return sb.toString();
  }
 
 public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException 
 {
     InputStream is = new URL(url).openStream();
     try 
     {
       BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
       String jsonText = readAll(rd);
       JSONObject json = new JSONObject(jsonText);
       return json;
     }
     finally 
     {
       is.close();
     }
 }

 public float[][] getDistanceMatrix(String beg, String end) 
 {
	 Integer tem;
	 Float dist;
	 float [][] distanceMatrix = new float[100][100];
	  JSONObject json=null;
	  try 
	  {
	  
	   json = readJsonFromUrl("https://maps.googleapis.com/maps/api/distancematrix/json?origins="+beg+"&destinations="+end+"&mode=driving&sensor=false");
	   populateDistMap(beg.toString());
	   json.get("rows");
	   
	   JSONArray arr=null;
	   arr = json.getJSONArray("rows");
	   for (int i = 0; i < arr.length(); i++) 
	   {
		   for (int j = 0; j < arr.length(); j++) {
			   tem=(Integer)arr.getJSONObject(i).getJSONArray("elements").getJSONObject(j).getJSONObject("distance").getInt("value");
			   distanceMatrix[i][j] = (float)tem/1000;
			
		}
		   
		
	   }
	  }
	  catch (JSONException e) 
	  {
	   e.printStackTrace();
	  } 
	  catch (IOException e)
	  {
	      e.printStackTrace();
	  }
	 return distanceMatrix;
	 }

public Map populateDistMap(String addresses) {
	// TODO Auto-generated method stub
	Map<Integer,String> distMap = new HashMap<Integer,String>();
	
	String [] distArray = addresses.split("\\|");
	for (int i = 0; i < distArray.length; i++) {
		distMap.put( i,distArray[i]);
	}
	return distMap;
}
}
