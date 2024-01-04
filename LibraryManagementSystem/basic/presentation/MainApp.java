package com.library.basic.presentation;

import java.util.List;
import java.util.Scanner;

import com.library.basic.entity.Admin;
import com.library.basic.service.AdminService;
import com.library.basic.service.AdminServiceImpl;

public class MainApp {
	private static Scanner scanner = new Scanner(System.in);
	private static String ch;// ,userId,userPass;
	private static AdminUser adminUser = new AdminUserImpl();
	private static StudentUser studentUser = new StudentUserImpl();
	private static BookUser bookUser = new BookUserImpl();
	private static AdminService adminService = new AdminServiceImpl();
	private static List<Admin> adminList;
	
	public static void main(String[] args) {
		while(true){
	//		String ch="";
			System.out.println("\n-----------------------------");
			System.out.println("--------- Main Page ---------");
			System.out.println("-----------------------------");
			System.out.println("\t1. Admin");
			System.out.println("\t2. Student");
			System.out.println("\t3. Exit");
			System.out.print("\nEnter Your choice : ");
			ch = scanner.next();
			scanner.nextLine();
		//	myLibrary = new MyLibraryImpl();
			switch(ch) {
			case "1":
				System.out.println("\nAdmin page...........\n");
				adminHomePage();
				break;
			case "2":
				adminList = adminService.getAllAdmin();
				if(adminList==null)
				{
					System.out.println("Something went wrong");
					break;
				}
				else if(adminList.isEmpty())
				{
					System.out.println("Sorry for Inconvenience! There is something wrong with application you cannot proceed !");
					break;
				}
				System.out.println("\nStudent page...........");
				studentHomePage();
				break;
			case "3":
				System.out.println("Application Terminated..................");
				System.exit(0);
			default:
				System.out.println("Oops! Invalid Input");
			}
		}
		
	}
	public static void adminHomePage() {
		while(true) {
	//		String ch="";
			System.out.println("\n-----------------------------------");
			System.out.println("--------- Admin Home Page ---------");
			System.out.println("-----------------------------------");
			System.out.println("\t1. Admin Registration");
			System.out.println("\t2. Admin Login");
			System.out.println("\t3. Exit");
			System.out.print("\nEnter Your choice : ");
			ch = scanner.next();
			scanner.nextLine();
			switch(ch) {
			case "1":
				System.out.println("Admin Registration...........");
				adminUser.registerAdmin();
				adminPage();
				break;
			case "2":
				if(adminUser.adminLogin()) adminPage();
				break;
			case "3":
				System.out.println("Exit..........");
				break;
			default:
				System.out.println("Oops! Invalid Input");
			}
			if(ch.compareTo("3")==0) break;
		}
	}
	public static void adminPage() {
		while(true){
	//		String ch="";
			System.out.println("\n------------------------------");
			System.out.println("--------- Admin Page ---------");
			System.out.println("------------------------------");
			System.out.println("   1. Register Book");
			System.out.println("   2. Update Stock");
			System.out.println("   3. Search Book");
			System.out.println("   4. Display All Book");
			System.out.println("   5. Display All Users");
			System.out.println("   6. Display My Details");
			System.out.println("   7. Update My Details");
			System.out.println("   8. Update Password");
			System.out.println("   9. Delete My Account");
			System.out.println("  10. Display request for confirm issue Book");
			System.out.println("  11. Display request for return Book");
			System.out.println("  12. Display student suggestion book");
			System.out.println("  13. Display issued Book");
			System.out.println("  14. Display delay Book");
			System.out.println("  15. Display Fine Details");
			System.out.println("  16. Logout");
			System.out.print("\nEnter Your choice : ");
			ch = scanner.next();
			scanner.nextLine();
			switch(ch) {
			case "1":
				System.out.println("Registering Book...........");
				adminUser.registerBook();
				break;
			case "2":
				System.out.println("Update Stock...........");
				bookUser.updateBookStock();
				break;
			case "3":
				System.out.println("Search Book...........");
				bookUser.searchBook();
				break;
			case "4":
				System.out.println("Display All Books...........");
				bookUser.getAllBook();
				break;
			case "5":
				System.out.println("Display All Users...........");
				adminUser.showAllStudentDetails();
				break;
			case "6":
				System.out.println("Display My Details...........");
				adminUser.showSingleAdminProfile();
				break;
			case "7":
				System.out.println("Update My Details...........");
				adminUser.updateAdminDetails();
				break;
			case "8":
				System.out.println("Update Password...........");
				adminUser.updateAdminPassword();
				break;
			case "9":
				System.out.println("Deleting Account...........");
	//			adminUser.deleteAdmin();
				break;
			case "10":
				System.out.println("Display request for confirm issue Book...........");
				adminUser.getAllBookIssueRequestOfAllStudent();
				break;
			case "11":
				System.out.println("Display request for return Book...........");
				adminUser.getAllReturnBookRequestOfAllStudent();
				break;
			case "12":
				System.out.println("Display student suggestion book...........");
				adminUser.getAllStudentSuggestedBook();
				break;
			case "13":
				System.out.println("Display issued Book...........");
				adminUser.getAllIssuedBook();
				break;
			case "14":
				System.out.println("Display delay Book...........");
				adminUser.getAllDelayedBook();
				break;
			case "15":
				System.out.println("Display Fine Details...........");
				adminUser.showAllStudentFine();
			//	adminUser.getAllStudentFineDetails();
				break;
			case "16":
				System.out.println("Logging Out..........");
				break;
			default:
				System.out.println("Oops! Invalid Input");
			}
			if(ch.compareTo("16")==0) break;
		}
	}
	public static void studentHomePage() {
		while(true) {
	//		String ch="";
			System.out.println("\n-----------------------------------");
			System.out.println("--------- Student Home Page ---------");
			System.out.println("-----------------------------------");
			System.out.println("\t1. Student Registration");
			System.out.println("\t2. Student Login");
			System.out.println("\t3. Exit");
			System.out.print("\nEnter Your choice : ");
			ch = scanner.next();
			scanner.nextLine();
			switch(ch) {
			case "1":
				System.out.println("Student Registration...........");
				studentUser.registerStudent();
				studentPage();
				break;
			case "2":
				System.out.println("Student Login...........");
				if(studentUser.studentLogin()){
					studentPage();
				}
				break;
			case "3":
				System.out.println("Exit..........");
				break;
			default:
				System.out.println("Oops! Invalid Input\n");
			}
			if(ch.compareTo("3")==0) break;
		}
	}
	public static void studentPage() {
		while(true){
	//		String ch="";
			System.out.println("\n------------------------------");
			System.out.println("--------- Student Page ---------");
			System.out.println("------------------------------");
			System.out.println("  1. View My Profile");
			System.out.println("  2. Update My Profile");
			System.out.println("  3. Update Password");
			System.out.println("  4. Delete My Account");
			System.out.println("  5. Display My Books");
			System.out.println("  6. Display Fine Details");
			System.out.println("  7. Display All Books");
			System.out.println("  8. Search Book");
			System.out.println("  9. Issue Book");
			System.out.println(" 10. Return Book");
			System.out.println(" 11. Display My Book Issue Details");
			System.out.println(" 12. Suggest to add new book");
			System.out.println(" 13. LogOut");
			System.out.print("\nEnter Your choice : ");
			ch = scanner.next();
			scanner.nextLine();
			switch(ch) {
			case "1":
				System.out.println("Your Profile...........");
				studentUser.viewStudentProfile();
				break;
			case "2":
				System.out.println("Updating Your Profile...........");
				studentUser.updatestudentDetails();
				break;
			case "3":
				System.out.println("Updating Password...........");
				studentUser.updatestudentPassword();
				break;
			case "4":
				System.out.println("Deleting Account...........");
				studentUser.deleteStudent();
				break;
			case "5":
				System.out.println("Your Books...........");
				studentUser.viewIssuedBookOfStudent();
				break;
			case "6":
				System.out.println("Your Fine Details...........");
				studentUser.viewFineDetails();
				break;
			case "7":
				System.out.println("-------------- Welcome To Dev Queen Book Library --------------\n");
				bookUser.getAllBook();	
				break;
			case "8":
				System.out.println("Searching Book...........");
				bookUser.searchBook();	
				break;
			case "9":
				System.out.println("Issue Book...........");
				studentUser.requestForIssueBook();
				break;
			case "10":
				System.out.println("Return Book...........");
				studentUser.requestForreturnBook();
				break;
			case "11":
				System.out.println("Your Book Issue Details...........");
				studentUser.viewBookIssueDetailsOfstudent();
				break;
			case "12":
				System.out.println("Suggest to add new book...........");
				studentUser.SuggestBook();
				break;
			case "13":
				System.out.println("LogOut...........");
				break;
			default:
				System.out.println("Oops! Invalid Input");
			}
			if(ch.compareTo("13")==0 ) break;
		}
	}

}
