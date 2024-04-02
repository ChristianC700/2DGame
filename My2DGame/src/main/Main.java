package main;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		//Create window
		JFrame window = new JFrame();
		//Lets window close properly when user presses "x"
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		window.setResizable(false);
		window.setTitle("2D Adventure");
		
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		window.pack();
		
		//Window displayed at center of screen
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		gamePanel.startGameThread();
	}

}
