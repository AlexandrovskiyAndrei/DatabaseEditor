package ru.ucoz.ctor.servlets;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

@WebServlet("/save")
public class SaveRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String usr = "admin";
	private String pass = "Kharkov2015";
	private String url = "jdbc:mysql://localhost:3306/books";
	
	protected void service(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id") );
		String published = parseDate(request.getParameter("published"));
		String author 	 = request.getParameter("author");
		String name 	 = request.getParameter("name");
		String pages 	 = request.getParameter("number_of_pages");
		double price 	 = Double.parseDouble(request.getParameter("price"));
		int available = Integer.parseInt(request.getParameter("availability"));
		
		String values = (id + "," + "'" + published + "'" +  "," + "'" + author + 
			"'" + "," + "'" + name + "'" + "," + pages + "," + price + "," + available); 
		
		// TODO: replace 'deleteRecord' and 'addRecord' with 'updateRecord'
		//		(meaning - use UPDATE in SQL, not DELETE and INSERT) 
		deleteRecord(id);
		addRecord (values);

		request.getRequestDispatcher("./").forward(request, response);
	}
	
	protected void deleteRecord(int deleteId){
		try {
			try {
				Class.forName("com.mysql.jdbc.Driver"); 
				Connection connection = (Connection) DriverManager.getConnection(url, usr, pass);	
				PreparedStatement statement = (PreparedStatement) connection.prepareStatement(
							"DELETE FROM store WHERE id ='"+deleteId+"'"	);
				statement.execute();
				} catch (SQLException e) { e.printStackTrace(); }
			} catch (Exception e) { e.printStackTrace(); }
	}
	
	protected void addRecord (String insertValues) {
	try {
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			Connection connection = (Connection) DriverManager.getConnection(url, usr, pass);	
			PreparedStatement statement = (PreparedStatement) connection.prepareStatement(
				"INSERT INTO store (id, published, author, name, number_of_pages, price, availability)"
						+ " VALUES ("+insertValues+");"  );
			statement.execute();
			} catch (SQLException e) { e.printStackTrace(); }
		} catch (Exception e) { e.printStackTrace(); } 
	}
	
	private static String parseDate (String req) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    Date paramDate = null;
	    try {
			paramDate = dateFormat.parse(req);
		} catch (ParseException e) { 
			e.printStackTrace(); 
		}
		String date = dateFormat.format(paramDate );
	    return date;
	}
}