/*=============================================================================
 |   Assignment:  Program #2 
 |      Authors:  Carlton Ochoa (cochoa@email.arizona.edu)
 |				  Haziel Zuniga (zuniga7@email.arizona.edu)
 |
 |       Course:  335
 |   Instructor:  R. Mercer
 |     Due Date:  Tuesday February 12, 2013 at 3:00
 |
 |  Description:  This class is what represents a song object.A song has a name, a 
 |				  length, an artist, a filelocation, and the number of plays. It also
 |				  checks if a song can be played on this same day
 |                
 *===========================================================================*/
package JukeBox;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Song implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5647356223212847250L;
	private String songName;
	private int numPlays = 0;
	private int songLength;
	private String fileLocation;
	private String artist;

	private Calendar originalDay;

	/**
	 * constructor for a song object
	 * 
	 * @param directory
	 */
	public Song(String name, String directory, String artist1, int length) {
		songName = name;
		songLength = length; // length of song is in seconds
		fileLocation = "./songfiles/" + directory;
		artist = artist1;

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
	 * returns the artist name for this song
	 * 
	 * @return
	 */
	public String getArtist() {
		return artist;
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
