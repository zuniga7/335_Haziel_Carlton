/*=============================================================================
 |   Assignment:  Program #2 
 |      Authors:  Carlton Ochoa (cochoa@email.arizona.edu)
 |				  Haziel Zuniga (zuniga7@email.arizona.edu)
 |
 |       Course:  335
 |   Instructor:  R. Mercer
 |     Due Date:  Tuesday February 12, 2013 at 3:00
 |
 |  Description:  This class is what represents a collection of song objects. 
 |				  A song collection takes care of having songs in the song list 
 |				  as well as in the playlist. It can add songs to the playList,
 |				  remove the top song, and "play" songs
 |                
 *===========================================================================*/
package JukeBox;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.Calendar;

import java.util.GregorianCalendar;

import java.util.LinkedList;

import java.util.Queue;

import songplayer.EndOfSongEvent;

import songplayer.EndOfSongListener;

import songplayer.SongPlayer;

public class SongCollection implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7286274801084824047L;
	private ArrayList<Song> songList;
	private Queue<Song> playList;

	/**
	 * 
	 * constructor to create a new Song Collection of song objects
	 */

	public SongCollection() {
		songList = new ArrayList<Song>();
		playList = new LinkedList<Song>();

		// add songs
		Song song1 = new Song("BlueRidgeMountainMist",
				"BlueRidgeMountainMist.mp3", "unknown artist", 38);
		Song song2 = new Song("DeterminedTumbao", "DeterminedTumbao.mp3",
				"Freeplay Music", 20);
		Song song3 = new Song("flute", "flute.aif", "unknown artist", 6);
		Song song4 = new Song("spacemusic", "spacemusic.au", "unknown artist",
				7);
		Song song5 = new Song("SwingCheese", "SwingCheese.mp3",
				"Freeplay Music", 15);
		Song song6 = new Song("tada", "tada.wav", "unknown artist", 2);
		Song song7 = new Song("UntameableFire", "UntameableFire.mp3",
				"unknown artist", 283);

		songList.add(song1);
		songList.add(song2);
		songList.add(song3);
		songList.add(song4);
		songList.add(song5);
		songList.add(song6);
		songList.add(song7);

	}

	/**
	 * gets the ArrayList containing the whole collection of songs
	 * 
	 * @return ArrayList songList
	 */

	public ArrayList<Song> getCollectionList() {
		return songList;
	}

	/**
	 * gets the Queue containing the whole playList of songs
	 * 
	 * @return Queue playList
	 */

	public Queue<Song> getPlayList() {
		return playList;
	}

	/**
	 * adds a song object into the song collection (ArrayList songList)
	 */

	public void addToSongList(Song newSong) {
		songList.add(newSong);
	}

	/**
	 * adds the selected song object (selected index) into the playlist queue
	 * (Queue playList)
	 */
	public void addToPlayList(int index) {
		Song selectedSong = songList.get(index);

		if (selectedSong.canPlaySong()) {
			playList.add(selectedSong);
			selectedSong.playSong();
		}
	}

	/**
	 * tells song player to play only the top song of the playList
	 */
	public void playSongAtTopOfPlayList() {

		if (playList.peek() != null) {
			ObjectWaitingForSongToEnd waiter = new ObjectWaitingForSongToEnd();
			SongPlayer.playFile(waiter, playList.peek().getSongDirectory());
		}
	}

	/**
	 * 
	 * remove the Song at the head of the playList
	 */
	public void removeTopSong() {
		playList.poll();

	}

	/**
	 * 
	 * An inner class that allows an instance of this to receive a
	 * songFinishedPlaying when the audio file has been played. Note: static was
	 * added here because it is called from main.
	 */

	private class ObjectWaitingForSongToEnd implements EndOfSongListener {

		public void songFinishedPlaying(EndOfSongEvent eosEvent) {
			System.out.print("Finished " + eosEvent.fileName());
			GregorianCalendar finishedAt = eosEvent.finishedTime();
			System.out.println(" at " + finishedAt.get(Calendar.HOUR_OF_DAY)
					+ ":" + finishedAt.get(Calendar.MINUTE) + ":"
					+ finishedAt.get(Calendar.SECOND));
		}
	}

	/**
	 * resets all of the number of plays for the day for all the songs in the
	 * collection
	 */
	public void resetPlays() {
		for (int x = 0; x < songList.size(); x++) {
			songList.get(x).canPlaySong();
		}

	}

}