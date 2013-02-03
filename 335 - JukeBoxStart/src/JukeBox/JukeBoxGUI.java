package JukeBox;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class JukeBoxGUI extends JFrame {

	private JButton playSongButton = new JButton("Play Song");
	private JTextArea songList = new JTextArea(25, 32);
	private JTextArea queueList = new JTextArea(25, 32);
	private JLabel welcome = new JLabel("Welcome ");
	private JButton loginButton = new JButton("Login");
	private JButton logoutButton = new JButton("Logout");

	private SongCollection songCollection = new SongCollection();
	private StudentList studentList = new StudentList();

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

			playAllPlayList playingPlayList = new playAllPlayList();
			new Thread(playingPlayList).start();

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

				// Student loggedInStudent = StudentList.get(name);

				// welcome.setText(("Welcome " + name + "! " +
				// "Minutes Remaining: " +loggedInStudent.getAvailableMinutes()+
				// "Number of Plays Today: " +
				// (3-loggedInStudent.getPlaysForTheDay())));
				welcome.setVisible(true);
			}

			else {
				JOptionPane
						.showMessageDialog(
								loginButton,
								"The username and password combination you enetered is incorrect",
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
			// TODO Auto-generated method stub

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
		welcome.setVisible(false);
		JPanel panel3 = new JPanel();
		panel3.add(welcome);
		this.add(panel3, BorderLayout.SOUTH);
	}

	private class playAllPlayList implements Runnable {

		@Override
		public void run() {

			while (songCollection.getPlayList().peek() != null) {
				songCollection.playSongAtTopOfPlayList();

				try {
					Thread.sleep(songCollection.getPlayList().peek()
							.getLength());

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				songCollection.removeTopSong();

				// update JTextArea .......

			}

		}

	}

}