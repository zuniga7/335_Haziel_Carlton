package JukeBox;

import java.util.HashMap;

//hardcore those into the collection
public class StudentList {

	private HashMap<String, Student> students = new HashMap<String, Student>();
	Student currentStudent = new Student(null, null);

	/**
	 * Hashmap of current users and their passwords.
	 */
	public StudentList() {

		Student student1 = new Student("Ali", "1111");
		Student student2 = new Student("Chris", "2222");
		Student student3 = new Student("River", "3333");
		Student student4 = new Student("Ryan", "4444");

		students.put("Ali", student1);
		students.put("Chris", student2);
		students.put("River", student3);
		students.put("Ryan", student4);

	}

	/**
	 * returns a Student from the collection by giving in their name
	 */
	public Student getStudent(String name) {

		return students.get(name);

	}

	/**
	 * Determines whether the user exists and whether the password matches
	 * 
	 * @param name
	 * @param password
	 * @return boolean of whether the user exists
	 */
	public boolean wasLoginSuccessful(String name, String password) {

		if (students.get(name) != null)
			return password.equals(students.get(name).getID());
		else
			return false;

	}

	/**
	 * 
	 * @return
	 */

	public HashMap<String, Student> returnTheList() {

		HashMap<String, Student> copyOfList = new HashMap<String, Student>();

		copyOfList = students;

		return copyOfList;

	}

}