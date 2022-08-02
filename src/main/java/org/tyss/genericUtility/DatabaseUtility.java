package org.tyss.genericUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.Driver;

/**
 * This class contains only database related reusable methods
 * @author MOHAN GOWDA
 *
 */
public final class DatabaseUtility {
	
	private Connection connection;
	
	
	/**
	 * This method is used to estrablish connection with MYSQL Database
	 * @param url
	 * @param userName
	 * @param password
	 */
	public void getConnectionWithDB(String url, String userName, String password) {
		Driver dbDriver = null;
		try {
			dbDriver = new Driver();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			DriverManager.registerDriver(dbDriver);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			 connection = DriverManager.getConnection(url,userName, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method is used to modify the data in Database
	 * @param sqlQuery
	 * @return
	 */
	public int modifyDataInDB(String sqlQuery) {
		int count=0;
		try {
			 count = connection.createStatement().executeUpdate(sqlQuery);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	
	/**
	 * This method is used to close the database connection
	 */
	public void closeDBConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Connection closed");
	}
	
	/**
	 * This method is used to get the data from the database in the form of List<String>
	 * @param query
	 * @return List<String>
	 */
//	public List<String> getDataFromDB(String query, String columnName) {
//		ResultSet results =null;
//	List<String> list=new ArrayList<>();
//		
//		try {
//			 results = connection.createStatement().executeQuery(query);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//
//		try {
//			while(results.next())
//			{
//				try {
//					 list.add(results.getString(columnName));
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return list;
//	}
	
	
//	public List<String> getDataFromDB(String query, int columnIndex) {
//		ResultSet results =null;
//	List<String> list=new ArrayList<>();
//		
//		try {
//			 results = connection.createStatement().executeQuery(query);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//
//		try {
//			while(results.next())
//			{
//				try {
//					 list.add(results.getString(columnIndex));
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return list;
//	}
	
	public List<String> getDataFromDB(String query, String columnNameOrcolumnIndex) {
		ResultSet results =null;
	List<String> list=new ArrayList<>();
		
	String s1="";
	
	for (int i = 0; i < columnNameOrcolumnIndex.length(); i++) {
		if(Character.isDigit(columnNameOrcolumnIndex.charAt(i)))
		{
			s1=s1+columnNameOrcolumnIndex.charAt(i);
		}
		else {
			break;
		}
	}
		try {
			 results = connection.createStatement().executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

		try {
			while(results.next())
			{
				try {
					if(columnNameOrcolumnIndex.length()==s1.length()) {
					 list.add(results.getString(Integer.parseInt(s1)));
					}
					else {
						 list.add(results.getString(columnNameOrcolumnIndex));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	/**
	 * This method is used to verify wheather the expected data is present or not
	 * @param query
	 * @param columnName
	 * @param expectedData
	 * @return
	 */
public boolean verifyDataInDB(String query,String columnNameOrcolumnIndex, String expectedData) {
	List<String> list=getDataFromDB(query,columnNameOrcolumnIndex);
	
	System.out.println(list);
	boolean flag=false;
	for (String data : list) {
		if(data.equalsIgnoreCase(expectedData)) {
			flag=true;
			break;
		}
	}
	return flag;
	
	}
	
	

}
