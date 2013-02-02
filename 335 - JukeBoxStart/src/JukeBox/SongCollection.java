package JukeBox;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

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
		Song song1 = new Song("BlueRidgeMountain.mp3", 38);
		Song song2 = new Song("DeterminedTumbao.mp3", 20);
		Song song3 = new Song("flute.mp3", 6);
		Song song4 = new Song("spacemusic.mp3", 1);
		Song song5 = new Song("SwingCheese.mp3", 15);
		Song song6 = new Song("tada.mp3", 2);
		Song song7 = new Song("UntameableFire.mp3", 283);
		
		songList.add(song1);
		songList.add(song2);
		songList.add(song3);
		songList.add(song4);
		songList.add(song5);
		songList.add(song6);
		songList.add(song7);
		
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
	public void addToPlayList(Song selectedSong) {
		playList.add(selectedSong);
	}
	
	/**
	 *  remove the Song at the head of the playList
	 */
	public void removeTopSong(){
		playList.poll();
	}
	

}
