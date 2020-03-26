import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

public class Board extends JPanel implements KeyListener{
	
	private Shape currentShape;
	
	public Board() {
		this.addKeyListener(this);
		this.setFocusable(true);
		this.requestFocus();
		this.currentShape = new Shape(0,0);
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.black);
		g.fillRect(this.currentShape.getX(), this.currentShape.getY(), 30, 30);
		
	}
	
	@Override 
	public Dimension getPreferredSize() {
		return new Dimension(300,600);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		switch(key) {
		case  KeyEvent.VK_LEFT:
			this.currentShape.setX(this.currentShape.getX() - 30);
			System.out.println(this.currentShape.getX());
			System.out.println("testi_vk_left");
			break;
		case KeyEvent.VK_RIGHT:
			this.currentShape.setX(this.currentShape.getX() + 30);
			System.out.println("testi_vk_right");
			System.out.println(this.currentShape.getX());
			break;
		case KeyEvent.VK_DOWN:
			this.currentShape.setY(this.currentShape.getY() + 30);
			System.out.println("testi_vk_down");
			System.out.println(this.currentShape.getY());
			break;
		case KeyEvent.VK_UP:
			this.currentShape.setY(this.currentShape.getY() - 30);
			System.out.println("testi_vk_up");
			System.out.println(this.currentShape.getY());
			break;
			
		default:
			break;
		}
		
		repaint();
		
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
