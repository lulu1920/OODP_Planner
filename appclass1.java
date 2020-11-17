package P1;

import java.io.BufferedReader;
import java.io.Console;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.Serializable;

public class appclass1 {
	static Scanner input = new Scanner(System.in);
	
	public static void login() {
		System.out.print("Enter Username: ");
		String username= input.next();
		System.out.print("Enter password: ");
		String password= input.next();

		String acc = userController.loginCheck(username, password);
		System.out.println("Login Successfull");
		if(acc.equals("student")) 
			Menu.studentmenu(username);
		
		else if(acc.equals("admin"))
			System.out.println("Admin Menu");
		
		else if(acc.equals("incorrect"))
			System.out.println("Incorect username or password");
				
	}
	
	public static void main(String[] args) {
		while(true)
			login();		

	}
}
