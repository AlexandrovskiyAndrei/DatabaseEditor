package ru.ucoz.ctor.test;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

// This utility class allows to check DB connection 
// 		quickly, without launching the project in Tomcat
public class ConnectionTest {

	@Test
	public void testDBConnection () {
    	String usr = "admin";
    	String pass = "Kharkov2015";
    	String url = "jdbc:mysql://localhost:3306/books";
    	int recordNumber = 0;
    	String title = null;
		try {
			try {
				Class.forName("com.mysql.jdbc.Driver"); 
				Connection connection = DriverManager.getConnection(url, usr, pass);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM store");
				resultSet.last();
				recordNumber = resultSet.getRow();
				title = resultSet.getString(4);
			} catch (Exception e) {e.printStackTrace();}
		} catch (Exception e) {e.printStackTrace();}
		assertNotNull(recordNumber);
		assertNotNull(title);
	}
}
