package com.library.basic.Dao;

import java.util.List;

import com.library.basic.entity.Student;

public interface StudentDao {
	// only admin functionality
	public List<Student> getAllStudents();
	public List<Student> getAllStudentFine();
	
	//student+admin functionality
	public String addStudent(Student student);
	public Boolean deleteStudent(Student student);
	public Student getStudentById(Integer studentId);
	public String updateStudentName(Student student,String studentName);
	public String updateStudentEmail(Student student,String studentEmail);
	public String updateStudentContact(Student student,String studentContactNo);
	public String updateStudentAddress(Student student,String studentAddress);
	public String updateStudentPassword(Student student,String studentPassword);
	public Boolean updateStudentTotalFine(Student student,Double studentTotalFine);

	public List<Student> getAllActiveStudents();
	
}
