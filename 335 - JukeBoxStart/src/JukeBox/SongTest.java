package JukeBox;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import demoSongPlayer.Play1SongNoListener;
import demoSongPlayer.Play3SongsWithAListener;

public class SongTest {

	@Test
	public void testGeneralSongs() {
		// add songs
		Song song1 = new Song("tada", "tada.mp3", 2);
		Song song2 = new Song("flute", "flute.mp3", 6);

		// names
		assertEquals(song1.getName(), "tada");
		assertEquals(song2.getName(), "flute");

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
		Song song1 = new Song("tada", "tada.mp3", 2);
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

		assertEquals(7, sc.getCollectionList().size());

		sc.addToSongList(new Song("Hello World", "hello.mp3", 5));
	}

	@Test
	public void testSongPlayListGeneral() {
		SongCollection sc = new SongCollection();
		sc.playSongAtTopOfPlayList();

		// add 1st song 1
		sc.addToPlayList(0);
		System.out.println(sc.getPlayList());
		sc.playSongAtTopOfPlayList();

		// add 1st song 2
		sc.addToPlayList(0);
		System.out.println(sc.getPlayList());
		sc.playSongAtTopOfPlayList();

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

	@Test
	public void test() {
		Play1SongNoListener play = new Play1SongNoListener();
		play.run();
	}

	@Test
	public void testStudentList() {

		StudentList newList = new StudentList();

		Student student1 = newList.getStudent("Ali");

		assertEquals(student1, newList.getStudent("Ali"));

		assertTrue("1111".equals(student1.getID()));

	}

	@Test
	public void testStudentList2() {

		StudentList newList = new StudentList();

		Student student1 = newList.getStudent("River");

		assertTrue("3333".equals(student1.getID()));
		Student student2 = newList.getStudent("Chris");

		assertTrue("2222".equals(student2.getID()));
		Student student3 = newList.getStudent("Ryan");

		assertTrue("4444".equals(student3.getID()));

	}

	@Test
	public void testStudentList3() {

		StudentList newList = new StudentList();

		Student student1 = newList.getStudent("Ali");
		assertFalse("2222.".equals(student1.getID()));

		Student student2 = newList.getStudent("Chris");

		assertFalse("3333".equals(student2.getID()));
		Student student3 = newList.getStudent("Ryan");

		assertFalse("1111".equals(student3.getID()));

	}

	@Test
	public void testStudentListWasLoginSuccessful() {

		StudentList newList = new StudentList();

		assertTrue(newList.wasLoginSuccessful("Ali", "1111"));
		assertFalse(newList.wasLoginSuccessful("River", "1111"));
		assertTrue(newList.wasLoginSuccessful("River", "3333"));
		assertFalse(newList.wasLoginSuccessful("Chris", "1111"));
		assertTrue(newList.wasLoginSuccessful("Chris", "2222"));
		assertFalse(newList.wasLoginSuccessful("Ryan", "2222"));
		assertTrue(newList.wasLoginSuccessful("Ryan", "4444"));
		assertFalse(newList.wasLoginSuccessful("River", "1111"));
	}

	@Test
	public void testStudent() {

		Student student1 = new Student("Ali", "1111");
		assertTrue("Ali".equals(student1.getName()));
		assertEquals(1500 * 60, student1.getAvailableMinutes());
		assertEquals(0, student1.getPlaysForTheDay());
		Student student2 = new Student("Chris", "2222");
		assertTrue("Chris".equals(student2.getName()));
		assertEquals(1500 * 60, student2.getAvailableMinutes());
		assertEquals(0, student2.getPlaysForTheDay());
		Student student3 = new Student("River", "3333");
		assertTrue("River".equals(student3.getName()));
		assertEquals(1500 * 60, student3.getAvailableMinutes());
		assertEquals(0, student3.getPlaysForTheDay());
		Student student4 = new Student("Ryan", "4444");
		assertTrue("Ryan".equals(student4.getName()));
		assertEquals(1500 * 60, student4.getAvailableMinutes());
		assertEquals(0, student4.getPlaysForTheDay());

	}

	// comment
	@Test
	public void testStudent2() {

		Student student1 = new Student("Ali", "1111");
		assertFalse("River".equals(student1.getName()));
		assertFalse(1900 * 60 == student1.getAvailableMinutes());
		assertFalse(4 == student1.getPlaysForTheDay());

	}

	@Test
	public void testStudent3() {
		SongCollection sc = new SongCollection();
		StudentList newList = new StudentList();

		Student student = newList.getStudent("Ali");
		ArrayList<Song> list = new ArrayList<Song>();
		list = sc.getCollectionList();

		if (list.get(3).canPlaySong())
			student.setSong(list.get(3));

	}

	@Test
	public void testMUSIC() {
		Play3SongsWithAListener test = new Play3SongsWithAListener();
		test.test();
	}

}
