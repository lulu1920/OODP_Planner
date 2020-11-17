package P1;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
	private static Scanner scanner = new Scanner(System.in);
	
	public static void studentmenu(String name) {
		if(!studentControler.isAccessPeriod(name))
			return;
		int choice=0;
		do {
			studentControler.displayNotification(name);
			System.out.println("Please select one of the options below: ");
			System.out.println("1. Register Course");
			System.out.println("2. Drop Course");
			System.out.println("3. Check / Print Courses Registered");
			System.out.println("4. Check / Print Waitlist Courses");
			System.out.println("5. Check Vacancies Available");
			System.out.println("6. Change Index Number of Course");
			System.out.println("7. Swop Index Number with Another Student");
			System.out.println("8. Change notification mode");
			System.out.println("9. Change Number or Email");
			System.out.println("10. Log out");
			while (!scanner.hasNextInt()) {
				scanner.next();
				System.out.println("Please enter valid option:");
			}
			choice = scanner.nextInt();

			switch (choice) {
			case 1:
				studentControler.registerCourse(name);
				break;
			case 2:
				studentControler.dropCourse(name);
				break;
			case 3:
				studentControler.printRegistered(name);
				break;
			case 4:
				studentControler.printWaitlist(name);
				break;
			case 5:
				studentControler.printVacancy();
				break;
			case 6:
				studentControler.changeIndex(name);
				break;
			case 7:
				studentControler.swopIndex(name);
				break;
			case 8:
				//StudentController.changeNotification(student.getMatricNo());
				break;
			case 9:
				//StudentController.changeNumOrEmail(student.getMatricNo());
				break;
			case 10:
				studentControler.logout(name);
				appclass1.login();
				break;
			default:
				System.out.println("Please enter valid option:");
				break;
			}
		} while (true);
	}

}
