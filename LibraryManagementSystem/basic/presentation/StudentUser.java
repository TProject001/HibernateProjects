package com.library.basic.presentation;

public interface StudentUser {
	// student
	Boolean studentLogin();
	void registerStudent();
	void deleteStudent();
	void viewStudentProfile();
	void updatestudentDetails();
	void updatestudentPassword();
	void viewFineDetails();
	
	// book
	void SuggestBook();
	
	// bookIssue
	void requestForIssueBook() ;
	void requestForreturnBook() ;
	void viewBookIssueDetailsOfstudent();	
	void viewIssuedBookOfStudent();

}
