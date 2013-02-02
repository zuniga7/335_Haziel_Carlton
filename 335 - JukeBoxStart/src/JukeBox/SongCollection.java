package JukeBox;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Queue;

import songplayer.EndOfSongEvent;
import songplayer.EndOfSongListener;
import songplayer.SongPlayer;

public class SongCollection {
	private ArrayList<Song> songList;
	private Queue<Song> playList;

	/**
	 * constructor to create a new Song Collection of song objects
	 */
	public SongCollection() {
		songList = new ArrayList<Song>();
		playList = new LinkedList<Song>();

		// add songs
		Song song1 = new Song("BlueRidgeMountainMist", "BlueRidgeMountainMist.mp3", 38);
		Song song2 = new Song("DeterminedTumbao", "DeterminedTumbao.mp3", 20);
		Song song3 = new Song("flute", "flute.aif", 6);
		Song song4 = new Song("spacemusic", "spacemusic.au", 1);
		Song song5 = new Song("SwingCheese", "SwingCheese.mp3", 15);
		Song song6 = new Song("tada", "tada.wav", 2);
		Song song7 = new Song("UntameableFire", "UntameableFire.mp3", 283);

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
	 * adds a song object into the playlist queue (Queue playList)
	 */
	public void addToPlayList(int index) {
		Song selectedSong = songList.get(index);

		if (selectedSong.canPlaySong()) {
			playList.add(selectedSong);
			selectedSong.playSong();
		}
	}

	public void playSongAtTopOfPlayList() {
		
	    SongPlayer.playFile("./songfiles/BlueRidgeMountainMist.mp3");
	    
		//while (playList.peek() != null) {
		//    ObjectWaitingForSongToEnd waiter = new ObjectWaitingForSongToEnd();
		//	SongPlayer.playFile(waiter, playList.peek().getSongDirectory());
	//	}
	}

	/**
	 * remove the Song at the head of the playList
	 */
	public void removeTopSong() {
		playList.poll();
	}

	// reset all numPlays for a new day??

	 /**
	   * An inner class that allows an instance of this to receive a
	   * songFinishedPlaying when the audio file has been played. Note: static was
	   * added here because it is called from main.
	   */
	  private class ObjectWaitingForSongToEnd implements EndOfSongListener {

	    public void songFinishedPlaying(EndOfSongEvent eosEvent) {
	      System.out.print("Finished " + eosEvent.fileName());
	      GregorianCalendar finishedAt = eosEvent.finishedTime();
	      System.out.println(" at " + finishedAt.get(Calendar.HOUR_OF_DAY) + ":"
	          + finishedAt.get(Calendar.MINUTE) + ":"
	          + finishedAt.get(Calendar.SECOND));
	    }
	  }
}
