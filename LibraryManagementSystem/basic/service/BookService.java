package com.library.basic.service;

import java.util.List;

import com.library.basic.entity.Book;

public interface BookService {
	Boolean addBook(Book book);
	void updateStock() ;
	void getAllStudentSuggestedBook();
//	Book getBookById(Integer bookId);
	void getBookByName(String bookName);
	void getBookByAuthor(String bookName);
	void getBookByPublication(String bookName);
	void getBookByType(String bookType);
//	void getAllArrivedBook() ;
	List<Book> getAllBooks();
//	void displayBook();
//	void showAllBooks();
}
