package ru.ucoz.ctor.servlets;

public class Book {
	private int 	bookId;
	private String 	bookPublished;
	private String 	bookAuthor;
	private String 	bookName;
	private int 	bookNumberOfPages;
	private double 	bookPrice;
	private int 	bookAvailability;
	
	public Book () {}
	
	public Book ( int bookId, String bookPublished, String bookAuthor, String bookName, 
							int bookNumberOfPages, double bookPrice, int bookAvailability) { 
			  this.bookId = bookId;
			  this.bookPublished = bookPublished;
			  this.bookAuthor = bookAuthor;
			  this.bookName = bookName;
			  this.bookNumberOfPages = bookNumberOfPages;
			  this.bookPrice = bookPrice; 
			  this.bookAvailability = bookAvailability;
	}
	
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getBookPublished() {
		return bookPublished;
	}
	public void setBookPublished(String bookPublished) {
		this.bookPublished = bookPublished;
	}
	public String getBookAuthor() {
		return bookAuthor;
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public int getBookNumberOfPages() {
		return bookNumberOfPages;
	}
	public void setBookNumberOfPages(int bookNumberOfPages) {
		this.bookNumberOfPages = bookNumberOfPages;
	}
	public double getBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(double bookPrice) {
		this.bookPrice = bookPrice;
	}
	public int getBookAvailability() {
		return bookAvailability;
	}
	public void setBookAvailability(int bookAvailability) {
		this.bookAvailability = bookAvailability;
	}
}
