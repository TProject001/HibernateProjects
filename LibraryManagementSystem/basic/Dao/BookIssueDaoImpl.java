package com.library.basic.Dao;
// check calculate fine method
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.library.basic.entity.Admin;
import com.library.basic.entity.Book;
import com.library.basic.entity.BookIssue;
import com.library.basic.entity.Student;

public class BookIssueDaoImpl implements BookIssueDao{
	private EntityManager entityManager=MyConnection.getEntityManagerObject();
	private EntityTransaction entityTransaction = entityManager.getTransaction();
	private String jpql;
//	private Query query;
	private List<BookIssue> bookIssueList;
	private BookIssue bookIssue;
	
	@Override
	public List<BookIssue> getAllBookIssueRequestOfAllStudent() 
	{
		entityTransaction.begin();
			bookIssueList= entityManager.createQuery("FROM BookIssue bookIssue WHERE bookIssue.bookStatus='NOT ISSUE'").getResultList();
		entityTransaction.commit();
		return bookIssueList;
	}

	@Override
	public String adminConfirmIssueBook(BookIssue bookIssue1, Admin bookIssueAdmin1) 
	{
	//	System.out.println("from confirm book");
	//	jpql = "UPDATE BookIssue b SET b.bookStatus='ISSUE',b.bookIssueAdminId=?1,b.issueDate=?2,b.dueDate?3 WHERE b.issueId=?4";
		entityTransaction.begin();
		jpql = "UPDATE BookIssue b SET b.issueDate=?1 , b.dueDate=?2 , b.bookStatus='ISSUE',b.bookIssueAdmin=?3 WHERE b.issueId=?4";
			entityManager.createQuery(jpql).setParameter(1, LocalDate.now()).setParameter(2, LocalDate.now().plusDays(1)).setParameter(3, bookIssueAdmin1).setParameter(4, bookIssue1.getIssueId()).executeUpdate();
			entityManager.flush();
			entityManager.refresh(bookIssue1);
		entityTransaction.commit();
		
		System.out.println("issue date : "+bookIssue1.getIssueDate());
		return "Requested Book issue to the student!"; //"Requested Book issue to the student!"
	}
	
	@Override
	public String adminConfirmReturnBook(BookIssue bookIssue1, Admin returnBookAdmin1) 
	{
		jpql = "UPDATE BookIssue b SET b.bookStatus='Return',b.returnDate=?1,b.bookReturnAdmin=?2 WHERE b.issueId=?3";
		entityTransaction.begin();
			entityManager.createQuery(jpql).setParameter(1, LocalDate.now()).setParameter(2, returnBookAdmin1).setParameter(3, bookIssue1.getIssueId()).executeUpdate();
			entityManager.refresh(bookIssue1);
		entityTransaction.commit();
		return "Requested Book Return from the student!";
	}
	
	@Override
	public List<BookIssue> getAllBookIssue() 
	{
		entityTransaction.begin();
			bookIssueList=entityManager.createQuery("From BookIssue bookIssue").getResultList();
		entityTransaction.commit();
		return bookIssueList;
	}
	
	@Override
	public List<BookIssue> getAllIssuedBook() 
	{
		entityTransaction.begin();
			bookIssueList=entityManager.createQuery("FROM BookIssue b WHERE b.bookStatus='ISSUE'").getResultList();
		entityTransaction.commit();
		return bookIssueList;
	}

	@Override
	public List<BookIssue> getAllDelayedBook() 
	{
		jpql = "FROM BookIssue b where b.bookStatus='ISSUE' and b.returnDate is b.dueDate<?1";
		entityTransaction.begin();
			bookIssueList=entityManager.createQuery(jpql).setParameter(1, LocalDate.now()).getResultList();
		entityTransaction.commit();
		return bookIssueList;
	}

	@Override
	public BookIssue getBookIssueByBookIssueId(Integer bookIssueId1) 
	{
		entityTransaction.begin();
			bookIssue=entityManager.find(BookIssue.class, bookIssueId1);
		entityTransaction.commit();
		return bookIssue;
	}

	@Override
	public List<BookIssue> getBookIssueDetailsOfstudent( Student student1) 
	{
		entityTransaction.begin();
			bookIssueList = entityManager.createQuery("From BookIssue b where b.student=?1")
				.setParameter(1, student1)
				.getResultList();
		entityTransaction.commit();
		return bookIssueList;
	}

	@Override
	public List<BookIssue> getAllReturnBookRequestOfAllStudent()
	{
		jpql = "FROM BookIssue b WHERE b.bookStatus='NOT ISSUE' and b.issueDate is not null and b.returnDate is null";
		entityTransaction.begin();
			bookIssueList=entityManager.createQuery(jpql).getResultList();
		entityTransaction.commit();
		return bookIssueList;
	}

	@Override
	public List<BookIssue> isStudentBookIssueRequestPresent(Student student1, Book book1) 
	{
		jpql = "FROM BookIssue b WHERE b.bookStatus='NOT ISSUE' and b.student=?1 and b.book=?2 and b.issueDate is null";
		entityTransaction.begin();
			bookIssueList=entityManager.createQuery(jpql)
				.setParameter(1, student1)
				.setParameter(2, book1)
				.getResultList();
		entityTransaction.commit();
		return bookIssueList;
	}

	@Override
	public List<BookIssue> isBookIssuedToStudent(Student student1, Book book1) 
	{
	//	System.out.println("hi");
		jpql = "FROM BookIssue b WHERE b.bookStatus='ISSUE' and b.student=?1 and b.book=?2 ";
		entityTransaction.begin();
		bookIssueList = entityManager.createQuery(jpql)
				.setParameter(1, student1)
				.setParameter(2, book1)
				.getResultList();
		entityTransaction.commit();
		return bookIssueList;
	}
	
	@Override
	public List<BookIssue> hasStudentReturnAllIssuedBookToLibrary(Student student1) 
	{
		entityTransaction.begin();
		bookIssueList = entityManager.createQuery("FROM BookIssue b WHERE b.bookStatus='ISSUE' and b.student=?1")
				.setParameter(1, student1)
				.getResultList();
		entityTransaction.commit();
		return bookIssueList;
	}

	public List<BookIssue> getIssuedBookOfstudent(Student student1) 
	{
//		System.out.println("get issued book of student");
		entityTransaction.begin();
			bookIssueList = entityManager.createQuery("FROM BookIssue b WHERE b.student=?1 and b.bookStatus='ISSUE'")
				.setParameter(1, student1)
				.getResultList();
			entityManager.flush();
		entityTransaction.commit();
		System.out.println("Accessing issued book");
		return bookIssueList;
	}
	
	@Override
	public String studentRequestIssueBook(BookIssue bookIssue1) 
	{
	//	BookIssue bookIssue = 
	//	System.out.println("Student request issue book");
		entityTransaction.begin();
			entityManager.persist(bookIssue1);
			entityManager.refresh(bookIssue1);
		entityTransaction.commit();
		return "Book issue request sended to the Admin";
	}

	@Override
	public String studentRequestReturnBook(BookIssue bookIssue1) 
	{
		String jpql = "UPDATE BookIssue b SET b.bookStatus='NOT ISSUE', b.fine = CASE WHEN ?1 < ?2 THEN 100 ELSE b.fine END WHERE b.issueId = ?3";
		entityTransaction.begin();
			entityManager.createQuery(jpql).setParameter(1, bookIssue1.getDueDate())
		        .setParameter(2, LocalDate.now())
		        .setParameter(3, bookIssue1.getIssueId())
		        .executeUpdate();
			entityManager.refresh(bookIssue1);
		entityTransaction.commit();
//		entityTransaction.begin();
//			entityManager.createQuery("UPDATE Student s SET s.studentTotalFine = CASE WHEN ?1 < ?2 THEN ?3 ELSE s.studentTotalFine END WHERE s.studentId = ?4")
//				.setParameter(1, bookIssue1.getDueDate())
//				.setParameter(2, LocalDate.now())
//				.setParameter(3, bookIssue1.getStudent().getStudentTotalFine()+100)
//				.setParameter(4, bookIssue1.getStudent().getStudentId())
//				.executeUpdate();
//		entityTransaction.commit();
		return "Return book request sended to the Admin";
	}

	@Override
	public List<BookIssue> getStudentFineDetail(Student student1) 
	{
		jpql = "From BookIssue b where b.student=?1 and fine>0";
		entityTransaction.begin();
			bookIssueList = entityManager.createQuery(jpql).setParameter(1, student1).getResultList();
		entityTransaction.commit();
		return bookIssueList;
	}

//	@Override
//	public Double getStudentBookIssueFineDetail(BookIssue bookIssue1) {
//		entityTransaction.begin();
//			entityManager.createQuery("SELECT b.fine WHERE b.issueId=?1").setParameter(0, bookIssue1)
//	}

}
/*
 * note
 * I see, it appears there may be a confusion in the way the JPA provider (like Hibernate) manages the entities and their state. 
 * The executeUpdate() method in JPA doesn't guarantee that the managed entities will be updated immediately within the same transaction.
 */
