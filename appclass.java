package P1;

import java.io.BufferedReader;
import java.io.Console;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
public class appclass {
	
	
	public static void main(String[] args) throws ParseException {
		LocalDate ld =LocalDate.of(2021, 1, 30); 
		LocalDate ld1 =LocalDate.of(2020, 11, 01); 
		
		//create objects and save in file
		Course foo1=new Course("Cz2002","OOPD","lec");
		Course foo5=new Course("Cz2001","ALGO","lec");		//binaryio.writeSerializedObject("courses.bin",foo1);
		User foo2=new Student("Ankitha","minnu","lolnoice", "k119",  ld,  ld1);
		User foo3=new Student("Agnesh","agnesh","hahaokok", "uuU118",  ld,  ld1);
		User foo4=new Student("Agneshee","agneshf","hahaok", "uuU118s",  ld,  ld1);
		

		//read file
		ArrayList<Object> al = binaryio.readSerializedObject("students.dat");
		if(al.size()==0) {
			System.out.println("Error reading the file!!");
			return;
		}
		for (int i = 0 ; i <al.size() ; i++) {
			Student xyz  = (Student)al.get(i);
			System.out.println(xyz.getUsername());
			for (int j = 0 ; j <xyz.getIndices().size() ; j++) {
				System.out.println(xyz.getIndices().get(j).getCourseId());
			}
		}	
	}
}
