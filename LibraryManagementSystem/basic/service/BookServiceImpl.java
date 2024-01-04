package com.library.basic.service;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.library.basic.Dao.BookDao;
import com.library.basic.Dao.BookDaoImpl;
import com.library.basic.entity.Book;
import com.library.basic.validation.MyValidation;

public class BookServiceImpl implements BookService{
	private static Scanner scanner = new Scanner(System.in);
	private BookDao bookDao = new BookDaoImpl();
	private MyValidation myValidation = new MyValidation();
	private List<Book> bookList;
	private String ch,bookKiId,bookKiQuantity;
	private Integer bookQuantity,bookId;
	private Book book;
//	private Boolean flag = false;
	
	@Override
	public Boolean addBook(Book book) {
		return bookDao.addBook(book);
	}
	@Override
	public void updateStock(){
		bookList = bookDao.getAllArrivedBook() ;
		do {
			if(bookList==null)
			{
				System.out.println("Something went wrong");
				break;
			}
			else if(bookList.isEmpty())
			{
				System.out.println("Sorry! Books are not available in stock");
				break;
			}
			showAllBooks(bookList);
			System.out.print("\nEnter book Id : ");
			bookKiId = scanner.nextLine();
			if(myValidation.isIdNumeric(bookKiId))
				bookId = Integer.parseInt(bookKiId);
			else
			{
				System.out.println("Please Enter Valid Book Id !");
				break;
			}
			Book book = bookDao.getBookById(bookId);
			if(book==null) {
				System.out.println("Please Enter Valid Book Id!");
			}
			else{
				System.out.print("Enter book quantity: ");
				bookKiQuantity = scanner.nextLine();
				if(myValidation.isIdNumeric(bookKiQuantity))
					bookQuantity = Integer.parseInt(bookKiQuantity);
				else
				{
					System.out.println("Please Enter Valid Book Quantity !");
					break;
				}
				if(bookDao.updateStock(book,bookQuantity))
					System.out.println("Book Stock Updated!");
				while(true) {
					System.out.println("\nAre you interested to update more book stock?(yes/no) :");
					ch =scanner.nextLine();
					if(ch.equalsIgnoreCase("no")) break;
					else if(!(ch.equalsIgnoreCase("yes"))) {
						System.out.println("!Invalid Input");
					}
				}
				if(ch.equalsIgnoreCase("no")) break;
			}
		}while(true);
	}
	@Override
	public void getAllStudentSuggestedBook() 
	{
		do
		{
			bookList= bookDao.getAllStudentSuggestedBook();
			if(bookList==null)
			{
				System.out.println("Something went wrong");
				break;
			}
			else if(bookList.isEmpty())
			{
				System.out.println("\nSorry! Books are not available in stock");
				break;
			}	
			showAllBooks(bookList);
			System.out.print("\nIs Suggested book arrived ? (yes/no) : ");
			ch = scanner.nextLine();
			if(ch.equalsIgnoreCase("yes"))
			{
				do {
					System.out.print("Please Enter Book Id : ");
					bookKiId = scanner.nextLine();
					if(myValidation.isIdNumeric(bookKiId))
					{
						bookId = Integer.parseInt(bookKiId);
						book = bookDao.getBookById(bookId);
						if(book!=null && bookList.contains(book)) 
						{
							System.out.print("Enter book quantity: ");
							bookKiQuantity = scanner.nextLine();
							if(myValidation.isIdNumeric(bookKiQuantity))
							{
								bookQuantity = Integer.parseInt(bookKiQuantity);
								book.setQuantity(bookQuantity);
					
								if(bookDao.setBookArrived(book)) 
								{
									System.out.println("Suggested book added to the stock");		
									System.out.println("Are you interested to update more book stock?(yes/no) :");
									ch =scanner.nextLine();
									if(ch.equalsIgnoreCase("no")) break;
									else if(!(ch.equalsIgnoreCase("yes"))) {
										System.out.println("!Invalid Input");
									}
									else
									{
										System.out.println("Something went wrong!");
										break;
									}
								}
								else 
									System.out.println("Something wrong with db at the time of setting book arrival");
							}
							else
								System.out.println("Please Enter Valid Book Quantity !");
						}
					}
					else
						System.out.println("Please Enter valid Book Id!");
				
				}while(true);
			}
			else if(ch.equalsIgnoreCase("no")) break;
			else System.out.println("Please Enter yes or no !");
		}while(true);
	}
//	@Override
//	public Book getBookById(Integer bookId) {
//		return bookDao.getBookById(bookId);
//	}

	@Override
	public void getBookByName(String bookName1) {
		bookList = bookDao.getBookByName(bookName1);
		if(bookList==null) System.out.println("No book available in stock name as "+bookName1);
		else BookServiceImpl.showAllBooks(bookList);
	}

	@Override
	public void getBookByType(String bookType1) {
		bookList = bookDao.getBookByType(bookType1);
		if(bookList==null) System.out.println("No book available in stock book type as "+bookType1+" book");
		else BookServiceImpl.showAllBooks(bookList);
	}

	@Override
	public void getBookByAuthor(String authorName1) {
		bookList = bookDao.getBookByauthorName(authorName1);
		if(bookList==null)System.out.println("No book available in stock author name as "+authorName1);
		else BookServiceImpl.showAllBooks(bookList);
	}

	@Override
	public void getBookByPublication(String bookPublisher1) {
		bookList = bookDao.getBookByPublisher(bookPublisher1);
		if(bookList==null)System.out.println("No book available in stock publication name as "+bookPublisher1);
		BookServiceImpl.showAllBooks(bookList);
	}

	@Override
	public List<Book> getAllBooks() {
		return bookDao.getAllBooks();
	//	if(bookList==null) System.out.println("No Book is available in stock");
	//	else BookServiceImpl.showAllBooks();
	}
	
	public static void showAllBooks(List<Book> bookList) {
	//	bookList = bookDao.getAllBooks();
		if(bookList!=null) {
			if(!bookList.isEmpty()) {
				Iterator<Book> itr = bookList.iterator();
				System.out.printf("\n\n| %-7s | %-35s | %-40s | %-25s | %-8s | %-11s |","Book Id","Book Name","Author Name","Book Type","quantity","arrived");
				//System.out.println("\n\nBook Id | Book Name\tAuthor Name\t Book Type\tquantity | arrived"); 
				while(itr.hasNext()) {
					Book book = itr.next();
					System.out.printf("\n| %-7s | %-35s | %-40s | %-25s | %-8s | %-11s |", book.getBookId()+"",book.getBookName()+"",book.getAuthorName()+"",book.getBookType()+"",book.getQuantity()+"",book.getBookArrived()+"");
				//	System.out.println(book.getBookId()+"   "+book.getBookName()+"\t"+book.getAuthorName()+"\t"+book.getBookType()+book.getQuantity());//+"   "+book.getBookArrived
				}
			}
			else System.out.println("Sorry! Books are not available in stock");
		}
		else System.out.println("Something went wrong");
	}
	
	
}
