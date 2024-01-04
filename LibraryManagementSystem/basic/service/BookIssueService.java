package com.library.basic.service;

import java.util.List;

import com.library.basic.entity.Admin;
import com.library.basic.entity.BookIssue;
import com.library.basic.entity.Student;

public interface BookIssueService {

	// Student functionality
	void studentRequestIssueBook(Student student1);
	void studentRequestReturnBook(Student student1);
	void payFine(Student student1);
	void viewIssuedBookOfstudent(Student student1) ;
	void getBookIssueDetailsOfstudent( Student student);
	Boolean hasStudentReturnAllIssuedBookToLibrary(Student student);
	
	// Admin functionality
	void getAllBookIssueRequestOfAllStudent(Admin admin1);
	void getAllReturnBookRequestOfAllStudent(Admin admin1);
	void getAllBookIssue();
	void getAllIssuedBook();
	void getAllDelayedBook();	
}
