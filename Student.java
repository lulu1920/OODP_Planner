package P1;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Student extends User{
	
	String student_id;
	LocalDate accessPeriod_Start;
	LocalDate accessPeriod_End;
	ArrayList<Index> registered_index;
	ArrayList<Index> waitlisted_index;
	ArrayList<String> notifications;
	
	public Student() {
		super(null,null,null,false);
		student_id = null;
		accessPeriod_Start=null;
		accessPeriod_End=null;
		registered_index= new ArrayList<Index>();
		waitlisted_index= new ArrayList<Index>();
		notifications=new ArrayList<String>();
	}
	public Student(String name,String un,String pw, String id, LocalDate ld, LocalDate ld1) {
		super(name,un,pw,false);
		student_id = id;
		accessPeriod_Start=ld;
		accessPeriod_End=ld1;
		registered_index= new ArrayList<Index>();
		waitlisted_index= new ArrayList<Index>();
		notifications=new ArrayList<String>();
		binaryio.writeSerializedObject("students.dat",this);
	}
	
	public String getName() {
		return super.name;
	}

	public void setName(String name) {
		super.name=name;
	}
	
	public String getUsername() {
		return super.username;
	}

	public void setUsername(String uname) {
		super.username=uname;
	}
		
	public String getStudentId() {
		return student_id;
	}

	public void setStudentId(String sId) {
		student_id=sId;
	}
		
	public ArrayList<Index> getIndices(){
		return registered_index;
	}
	
	public void setIndices(ArrayList<Index> indicies){
		registered_index=indicies;
	}
	
	public ArrayList<Index> getWaitIndices(){
		return waitlisted_index;
	}
	
	public void setwaitIndices(ArrayList<Index> indicies){
		waitlisted_index=indicies;
	}
	
	public LocalDate getstartDate() {
		return accessPeriod_Start;
	}

	public void setstartDate(LocalDate Sdate) {
		accessPeriod_Start=Sdate;
	}
	
	public LocalDate getendDate() {
		return accessPeriod_End;
	}

	public void setendDate(LocalDate Edate) {
		accessPeriod_End=Edate;
	}
	
	public ArrayList<String> getNotifications(){
		return notifications;
	}
	
	public void setNotifications(ArrayList<String> noti){
		notifications=noti;
	}
	
	public static void send(Message message, Address[] address){
		Transport.send(message); 
		System.out.println("message sent successfully.");
	}
}
