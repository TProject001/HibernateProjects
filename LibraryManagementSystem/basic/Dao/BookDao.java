package com.library.basic.Dao;

import com.library.basic.entity.Book;
import com.library.basic.entity.Student;

import java.util.List;

public interface BookDao {
	// admin functionality
	Boolean addBook(Book book);
	Boolean updateStock(Book book1,Integer newQuantity) ;
	Boolean setBookArrived(Book book);
	List<Book> getAllBooks();
	List<Book> getAllArrivedBook();
	Book getBookById(Integer bookId);
	List<Book> getBookByName(String bookName);
	List<Book> getBookByauthorName(String authorName);
	List<Book> getBookByPublisher(String bookPublisher);
	List<Book> getBookByType(String bookType);
	List<Book> getAllStudentSuggestedBook();
	
	// student
	List<Book> getAllBookForIssue();
	Boolean setStudentIdOfSuggestedBookNull(Student student);
//	Boolean SetRequestedStudent(Student student);
}
