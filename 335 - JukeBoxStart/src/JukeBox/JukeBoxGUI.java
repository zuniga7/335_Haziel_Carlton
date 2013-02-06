package JukeBox;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
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

public class JukeBoxGUI extends JFrame {

	private JButton playSongButton = new JButton("Play Song");
	private JTextArea songList = new JTextArea(25, 32);
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

	/**
	 * 
	 * @param args
	 */

	public static void main(String[] args) {
		JFrame view = new JukeBoxGUI();
		view.setVisible(true);

	}

	public JukeBoxGUI() {
		// setUpStuff---- new JukeBox or every list??
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
					// add selected song to playList
					int index = songTable.getSelectedRow();
					songCollection.addToPlayList(index);

					// refresh tables ---- TESTING
			//		songTable.
			//		songTable.editCellAt(songTable.getSelectedRow(), 3);
					setUpPlayList();

					// change user minutes and plays ---- ADD
				//	Song selection = songCollection
				//	loggedInStudent.setSong(songTable.getSelectedRow().;

					// if the playList has 1 song... start playing music
					// playlist else, don't play playList again
					if (songCollection.getPlayList().size() == 1) {
						playAllPlayList playingPlayList = new playAllPlayList();
						new Thread(playingPlayList).start();
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

				welcome.setText(("Welcome " + name + "! - "
						+ "Minutes Remaining: "
						+ (loggedInStudent.getAvailableMinutes() / 60.0)
						+ " - Number of Plays Left: " + (2 - loggedInStudent
						.getPlaysForTheDay())));
				welcome.setVisible(true);
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
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// set song list and queue on GUI ---- TEMPORARY!!!
		setUpPlayList();
		songListScrollPane = setUpSongList();
		
		this.add(songListScrollPane, BorderLayout.WEST);
		this.add(playListScroll, BorderLayout.EAST);

	//	queueList.setSize(300, 300);
	//	this.add(queueList, BorderLayout.EAST);

		// JScrollPane playListScrollPane = setUpPlayList(); // maybe table or
		// just list?????

		// playing around view (Songlist / button / Playlist)
		JPanel panel = new JPanel();
		// panel.add(songListScrollPane);
		panel.add(playSongButton);
		// panel.add(queueList);

		this.add(panel, BorderLayout.CENTER);

		// user/pass
		JPanel panel2 = new JPanel();
		panel2.add(userLabel);
		panel2.add(userNameField);
		panel2.add(passLabel);
		panel2.add(passField);
		panel2.add(loginButton);
		panel2.add(logoutButton);
		this.add(panel2, BorderLayout.NORTH);
		logoutButton.setEnabled(false);

		// welcome label
		welcome.setVisible(true);
		welcome.setFont(new Font("Courier", Font.BOLD, 16));
		JPanel panel3 = new JPanel();
		panel3.add(welcome);
		this.add(panel3, BorderLayout.SOUTH);
	}

	/**
	 * sets up the look for the play list inside the GUI ---- TESTING!!!
	 */
	private void setUpPlayList() {

		String[] columns = { "Now Playing" };

		DefaultListModel queue = new DefaultListModel();

		ArrayList<Song> temp = new ArrayList<Song>(songCollection.getPlayList());
		// Object[][] data = new Object[][1];

		for (int x = 0; x < songCollection.getPlayList().size(); x++) {
			queue.addElement(temp.get(x).getName());
		}

		queueList.setModel(queue);
		queueList.toString();

	}

	/**
	 * sets up the look for the song list inside the GUI
	 * 
	 * @return
	 */
	private JScrollPane setUpSongList() {

		String[] columns = { "Song Name", "Song Length", "Artist",
				"Number of Plays" };

		int listSize = songCollection.getCollectionList().size();
		Object[][] data = new Object[listSize][4];

		for (int x = 0; x < listSize; x++) {
			data[x][0] = songCollection.getCollectionList().get(x).getName();
			data[x][1] = songCollection.getCollectionList().get(x).getLength();
			data[x][2] = songCollection.getCollectionList().get(x).getArtist();
			data[x][3] = songCollection.getCollectionList().get(x)
					.getNumPlays();
		}

		songTable = new JTable(data, columns);
		songTable.setAutoCreateRowSorter(true);
		songTable.setPreferredScrollableViewportSize(new Dimension(400, 400));
		songTable.setFillsViewportHeight(true);

		// songListScrollPane.setViewportView(songTable);

		JScrollPane scroll = new JScrollPane(songTable);

		return scroll;
	}

	/**
	 * plays all of the songs in the playlist -- playing one song at a time and
	 * going to the next when that song ends (+ a split second for little more
	 * room between songs)
	 */
	private class playAllPlayList implements Runnable {

		@Override
		public void run() {

			while (songCollection.getPlayList().peek() != null) {
				songCollection.playSongAtTopOfPlayList();

				try {
					Thread.sleep(songCollection.getPlayList().peek()
							.getLength() * 1000 + 50);

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