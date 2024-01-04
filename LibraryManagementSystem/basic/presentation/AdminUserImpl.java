package com.library.basic.presentation;

import java.util.List;
import java.util.Scanner;

import javax.persistence.Column;

import com.library.basic.entity.Admin;
import com.library.basic.service.AdminService;
import com.library.basic.service.AdminServiceImpl;
import com.library.basic.service.BookIssueService;
import com.library.basic.service.BookIssueServiceImpl;
import com.library.basic.service.BookService;
import com.library.basic.service.BookServiceImpl;
import com.library.basic.service.StudentService;
import com.library.basic.service.StudentServiceImpl;
import com.library.basic.validation.MyValidation;

public class AdminUserImpl implements AdminUser{
	private static Scanner scanner = new Scanner(System.in);
	private AdminService adminService = new AdminServiceImpl();
	private BookIssueService bookIssueService = new BookIssueServiceImpl();
	private BookService bookService = new BookServiceImpl();
	private StudentService studentService = new StudentServiceImpl();
	private BookUser bookUser = new BookUserImpl();
	private static Admin admin;
	private Integer adminId;
	private MyValidation myValidation = new MyValidation();
	private String adminPassword,ch,adminName,adminEmail,adminContactNo,adminAddress,adminKiId;
	
	private List<Admin> adminList;

	@Override
	public void registerAdmin() 
	{
		do{
			System.out.println("\nName must contain alphabet and length should be in between 3 to 50");
			System.out.print("Enter Your Name :");
			adminName =scanner.nextLine();
	//		System.out.println(myValidation.isNameValid(adminName.trim()));
		}while(!myValidation.isNameValid(adminName.trim()));
		
		do {
			System.out.println("\nPlease enter valid Email Id");
			System.out.print("Enter Your Email Id :");
			adminEmail=scanner.nextLine();
		}while(!myValidation.isEmailIdValid(adminEmail.trim()));
		do {
			System.out.println("\nPlease Enter valid Indian Contact No.");
			System.out.print("Enter Your Contact Number :");//three space
			adminContactNo=scanner.nextLine();
		}while(!myValidation.isIndianPhoneNumberValid(adminContactNo.trim()));
		
		do {
			System.out.println("\nPlease Enter Valid Address");
			System.out.print("Enter Your Address  :");
			adminAddress=scanner.nextLine();
		}while(!myValidation.isAddressValid(adminAddress.trim()));
		
		do{
			System.out.println("\nPassword must contain at least one special character, one uppercase letter, one lowercase letter, one digit, and a length between 4 and 30 characters");
			System.out.print("Enter Your Password :");
			adminPassword=scanner.nextLine();
		}while(!myValidation.isPasswordValid(adminPassword.trim()));
		
		admin = new Admin();
		admin.setAdminName(adminName);
		admin.setAdminEmail(adminEmail);
		admin.setAdminContactNo(adminContactNo);
		admin.setAdminAddress(adminAddress);
		admin.setAdminPassword(adminPassword);
		admin.setAccoutStatus("ACTIVE");
			
		adminService.addAdmin(admin);
	}

	@Override
	public void deleteAdmin() 
	{
		adminService.deleteAdmin(admin);
		admin=null;
		MainApp.adminHomePage();	
	}
	
	@Override
	public Boolean adminLogin() 
	{
		int count=3;
		do{
			adminList = adminService.getAllAdmin();
			if(adminList!=null) 
			{
				if(!adminList.isEmpty()) 
				{
					System.out.print("Enter UserId : ");
					adminKiId = scanner.nextLine();
					if(myValidation.isIdNumeric(adminKiId)) adminId=Integer.parseInt(adminKiId);
					else return false;
					
					System.out.print("Enter password: ");
					adminPassword = scanner.nextLine();
					admin = adminService.getAdminById(adminId);
					if(admin!=null) {
						if(adminPassword.equals(admin.getAdminPassword())) {
							System.out.println("\n----------Welcome "+admin.getAdminName()+" ----------");
							return true;
						}
					}
					else System.out.println("Invalid Admin Id");
				}
				else {
					System.out.println("no one is present in admin dataset");
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
	public void updateAdminDetails() 
	{
		while(true)
		{
			System.out.println("\n------------------------------");
			System.out.println("--Admin Details Update Page --");
			System.out.println("------------------------------");
			System.out.println(" 1. Update My Name");
			System.out.println(" 2. Update My Email");
			System.out.println(" 3. Update My Contact Number");
			System.out.println(" 4. Update My Address");
			System.out.println(" 5. Update My Password");
			System.out.println(" 6. Exit");
			
			System.out.print("\nEnter Your choice : ");
			ch = scanner.next();
			scanner.nextLine();
			
			switch(ch) 
			{
			case "1":
				System.out.println("Updating Admin Name...........");
				System.out.print("Enter New Name: ");
				adminName =scanner.nextLine() ;
				if(adminName.equals(admin.getAdminName())) 
				{
					System.out.println("New name must be not same as old name");
				}
				else 
				{
					if(myValidation.isNameValid(adminName.trim()))
						adminService.updateAdminName(admin, adminName.trim());
					else 
						System.out.println("Admin Name must contain alphabet and length should be in between 3 to 50");
				}
				break;
			
			case "2":
				System.out.println("Updating My Email...........");
				System.out.print("Enter new Email Id :");
				adminEmail=scanner.nextLine();
				
				if(adminEmail.equals(admin.getAdminEmail()))
					System.out.println("New email id must be not same as old email id");
				else 
				{
					if(myValidation.isEmailIdValid(adminEmail.trim()))
						adminService.updateAdminEmail(admin, adminEmail.trim());
					else
						System.out.println("Invalid Email Id");
				}
				break;
			
			case "3":
				System.out.println("Updating My Contact Number...........");
				System.out.print("Enter new Contact Number:");//three space
				adminContactNo=scanner.nextLine();
				
				if(adminContactNo.equals(admin.getAdminContactNo()))
					System.out.println("New contact number must be not same as old contact number");
				
				else 
				{
					if(myValidation.isIndianPhoneNumberValid(adminContactNo.trim()))
						adminService.updateAdminContact(admin, adminContactNo.trim());
					else
						System.out.println("Please Enter valid Indian Contact No.");
				}
				break;
			
			case "4":
				System.out.println("Updating My Address...........");
				System.out.print("Enter new Address  :");
				adminAddress=scanner.nextLine();
				
				if(adminAddress.equals(admin.getAdminAddress()))
					System.out.println("New address must be not same as old address");
				else 
				{
					if(myValidation.isAddressValid(adminAddress.trim()))
						adminService.updateAdminAddress(admin, adminAddress.trim());
					else
						System.out.println("Please Enter Valid Address");
				}
				break;
			
			case "5":
				System.out.println("Updating My Password...........");
				System.out.print("Enter new Password :");
				adminPassword=scanner.nextLine();
				
				if(adminPassword.equals(admin.getAdminPassword()))
					System.out.println("New password must be not same as old password");
				
				else 
				{
					if(myValidation.isPasswordValid(adminPassword.trim()))
						adminService.updateAdminPassword(admin, adminPassword.trim());
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
	public void updateAdminPassword() 
	{
		System.out.println("Updating My Password...........");
		System.out.print("Enter new Password :");
		adminPassword=scanner.nextLine();
		
		if(adminPassword.equals(admin.getAdminPassword()))
			System.out.println("New password must be not same as old password");
		
		else 
		{
			if(myValidation.isPasswordValid(adminPassword.trim()))
				adminService.updateAdminPassword(admin, adminPassword.trim());
			else
				System.out.println("Password must contain at least one special character, one uppercase letter, one lowercase letter, one digit, and a length between 4 and 30 characters");
		}
	}
	// book entity related
	@Override
	public void registerBook() 
	{
		bookUser.registerBook(admin,null);
	}
	

	@Override
	public void getAllStudentSuggestedBook() 
	{
		bookService.getAllStudentSuggestedBook();
	}
	
	// student entity related
	@Override
	public void showAllStudentDetails() 
	{
		studentService.showAllStudentsDetail();
	}
	@Override
	public void showAllStudentFine() 
	{
		studentService.showAllStudentFine();
	}
	
	// bookIssue entity related
	@Override
	public void getAllBookIssueRequestOfAllStudent() 
	{
		bookIssueService.getAllBookIssueRequestOfAllStudent(admin);
	}
	
	@Override
	public void getAllReturnBookRequestOfAllStudent() 
	{
		bookIssueService.getAllReturnBookRequestOfAllStudent(admin);
	}
	
	@Override
	public void getAllBookIssue() 
	{
		bookIssueService.getAllBookIssue();	
	}
	
	@Override
	public void getAllIssuedBook() 
	{
		bookIssueService.getAllIssuedBook();
	}
	
	@Override
	public void getAllDelayedBook() 
	{
		bookIssueService.getAllDelayedBook();
	}

	@Override
	public void showSingleAdminProfile() {
		System.out.println("------------- My Profile -------------");
		System.out.println("Admin Id            : "+admin.getAdminId());
		System.out.println("Admin Name          : "+admin.getAdminName());
		System.out.println("Admin Email Id      : "+admin.getAdminEmail());
		System.out.println("Admin Contact Number: "+admin.getAdminContactNo());
		System.out.println("Admin Address       : "+admin.getAdminAddress());
		System.out.println("Admin Password      : "+admin.getAdminPassword());
		System.out.println("---------------------------------------\n");
	}
	
}
