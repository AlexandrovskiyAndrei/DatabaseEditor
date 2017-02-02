package ru.ucoz.ctor.test;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ru.ucoz.ctor.servlets.Book;

public class BookTest {

	static Book book;
	
	@BeforeClass
	public static void setup() {
		book = new Book (49,"2014-01-02","Sujoy Acharya","Mockito Essentials",290,17.99,1);
	}
	
	@Test
	public void testGetBookId() {
		assertEquals(book.getBookId(), 49);
	}

	@Test
	public void testGetBookPublished() {
		assertEquals(book.getBookPublished(), "2014-01-02");
	}

	@Test
	public void testGetBookAuthor() {
		assertEquals(book.getBookAuthor(), "Sujoy Acharya");
	}

	@Test
	public void testGetBookName() {
		assertEquals(book.getBookName(), "Mockito Essentials");
	}

	@Test
	public void testGetBookNumberOfPages() {
		assertEquals(book.getBookNumberOfPages(),290);
	}

	@Test
	public void testGetBookPrice() {
		assertEquals(book.getBookPrice(), 17.99, 0.01);
	}

	@Test
	public void testGetBookAvailability() {
		assertEquals(book.getBookAvailability(), 1);
	}
	
	@AfterClass
	public static void tearDown() {
		book = null;
	}
}
