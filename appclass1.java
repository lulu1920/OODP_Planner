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
	public static final String SEPARATOR = "|";
	
	
	public static void main(String[] args) {
		String filename = "users.bin" ;
		System.out.print("Enter Username");
		String username= input.next();
		System.out.print("Enter password");
		String password= input.next();
        // read file containing Professor records.
		ArrayList<Object> al = binaryio.readSerializedObject(filename);
		for (int i = 0 ; i <al.size() ; i++) {
			User xyz  = (User)al.get(i);
			if(xyz.login(username, password)) {
				System.out.println("Login Successfull");
				if(!xyz.getAdmin()) {
					System.out.println("Student Menu");
					
				}
				else {
					System.out.println("Admin Menu");
				}
			break;
			}
			else if(i+1==al.size())
				System.out.print("Incorect username or password");
		}
		
	
	}
		

}
