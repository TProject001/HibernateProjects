package com.library.basic.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.library.basic.entity.Book;
import com.library.basic.entity.Student;

public class BookDaoImpl implements BookDao{
	private EntityManager entityManager = MyConnection.getEntityManagerObject();
	private EntityTransaction entityTransaction = entityManager.getTransaction();
//	private Query query;
	private List<Book> bookList;
	private Book book;
	
	@Override
	public Boolean addBook(Book book) 
	{
		entityTransaction.begin();
			entityManager.persist(book);
		entityTransaction.commit();
		return true;
	}

	@Override
	public Book getBookById(Integer bookId) 
	{
		entityTransaction.begin();
			book=entityManager.find(Book.class, bookId);
		entityTransaction.commit();
		return book;
	}

	@Override
	public List<Book> getBookByName(String bookName) 
	{
		entityTransaction.begin();
			bookList=entityManager.createQuery("FROM Book b WHERE b.bookName like ?1").setParameter(1,"%"+ bookName+"%").getResultList();
		entityTransaction.commit();
		return bookList;
	}

	@Override
	public List<Book> getBookByType(String bookType) 
	{ 
		entityTransaction.begin();
			bookList = entityManager.createQuery("FROM Book b WHERE b.bookType like ?1").setParameter(1, "%"+bookType+"%").getResultList();
		entityTransaction.commit();
		return bookList;
	}
	
	@Override
	public List<Book> getBookByauthorName(String authorName) 
	{
		entityTransaction.begin();
			bookList = entityManager.createQuery("FROM Book b WHERE b.authorName like ?1").setParameter(1, "%"+authorName+"%").getResultList();
		entityTransaction.commit();
		return bookList;
	}

	@Override
	public List<Book> getBookByPublisher(String bookPublisher) 
	{
		entityTransaction.begin();
			bookList = entityManager.createQuery("FROM Book b WHERE b.bookPublisher like ?1").setParameter(1, "%"+bookPublisher+"%").getResultList();
		entityTransaction.commit();
		return bookList;
	}
	
	@Override
	public Boolean updateStock(Book book1,Integer newQuantity) 
	{
		entityTransaction.begin();
			book1.setQuantity(book1.getQuantity()+newQuantity);
		entityTransaction.commit();
		return true;
	}
	
	public List<Book> getAllBooks() 
	{
		entityTransaction.begin();
			bookList = entityManager.createQuery("From Book b").getResultList();
		entityTransaction.commit();
		return bookList;
	}

	@Override
	public List<Book> getAllBookForIssue()
	{
		entityTransaction.begin();
			bookList = entityManager.createQuery("FROM Book b where b.quantity>0 and b.bookArrived='ARRIVED'").getResultList();
		entityTransaction.commit();
		return bookList;
		
	}

	// problem = it gives all book along with suggested book
	@Override
	public List<Book> getAllStudentSuggestedBook() 
	{
		entityTransaction.begin();
			bookList = entityManager.createQuery("FROM Book b WHERE b.bookArrived='NOT ARRIVED'").getResultList();
		entityTransaction.commit();
		return bookList;
	}

	@Override
	public Boolean setBookArrived(Book book) 
	{
		entityTransaction.begin();
			entityManager.createQuery("UPDATE Book b SET b.bookArrived='ARRIVED',b.quantity=?2 WHERE b.bookId=?1").setParameter(1, book.getBookId()).setParameter(2,book.getQuantity()).executeUpdate();
		entityTransaction.commit();
		return true;
	}
	
	@Override
	public Boolean setStudentIdOfSuggestedBookNull(Student student) 
	{
		entityManager.getTransaction().begin();
			entityManager.createQuery("UPDATE Book b SET b.requestedStudent NULL WHERE b.requestedStudent=?1").setParameter(1, student).executeUpdate();
		entityManager.getTransaction().commit();
		return true;
	}

	@Override
	public List<Book> getAllArrivedBook() 
	{
		entityTransaction.begin();
			bookList = entityManager.createQuery("FROM Book b WHERE b.bookArrived='ARRIVED'").getResultList();
		entityTransaction.commit();
		return bookList;
	}

//	@Override
//	public Boolean SetRequestedStudent(Student student) 
//	{
//		entityTransaction.begin();
//			entityManager.createQuery("UPDATE Book b SET b.requestedStudent is null WHERE b.requestedStudent=?1").setParameter(1, student).executeUpdate();
//		entityTransaction.commit();
//		return true;
//	}


}
