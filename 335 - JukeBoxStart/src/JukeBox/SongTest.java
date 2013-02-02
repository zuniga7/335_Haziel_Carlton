package JukeBox;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;

public class SongTest {

	@Test
	public void testGeneralSongs() {
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
	public void testAddDaysToSongs() {
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

	@Test
	public void testSongCollectionGeneral() {
		SongCollection sc = new SongCollection();

		// add songs
		Song song1 = new Song("BlueRidgeMountain.mp3", 38);
		Song song2 = new Song("DeterminedTumbao.mp3", 20);
		Song song3 = new Song("flute.mp3", 6);
		Song song4 = new Song("spacemusic.mp3", 1);
		Song song5 = new Song("SwingCheese.mp3", 15);
		Song song6 = new Song("tada.mp3", 2);
		Song song7 = new Song("UntameableFire.mp3", 283);

		ArrayList<Song> songList = new ArrayList<Song>();
		songList.add(song1);
		songList.add(song2);
		songList.add(song3);
		songList.add(song4);
		songList.add(song5);
		songList.add(song6);
		songList.add(song7);

		// check if all elements are there
		Object[] list1 = new Object[7];
		list1 = songList.toArray();
		Object[] list2 = new Object[7];
		list2 = sc.getCollectionList().toArray();

		for (int x = 0; x < list1.length; x++)
			assertEquals(list1[x], list2[x]);
	}

	@Test
	public void testSongPlayListGeneral() {
		SongCollection sc = new SongCollection();

		// add 1st song 1
		sc.addToPlayList(0);
		System.out.println(sc.getPlayList());
		
		// add 1st song 2
		sc.addToPlayList(0);
		System.out.println(sc.getPlayList());
		
		// add 1st song 3
		sc.addToPlayList(0);
		System.out.println(sc.getPlayList());
		
		// add 1st song 4
		sc.addToPlayList(0);
		System.out.println(sc.getPlayList());
		
		// add 1st song 5
		sc.addToPlayList(0);
		System.out.println(sc.getPlayList());
		
		// add 1st song 6? -- nope
		sc.addToPlayList(0);
		System.out.println(sc.getPlayList());
		
		// add 2nd song 1
		sc.addToPlayList(1);
		System.out.println(sc.getPlayList());
		
		// remove top song1
		sc.removeTopSong();
		System.out.println(sc.getPlayList());
		
		// remove top song2
		sc.removeTopSong();
		System.out.println(sc.getPlayList());
		
		// remove top song3
		sc.removeTopSong();
		System.out.println(sc.getPlayList());
		
		// remove top song4
		sc.removeTopSong();
		System.out.println(sc.getPlayList());
		
		// remove top song5
		sc.removeTopSong();
		System.out.println(sc.getPlayList());
		
		// remove top song6
		sc.removeTopSong();
		System.out.println(sc.getPlayList());
		
		// remove top song7? -- with nothing in playList
		sc.removeTopSong();
		System.out.println(sc.getPlayList());

	}
}
