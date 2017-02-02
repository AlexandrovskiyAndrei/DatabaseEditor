package ru.ucoz.ctor.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/refresh")
public class Refresh extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private String usr = "admin";
	private String pass = "Kharkov2015";
	private String url = "jdbc:mysql://localhost:3306/books";
	
	private List<Book> rows = new ArrayList<Book>();
	private int recordsNumber;
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			try {
				Class.forName("com.mysql.jdbc.Driver"); 
				Connection connection = DriverManager.getConnection(url, usr, pass);
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM store");
				recordsNumber = resultSet.getRow();				
				
				resultSet.beforeFirst();
				while(resultSet.next()){
					Book book = new Book();
					book.setBookId(resultSet.getInt(1));
					book.setBookPublished(resultSet.getString(2));
					book.setBookAuthor(resultSet.getString(3));
					book.setBookName(resultSet.getString(4));
					book.setBookNumberOfPages(resultSet.getInt(5));
					book.setBookPrice(resultSet.getDouble(6));
					book.setBookAvailability(resultSet.getInt(7));
					rows.add(book);
				}
				
				request.setCharacterEncoding("UTF-8");
				request.setAttribute("records", rows);
				request.getRequestDispatcher("/datagrid.jsp").forward(request, response);
				rows.clear();
				resultSet.close();
				statement.close();
				connection.close();
			} catch (Exception e) {e.printStackTrace();}
		} catch (Exception e) {e.printStackTrace();}
	}
	
	public int getRecordsNumber () {
		return recordsNumber;
	}
}
