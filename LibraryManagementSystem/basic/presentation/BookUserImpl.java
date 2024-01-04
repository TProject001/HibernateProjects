package com.library.basic.presentation;

import java.util.List;
import java.util.Scanner;

import com.library.basic.entity.Admin;
import com.library.basic.entity.Book;
import com.library.basic.entity.Student;
import com.library.basic.service.BookService;
import com.library.basic.service.BookServiceImpl;
import com.library.basic.validation.MyValidation;

public class BookUserImpl implements BookUser{
	private static Scanner scanner = new Scanner(System.in);
	private static BookService bookService = new BookServiceImpl();
	private static String ch;
	private List<Book> bookList;
	private MyValidation myValidation = new MyValidation();
	private String bookName,authorName,bookPublisher,bookType,bookKiQuantity;
	private Integer bookQuantity;
	private Boolean bookRegister=false;

	@Override
	public void registerBook(Admin admin1,Student student1) {
		do {
			System.out.print("\nPlease enter valid Book Name           : ");
			bookName =scanner.nextLine() ;
		}while(!myValidation.isBookValid(bookName));
		do {
			System.out.print("\nPlease enter valid author Name         : ");
			authorName =scanner.nextLine();
		}while(!myValidation.isBookValid(authorName));
		do {
			System.out.print("\nPlease enter valid publication Name    : ");
			bookPublisher =scanner.nextLine();
		}while(!myValidation.isBookValid(bookPublisher));
		do {
			System.out.print("\nPlease enter valid book Type           : ");
			bookType =scanner.nextLine();
		}while(!myValidation.isBookValid(bookType));
		
		if(student1==null)
		{	
			do {
				System.out.print("\nPlease enter valid book quantity       : ");
				bookKiQuantity =scanner.nextLine();
			}while(!myValidation.isIdNumeric(bookKiQuantity));
			bookQuantity = Integer.parseInt(bookKiQuantity);
		}
		Book book = new Book();
		book.setBookName(bookName);
		book.setBookType(bookType);
		book.setAuthorName(authorName);
		book.setBookPublisher(bookPublisher);
		if(student1==null)
		{
			book.setQuantity(bookQuantity);
			book.setBookArrived("ARRIVED");
		}
		else
			book.setRequestedStudent(student1);
		bookRegister = bookService.addBook(book);
		if(bookRegister)
		{
			if(student1!=null)
				System.out.println("\nThanks for Suggesting Book!");
			else
				System.out.println("\nBook Registered!");
		}
	}
	
	@Override
	public void updateBookStock() {
		bookService.updateStock();
	}
	
	@Override
	public void searchBook() {
		while(true){
			bookList = bookService.getAllBooks();
			if(bookList==null) 
			{
				System.out.println("Something went wrong!");
				break;
			}
			else if(bookList.isEmpty()) 
			{
				System.out.println("Book is not available in stock");
				break;
			}
			System.out.println("\n------------------------------");
			System.out.println("--------- Book Search Page ---------");
			System.out.println("------------------------------");
			System.out.println(" 1. Search Book By Name");
			System.out.println(" 2. Search Book By Author Name");
			System.out.println(" 3. Search Book By Publication");
			System.out.println(" 4. Search Book By Type");
			System.out.println(" 5. Exit");
			System.out.print("\nEnter Your choice : ");
			ch = scanner.next();
			scanner.nextLine();
			switch(ch) {
			case "1":
				System.out.println("Searching Book By Name...........");
				System.out.print("Enter Book Name: ");
				bookName =scanner.nextLine() ;
				bookService.getBookByName(bookName);
				break;
			case "2":
				System.out.println("Searching Book By Author Name...........");
				System.out.print("Enter Author Name: ");
				authorName =scanner.nextLine() ;
				bookService.getBookByAuthor(authorName);
				break;
			case "3":
				System.out.println("Searching Book By Publication...........");
				System.out.print("Enter Publication Name: ");
				bookPublisher =scanner.nextLine() ;
				bookService.getBookByPublication(bookPublisher);
				break;
			case "4":
				System.out.println("Searching Book By Type...........");
				System.out.print("Enter Book Type: ");
				bookType =scanner.nextLine() ;
				bookService.getBookByType(bookType);
				break;
			case "5":
				System.out.println("Exit..........");
				break;
			default:
				System.out.println("Oops! Invalid Input");
			}
			if(ch.compareTo("5")==0) break;
		}
	}
	@Override
	public void getAllBook() {
		bookList = bookService.getAllBooks();
		BookServiceImpl.showAllBooks(bookList);
	}
}
