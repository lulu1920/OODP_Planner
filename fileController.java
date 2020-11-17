package P1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Scanner;

public class fileController {

	private static Scanner scanner = new Scanner(System.in);
	
//----------------Get info about student------------------- 
	public static String getStudent_Username(Student obj) {
		return obj.getUsername();
	}
	
	public static String getStudent_StudId(Student obj) {
		return obj.getStudentId();
	}
	
	public static ArrayList<Object> getStudents() {
		ArrayList<Object> StudentsDetails = binaryio.readSerializedObject("students.dat");
		return StudentsDetails;
	}
	
	public static Student getStudent(String un) {
		ArrayList<Object> StudentDetails = getStudents();
		
		if(StudentDetails.size()==0) {
			System.out.println("Error reading the file!!");
			return null;
		}
		for (int i = 0 ; i <StudentDetails.size() ; i++) {
			Student stud  = (Student)StudentDetails.get(i);
			if(stud.getUsername().equals(un)) {
				return stud;
			}
			else if(i+1==StudentDetails.size())
				System.out.print("Incorect username");
		}	
		return null;
	}

	public static ArrayList<Index> getStudent_Registered(Student obj) {
		return obj.getIndices();		
	}

	public static ArrayList<Index> getStudent_Waitlisted(Student obj) {
		return obj.getWaitIndices();
	}
	
	public static ArrayList<String> getStudent_Notifications(Student name) {
		return name.getNotifications();
	}

//--------------------Changing attributes of students-----------------
	public static void assignIndex(Student name, Index ind) {
		ArrayList<Index> update = name.getIndices();
		update.add(ind);
		name.setIndices(update);
	}

	public static void unAssignIndex(Student name, Index ind) {
		ArrayList<Index> update = name.getIndices();
		Iterator<Index> itr = update.iterator();		
		while (itr.hasNext()) {
			Index nxt = itr.next(); 
			if (ind.getIndexId().equals(nxt.getIndexId()))
				itr.remove();
			break;
		}
		name.setIndices(update);
	}

	public static void waitIndex(Student name, Index ind) {
		ArrayList<Index> update = name.getWaitIndices();
		update.add(ind);
		name.setwaitIndices(update);
	}

	public static void removewait(Student name, Index ind) {
		ArrayList<Index> update = name.getWaitIndices();
		Iterator<Index> waititr = update.iterator();
		while (waititr.hasNext()) {
			Index nxt = waititr.next(); 
			if (ind.getIndexId().equals(nxt.getIndexId())) {
				waititr.remove();
				break;
			}
		}
		name.setwaitIndices(update);
	}
	
	public static void addNotification(Student name, String noti) {
		ArrayList<String> update = name.getNotifications();
		update.add(noti);
		name.setNotifications(update);
	}
	
	public static void setStudent_Notifications(Student name, ArrayList<String> noti) {
		name.setNotifications(noti);
	}

//------------Student functions-------------------------------
	public static int StudentAccessPeriod(String name) {
		Student stud = getStudent(name);
		LocalDate SD = stud.getstartDate();
		LocalDate ED = stud.getendDate();
		LocalDate today = LocalDate.now();
		
		if (today.isAfter(SD) && today.isBefore(ED))
			return 0;
		else if(today.isAfter(ED))
			return 1;
		else
			return -1;
	}
	
	public static void printStudent_Notifications(String name) {
		Student stud=getStudent(name);
		ArrayList<String> noti=getStudent_Notifications(stud);
		if(noti!=null)
			for(String nxt : noti)
				io.print(nxt);
		noti=new ArrayList<String>();
		setStudent_Notifications(stud,noti);
	}

	public static void printStudentIndices(String username,String WaitReg) {
		Student obj=getStudent(username);
		ArrayList<Index> indices=null;
		if(WaitReg.equals("wait"))
			indices=obj.getWaitIndices();
		if(WaitReg.equals("reg"))
			indices=obj.getIndices();
		System.out.println("   Course Code\tIndex ID\t Waitlist\t Vacancy \tSchedule ");
		System.out.println("---------------------------------------------------------------------------");
		for (int i = 0 ; i <indices.size() ; i++) {
			io.print("hi");
			Index ind  = indices.get(i);
			System.out.println(i+1+") "+ind.getCourseId()+"\t    \t"+ind.getIndexId()+
					"\t    \t"+ind.getNoWaitlist()+"\t    \t"+ind.getVacancy()+
					"\t    \t"+ind.getSchedule());
		}	
	}

	public static void logout(String name) {
		Student user=getStudent(name);
		updateStudent(user);
		courseStudentUpdate(user);		
	}

//-------------------Updating Student file to save changes-------------
	public static void updateStudent(Student name) {
		ArrayList<Object> StudentsDetails = getStudents();
		Iterator<Object> itr = StudentsDetails.iterator();
		while (itr.hasNext()) {
			Student nxt = (Student)itr.next(); 
			if (name.getUsername().equals(nxt.getUsername()))
				itr.remove();
		}
	
		StudentsDetails.add(name);
		binaryio.clearwriteSerializedObject("students.dat", StudentsDetails);
	}
	
	public static void updateStudentIndex(Index ind) {
		Student cur=null;
		ArrayList<Object> studentDetails = getStudents();
		Iterator<Object> studitr = studentDetails.iterator();
	
		ArrayList<Student> updated = new ArrayList<Student>();
		while (studitr.hasNext()){
			cur  = (Student)studitr.next();
			
			ArrayList<Index> indices = cur.getIndices();
			Iterator<Index> itr = indices.iterator();
			int reg=0;
			while (itr.hasNext()) {
				Index nxt = itr.next(); 
				if (ind.getIndexId().equals(nxt.getIndexId())) {
					itr.remove();
					indices.add(ind);
					reg=1;
					cur.setIndices(indices);
					break;
				}
			}
			if(reg==0) {
				ArrayList<Index> wait = cur.getWaitIndices();
				Iterator<Index> waititr = wait.iterator();
				while (waititr.hasNext()) {
					Index nxt = waititr.next(); 
					if (ind.getIndexId().equals(nxt.getIndexId())) {
						waititr.remove();
						wait.add(ind);
						cur.setwaitIndices(wait);
						break;
					}
				}
			}
			studitr.remove();
			updated.add(cur);
		}	
		for(int i=0;i<updated.size();i++) {
			studentDetails.add(updated.get(i));
		}
		binaryio.clearwriteSerializedObject("students.dat", studentDetails);
			
	}

	
//=================================================================================================================================
	
//----------------Get info about courses/index-------------------



	public static ArrayList<Object> getCourses() {
		ArrayList<Object> courses=binaryio.readSerializedObject("courses.dat");
		return courses;
	}
	
	public static Course getCourse(String courseId) {
		ArrayList<Object> courseDetails = getCourses();
		
		if(courseDetails.size()==0) {
			System.out.println("Error reading the file!!");
			return null;
		}
		for (int i = 0 ; i <courseDetails.size() ; i++) {
			Course cur  = (Course)courseDetails.get(i);
			if(cur.getCourseCode().equals(courseId)) {
				return cur;
			}
			else if(i+1==courseDetails.size())
				System.out.print("No course");
		}	
		return null;
	}

	public static Index findIndex(Course cur, String indId) {
		ArrayList<Index> indices = cur.getIndices();
		for(Index nxt : indices)
			if(nxt.getIndexId().equals(indId))
				return nxt;
		
		return null;
	}

//--------------------Change Attributes---------------
	public static void addStudenttoCourse(Student name,Index ind) {
		Course cour = getCourse(ind.getCourseId());
		Hashtable<String, String> update = cour.getRegistered();
		update.put(name.getUsername(), ind.getIndexId());
		cour.setRegistered(update);
		updateCoursefile_course(cour);
	}
	
	public static void addStudenttoWaitlist(Student name, Index ind) {
		Course cour = getCourse(ind.getCourseId());
		Hashtable<String, String> update = cour.getWaitlist();
		update.put(name.getUsername(), ind.getIndexId());
		cour.setWaitlist(update);
		updateCoursefile_course(cour);
	}

	public static void courseRemoveStudent(Student name, Index ind) {
		Course cour = getCourse(ind.getCourseId());
		Hashtable<String, String> update = cour.getRegistered();
		update.remove(name.getUsername());
		cour.setRegistered(update);
		updateCoursefile_course(cour);
	}

	public static void removeCourseWait(Student wait, Index ind) {
		Course cour = getCourse(ind.getCourseId());
		Hashtable<String, String> update = cour.getWaitlist();
		update.remove(wait.getUsername());
		cour.setWaitlist(update);	
		updateCoursefile_course(cour);
	}

	
//--------------------Course/Index functions--------------
	public static String[] getRegisterIndex() {
		System.out.println("Choose a course to register: ");
		ArrayList<Object> courses=getCourses();
		int max=printAllCourses();
		System.out.println(max+1+") To go back.");
		Index ind=null;
		String[] course= {"",""};
		int choice = scanner.nextInt();
		
		if(choice-1<max) {
			System.out.println("Choose an Index to register: ");
			Course cour  = (Course)courses.get(choice-1);
			printIndices(cour,0); 
			System.out.println(cour.getIndices().size()+1+") To go back.");
			
			choice = scanner.nextInt();
			
			if(choice-1<cour.getIndices().size()) {
				ind = cour.getIndices().get(choice-1);
				course[0]= ind.getIndexId();
				course[1]=ind.getCourseId();
			}
			else
				getRegisterIndex();
		}
		else
			System.out.println("Back to Menu");
		return course;
	}

	
	public static int printAllCourses() {
		ArrayList<Object> courses=fileController.getCourses();
		
		System.out.println("   Course Code\t Course Name ");

		System.out.println("-------------------------------------");
		for (int i = 0 ; i <courses.size() ; i++) {
			Course cour  = (Course)courses.get(i);
			System.out.println(i+1+") "+cour.getCourseCode()+"\t    "+cour.getCourseName());
		}	
		
		return courses.size();
	}
	
	public static void printIndices(Course cour, int i) {
		if(cour==null) {
			ArrayList<Object> courses=fileController.getCourses();
			cour=(Course)courses.get(i);
		}
		System.out.println("   Course Code\tIndex ID\t Waitlist\t Vacancy \tSchedule ");
		System.out.println("---------------------------------------------------------------------------");
		ArrayList<Index> indices=cour.getIndices();
		for (int j = 0 ; j <indices.size() ; j++) {
			Index ind  = indices.get(j);
			System.out.println(j+1+") "+ind.getCourseId()+"\t    \t"+ind.getIndexId()+
					"\t    \t"+ind.getNoWaitlist()+"\t    \t"+ind.getVacancy()+
					"\t    \t"+ind.getSchedule());
		}	
	}
	
	public static String[] getChangeIndex(String courseId) {
		System.out.println("Choose Index to change: ");
		Course cour=getCourse(courseId);
		System.out.println("   Course Code\tIndex ID\t Waitlist\t Vacancy \tSchedule ");
		System.out.println("---------------------------------------------------------------------------");
		ArrayList<Index> indices=cour.getIndices();
		for (int j = 0 ; j <indices.size() ; j++) {
			Index ind  = indices.get(j);
			System.out.println(j+1+") "+ind.getCourseId()+"\t    \t"+ind.getIndexId()+
					"\t    \t"+ind.getNoWaitlist()+"\t    \t"+ind.getVacancy()+
					"\t    \t"+ind.getSchedule());
		}
		System.out.println(indices.size()+1+") To go back.");
		
		String[] result= {"",""};
		int choice = scanner.nextInt();
		
		if(choice-1<indices.size())  {
			Index ind=indices.get(choice);
			result[0]=ind.getIndexId();
			if(ind.getVacancy()>0) 
				result[1]="true";
		}
		return result;
	}

	public static int assignStudent(String username, String course[]) {
		Course cour = getCourse(course[1]);
		Index ind= findIndex(cour,course[0]);
		Student name=getStudent(username);
		
		Hashtable<String,String> reg=cour.getRegistered();
		Hashtable<String,String> wait=cour.getWaitlist();
		
		if((wait.get(username)!=null)
				||(reg.get(username)!=null)) {
			return -1;
		}

		//check for clash
		//if no clash{
			if (ind.getVacancy() > 0) {
				ArrayList<Student> update = ind.getRegistered();
				update.add(name);
				ind.setRegistered(update);
				ind.setVacancy(ind.getVacancy()-1);
				assignIndex(name,ind);
				addStudenttoCourse(name,ind);
				
				//save the change to file
				courseUpdate(ind);
				updateStudentIndex(ind);
				
				courseStudentUpdate(name);
				updateStudent(name);
				
				return 0;
			}
			else {
				ind.setNoWaitlist(ind.getNoWaitlist()+1);
				ArrayList<Student> update = ind.getWaitlist();
				update.add(name);
				ind.setWaitlist(update);
				addStudenttoWaitlist(name,ind);
				fileController.waitIndex(name,ind);

				//save the change to file
				courseUpdate(ind);
				updateStudentIndex(ind);
				
				courseStudentUpdate(name);
				updateStudent(name);
				
				return 1;
			}
		
		//}
		//if clash
		//return 2;
		//System.out.println("Sorry there is a clash in your timetable");
	
	}

	
	public static String[] getDropIndex(String user) {
		Student obj=getStudent(user);
		ArrayList<Index> indices= getStudent_Registered(obj);
		printStudentIndices(user,"reg");
		System.out.println(indices.size()+1+") To go back.");
		Index ind=null;
		int choice = scanner.nextInt();
		String[] course= {"",""};
		if(choice-1<indices.size()) {
			ind  = indices.get(choice-1);
			course[0]= ind.getIndexId();
			course[1]= ind.getCourseId();
		}
		else
			System.out.println("Back to Menu");
		
		return course;
	
	}

	public static void unAssignStudent(String username, String course[]) {
		Student name=getStudent(username);
		Course cour=getCourse(course[1]);
		Index ind=findIndex(cour,course[0]);
		
		ArrayList<Student> update = ind.getRegistered();
		Iterator<Student> itr = update.iterator();		
		while (itr.hasNext()) {
			Student nxt = itr.next(); 
			if (fileController.getStudent_Username(name).equals(fileController.getStudent_Username(nxt)))
				itr.remove();
			break;
		}
		ind.setRegistered(update);
		courseRemoveStudent(name,ind);
		unAssignIndex(name,ind);
		ind.setVacancy(ind.getVacancy()+1);
		if(ind.getNoWaitlist()>0) {
			update = ind.getWaitlist();
			int success;
			do{
				Student wait=update.get(0);

				fileController.removewait(wait,ind);
				removeCourseWait(wait,ind);
				itr = update.iterator();		
				while (itr.hasNext()) {
					Student nxt = itr.next(); 
					if (fileController.getStudent_Username(wait).equals(fileController.getStudent_Username(nxt)))
						itr.remove();
					break;
				} 
				ind.setWaitlist(update);
				ind.setNoWaitlist(ind.getNoWaitlist()-1);
				
				String[] newCourse= {ind.getIndexId(),ind.getCourseId()};
				success=assignStudent(wait.getUsername(),newCourse);
				if(success==-1) {
					io.print("lol");
					//studentControler.addNotification(wait,"Sorry!! You have already registered to course: " 
							//+ind.getCourseId());
				}
				else if(success==0) {
					io.print("lol");
					//studentControler.addNotification(wait,"You have been registered to course: " 
							//+ind.getCourseId()+ ", Index Id: "+ind.getIndexId());
				}
				else if(success==2) {
					io.print("lol");
					//studentControler.addNotification(wait,"Sorry!! There is a clash in your timetable for course: " 
							//+ind.getCourseId()+ ", Index Id: "+ind.getIndexId()+"\n You have been removed from the waitlist");
				}
				
				//studentControler.logout(wait);

			}while(success!=0);
		}
		System.out.println("The index has been removed from your timetable");
	
		//save the change to file
		courseUpdate(ind);
		updateStudentIndex(ind);
		
		courseStudentUpdate(name);
		updateStudent(name);
	}



//-------------------Update Course file to save changes------------
	public static void courseUpdate(Index ind) {
		Course cur=null;
		ArrayList<Object> courseDetails = getCourses();
		Iterator<Object> courseitr = courseDetails.iterator();
		while (courseitr.hasNext()){
			cur  = (Course)courseitr.next();
			if(cur.getCourseCode().equals(ind.getCourseId())) {
				courseitr.remove();
				ArrayList<Index> indices = cur.getIndices();
				Iterator<Index> itr = indices.iterator();
				while (itr.hasNext()) {
					Index nxt = itr.next(); 
					if (ind.getIndexId().equals(nxt.getIndexId())) {
						itr.remove();
						indices.add(ind);
						cur.setIndices(indices);
						break;
					}
				}
				
				
				courseDetails.add(cur);
				break;
			}	
		}
		
		
		binaryio.clearwriteSerializedObject("courses.dat", courseDetails);		
	}
	
	public static void courseStudentUpdate(Student name) {
		Course cur=null;
		ArrayList<Object> courseDetails = getCourses();
		Iterator<Object> courseitr = courseDetails.iterator();
		
		String Username =fileController.getStudent_Username(name);
		ArrayList<Object> updated = new ArrayList<Object>();
		while (courseitr.hasNext()){
			cur  = (Course)courseitr.next();
			
			Hashtable<String, String> regs = cur.getRegistered();
			String indId= regs.get(Username);

			Hashtable<String, String> wait = cur.getWaitlist();
			String indwaitId= wait.get(Username);
			
			if(indId !=null) {
				courseitr.remove();
				ArrayList<Index> indices = cur.getIndices();
				Iterator<Index> Indexitr = indices.iterator();
			
				while (Indexitr.hasNext()){
					Index ind = Indexitr.next();
				
					if(indId == ind.getIndexId()) {
						Indexitr.remove();
						ArrayList<Student> students = ind.getRegistered();
						Iterator<Student> studitr = students.iterator();
					
						while(studitr.hasNext()) {
							Student stud=studitr.next();
						
							if(Username == fileController.getStudent_Username(stud)) {
								studitr.remove();
								students.add(name);
								break;
								}
						}
						ind.setRegistered(students);
						indices.add(ind);
						break;
					}
				}
				cur.setIndices(indices);
				updated.add((Object)cur);
				
			}
			else if(indwaitId!=null) {	
				courseitr.remove();
				ArrayList<Index> indices = cur.getIndices();
				Iterator<Index> Indexitr = indices.iterator();
			
				while (Indexitr.hasNext()){
					Index ind = Indexitr.next();
				
					if(indId == ind.getIndexId()) {
						Indexitr.remove();
						ArrayList<Student> students = ind.getWaitlist();
						Iterator<Student> studitr = students.iterator();
					
						while(studitr.hasNext()) {
							Student stud=studitr.next();
						
							if(Username == fileController.getStudent_Username(stud)) {
								studitr.remove();
								students.add(name);
								break;
								}
						}
						ind.setRegistered(students);
						indices.add(ind);
						break;
					}
				}
				cur.setIndices(indices);
				updated.add((Object)cur);
			}		
		}
		for(int i=0;i<updated.size();i++) {
			courseDetails.add((Object)updated.get(i));
		}
		binaryio.clearwriteSerializedObject("courses.dat", courseDetails);		
	}
	
	public static void updateCoursefile_course(Course cour) {
			ArrayList<Object> courseDetails = getCourses();
			Iterator<Object> courseitr = courseDetails.iterator();
			while(courseitr.hasNext()){
				Course cur  = (Course)courseitr.next();
				if((cour.getCourseCode()).equals(cur.getCourseCode())) {
					courseitr.remove();
					courseDetails.add((Object)cour);
					break;
				}
			}
			binaryio.clearwriteSerializedObject("courses.dat", courseDetails);		
		}

	

	

		

	
	
	


}
