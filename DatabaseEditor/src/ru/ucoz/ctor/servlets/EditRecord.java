package ru.ucoz.ctor.servlets;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;

@WebServlet("/edit")
public class EditRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String usr = "admin";
	private String pass = "Kharkov2015";
	private String url = "jdbc:mysql://localhost:3306/books";
	
	protected void service(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		String received = request.getParameter("picked");
		request.removeAttribute("picked");
		if (received == ""){
			request.setAttribute("add", received);
		} else {
			request.setAttribute("save", received);
			Book toSave = retrieveRecord(received); 
			request.setAttribute("saveBook", toSave);
		}
		request.getRequestDispatcher("/edit.jsp").forward(request, response);
	}
	
	protected Book retrieveRecord (String bookId) {
		Book book = null;
		try {																				
			Connection connection = (Connection) DriverManager.getConnection(url, usr, pass);	
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM store WHERE id ='"+bookId+"'");
			resultSet.beforeFirst();
			resultSet.next();
			book = new Book();
				book.setBookId(resultSet.getInt("id"));
				book.setBookPublished(resultSet.getString("published"));
				book.setBookAuthor(resultSet.getString("author"));
				book.setBookName(resultSet.getString("name"));
				book.setBookNumberOfPages(resultSet.getInt("number_of_pages"));
				book.setBookPrice(resultSet.getDouble("price"));
				book.setBookAvailability(resultSet.getInt("availability"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return book;
	}
}