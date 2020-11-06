package P1;

import java.io.Serializable;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;


public class User implements Serializable {
	String name;
	String username;
	String password;
	boolean admin;
	
	static Scanner input = new Scanner(System.in);
	
	public User(String nam,String un, String pw, boolean ad) {
		name=nam;
		username =un;
		password = pw;
		admin = ad;
	}
		
	/*
	 * login(string username, string password): bool
	+ logout(): void
	+ print student menu
	+ print admin menu
	 */
	public boolean login(String un, String pw) {
		if(un.equals(username) &&pw.equals(password))
			return true;
		else 
			return false;
	}
	
	public boolean getAdmin() {
		return admin;
	}

	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}
	
}
