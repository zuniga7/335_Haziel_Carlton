package JukeBox;

import static org.junit.Assert.*;

import org.junit.Test;

public class SongTest {

	@Test
	public void testGeneral() {
		// add songs
		Song song1 = new Song("tada.mp3", 2);
		Song song2 = new Song("flute.mp3", 6);

		// names
		assertEquals(song1.getName(), "tada.mp3");
		assertEquals(song2.getName(), "flute.mp3");

		// get num plays
		assertEquals(song1.getNumPlays(), 0);
		assertEquals(song2.getNumPlays(), 0);

		// get length
		assertEquals(song1.getLength(), 2);
		assertEquals(song2.getLength(), 6);

		// can play song
		assertTrue(song1.canPlaySong());
		assertTrue(song2.canPlaySong());

		// make sure number of plays wasnt modified
		assertEquals(song1.getNumPlays(), 0);
		assertEquals(song2.getNumPlays(), 0);
	}

	@Test
	public void testAddDays() {
		Song song1 = new Song("tada.mp3", 2);
		assertEquals(song1.getNumPlays(), 0);
		assertTrue(song1.canPlaySong());

		// play 1
		song1.playSong();
		assertEquals(song1.getNumPlays(), 1);
		assertTrue(song1.canPlaySong());

		// play2
		song1.playSong();
		assertEquals(song1.getNumPlays(), 2);
		assertTrue(song1.canPlaySong());

		// play3
		song1.playSong();
		assertEquals(song1.getNumPlays(), 3);
		assertTrue(song1.canPlaySong());

		// play4
		song1.playSong();
		assertEquals(song1.getNumPlays(), 4);
		assertTrue(song1.canPlaySong());

		// play5
		song1.playSong();
		assertEquals(song1.getNumPlays(), 5);
		assertFalse(song1.canPlaySong());
		
		// play6??? -- nope
		song1.playSong();
		assertEquals(song1.getNumPlays(), 5);
		assertFalse(song1.canPlaySong());
		
		// pretend its tomorrow -- should play
		song1.pretendItsTomorrow();
		assertEquals(0, song1.getNumPlays());
		assertTrue(song1.canPlaySong());

	}

}
