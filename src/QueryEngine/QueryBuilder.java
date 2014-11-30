package QueryEngine;

public class QueryBuilder {
	public static String getInsertQuery(String tableName,String[] fields,Object[] values)
	{
		String query = "INSERT INTO "+tableName+"("+getfields(fields) +") VALUES ("+getValues(values)+")" ;
		return query;
	}

	private static String getValues(Object[] values) {
		// TODO Auto-generated method stub
		return null;
	}

	private static String getfields(String[] fields) {
		// TODO Auto-generated method stub
		String fieldsString ="";
		int i;
		for (i = 0; i < fields.length-1; i++) {
			fieldsString += (fields[i] +",");
		}
		fieldsString += fields[i];
		return fieldsString;
	}
	public static String getValueinQuotes(String org)
	{
		return "\""+org+"\"";
	}
}
