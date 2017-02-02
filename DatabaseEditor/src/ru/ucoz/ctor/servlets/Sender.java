package ru.ucoz.ctor.servlets;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/send")
public class Sender extends DispatcherServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getParameter("edit")   != "") { super.forward("/edit", request, response); 	}
		if (request.getParameter("delete") != "") { super.forward("/delete", request, response); }
		
		if (request.getParameter("add") != "" && request.getParameter("add") != null) { 
			super.forward("/add", request, response);
		}
		if (request.getParameter("save") != ("") && request.getParameter("save") != null) {
			super.forward("/save", request, response);
		}
	}
}
