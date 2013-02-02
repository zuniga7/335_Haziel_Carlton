package JukeBox;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Student {

	private int songChosenLength;
	private int currentTimeRemaining = 1500 * 60;
	private String studentName = "";
	private StudentList<String, Integer> theStudents = new StudentList<String, Integer>();
	private int playsForTheDay = 0;
	private Calendar originalDay;


	/**
	 * Set the students starting time
	 * 
	 * @param name
	 * @param identification
	 */
	public Student(String name, int identification) {

		if (theStudents.wasLoginSuccessful(name, identification)) {

			studentName = name;
		}

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
	 * get the length of the song
	 * 
	 * @param nameOfSong
	 */

	public int getSongLength(Song nameOfSong) {

		songChosenLength = nameOfSong.getLength();
		return songChosenLength;

	}
	/**
	 * Increments plays for the day
	 */
	
	public void songWasPlayed() {
		if (studentCanPlay()) {
			playsForTheDay++;
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
	 * Subtract the chosen song length from the running total of the current
	 * time remaining
	 * 
	 * @param songLength
	 */

	public void subtractTime(int songLength) {

		currentTimeRemaining = currentTimeRemaining - songLength;

	}

	/**
	 * Return whether or not this student is eligible to play songs
	 * 
	 * @return
	 */

	public boolean studentCanPlay() {

		GregorianCalendar today = new GregorianCalendar();

		isSameDay(today);

		// count from 0 to 4
		return (playsForTheDay < 5);

	}
	
	private boolean isSameDay(GregorianCalendar today) {

		// two days are the same
		if (originalDay.YEAR == today.YEAR && originalDay.MONTH == today.MONTH
				&& originalDay.DATE == today.DATE)
			return true;

		// it is a brand new day!
		else {
			originalDay = today;
			playsForTheDay = 0;
			return false;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
