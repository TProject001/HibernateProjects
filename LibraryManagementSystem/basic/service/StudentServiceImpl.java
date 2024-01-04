package com.library.basic.service;

import java.util.Iterator;
import java.util.List;

import com.library.basic.Dao.BookDao;
import com.library.basic.Dao.BookDaoImpl;
import com.library.basic.Dao.StudentDao;
import com.library.basic.Dao.StudentDaoImpl;
import com.library.basic.entity.Student;

public class StudentServiceImpl implements StudentService{
	private static StudentDao studentDao = new StudentDaoImpl();
//	private BookDao bookDao = new BookDaoImpl();
	private static List<Student> studentList ;
	
	@Override
	public void addStudent(Student student) 
	{
		System.out.println( studentDao.addStudent(student));
	}

	@Override
	public void deleteStudent(Student student) 
	{
		if(studentDao.deleteStudent(student))
			System.out.println("Your Account Deleted!");
//		if(bookDao.SetRequestedStudent(student) && studentDao.deleteStudent(student))
//			System.out.println("Student Account Deleted!");
//		else
//			System.out.println("Something went wrong");	
	}
	
	@Override
	public Student getStudentById(Integer studentId) 
	{
		return studentDao.getStudentById(studentId);
	}
	
	//update single student details
	
	@Override
	public void updateStudentName(Student student1, String studentName) 
	{
		System.out.println(studentDao.updateStudentName(student1, studentName));
	}

	@Override
	public void updateStudentEmail(Student student1, String studentEmail) 
	{
		System.out.println(studentDao.updateStudentEmail(student1, studentEmail));
	}

	@Override
	public void updateStudentContact(Student student1, String studentContactNo) 
	{
		System.out.println(studentDao.updateStudentContact(student1, studentContactNo));
	}

	@Override
	public void updateStudentAddress(Student student1, String studentAddress) 
	{
		System.out.println(studentDao.updateStudentAddress(student1, studentAddress));
	}

	@Override
	public void updateStudentPassword(Student student1, String studentPassword) 
	{
		System.out.println(studentDao.updateStudentPassword(student1, studentPassword));
	}
	
	// All student
	
	@Override
	public List<Student> getAllActiveStudents()
	{
		return studentDao.getAllActiveStudents();
	}
	
	@Override
	public void showAllStudentsDetail() 
	{
		studentList = studentDao.getAllStudents();
		if(studentList==null)
		{
			System.out.println("Something went wrong");
		}
		else if(studentList.isEmpty())
		{
			System.out.println("no student is available");
		}
		else 
			StudentServiceImpl.showStudentsDetail();
	}

	@Override
	public void showAllStudentFine() 
	{
		studentList = studentDao.getAllStudentFine();
		StudentServiceImpl.showStudentsDetail();
	}
	
	// static method
	public static void showStudentsDetail() 
	{
		if(studentList!=null) 
		{
			if(!studentList.isEmpty()) 
			{
				Iterator<Student> itr = studentList.iterator();
				System.out.printf("\n\n| %-7s | %-30s | %-30s | %-14s | %-20s | %-8s |%-5s |","Id" ,"Student Name", "Student Email","Contact no","password","status","fine"); 
//				System.out.println("student Id | Student Name \t |  Student Email | Student Contact no | Student password"+"| Student fine"); 
				
				while(itr.hasNext()) 
				{
					Student student = itr.next();
					System.out.printf("\n| %-7s | %-30s | %-30s | %-14s | %-20s | %-8s |%-5s |",student.getStudentId()+"",student.getStudentName()+"",student.getStudentEmail()+"",student.getStudentContactNo()+"",student.getPassword()+"",student.getAccoutStatus()+"",student.getStudentTotalFine()+"");
		//			System.out.println(student.getStudentId()+"\t\t"+student.getStudentName()+"\t\t"+student.getStudentEmail()+"  "+student.getStudentContactNo()+"\t\t"+student.getPassword()+"\t"+student.getStudentTotalFine());
				}
			}
			else System.out.println("Sorry! No user found in database");
		}
		else System.out.println("Something went wrong");
	}

}
