package ru.ucoz.ctor.test;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import ru.ucoz.ctor.servlets.CancelEditing;

public class CancelEditingTest {

	@Mock private HttpServletRequest request;
	@Mock private HttpServletResponse response;
	@Mock private RequestDispatcher dispatcher;
	
	@Test
	public void cancelEditingTest() throws ServletException, IOException {
		MockitoAnnotations.initMocks(this);
		
		when(request.getRequestDispatcher("./")).thenReturn(dispatcher);
		
		new CancelEditing().service(request, response);
		
		verify(request, atLeast(1)).removeAttribute("add");
		verify(request, atLeast(1)).removeAttribute("save");
		verify(request).getRequestDispatcher(eq("./"));
		verify(dispatcher).forward(request, response);
	}

}
