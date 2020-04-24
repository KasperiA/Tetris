package main;

import javax.swing.JFrame;

public class Main {
	
	/**
	 * Creates the game window
	 */
	private static void createWindow() {
		JFrame frame = new JFrame("Tetris");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		Board tetris = new Board();
		frame.add(tetris);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		createWindow();
	}

}
