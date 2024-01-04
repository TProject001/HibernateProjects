package com.library.basic.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.library.basic.entity.Student;

public class StudentDaoImpl implements StudentDao{
	private EntityManager entityManager = MyConnection.getEntityManagerObject();
	private EntityTransaction entityTransaction = entityManager.getTransaction();
	private List<Student> studentList;
	private Student student;

	@Override
	public String addStudent(Student student1) 
	{
		entityTransaction.begin();
			entityManager.persist(student1);
		entityTransaction.commit();
		return "Student Registration Done\nStudent Id : "+student1.getStudentId();
	}
	
	@Override
	public Boolean deleteStudent(Student student1) 
	{
		entityTransaction.begin();
			student1.setAccoutStatus("DEACTIVE");
//			entityManager.createQuery("UPDATE Student s SET s.accoutStatus='DEACTIVE' WHERE s.studentId=?1").setParameter(1, student.getStudentId()).executeUpdate();
//			entityManager.remove(student);
		entityTransaction.commit();
		return true;
	}

	@Override
	public Student getStudentById(Integer studentId1) 
	{
		entityTransaction.begin();
			student = entityManager.find(Student.class,studentId1);
		entityTransaction.commit();
		return student;
	}
	
	@Override
	public String updateStudentName(Student student1, String studentName1) 
	{
		entityTransaction.begin();
			student1.setStudentName(studentName1);
		entityTransaction.commit();
		return "Student Name Updated successfully!";
	}
	
	@Override
	public String updateStudentEmail(Student student1, String studentEmail1) 
	{
		entityTransaction.begin();
			student1.setStudentEmail(studentEmail1);
		entityTransaction.commit();
		return "Student Name Updated successfully!";
	}
	
	@Override
	public String updateStudentContact(Student student1, String studentContactNo1) 
	{
		entityTransaction.begin();
			student1.setStudentContactNo(studentContactNo1);
		entityTransaction.commit();
		return "Student Name Updated successfully!";
	}
	
	@Override
	public String updateStudentAddress(Student student1, String studentAddress1) {
		entityTransaction.begin();
			student1.setStudentAddress(studentAddress1);
		entityTransaction.commit();
		return "Student Name Updated successfully!";
	}
	
	@Override
	public String updateStudentPassword(Student student1, String studentPassword1) {
		entityTransaction.begin();
			student1.setPassword(studentPassword1);
		entityTransaction.commit();
		return "Student Name Updated successfully!";
	}
	
	@Override
	public Boolean updateStudentTotalFine(Student student1,Double studentTotalFine1) {
		entityTransaction.begin();
			student1.setStudentTotalFine(studentTotalFine1);
		//	Integer status = entityManager.createQuery("UPDATE Student s SET s.studentTotalFine=?1 WHERE s.studentId=?2").setParameter(1, studentTotalFine1).setParameter(2, student1.getStudentId()).executeUpdate();
		entityTransaction.commit();
		return true;
	}
	
	@Override
	public List<Student> getAllStudents() 
	{
		//"Select b from Book b"
		entityTransaction.begin();
			studentList=entityManager.createQuery("From Student s").getResultList();
		entityTransaction.commit();
		return studentList;
	}
	
	@Override
	public List<Student> getAllStudentFine() 
	{
		entityTransaction.begin();
			studentList=entityManager.createQuery("FROM Student s WHERE s.studentTotalFine>0").getResultList();
		entityTransaction.commit();
		return studentList;
	}

	@Override
	public List<Student> getAllActiveStudents()
	{
		entityTransaction.begin();
			studentList=entityManager.createQuery("FROM Student s WHERE s.accoutStatus='ACTIVE'").getResultList();
		entityTransaction.commit();
		return studentList;
	}

}
