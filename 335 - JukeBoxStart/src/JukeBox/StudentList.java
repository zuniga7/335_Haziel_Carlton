package JukeBox;

import java.util.HashMap;

//hardcore those into the collection
public class StudentList {

	private HashMap<Student, String> students = new HashMap<Student, String>();

	/**
	 * Hashmap of current users and their passwords.
	 */
	public StudentList() {

		Student student1 = new Student("Ali", "1111");
		Student student2 = new Student("Chris", "2222");
		Student student3 = new Student("River", "3333");
		Student student4 = new Student("Ryan", "4444");

		// please do this:
		//
		// WE NEED TO CHANGE THE VALUE OPTION TO STUDENT!!! AND THE KEY SHOULD
		// BE THE NAME (STRING).... PLEASE SWITCH THEM AROUND
		//
		// change everything as necessary
		// 
		// after that.... implement the method below
		//
		// THANK YOU!
		
		students.put(student1, "1111");
		students.put(student2, "2222");
		students.put(student3, "3333");
		students.put(student4, "4444");

	}
	
	/**
	 * implement this!
	 */
	public Student getStudent(String name){
		
	}
	
	
	

	/**
	 * Determines whether the user exists and whether the password matches
	 * 
	 * @param name
	 * @param password
	 * @return boolean of whether the user exists
	 */
	public boolean wasLoginSuccessful(String name, String password) {

		return password.equals(students.get(name));

	}


	/**
	 * 
	 * @return
	 */

	public HashMap<Student, String> returnTheList() {

		HashMap<Student, String> copyOfList = new HashMap<Student, String>();

		copyOfList = students;

		return copyOfList;

	}

}
