package com.library.basic.service;
 // complete the methdo
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.hibernate.internal.build.AllowSysOut;

import com.library.basic.Dao.BookDao;
import com.library.basic.Dao.BookDaoImpl;
import com.library.basic.Dao.BookIssueDao;
import com.library.basic.Dao.BookIssueDaoImpl;
import com.library.basic.Dao.StudentDao;
import com.library.basic.Dao.StudentDaoImpl;
import com.library.basic.entity.Admin;
import com.library.basic.entity.Book;
import com.library.basic.entity.BookIssue;
import com.library.basic.entity.Student;
import com.library.basic.validation.MyValidation;

public class BookIssueServiceImpl implements BookIssueService{
	private BookIssueDao bookIssueDao = new BookIssueDaoImpl();
	private StudentDao studentDao = new StudentDaoImpl();
	private BookDao bookDao = new BookDaoImpl();
	private MyValidation myValidation = new MyValidation();
	private List<Book> bookList;
	private List<BookIssue> bookIssueList;
	private static String ch,BIReturnBookAdminId =null,BIBookIssueAdminId =null,BIStudentId=null,BIBookId=null;
	private String bookIssueKiId,bookKiId,fineKiAmt;
	private BookIssue bookIssue;
	private Book book;
	private Integer bookIssueId,bookId;
	private Double fineAmt; //,studentTotalFine
	private static Scanner scanner = new Scanner(System.in);
	
	@Override
	public void getAllReturnBookRequestOfAllStudent(Admin admin1) 
	{
		do {
			bookIssueList = bookIssueDao.getAllReturnBookRequestOfAllStudent();
			if(bookIssueList==null)
			{
				System.out.println("Something went wrong!");
				break;
			}
			else if(bookIssueList.isEmpty())
			{
				System.out.println("Sorry! No Book return request is available");
				break;
			}
			BookIssueServiceImpl.showAllBookIssue(bookIssueList);
			System.out.print("\nAre you interested to approved student request? (yes/no) : ");
			ch =scanner.nextLine();
			if(ch.equalsIgnoreCase("no")) break;
			else if(ch.equalsIgnoreCase("yes")) {
				System.out.println("\nEnter book Issue Id : ");
				bookIssueKiId = scanner.nextLine();
				if(myValidation.isIdNumeric(bookIssueKiId))
				{
					bookIssueId = Integer.parseInt(bookIssueKiId);
					bookIssue = bookIssueDao.getBookIssueByBookIssueId(bookIssueId);
					if(bookIssue==null) {
						System.out.println("Please Enter Valid Book Issue Id !");
					}
					else {
						if(bookIssueList.contains(bookIssue)) {
							// no need to check book Quantity
							System.out.println(bookIssueDao.adminConfirmReturnBook(bookIssue, admin1));
							bookDao.updateStock(bookIssue.getBook(),1);
						}
						else System.out.println("Please Enter Valid Book Issue Id !");
					}
				}
				else
				{
					System.out.println("Please Enter Valid Book Issue Id !");
				}
			}
			else System.out.println("!Invalid Input");
		}while(true);	
	}

	@Override
	public void getAllBookIssueRequestOfAllStudent(Admin admin1) 
	{
		do {
			bookIssueList = bookIssueDao.getAllBookIssueRequestOfAllStudent();
			if(bookIssueList==null)
			{
				System.out.println("Something went wrong!");
				break;
			}
			else if(bookIssueList.isEmpty())
			{
				System.out.println("Sorry! No Book issue request is available");
				break;
			}
			BookIssueServiceImpl.showAllBookIssue(bookIssueList);
			System.out.print("\nAre you interested to approved student request? (yes/no) : ");
			ch =scanner.nextLine();
			if(ch.equalsIgnoreCase("no")) break;
			else if(ch.equalsIgnoreCase("yes")) {
				System.out.println("\nEnter book Issue Id : ");
				bookIssueKiId = scanner.nextLine();
				if(myValidation.isIdNumeric(bookIssueKiId))
				{
					bookIssueId = Integer.parseInt(bookIssueKiId);
					bookIssue = bookIssueDao.getBookIssueByBookIssueId(bookIssueId);
					if(bookIssue==null) {
						System.out.println("Please Enter Valid Book Issue Id !");
					}
					else {
						if(bookIssueList.contains(bookIssue)) {
						
							if(bookIssue.getBook().getQuantity()==0) {
								System.out.println("Book Out Of Stock!");
							}
							else {
								if(bookDao.updateStock(bookIssue.getBook(),-1))
									System.out.println(bookIssueDao.adminConfirmIssueBook(bookIssue, admin1));						
							}
						}
						else System.out.println("Please Enter Valid Book Issue Id !");
					}
				}
				else
				{
					System.out.println("Please Enter Valid Book Issue Id !");
				}
			}
			else System.out.println("!Invalid Input");
		}while(true);	 
	}
	
	@Override
	public void getAllBookIssue() 
	{
		bookIssueList = bookIssueDao.getAllBookIssue();
		BookIssueServiceImpl.showAllBookIssue(bookIssueList);
	}

	@Override
	public void getAllIssuedBook() 
	{
		bookIssueList = bookIssueDao.getAllIssuedBook();
		if(bookIssueList==null)
		{
			System.out.println("Something went wrong");
		}
		else if(bookIssueList.isEmpty())
		{
			System.out.println("Sorry! No Issued book is available");
		}
		else BookIssueServiceImpl.showAllBookIssue(bookIssueList);
	}

	@Override
	public void getAllDelayedBook() 
	{
		bookIssueList = bookIssueDao.getAllDelayedBook();
		if(bookIssueList==null)
		{
			System.out.println("Something went wrong");
		}
		else if(bookIssueList.isEmpty())
		{
			System.out.println("Sorry! No Delayed book is available");
		}
		else BookIssueServiceImpl.showAllBookIssue(bookIssueList);
	}

	// student 
	
	@Override
	public void getBookIssueDetailsOfstudent( Student student1) 
	{
		bookIssueList = bookIssueDao.getBookIssueDetailsOfstudent(student1);
		BookIssueServiceImpl.showAllBookIssue(bookIssueList);
	}
	
	@Override
	public void studentRequestIssueBook(Student student1) 
	{
		do {
			if(student1==null) {
				System.out.println("Something went wrong");
				break;
			}
			bookList = bookDao.getAllBookForIssue();
			if(bookList==null)
			{
				System.out.println("something went wrong");
				break;
			}
			else if(bookList.isEmpty())
			{
				System.out.println("No book is available");
				break;
			}	
			BookServiceImpl.showAllBooks(bookList);
			System.out.print("\nDo you want to issue book?(Yes/no) : ");
			ch = scanner.nextLine();
			if(ch.equalsIgnoreCase("no")) break;
			else if(!ch.equalsIgnoreCase("yes")) {
				System.out.println("Please Enter valid Input !");
			}
			else{
				System.out.print("Enter Book Id : ");
				bookKiId = scanner.nextLine();
				if(myValidation.isIdNumeric(bookKiId))
					bookId = Integer.parseInt(bookKiId);
				else
				{
					System.out.println("Please Enter Valid Book Id !");
					break;
				}
				book = bookDao.getBookById(bookId);
				if(book==null) System.out.println("Please Enter Valid Book Id");
				else {
					bookIssueList = bookIssueDao.isStudentBookIssueRequestPresent(student1, book);
					if(bookIssueList==null) 
						System.out.println("Something went wrong");
					else if(bookIssueList.isEmpty())
					{
						bookIssueList = bookIssueDao.isBookIssuedToStudent(student1,book) ;
						if(bookIssueList==null) 
							System.out.println("Something went wrong");
						else if(bookIssueList.isEmpty())
						{
							System.out.println("bookIssueList : "+bookIssueList);
							bookIssue = new BookIssue();
							bookIssue.setBook(book);
							bookIssue.setStudent(student1);
							System.out.println(bookIssueDao.studentRequestIssueBook(bookIssue));
						}
						else 
							System.out.println("Book is already Issue to you!");
					}
					else 
						System.out.println("Book Issue Request is already present!");
				}
			}
		}while(true);		
	}
	
	@Override
	public void studentRequestReturnBook(Student student1) 
	{
		do {
			bookIssueList = bookIssueDao.getIssuedBookOfstudent(student1);
			if(bookIssueList==null)
			{
				System.out.println("Something went wrong");
				break;
			}
			else if(bookIssueList.isEmpty())
			{
				System.out.println("Sorry! Number of book issue is zero");
				break;
			}
			BookIssueServiceImpl.showBookIssuedToStudent(bookIssueList);
	//		BookIssueServiceImpl.showAllBookIssue();
			System.out.print("\nDo you want to return book?(Yes/no) : ");
			ch = scanner.nextLine();
			if(ch.equalsIgnoreCase("no")) break;
			else if(ch.equalsIgnoreCase("yes")) {
				System.out.print("Enter Book Issue Id : ");
				bookIssueKiId = scanner.nextLine();
				if(!myValidation.isIdNumeric(bookIssueKiId)) {
					System.out.println("Please Enter Valid Book Issue Id !");
				}
				else
				{
					bookIssueId = Integer.parseInt(bookIssueKiId);	
					bookIssue = bookIssueDao.getBookIssueByBookIssueId(bookIssueId);
					if(bookIssue==null) System.out.println("Please Enter Valid BookIssue Id! ");
					else {
						if(bookIssueList.contains(bookIssue))
						{
							System.out.println(bookIssueDao.studentRequestReturnBook(bookIssue));
							System.out.println("fine amt = " +bookIssue.getFine());
							// update student fine
							studentDao.updateStudentTotalFine(student1, student1.getStudentTotalFine()+bookIssue.getFine());
						//	BookIssue bookIssue1 = bookIssueDao.getBookIssueByBookIssueId(bookIssueId);
						//	System.out.println("BookIssue fine : "+bookIssue1.getFine());
						//	student1.setStudentTotalFine(student1.getStudentTotalFine()+bookIssue1.getFine());
						//	System.out.println("Student Total fine : "+student1.getStudentTotalFine());
						}
						else System.out.println("Please Enter Valid BookIssue Id!");
					}
				}
			}
			else System.out.println("Enter valid Input!");
		}while(true);
	}
	
	@Override
	public void viewIssuedBookOfstudent(Student student1) 
	{
		bookIssueList = bookIssueDao.getIssuedBookOfstudent(student1);
		BookIssueServiceImpl.showBookIssuedToStudent(bookIssueList);	
	}
	
	@Override
	public void payFine(Student student1) 
	{
		bookIssueList = bookIssueDao.getStudentFineDetail(student1);
		if(bookIssueList==null) 
		{
			System.out.println("Something went wrong");
		}
		else if(bookIssueList.isEmpty())
		{
			System.out.println("Student fine is null");
		}
		else
		{	
			BookIssueServiceImpl.showBookIssuedToStudent(bookIssueList);
			Double studentTotalFine = student1.getStudentTotalFine();
			System.out.println("Your Total fine is "+studentTotalFine);
			
			if(studentTotalFine>0)
			{
				do {
					System.out.print("\nWould you like to pay your fine ? (yes/no) : ");
					ch = scanner.nextLine();
					if(ch.equalsIgnoreCase("yes"))
					{
						do {
							System.out.print("Please Enter valid amount : ");
							fineKiAmt = scanner.nextLine();
							if(myValidation.isIdNumeric(fineKiAmt))
							{
								fineAmt = Double.parseDouble(fineKiAmt);
								if(fineAmt>0 && fineAmt<=studentTotalFine) 
								{
									studentTotalFine -= fineAmt;
									if(studentDao.updateStudentTotalFine(student1,studentTotalFine))
									{
										System.out.println("Now Your fine is "+studentTotalFine);
										break;
									}
								}
							}
							System.out.println("Please Enter valid Amount!");
						}while(true);
					}
					else if(ch.equalsIgnoreCase("no")) break;
					else System.out.println("Please Enter yes or no !");
			
				}while(student1.getStudentTotalFine()>0);
			}
			else 
				System.out.println("Student fine is null");
		}	
	}
	@Override
	public Boolean hasStudentReturnAllIssuedBookToLibrary(Student student) 
	{
		bookIssueList = bookIssueDao.hasStudentReturnAllIssuedBookToLibrary(student);
		if(bookIssueList==null)
		{
			System.out.println("something went wrong");
		}
		else if(bookIssueList.isEmpty())
		{
			return true;
		}
		return false;
	}
	
    //	static method
	public static void showAllBookIssue(List<BookIssue> bookIssueList1) {
		if(bookIssueList1!=null) {
			if(!bookIssueList1.isEmpty()) {
				Iterator<BookIssue> itr = bookIssueList1.iterator();
				System.out.printf("\n\n| %-8s | %-9s | %-8s | %-11s | %-11s | %-12s | %-10s | %-13s | %-13s | %-5s |","Issue Id","StudentId","Book Id","IssueStatus","IssueDate","IssueAdminId","ReturnDate","ReturnAdminId","dueDate","fine"); 
			//	System.out.println("\n\nBook Issue Id | Student Id | Book Id | Book Issue Status | Book Issue Date | Book Issue Admin Id | Book Return Date | Book Return Admin Id | due Date | fine |"); 
				while(itr.hasNext()) {
					BookIssue bookIssue = itr.next();
					Admin issueBookAdmin = bookIssue.getBookIssueAdmin();
					if(issueBookAdmin!=null) BIBookIssueAdminId = issueBookAdmin.getAdminId()+"";
					Admin returnBookAdmin = bookIssue.getBookReturnAdmin();
					if(returnBookAdmin!=null) BIReturnBookAdminId = returnBookAdmin.getAdminId()+"";
					Student student = bookIssue.getStudent();
					if(student!=null) BIStudentId=student.getStudentId()+"";
					Book book = bookIssue.getBook();
					if(book!=null) BIBookId = book.getBookId()+"";
					System.out.printf("\n| %-8s | %-9s | %-8s | %-11s | %-11s | %-12s | %-10s | %-13s | %-13s | %-5s |",bookIssue.getIssueId()+"",BIStudentId,BIBookId,bookIssue.getBookStatus()+"",bookIssue.getIssueDate()+"",BIBookIssueAdminId,bookIssue.getReturnDate()+"",BIReturnBookAdminId,bookIssue.getDueDate()+"",bookIssue.getFine()+"");
			//		System.out.println(bookIssue.getIssueId()+"\t\t"+BIStudentId+"\t    "+BIBookId+"\t\t"+bookIssue.getBookStatus()+"\t\t"+bookIssue.getIssueDate()+"\t\t"+BIBookIssueAdminId+"\t\t\t"+bookIssue.getReturnDate()+"\t\t"+BIReturnBookAdminId+"\t\t\t"+bookIssue.getDueDate()+"\t"+bookIssue.getFine());
				}
			}
			else System.out.println("Sorry! No Book Issue is available");
		}
		else System.out.println("Something went wrong");
	}

	public static void showBookIssuedToStudent(List<BookIssue> bookIssueList1) {
//		if(bookIssueList==null) bookIssueList = BookIssueDaoImpl.getBookIssueTostudent(student1);
		if(bookIssueList1!=null) {
			if(!bookIssueList1.isEmpty()) {
				Iterator<BookIssue> itr = bookIssueList1.iterator();
				System.out.printf("\n\n| %-8s | %-11s | %-11s | %-11s | %-8s | %-30s |","Issue Id","Issue Date","Due Date","Return Date","Fine","Book Name");
		//		System.out.println("\n\nBook Issue Id | Book Issue Date | Book Due Date | Book Return Date | Fine |Book Name "); 
				while(itr.hasNext()) {
					BookIssue bookIssue1 = itr.next();
					System.out.printf("\n| %-8s | %-11s | %-11s | %-11s | %-8s | %-30s |",bookIssue1.getIssueId()+"",bookIssue1.getIssueDate()+"",bookIssue1.getDueDate()+"",bookIssue1.getReturnDate()+"",bookIssue1.getFine()+"",bookIssue1.getBook().getBookName());
			//		System.out.println(bookIssue1.getIssueId()+"\t\t"+bookIssue1.getIssueDate()+"\t    "+bookIssue1.getDueDate()+"\t    "+bookIssue1.getReturnDate()+"\t    "+bookIssue1.getFine()+"  "+bookIssue1.getBook().getBookName());
				}
			}
			else System.out.println("Sorry! No Book Issue is available");
		}
		else System.out.println("Something went wrong");
	}
}
