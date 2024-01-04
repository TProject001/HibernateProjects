package com.library.basic.presentation;

import java.util.List;
import java.util.Scanner;

import com.library.basic.entity.Student;
import com.library.basic.service.BookIssueService;
import com.library.basic.service.BookIssueServiceImpl;
import com.library.basic.service.StudentService;
import com.library.basic.service.StudentServiceImpl;
import com.library.basic.validation.MyValidation;

public class StudentUserImpl implements StudentUser{
	private Scanner scanner = new Scanner(System.in);
	private MyValidation myValidation = new MyValidation();
	private StudentService studentService = new StudentServiceImpl();
	private BookUser bookUser = new BookUserImpl();
	private BookIssueService bookIssueService = new BookIssueServiceImpl();
	private List<Student> studentList;
	private Integer studentId;
	private String studentPassword,ch,studentName,studentEmail,studentContactNo,studentAddress,studentKiId;
	private static Student student;
	
	
	@Override
	public void registerStudent()
	{
		do 
		{
			System.out.println("\nName must contain alphabet and length should be in between 3 to 50");
			System.out.print("Enter Your Name : ");
			studentName =scanner.nextLine();
			
		}while(!myValidation.isNameValid(studentName.trim()));
		
		do 
		{
			System.out.println("\nPlease Enter valid Email Id");
			System.out.print("Enter Your Email Id : ");
			studentEmail=scanner.nextLine();
		
		}while(!myValidation.isEmailIdValid(studentEmail.trim()));
		
		do 
		{
			System.out.println("\nPlease Enter valid Indian Contact No.");
			System.out.print("Enter Your Contact Number : ");//three space
			studentContactNo=scanner.nextLine();
		
		}while(!myValidation.isIndianPhoneNumberValid(studentContactNo.trim()));
		
		do 
		{
			System.out.println("\nPlease Enter Valid Address");
			System.out.print("Enter Your Address : ");
			studentAddress=scanner.nextLine();
		
		}while(!myValidation.isAddressValid(studentAddress.trim()));
		
		do
		{
			System.out.println("\nPassword must contain at least one special character, one uppercase letter, one lowercase letter, one digit, and a length between 4 and 30 characters");
			System.out.print("Enter Your Password : ");
			studentPassword=scanner.nextLine();
		
		}while(!myValidation.isPasswordValid(studentPassword.trim()));
		
		student = new Student();
		student.setStudentName(studentName);
		student.setStudentEmail(studentEmail);
		student.setStudentContactNo(studentContactNo);
		student.setStudentAddress(studentAddress);
		student.setPassword(studentPassword);
		student.setAccoutStatus("ACTIVE");

		studentService.addStudent(student);
		System.out.println("\n----------Welcome "+student.getStudentName()+" ----------");
	}
	
	@Override
	public void deleteStudent() 
	{
		if(bookIssueService.hasStudentReturnAllIssuedBookToLibrary(student))
		{
			if(student.getStudentTotalFine()>0)
			{
				System.out.println("To delete your account you need to pay your all fine!");
				bookIssueService.payFine(student);
			}
				else
			{
				studentService.deleteStudent(student);
				student=null;
				ch="";
				MainApp.studentHomePage();
			}
		}
		else
		{
			System.out.println("To delete your account you need to return all your books!");
			bookIssueService.studentRequestReturnBook(student);
		}
	}
	
	@Override
	public Boolean studentLogin() 
	{
		int count=3;
		do
		{
			studentList = studentService.getAllActiveStudents();
			if(studentList!=null) 
			{
				if(!studentList.isEmpty()) 
				{	
					System.out.print("Enter UserId : ");
					studentKiId = scanner.nextLine();
					
					if(myValidation.isIdNumeric(studentKiId)) 
						studentId=Integer.parseInt(studentKiId);
					else 
						return false;
					
					System.out.print("Enter password: ");
					studentPassword = scanner.nextLine();
					student = studentService.getStudentById(studentId);
					
					if(student!=null) 
					{	
						if(studentPassword.equals(student.getPassword())) {
							{
								System.out.println("\n----------Welcome "+student.getStudentName()+" ----------");
								return true;
							}
						}
					}
	//				else System.out.println("Invalid Student Id");
				}
				else 
				{
					System.out.println("no one is present in student dataset");
					return false;
				}
			}
			else
			{
				System.out.println("Something went wrong");
				return false;
			}
			count--;
			System.out.println("Invalid Credential\n");
		
		}while(count>0);
		
		System.out.println("To Many trials ");
		return false;
	}
	
	@Override
	public void updatestudentDetails() 
	{
		if(student==null) student=studentService.getStudentById(studentId);
		while(true)
		{
			System.out.println("\n------------------------------");
			System.out.println("--------- Student Details Update Page ---------");
			System.out.println("------------------------------");
			System.out.println(" 1. Update My Name");
			System.out.println(" 2. Update My Email");
			System.out.println(" 3. Update My Contact No");
			System.out.println(" 4. Update My Address");
			System.out.println(" 5. Update My Password");
			System.out.println(" 6. Exit");
			System.out.print("\nEnter Your choice : ");
			ch = scanner.next();
			scanner.nextLine();
			
			switch(ch) 
			{
			case "1":
				System.out.println("Updating Student Name...........\n");
				System.out.print("Enter New Name: ");
				studentName =scanner.nextLine() ;
				if(studentName.equals(student.getStudentName())) 
					System.out.println("New name must be not same as old name");
				else 
				{
					if(myValidation.isNameValid(studentName.trim()))
						studentService.updateStudentName(student, studentName);
					else 
						System.out.println("Name must contain alphabet and length should be in between 3 to 50");
				}
				break;
			
			case "2":
				System.out.println("Updating My Email...........");
				System.out.print("Enter new Email Id :");
				studentEmail=scanner.nextLine();
				if(studentEmail.equals(student.getStudentEmail()))
					System.out.println("New email id must be not same as old email id");
				else 
				{
					if(myValidation.isEmailIdValid(studentEmail.trim()))
						studentService.updateStudentEmail(student, studentEmail);
					else
						System.out.println("Invalid Email Id");
				}
				break;
			
			case "3":
				System.out.println("Updating My Contact No...........");
				System.out.print("Enter new Contact Number:");//three space
				studentContactNo=scanner.nextLine();
				if(studentContactNo.equals(student.getStudentContactNo()))
					System.out.println("New contact number must be not same as old contact number");
				else 
				{
					if(myValidation.isIndianPhoneNumberValid(studentContactNo.trim()))
						studentService.updateStudentContact(student, studentContactNo);
					else
						System.out.println("Please Enter Indian Contact No.");
				}
				break;
			
			case "4":
				System.out.println("Updating My Address...........");
				System.out.print("Enter new Address  :");
				studentAddress=scanner.nextLine();
				if(studentAddress.equals(student.getStudentAddress()))
					System.out.println("New address must be not same as old address");
				else 
				{
					if(myValidation.isAddressValid(studentAddress.trim()))
						studentService.updateStudentAddress(student, studentAddress);
					else
						System.out.println("Please Enter Valid Address");
				}
				break;
			
			case "5":
				System.out.println("Updating My Password...........");
				System.out.print("Enter new Password :");
				studentPassword=scanner.nextLine();
				if(studentPassword.equals(student.getPassword()))
					System.out.println("New password must be not same as old password");
				else 
				{
					if(myValidation.isPasswordValid(studentPassword.trim()))
						studentService.updateStudentPassword(student, studentPassword);
					else
						System.out.println("Password must contain at least one special character, one uppercase letter, one lowercase letter, one digit, and a length between 4 and 30 characters");
				}
				break;
			
			case "6":
				System.out.println("Exit..........");
				break;
			
			default:
				System.out.println("Oops! Invalid Input");
			}	
			
			if(ch.compareTo("6")==0) break;
		}
	}

	@Override
	public void updatestudentPassword() 
	{
		System.out.println("Updating My Password...........");
		System.out.print("Enter new Password :");
		studentPassword=scanner.nextLine();
		if(studentPassword.equals(student.getPassword()))
			System.out.println("New password must be not same as old password");
		else 
		{
			if(myValidation.isPasswordValid(studentPassword.trim()))
				studentService.updateStudentPassword(student, studentPassword);
			else
				System.out.println("Password must contain at least one special character, one uppercase letter, one lowercase letter, one digit, and a length between 4 and 30 characters");
		}
	}

	@Override
	public void viewStudentProfile() 
	{
		if(student!=null) 
		{
			System.out.println("\n------------ My Details ------------");
			System.out.println("Student Id        :"+student.getStudentId());
			System.out.println("Student Name      : "+student.getStudentName());
			System.out.println("Student Email     : "+student.getStudentEmail());
			System.out.println("Student Contact No: "+student.getStudentContactNo());
			System.out.println("Student Address   : "+student.getStudentAddress());
			System.out.println("Student Password  : "+student.getPassword());
			System.out.println("Student fine      : "+student.getStudentTotalFine()+"\n");
		}
		else 
			System.out.println("Something went wrong!");
	}
	
	@Override
	public void viewFineDetails() 
	{
		bookIssueService.payFine(student);
	}

	// book
	
	@Override
	public void SuggestBook() 
	{
		bookUser.registerBook(null, student);
	}
	
	// bookIssue
	
	@Override
	public void requestForIssueBook() 
	{
		bookIssueService.studentRequestIssueBook(student);
	}

	@Override
	public void requestForreturnBook() 
	{
		bookIssueService.studentRequestReturnBook(student);
	}

	@Override
	public void viewIssuedBookOfStudent() 
	{
		bookIssueService.viewIssuedBookOfstudent(student);
	}
	
	@Override
	public void viewBookIssueDetailsOfstudent() 
	{
		bookIssueService.getBookIssueDetailsOfstudent(student);
	}

	
}
