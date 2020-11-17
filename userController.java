package P1;

import java.util.ArrayList;

public class userController {

	public static ArrayList<Object> getUsers() {
		ArrayList<Object> UsersDetails = binaryio.readSerializedObject("users.dat");
		return UsersDetails;
	}
	
	public static String loginCheck(String username, String password) {
		ArrayList<Object> al = getUsers();
		if(al.size()==0) {
			System.out.println("Error reading the file!!");
			return "error";
		}
		for (int i = 0 ; i <al.size() ; i++) {
			User firstuser  = (User)al.get(i);
			String un = firstuser.getUsername();
			String pw = firstuser.getPassword();
			
			if(un.equals(username) && pw.equals(password)) 
			
				if(firstuser.getAdmin())
					return "admin";
				
				else 					
					return "student";
		}
		return "incorrect";
	}

	public static boolean isAdmin(User firstuser) {
		return firstuser.getAdmin();
	}

	public static String getName(User firstuser) {
		return firstuser.getUsername();
	}
	
	public static Student FindStudent(String username) {
		Student stud=fileController.getStudent(username);
		return stud;
	}

}
