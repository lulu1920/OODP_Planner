package P1;

import java.io.Serializable;
import java.util.*;

public class Course implements Serializable{
	//
	String courseCode;
	String courseName;
	ArrayList <Index> indices=new ArrayList<Index>();
	String type;
	Hashtable<String, String> registered;
	Hashtable<String, String> Waitlist;

	public Course() {
		courseName=null;
		courseCode= null;
		indices=null;
		type=null;
		registered = new Hashtable<String, String>();
		Waitlist = new Hashtable<String, String>();
	}
	
	public Course(String id,String name, String ltl) {
		courseName=name;
		courseCode= id;
		type=ltl;
		registered = new Hashtable<String, String>();
		Waitlist = new Hashtable<String, String>();
		
		System.out.printf("How many indices do you want to create? ");
		Scanner input = new Scanner(System.in);
		int num = input.nextInt();
		for(int i=1;i<=num;i++){
			System.out.printf("Enter Index id for index %d",i);		
			String index_id = input.next();
			
			System.out.printf("Enter Schedule for index %d",i);		
			String time = input.next();
			
			System.out.printf("Enter number of vacancies for index %d",i);		
			int slot = input.nextInt();
			indices.add(new Index(courseCode,index_id,time,slot));
		}
		
		//enter different indices and timings
		binaryio.writeSerializedObject("courses.dat",this);
	}
	
	public String getCourseName() {
		return courseName;
	}
	
	public void setCourseName(String cname) {
		courseName=cname;
	}
	public String getCourseCode() {
		return courseCode;
	}
	
	public void setCourseCode(String ccode) {
		courseCode=ccode;
	}
	
	public ArrayList<Index> getIndices() {
		return indices;
	}
	
	public void setIndices(ArrayList<Index> update) {
		indices=update;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String typ) {
		type=typ;
	}
	
	public Hashtable<String, String> getRegistered() {
		return registered;
	}

	public void setRegistered(Hashtable<String, String> update) {
		registered=update;
	}

	public Hashtable<String, String> getWaitlist() {
		return Waitlist;
	}

	public void setWaitlist(Hashtable<String, String> update) {
		Waitlist=update;
	}
}
