package P1;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Student extends User{
	
	private static Scanner scanner = new Scanner(System.in);
	String student_id;
	LocalDate accessPeriod_Start;
	LocalDate accessPeriod_End;
	//ArrayList<index> registered_index;
	
	public Student(String un, String name,String pw, String id, LocalDate ld, LocalDate ld1) {
		super(name,un,pw,false);
		student_id = id;
		accessPeriod_Start=ld;
		accessPeriod_End=ld1;
		//registered_index= rl;
	}
	
	public void printstudentmenu() {
		do {
			System.out.println("Please select one of the options below: ");
			System.out.println("1. Register Course");
			System.out.println("2. Drop Course");
			System.out.println("3. Check / Print Courses Registered");
			System.out.println("4. Check Vacancies Available");
			System.out.println("5. Change Index Number of Course");
			System.out.println("6. Swop Index Number with Another Student");
			System.out.println("7. Change notification mode");
			System.out.println("8. Change Number or Email");
			System.out.println("9. Log out");
			while (!scanner.hasNextInt()) {
				scanner.next();
				System.out.println("Please enter valid option:");
			}
			int choice = scanner.nextInt();

			switch (choice) {
			case 1:
				StudentController.addCourse(student.getMatricNo());
				break;
			case 2:
				StudentController.dropCourse(student.getMatricNo());
				break;
			case 3:
				StudentController.printRegistered(student.getMatricNo());
				break;
			case 4:
				StudentController.checkVacancy();
				break;
			case 5:
				StudentController.changeIndex(student.getMatricNo());
				break;
			case 6:
				StudentController.swopIndex(student.getMatricNo());
				break;
			case 7:
				StudentController.changeNotification(student.getMatricNo());
				break;
			case 8:
				StudentController.changeNumOrEmail(student.getMatricNo());
				break;
			case 9:
				STARSApp.logout();
				break;
			default:
				System.out.println("Please enter valid option:");
				break;
			}
		} while (choice < 0 || choice > 0);
	}
	
	public String getName() {
		return super.name;
	}

	public String getUsername() {
		return super.username;
	}
	
	public String getStudentId() {
		return student_id;
	}
	
	/*public ArrayList<Index> getIndices(){
		return registered_index;
	}*/
	
	public LocalDate getstartDate() {
		return accessPeriod_Start;
	}
	
	public LocalDate getendDate() {
		return accessPeriod_End;
	}
}
