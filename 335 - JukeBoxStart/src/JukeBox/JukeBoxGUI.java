package JukeBox;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class JukeBoxGUI extends JFrame {

	private JButton playSongButton = new JButton("Play Song");
	private JTextArea songList = new JTextArea(25, 32);
	private JTextArea queueList = new JTextArea(25, 32);
	
	private JTextField userNameField= new JTextField(10);
	private JPasswordField passField = new JPasswordField(10);
	private JLabel userLabel = new JLabel("UserName:");
	private JLabel passLabel = new JLabel("Password:");
	

	/**
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

	private void registerListeners() {
		// TODO Auto-generated method stub

	}

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
		
		this.add(panel2, BorderLayout.NORTH);
		
	}

}
