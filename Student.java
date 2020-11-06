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
