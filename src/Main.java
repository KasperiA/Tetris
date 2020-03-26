import javax.swing.JFrame;

public class Main {
	
	private static void createWindow() {
		JFrame frame = new JFrame("Tetris");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setLocationRelativeTo(null);
		frame.setSize(300, 600);
		Board tetris = new Board();
		frame.add(tetris);
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		createWindow();
	}

}
