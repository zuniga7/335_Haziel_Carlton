package JukeBox;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Song {
	private String songName;
	private int numPlays = 0;
	private int songLength;
	private String fileLocation;

	private Calendar originalDay;

	/**
	 * constructor for a song object
	 * 
	 * @param directory
	 */
	public Song(String name, String directory, int length) {
		songName = name;
		songLength = length; // length of song is in seconds
		fileLocation = "./songfiles/" + directory;

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
	 * returns the directory containing the song file
	 * 
	 * @return String fileLocation
	 */
	public String getSongDirectory() {
		return fileLocation;
	}

	/**
	 * simulates playing the song by adding one to the number of plays
	 */
	public void playSong() {
		if (canPlaySong()) {
			numPlays++;
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
		if (originalDay.get(originalDay.YEAR) == today.get(today.YEAR)
				&& originalDay.get(originalDay.MONTH) == today.get(today.MONTH)
				&& originalDay.get(originalDay.DATE) == today.get(today.DATE))
			return true;

		// it is a brand new day!
		else {
			originalDay = today;
			numPlays = 0;
			return false;
		}
	}

	/**
	 * pretends its tomorrow for testing purposes
	 */
	public void pretendItsTomorrow() {

		originalDay.roll(originalDay.DAY_OF_MONTH, 1); // tomorrow
	}

}
