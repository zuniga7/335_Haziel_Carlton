public class Song {
	private String songName;
	private int numPlays = 0;
	private int songLength;

	/**
	 * constructor for a song object
	 */
	public Song(String name, int length) {
		songName = name;
		songLength = length; // length of song is in seconds
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
		return numPlays;

	}

	/**
	 *  does the necessary steps in order to play a song
	 */
	public void playSong() {

	}
	
	/**
	 * returns if it is possible to play this song
	 * @return true -- the song has been played <=2 times
	 */
	public boolean canPlaySong(){
		return (numPlays<=2);
	}

}
