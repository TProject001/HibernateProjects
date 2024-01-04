package com.library.basic.Dao;

import java.util.List;

import com.library.basic.entity.Admin;
import com.library.basic.entity.Book;
import com.library.basic.entity.BookIssue;
import com.library.basic.entity.Student;

public interface BookIssueDao {
	public BookIssue getBookIssueByBookIssueId(Integer bookIssueId);
	
	// Admin functionality
	public String adminConfirmIssueBook(BookIssue bookIssue1, Admin bookIssueAdmin1);
	public String adminConfirmReturnBook(BookIssue bookIssue1, Admin returnBookAdmin1);
	public List<BookIssue> getAllBookIssueRequestOfAllStudent();
	public List<BookIssue> getAllIssuedBook();
	public List<BookIssue> getAllDelayedBook();
	public List<BookIssue> getAllReturnBookRequestOfAllStudent();
	public List<BookIssue> getAllBookIssue();
	
	// Student functionality
	public String studentRequestIssueBook(BookIssue bookIssue);
	public String studentRequestReturnBook(BookIssue bookIssue);
//	public Double getStudentBookIssueFineDetail(BookIssue bookIssue1);
	public List<BookIssue> getBookIssueDetailsOfstudent( Student student);
	public List<BookIssue> getIssuedBookOfstudent(Student student1);
	public List<BookIssue> getStudentFineDetail(Student student);
//	public Double getStudentBookIssueFineDetail(BookIssue bookIssue);
	public List<BookIssue> isStudentBookIssueRequestPresent(Student student,Book book);
	public List<BookIssue> isBookIssuedToStudent(Student student,Book book);
	public List<BookIssue> hasStudentReturnAllIssuedBookToLibrary(Student student);
}
