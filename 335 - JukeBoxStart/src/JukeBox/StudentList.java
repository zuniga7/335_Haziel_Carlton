package JukeBox;

import java.util.HashMap;

//hardcore those into the collection
public class StudentList<K, V> {

	private HashMap<String, Integer> students = new HashMap<String, Integer>();

	/**
	 * Hashmap of current users and their passwords.
	 */
	public StudentList() {

		students.put("Ali", 1111);
		students.put("Chris", 2222);
		students.put("River", 3333);
		students.put("Ryan", 4444);

	}

	/**
	 * Determines whether the user exists and whether the password matches
	 * 
	 * @param name
	 * @param password
	 * @return boolean of whether the user exists
	 */

	public boolean wasLoginSuccessful(String name, int password) {
		
		return password == students.get(name);
		
	}

}
