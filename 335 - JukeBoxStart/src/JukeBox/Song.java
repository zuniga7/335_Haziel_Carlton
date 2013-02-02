package JukeBox;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Song {
	private String songName;
	private int numPlays = 0;
	private int songLength;

	private Calendar originalDay;

	/**
	 * constructor for a song object
	 */
	public Song(String name, int length) {
		songName = name;
		songLength = length; // length of song is in seconds

		originalDay = new GregorianCalendar(); // new calendar

	}

	/**
	 * gets the length of a song in seconds
	 * 
	 * @return songLength
	 */
	public int getLength() {
		return songLength;
	}

	/**
	 * gets the name of a song
	 * 
	 * @return songName
	 */
	public String getName() {
		return songName;

	}

	/**
	 * gets the the total number of plays this song has been played in a single
	 * day
	 * 
	 * @return numPlays
	 */
	public int getNumPlays() {

		GregorianCalendar today = new GregorianCalendar();

		isSameDay(today);

		return numPlays;

	}

	/**
	 * does the necessary steps in order to play a song
	 */
	public void playSong() {
		if (canPlaySong()) {
			numPlays++;

			// set up to play
		}

	}

	/**
	 * returns if it is possible to play this song
	 * 
	 * @return true -- the song has been played <=5 times today... else return
	 *         false
	 */
	public boolean canPlaySong() {

		GregorianCalendar today = new GregorianCalendar();

		isSameDay(today);

		// count from 0 to 4
		return (numPlays < 5);
	}

	/**
	 * private helper method to check if two days are the same
	 * 
	 * @param today
	 * @return
	 */
	private boolean isSameDay(GregorianCalendar today) {

		// two days are the same
		if (originalDay.YEAR == today.YEAR && originalDay.MONTH == today.MONTH
				&& originalDay.DATE == today.DATE)
			return true;

		// it is a brand new day!
		else {
			originalDay = today;
			numPlays = 0;
			return false;
		}
	}

	/**
	 * pretends its tomorrow -- NOT WORKING BUT IDK WHY!!!!
	 */
	public void pretendItsTomorrow() {
		System.out.println(GregorianCalendar.DAY_OF_MONTH);
		
		Calendar tomorrow = new GregorianCalendar(Calendar.YEAR, Calendar.MONTH,
				Calendar.DATE + 1);
		
		originalDay.clear();
		
		System.out.println(originalDay.YEAR);
		originalDay.roll(GregorianCalendar.DAY_OF_MONTH, 1); // tomorrow
		
		
		System.out.println(tomorrow.DAY_OF_MONTH);
		System.out.println(originalDay.DAY_OF_MONTH);

	}

}
