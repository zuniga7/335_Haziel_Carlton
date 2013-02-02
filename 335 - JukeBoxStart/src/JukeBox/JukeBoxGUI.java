package JukeBox;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class JukeBoxGUI extends JFrame {

	private JButton playSongButton = new JButton("Play Song");
	private JTextArea songList = new JTextArea();
	private JTextArea queueList = new JTextArea();

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		JFrame view = new JukeBoxGUI();
		view.setVisible(true);
	}

	public JukeBoxGUI() {
		// setUpStuff----
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
	}

}
