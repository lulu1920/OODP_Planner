package P1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class studentControler {
	private static Scanner scanner = new Scanner(System.in);

	public static boolean isAccessPeriod(String name) {
		int out = fileController.StudentAccessPeriod(name);
		if(out==0)
			return true;
		else if(out==1) {
			System.out.println("Your STARS access has not started yet!!!");
			return false;
		}
		else if(out==-1) {
			System.out.println("Your STARS access has been closed!!!");
			return false;
		}
		else
			return false;
	}	
	public static void displayNotification(String name) {
		fileController.printStudent_Notifications(name);
	}
	
	public static void registerCourse(String name) {
		String[] course=fileController.getRegisterIndex();
		if(course[0].equals(""))
			return;
		int success=fileController.assignStudent(name,course);
		
		if(success==-1)
			io.print("Sorry!! You have already been registered/waitlisted for course: " 
					+course[1]);
		else if(success==0)
			io.print("You have been registered to course: " 
					+course[1]+ ", Index Id: "+course[0]);
		else if(success==1)
			io.print("You have been added to the waitinglist for course: " 
					+course[1]+ ", Index Id: "+course[0]);
		else if(success==2)
			io.print("Sorry!! There is a clash in your timetable for course: " 
					+course[1]+ ", Index Id: "+course[0]);
			
	}

	public static void dropCourse(String name) {
		System.out.println("Choose course to drop: ");
		String[] course=fileController.getDropIndex(name);
		if(course[0].equals(""))
			return;
		System.out.println("Are you sure want to drop index "+ course[0] +
				" of Course "+ course[1]+ " (yes/no)?");
		
		String cnfm = scanner.next();
			
		if(cnfm.toLowerCase().equals("yes")) {
			fileController.unAssignStudent(name,course);
		}
		else
			dropCourse(name);
	}

	public static void printRegistered(String name) {
		fileController.printStudentIndices(name,"reg");
	}

	public static void printWaitlist(String name) {
		fileController.printStudentIndices(name,"wait");
	}

	public static void printVacancy() {
		System.out.println("Choose a course to check vaccancy: ");
		int max = fileController.printAllCourses();
		System.out.println(max+1+") To go back.");
		
		int cur = scanner.nextInt();

		if(cur-1<max) 
			fileController.printIndices(null,cur-1);
		else
			System.out.println("Back to Menu");
	}
	
	public static void changeIndex(String name) {
		System.out.println("Choose course to change Index: ");
		String[] course=fileController.getDropIndex(name);
		if(course[0].equals(""))
			return;
		String[] index = fileController.getChangeIndex(course[1]);
		
		if(index[1].equals("")) {
			System.out.println("Sorry!! The requested index "+index[0]+" has no vacany!!");
		}
		else {
			//if no clash
			String[] drop= course;
			String[] add= {index[0],course[1]};
			fileController.unAssignStudent(name,drop);
			fileController.assignStudent(name,add);
		}
	}
	
	public static void swopIndex(String name) {
		System.out.println("Choose course to swop Index: ");
		String[] course=fileController.getDropIndex(name);
		if(course[0].equals(""))
			return;
		
		System.out.print("Enter Student ID to swop Index : ");
		int cur = scanner.nextInt();
	}
	
	public static void logout(String name) {
		fileController.logout(name);
	}

	

	

		
	
}
