/*=============================================================================
 |   Assignment:  Program #2 
 |      Authors:  Carlton Ochoa (cochoa@email.arizona.edu)
 |				  Haziel Zuniga (zuniga7@email.arizona.edu)
 |
 |       Course:  335
 |   Instructor:  R. Mercer
 |     Due Date:  Tuesday February 12, 2013 at 3:00
 |
 |  Description:  This program is the GUI for the jukebox. It takes care of all
 |				  of the layout and visual aspects of a jukebox, including
 |				  allowing a user to login, logout, display their information,
 |				  display song information, the songlist, and playlist
 |                
 *===========================================================================*/

package jukeBoxGUI;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import JukeBox.Song;
import JukeBox.SongCollection;
import JukeBox.Student;
import JukeBox.StudentList;
import JukeBox.TableOfSongs;

public class JukeBoxGUI extends JFrame {

	private JButton playSongButton = new JButton();
	private JTable songTable;
	private JList queueList = new JList();
	private JLabel welcome = new JLabel("Welcome to the JukeBox!");
	private JButton loginButton = new JButton("Login");
	private JButton logoutButton = new JButton("Logout");

	private SongCollection songCollection = new SongCollection();
	private StudentList studentList = new StudentList();
	private JScrollPane songListScrollPane = new JScrollPane();
	private JScrollPane playListScroll = new JScrollPane(queueList);
	private Student loggedInStudent;

	private JTextField userNameField = new JTextField(10);
	private JPasswordField passField = new JPasswordField(10);
	private JLabel userLabel = new JLabel("UserName:");
	private JLabel passLabel = new JLabel("Password:");
	private final JLabel nowPlaying = new JLabel("Playing Next:");

	public static String baseDir = System.getProperty("user.dir")
			+ File.separator + "serializedObjects" + File.separator;
	public static final String FILE_NAME_TO_STORE_SONG_COLLECTION = baseDir
			+ "songCollection.object";
	public static final String FILE_NAME_TO_STORE_STUDENT_LIST = baseDir
			+ "studentCollection.object";

	/**
	 * 
	 * @param args
	 */

	public static void main(String[] args) {
		JFrame view = new JukeBoxGUI();
		view.setVisible(true);
	}

	/**
	 * setup all the visual components
	 */
	public JukeBoxGUI() {
		layoutGUI();
		registerListeners();

	}

	/**
	 * 
	 * make listeners for all the buttons
	 */

	private void registerListeners() {
		PlayButtonListener play = new PlayButtonListener();

		playSongButton.addActionListener(play);

		LoginButtonListener login = new LoginButtonListener();
		loginButton.addActionListener(login);

		LogoutButtonListener logout = new LogoutButtonListener();
		logoutButton.addActionListener(logout);

		MyWindowListener window = new MyWindowListener();
		this.addWindowListener(window);
	}

	/**
	 * 
	 * listener for the playButton -- adds a song to the play list queue
	 */
	private class PlayButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {

			// must be logged in to play music
			if (!loginButton.isEnabled()) {

				// if the user picked a song
				if (songTable.getSelectedRow() != -1) {

					// get the selected song and set it as the selection of the
					// user
					int rowIndex = songTable.getSelectedRow();

					// get actual song from rowIndex -- ADD -- TESTING
					int indexOfSong = getActualSong(rowIndex);
					Song selection = songCollection.getCollectionList().get(
							indexOfSong);

					loggedInStudent.setSong(selection);

					// check to see if the logged in student can Play the song
					if (loggedInStudent.studentCanPlay()) {

						// if you can play the current song
						if (selection.canPlaySong()) {
							// Subtract the time of song from user
							loggedInStudent.songWasPlayed();
							setUpTimeRemaining();

							// add selected song to playList
							songCollection.addToPlayList(indexOfSong);

							// refresh playList & songList
							setUpPlayList();
							// refreshSongList();

							// if the playList has 1 song... start playing music
							// playlist else, don't play playList again
							if (songCollection.getPlayList().size() == 1) {
								playAllPlayList playingPlayList = new playAllPlayList();
								new Thread(playingPlayList).start();
							}
						}
						// This song is no longer eligible to be played today
						else {
							String message = "Sorry, but this song is no longer eligible\n"
									+ " to be played today. Please try again tomorrow.";

							JOptionPane.showMessageDialog(playSongButton,
									message, "Unable to Play Current Song",
									JOptionPane.ERROR_MESSAGE);
						}

					}

					// The student is not eligible to play any more songs today
					else {
						String message = "Sorry, but you are either not able to play any\n"
								+ "more songs today or you do not have a sufficient amount\n"
								+ "of time available on your account to play the selected song.";

						JOptionPane.showMessageDialog(playSongButton, message,
								"Unable to Play More Songs",
								JOptionPane.ERROR_MESSAGE);
					}
				}
				// else tell the user to pick a song
				else {
					JOptionPane.showMessageDialog(playSongButton,
							"Please make a selection before playing a song",
							"Select a Song", JOptionPane.ERROR_MESSAGE);
				}
			}

			// else ask user to log in to their account if they arent logged in
			else {
				JOptionPane.showMessageDialog(playSongButton,
						"Must be logged in before attempting to play music",
						"Please Login", JOptionPane.ERROR_MESSAGE);
			}

		}

	}

	/**
	 * 
	 * listener for the loginButton -- attempts to login a user
	 */
	private class LoginButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String name = userNameField.getText();
			String pass = getPassword(passField.getPassword());

			if (studentList.wasLoginSuccessful(name, pass)) {
				loginButton.setEnabled(false);
				logoutButton.setEnabled(true);

				loggedInStudent = studentList.getStudent(name);

				// resets user info of playability
				loggedInStudent.studentCanPlay();

				setUpTimeRemaining();
			}

			else {
				passField.setText("");
				JOptionPane
						.showMessageDialog(
								loginButton,
								"The username and password combination you entered is incorrect",
								"Incorrect Login", JOptionPane.ERROR_MESSAGE);
			}
		}

		/**
		 * private helper method to convert the password in the password field
		 * (which is in char[]) into a String
		 * 
		 * @param password
		 * @return String pass -- the real password
		 */
		private String getPassword(char[] password) {
			String pass = "";

			for (int x = 0; x < password.length; x++)
				pass += password[x];

			return pass;
		}
	}

	/**
	 * 
	 * listener for the logoutButton -- logs the user out
	 */
	private class LogoutButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			welcome.setText("Thank you for using the JukeBox. Please come back again!");
			userNameField.setText("");
			passField.setText("");

			logoutButton.setEnabled(false);
			loginButton.setEnabled(true);

		}

	}

	private class MyWindowListener implements WindowListener {

		@Override
		public void windowActivated(WindowEvent arg0) {

		}

		@Override
		public void windowClosed(WindowEvent arg0) {

		}

		@Override
		public void windowClosing(WindowEvent arg0) {
			int choice = JOptionPane.showConfirmDialog(null,
					"Do you wish to save?", "Save", 1);

			// if the user wants to save
			if (choice == JOptionPane.YES_OPTION) {
				saveData();
				System.exit(0);
			}
			// else if the user doesnt want to save
			else if (choice == JOptionPane.NO_OPTION) {
				System.exit(0);
			}
			// else they hit cancel
			else {

			}
		}

		@Override
		public void windowDeactivated(WindowEvent arg0) {

		}

		@Override
		public void windowDeiconified(WindowEvent arg0) {

		}

		@Override
		public void windowIconified(WindowEvent arg0) {

		}

		@Override
		public void windowOpened(WindowEvent arg0) {
			int choice = JOptionPane.showConfirmDialog(null,
					"Do you want to enable Serializability",
					"Enable Serializable?", 0);

			// load the needed resources
			if (choice == JOptionPane.YES_OPTION) {
				loadData();
				// refreshSongList();
				
				// refresh playlist and play all songs in playList
				setUpPlayList();
				playAllPlayList playingPlayList = new playAllPlayList();
				new Thread(playingPlayList).start();
			}
		}
	}

	/**
	 * save the songCollection and StudentList into files
	 */
	private void saveData() {
		try {
			// save song collection
			FileOutputStream file = new FileOutputStream(
					FILE_NAME_TO_STORE_SONG_COLLECTION);
			ObjectOutputStream outputStream = new ObjectOutputStream(file);
			outputStream.writeObject(songCollection);
			outputStream.close();

			// save student list
			file = new FileOutputStream(FILE_NAME_TO_STORE_STUDENT_LIST);
			outputStream = new ObjectOutputStream(file);
			outputStream.writeObject(studentList);
			outputStream.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * load the songCollection and StudentList from files
	 */
	private void loadData() {
		// load song collection
		try {
			FileInputStream file = new FileInputStream(
					FILE_NAME_TO_STORE_SONG_COLLECTION);
			ObjectInputStream inputStream = new ObjectInputStream(file);
			songCollection = (SongCollection) inputStream.readObject();
			inputStream.close();

			// load student list
			file = new FileInputStream(FILE_NAME_TO_STORE_STUDENT_LIST);
			inputStream = new ObjectInputStream(file);
			studentList = (StudentList) inputStream.readObject();

			inputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * sets everything you can see in the jukebox GUI -- dimensions, buttons,
	 * 
	 * textfields, password fields, and labels
	 */

	private void layoutGUI() {

		// GUI dimensions and general stuff
		this.setTitle("JukeBox");
		this.setSize(925, 550);
		this.setLocation(100, 100);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		// set song list and queue on GUI
		setUpPlayList();
		setUpSongList();

		// now playing label
		nowPlaying.setOpaque(true);
		playListScroll.setColumnHeaderView(nowPlaying);

		// play button --- icon, null layout, position
		playSongButton.setIcon(new ImageIcon(baseDir + "logo.png"));
		playSongButton.setLocation(6, 34);
		
		// playing around view (Songlist / button / Playlist)
		playSongButton.setSize(155, 155);
		playSongButton.setLayout(new BorderLayout());
		JLabel playLabel = new JLabel("  Play Song ");
		playLabel.setFont(new Font("Courier", Font.BOLD, 18));
		playSongButton.add(playLabel, BorderLayout.SOUTH);
		JPanel panel = new JPanel();
		panel.add(playSongButton);

		panel.setLayout(null);

		// add elements with border layout
		getContentPane().add(panel, BorderLayout.CENTER);
		getContentPane().add(songListScrollPane, BorderLayout.WEST);
		getContentPane().add(playListScroll, BorderLayout.EAST);

		// user/pass
		JPanel panel2 = new JPanel();
		panel2.add(userLabel);
		panel2.add(userNameField);
		panel2.add(passLabel);
		panel2.add(passField);
		panel2.add(loginButton);
		panel2.add(logoutButton);
		getContentPane().add(panel2, BorderLayout.NORTH);
		logoutButton.setEnabled(false);

		// welcome label
		welcome.setVisible(true);
		welcome.setFont(new Font("Courier", Font.BOLD, 17));
		JPanel panel3 = new JPanel();
		panel3.add(welcome);
		getContentPane().add(panel3, BorderLayout.SOUTH);
	}

	/**
	 * sets up the look for the play list inside the GUI, using a JList
	 */
	private void setUpPlayList() {

		DefaultListModel queue = new DefaultListModel();

		ArrayList<Song> temp = new ArrayList<Song>(songCollection.getPlayList());

		for (int x = 0; x < songCollection.getPlayList().size(); x++) {
			queue.addElement(temp.get(x).getName());
		}
		
		// make 1 selection at a time
		queueList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		queueList.setModel(queue);
		queueList.setFixedCellWidth(300);

	}

	/**
	 * sets up the look for the song list inside the GUI, using JTable
	 * 
	 * @return
	 */
	private void setUpSongList() {

		TableModel model = new TableOfSongs();
		songTable = new JTable(model);

		// make 1 selection at a time
		songTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		songListScrollPane = new JScrollPane(songTable);
		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
		songTable.setRowSorter(sorter);
	}

	/**
	 * refreshes all of the number of plays for the whole collection of songs in
	 * the songList
	 */
	private void refreshSongList() {
		// go through the whole collection and refresh the number of plays on the table
		for (int x = 0; x < songCollection.getCollectionList().size(); x++) {
			int realIndex = getActualSong(x);
			int realNumPlays = songCollection.getCollectionList()
					.get(realIndex).getNumPlays();
			songTable.setValueAt(realNumPlays, x, 3);
		}
	}

	/**
	 * Obtains the real song index inside the collection from the selected row
	 * from the table.
	 * 
	 * @param rowIndex
	 *            - the selected row
	 * @return the index of the song
	 */
	private int getActualSong(int rowIndex) {
		
		// get real name
		String songName = (String) songTable.getValueAt(rowIndex, 0); 

		// go through all of the song collection looking for the song with the
		// real song name
		for (int x = 0; x < songCollection.getCollectionList().size(); x++) {
			if (songName.equals(songCollection.getCollectionList().get(x)
					.getName()))
				return x;

		}
		// this will never happen -- unless the song selected is not on the collection lol
		return -1;
	}

	/**
	 * sets up the welcome label which displays the logged in users name, their
	 * time remaining, and their number of plays left
	 */
	private void setUpTimeRemaining() {
		// get hours, minutes, and seconds left
		int hours = loggedInStudent.getAvailableMinutes() / 3600;
		int minutes = (loggedInStudent.getAvailableMinutes() / 60) % 60;
		int seconds = loggedInStudent.getAvailableMinutes() % 60;

		// set welcome text
		welcome.setText(("Welcome " + loggedInStudent.getName() + "! - "
				+ "Time Remaining: " + hours + "Hours " + minutes + "Minutes "
				+ seconds + "Seconds - Number of Plays Left: " + (2 - loggedInStudent
				.getPlaysForTheDay())));
	}

	/**
	 * plays all of the songs in the playlist -- playing one song at a time and
	 * going to the next when that song ends (+ a split second for little more
	 * room between songs)
	 */
	private class playAllPlayList implements Runnable {

		@Override
		public void run() {

			// while there are songs on the playlist
			while (songCollection.getPlayList().peek() != null) {
				songCollection.playSongAtTopOfPlayList();

				// wait for the song to end to continue this thread
				try {
					Thread.sleep(songCollection.getPlayList().peek()
							.getLength() * 1000 + 100);

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// when song is over, remove from list
				songCollection.removeTopSong();

				// update PlayList
				setUpPlayList();

			}

		}

	}

}