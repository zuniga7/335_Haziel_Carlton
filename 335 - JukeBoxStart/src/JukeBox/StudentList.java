/*=============================================================================
 |   Assignment:  Program #2 
 |      Authors:  Carlton Ochoa (cochoa@email.arizona.edu)
 |				  Haziel Zuniga (zuniga7@email.arizona.edu)
 |
 |       Course:  335
 |   Instructor:  R. Mercer
 |     Due Date:  Tuesday February 12, 2013 at 3:00
 |
 |  Description:  This class is what represents a collection of student objects.
 |                
 *===========================================================================*/
package JukeBox;

import java.io.Serializable;
import java.util.HashMap;

public class StudentList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3155742663053740950L;
	
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

	//Deleted returnTheList

}
