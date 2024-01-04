package com.library.basic.service;

import java.util.List;

import com.library.basic.entity.Student;

public interface StudentService 
{
	//admin
	public List<Student> getAllActiveStudents();
	public void showAllStudentFine();
	public void showAllStudentsDetail();
	
	
	public void addStudent(Student student);
	public void deleteStudent(Student student);
	public Student getStudentById(Integer studentId);
//	public void getStudentDetail(Student student);
	public void updateStudentName(Student student,String studentName);
	public void updateStudentEmail(Student student,String studentEmail);
	public void updateStudentContact(Student student,String studentContactNo);
	public void updateStudentAddress(Student student,String studentAddress);
	public void updateStudentPassword(Student student,String studentPassword);
	
//	void showStudentsDetail();
}
