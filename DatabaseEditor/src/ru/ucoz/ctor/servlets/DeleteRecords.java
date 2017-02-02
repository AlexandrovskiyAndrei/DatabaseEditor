package ru.ucoz.ctor.servlets;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

@WebServlet("/delete")
public class DeleteRecords extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String usr = "admin";
	private String pass = "Kharkov2015";
	private String url = "jdbc:mysql://localhost:3306/books";
	
	private int numberOfRowsDeleted;
	
	protected void service(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		String deleteRecordIds = request.getParameter("picked");
		try {
			try {
				Class.forName("com.mysql.jdbc.Driver"); 
				Connection connection = (Connection) DriverManager.getConnection(url, usr, pass);	
				PreparedStatement statement = (PreparedStatement) connection.prepareStatement(
							"DELETE FROM store WHERE id IN ("+deleteRecordIds+");"	);
				statement.execute();
				} catch (SQLException e) { e.printStackTrace(); }
			} catch (Exception e) { e.printStackTrace(); }
			request.getRequestDispatcher("./").forward(request, response);
	}
	
	protected int getNumberOfRowsDeleted(){
		return numberOfRowsDeleted;
	}
}