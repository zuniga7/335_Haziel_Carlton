import java.util.Calendar;
import java.util.GregorianCalendar;

public class Student {

	private int startingTime = 1500 * 60;
	private int songChosenLength;
	private int currentTimeRemaining;
	private String studentName = "";
	private int IDNumber;
	private StudentList theStudents = new StudentList();
	private SongCollection songCollection = new SongCollection();
	private int playsForTheDay = 0;
	private Calendar cal1 = new GregorianCalendar();
	private boolean canPlay;

	public Student(String name, int identification) {

		studentName = name;
		IDNumber = identification;
		currentTimeRemaining = startingTime;

	}

	public void chooseSong(Song nameOfSong) {

		songChosenLength = nameOfSong.getLength();
		this.subtractTime(songChosenLength);

		if (this.studentCanPlay() == true) {

			playsForTheDay++;

		}

	}

	public int getAvailableMinutes() {

		return currentTimeRemaining;

	}

//	public void calculateCurrentTimeRemaining() {
//
//		// int temp = this.getAvailableMinutes();
//
//	}

	public void subtractTime(int songLength) {

		currentTimeRemaining = currentTimeRemaining - songLength;

	}

	public boolean studentCanPlay() {

		if (playsForTheDay > 2 && this.getAvailableMinutes() < 0) {
			canPlay = false;
			return canPlay;

		} else {
			canPlay = true;
			return canPlay;
		}

	}

}
