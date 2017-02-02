package ru.ucoz.ctor.test;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.atLeast;
import ru.ucoz.ctor.servlets.AddRecord;

public class AddRecordTest {
	@Mock private HttpServletRequest request;
	@Mock private HttpServletResponse response;
	@Mock private RequestDispatcher dispatcher;
	@Mock private Connection connection;
	@Mock private PreparedStatement statement;
	
	@Test 
	public void addRecordTest() throws ServletException, IOException, SQLException {
		MockitoAnnotations.initMocks(this);

		// Currently this test appends a new record to the database
		// SQL exception is thrown when the test attempts to add 
		// 		a record with a duplicate id.
		// "newId" resolves the above described issue
		Integer newId = recordsNumber() + 1;
		
		when(request.getParameter("id")).thenReturn(newId.toString());
		when(request.getParameter("published")).thenReturn("2014-01-02");
		when(request.getParameter("author")).thenReturn("Sujoy Acharya");
		when(request.getParameter("name")).thenReturn("Mockito Essentials");
		when(request.getParameter("number_of_pages")).thenReturn("290");
		when(request.getParameter("price")).thenReturn("17.99");
		when(request.getParameter("availability")).thenReturn("1");
		when(request.getRequestDispatcher("./")).thenReturn(dispatcher);
		when(connection.prepareStatement(
			"180,'2014-01-02','Sujoy Acharya','Mockito Essentials',290,17.99,1")).thenReturn(statement);
		when(statement.execute()).thenReturn(true);
		
		new AddRecord().service(request, response);

		verify(request, atLeast(1)).getParameter("id");
		verify(request, atLeast(1)).getParameter("published");
		verify(request, atLeast(1)).getParameter("author");
		verify(request, atLeast(1)).getParameter("name");
		verify(request, atLeast(1)).getParameter("number_of_pages");
		verify(request, atLeast(1)).getParameter("price");
		verify(request, atLeast(1)).getParameter("availability");
		verify(request).getRequestDispatcher(eq("./"));
		verify(dispatcher).forward(request, response);
		
		Integer increasedByOne = recordsNumber();
		assertEquals(increasedByOne, newId);
	}
	
	// returns current number of records in the database
	private int recordsNumber(){	
		String usr = "admin";
		String pass = "Kharkov2015";
		String url = "jdbc:mysql://localhost:3306/books";
		int recordNumber = 0;
		try {
			try {
				Class.forName("com.mysql.jdbc.Driver"); 
				Connection connection = (Connection) DriverManager.getConnection(url, usr, pass);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM store");
				resultSet.last();
				recordNumber = resultSet.getRow();
			} catch (Exception e) {e.printStackTrace();}
		} catch (Exception e) {e.printStackTrace();}
		return recordNumber;
	}
}
