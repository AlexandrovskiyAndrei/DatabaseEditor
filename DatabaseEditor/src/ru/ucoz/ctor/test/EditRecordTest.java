package ru.ucoz.ctor.test;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.never;
import ru.ucoz.ctor.servlets.EditRecord;

public class EditRecordTest {
	@Mock private HttpServletRequest request;
	@Mock private HttpServletResponse response;
	@Mock private RequestDispatcher dispatcher;
	@Mock private Connection connection;
	@Mock private PreparedStatement statement;
	
	@Test 
	public void editRecordTest() throws ServletException, IOException, SQLException {
		MockitoAnnotations.initMocks(this);
		
		when(request.getParameter("picked")).thenReturn("");
		when(request.getRequestDispatcher("/edit.jsp")).thenReturn(dispatcher);
		
		new EditRecord().service(request, response);
		
		verify(request, atLeast(1)).removeAttribute("picked");
		verify(request, atLeast(1)).setAttribute("add", "");
		verify(request, never()).setAttribute("save", "");
	}

	@Test 
	public void saveRecordTest() throws ServletException, IOException, SQLException {
		MockitoAnnotations.initMocks(this);
		
		when(request.getParameter("picked")).thenReturn("3");
		when(request.getRequestDispatcher("/edit.jsp")).thenReturn(dispatcher);
		
		new EditRecord().service(request, response);
		
		verify(request, atLeast(1)).removeAttribute("picked");
		verify(request, atLeast(1)).setAttribute("save", "3");
		verify(request, never()).setAttribute("add", "");
	}
}
