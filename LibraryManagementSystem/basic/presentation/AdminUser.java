package com.library.basic.presentation;

public interface AdminUser {
	// Admin related functionality
	void registerAdmin();
	void deleteAdmin() ;
	Boolean adminLogin() ;
	void showSingleAdminProfile();
	void updateAdminDetails();
	void updateAdminPassword();
	
	// book related functionality
	void registerBook() ;
	void getAllStudentSuggestedBook() ;
	
	// student related functionality
	void showAllStudentDetails();
	void showAllStudentFine();
	
	// Book Issue related functionality
	void getAllBookIssueRequestOfAllStudent();
	void getAllReturnBookRequestOfAllStudent();
	void getAllBookIssue();
	void getAllIssuedBook();
	void getAllDelayedBook();
}
