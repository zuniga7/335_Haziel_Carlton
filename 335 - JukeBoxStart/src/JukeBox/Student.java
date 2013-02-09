/*=============================================================================
 |   Assignment:  Program #2 
 |      Authors:  Carlton Ochoa (cochoa@email.arizona.edu)
 |				  Haziel Zuniga (zuniga7@email.arizona.edu)
 |
 |       Course:  335
 |   Instructor:  R. Mercer
 |     Due Date:  Tuesday February 12, 2013 at 3:00
 |
 |  Description:  This class is what represents a student object.
 |                
 *===========================================================================*/
package JukeBox;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Student implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 8852680785985926563L;
	private int currentTimeRemaining;
	private String studentName = "";
	private int playsForTheDay;
	private Calendar originalDay;
	private Song nameOfSong;
	private String ID;

	/**
	 * Set the students starting time
	 * 
	 * @param name
	 * @param identification
	 */
	public Student(String name, String identification) {

		studentName = name;
		ID = identification;
		currentTimeRemaining = 1500 * 60;
		playsForTheDay = 0;
		originalDay = new GregorianCalendar();

	}

	/**
	 * Returns the student's name
	 * 
	 * @return
	 */
	public String getName() {
		return studentName;
	}

	/**
	 * Returns the student's ID
	 * 
	 * @return
	 */
	public String getID() {
		return ID;
	}

	/**
	 * Get the song object
	 * 
	 * @param nameOfSong
	 */

	public void setSong(Song nameOfSong) {
		this.nameOfSong = nameOfSong;
	}

	/**
	 * Increments plays for the day
	 */

	public void songWasPlayed() {
		if (studentCanPlay()) {
			playsForTheDay++;
			subtractTime();
		}

	}

	/**
	 * Get the current time remaining for the current user
	 * 
	 * @return
	 */

	public int getAvailableMinutes() {

		return currentTimeRemaining;

	}

	/**
	 * Get the total number of plays this student has accumulated today
	 * 
	 * @return playsForTheDay
	 */
	public int getPlaysForTheDay() {
		return playsForTheDay;
	}

	/**
	 * Subtract the chosen song length from the running total of the current
	 * time remaining
	 * 
	 * @param songLength
	 */

	public void subtractTime() {

		if (currentTimeRemaining - nameOfSong.getLength() < 0) {
			;
		} else {

			currentTimeRemaining = currentTimeRemaining
					- nameOfSong.getLength();
		}

	}

	/**
	 * Return whether or not this student is eligible to play songs
	 * 
	 * @return
	 */

	public boolean studentCanPlay() {

		GregorianCalendar today = new GregorianCalendar();

		isSameDay(today);

		if (nameOfSong != null
				&& (currentTimeRemaining - nameOfSong.getLength() < 0)) {
			return false;
		}
		// count from 0 to 1
		return (playsForTheDay < 2);

	}

	private boolean isSameDay(GregorianCalendar today) {

		// two days are the same
		if (originalDay.get(originalDay.YEAR) == today.get(today.YEAR)
				&& originalDay.get(originalDay.MONTH) == today.get(today.MONTH)
				&& originalDay.get(originalDay.DATE) == today.get(today.DATE))
			return true;

		// it is a brand new day!
		else {
			originalDay = today;
			playsForTheDay = 0;
			return false;
		}
	}

	public void pretendItsTomorrow() {

		originalDay.roll(originalDay.DAY_OF_MONTH, 1); // tomorrow
	}

}
