package JukeBox;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class JukeBoxGUI extends JFrame {

	private JButton playSongButton = new JButton("Play Song");
	private JTextArea songList = new JTextArea(25, 32);
	private JTextArea queueList = new JTextArea(25, 32);
	private JLabel welcome = new JLabel("Welcome to the JukeBox!");
	private JButton loginButton = new JButton("Login");
	private JButton logoutButton = new JButton("Logout");

	private SongCollection songCollection = new SongCollection();
	private StudentList studentList = new StudentList();
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

				// add selected song to playList -- TESTING
				songCollection.addToPlayList(2);

				// if the playList has 1 song... start playing music playlist
				// else, don't play playList again
				if (songCollection.getPlayList().size() == 1) {
					playAllPlayList playingPlayList = new playAllPlayList();
					new Thread(playingPlayList).start();
				}

			}

			// ask user to log in to their account if they arent logged in
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

		// playing around
		JPanel panel = new JPanel();
		panel.add(songList);
		panel.add(playSongButton);
		panel.add(queueList);
		this.add(panel, BorderLayout.CENTER);

		// set song list and queue on GUI ---- TEMPORARY!!!
		setUpSongList();
		setUpPlayList();

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

	private void setUpPlayList() {
		// TODO Auto-generated method stub

	}

	/**
	 * sets up the look for the song list inside the GUI
	 */
	private void setUpSongList() {

		String[] columns = { "Song Name", "Song Length" };

		// JTable songsList = new JTable(data, columns);

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
							.getLength() * 1050);

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				songCollection.removeTopSong();

				// update JTextArea .......

			}

		}

	}

}