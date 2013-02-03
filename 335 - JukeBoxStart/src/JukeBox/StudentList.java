package JukeBox;

import java.util.HashMap;

//hardcore those into the collection
public class StudentList {

	private HashMap<String, String> students = new HashMap<String, String>();

	/**
	 * Hashmap of current users and their passwords.
	 */
	public StudentList() {

		students.put("Ali", "1111");
		students.put("Chris", "2222");
		students.put("River", "3333");
		students.put("Ryan", "4444");

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

	public HashMap<String, String> returnTheList() {

		HashMap<String, String> copyOfList = new HashMap<String, String>();

		copyOfList = students;

		return copyOfList;

	}

}
