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

}
