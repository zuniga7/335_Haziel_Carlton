package JukeBox;

import java.util.HashMap;

//hardcore those into the collection
public class StudentList {

	private HashMap<Student, String> students = new HashMap<Student, String>();

	/**
	 * Hashmap of current users and their passwords.
	 */
	public StudentList() {

		students.put(new Student("Ali", "1111"), "1111");
		students.put(new Student("Chris", "2222"), "2222");
		students.put(new Student("River", "3333"), "3333");
		students.put(new Student("Ryan", "4444"), "4444");

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
