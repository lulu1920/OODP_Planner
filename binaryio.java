package P1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


public class binaryio {
	public static ArrayList<Object> readSerializedObject(String filename) {
		ArrayList<Object> pDetails = new ArrayList<Object>();
		Object obj = null;
		try {
			ObjectInputStream o = new ObjectInputStream(new FileInputStream(filename));
			while ((obj = o.readObject()) != null) {
	            pDetails.add(obj);
	        }
			//pDetails = (ArrayList) o.readObject();
			o.close();
		} catch (IOException ex) {
			System.out.print("");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return pDetails;
	}
	
	public static void writeSerializedObject(String filename, Object write) {
		ArrayList<Object> pDetails = new ArrayList<Object>();
		Object obj = null;
		try {
			ObjectInputStream o = new ObjectInputStream(new FileInputStream(filename));
			while ((obj = o.readObject()) != null) {
				System.out.print(obj);
	            pDetails.add(obj);
	        }
			o.close();
		} catch (IOException ex) {
			System.out.print("");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		try {
			pDetails.add(write);
			ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(filename));
			o.writeObject(pDetails);
			o.close();
		} catch (IOException ex) {
			System.out.print("");
		}
		
	}
}
