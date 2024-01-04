package com.library.basic.presentation;

import com.library.basic.entity.Admin;
import com.library.basic.entity.Student;

public interface BookUser {
	void registerBook(Admin admin,Student student);
	void searchBook();
	void getAllBook();
	void updateBookStock();
//	void ViewBookDetails();
}
