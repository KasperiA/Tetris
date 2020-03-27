import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Board extends JPanel implements KeyListener{
	
	private Shape currentShape;
	private final int blockSize = 30;
	private int[][] blocks;
	
	public Board() {
		this.addKeyListener(this);
		this.setFocusable(true);
		this.requestFocus();
		this.currentShape = new Shape(7);
		this.blocks = new int[20][10];
		this.setBlocks(this.currentShape.getCoords());
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.BLUE);
		for (int i = 0; i < this.blocks.length; i++) {
			for (int j = 0; j < this.blocks[i].length; j++) {
				if (this.blocks[i][j] != 0) {
					g.fillRect(j * this.blockSize, i * this.blockSize, this.blockSize - 1, this.blockSize - 1);
				}
			}
		}
		
	}
	
	//for debugging
	private void printBlocks() {
		for (int i = 0; i < this.blocks.length; i++) {
			System.out.print("[ ");
			for(int j = 0; j < this.blocks[i].length; j++) {
				System.out.print(this.blocks[i][j] + " ");
			}
			System.out.println("]");
		}
	}
	
	@Override 
	public Dimension getPreferredSize() {
		return new Dimension(300,600);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		switch(key) {
		case  KeyEvent.VK_LEFT:
			//this.blocks[this.currentShape.getY()][this.currentShape.getX()] = 0;
			//this.currentShape.setX(this.currentShape.getX() - 1);
			//this.blocks[this.currentShape.getY()][this.currentShape.getX()] = 1;
			//System.out.println(this.currentShape.getX());
			//System.out.println("testi_vk_left");
			printBlocks();
			break;
		case KeyEvent.VK_RIGHT:
			this.blocks[this.currentShape.getY()][this.currentShape.getX()] = 0;
			this.currentShape.setX(this.currentShape.getX() + 1);
			this.blocks[this.currentShape.getY()][this.currentShape.getX()] = 1;
			System.out.println("testi_vk_right");
			System.out.println(this.currentShape.getX());
			printBlocks();
			break;
		case KeyEvent.VK_DOWN:
			this.blocks[this.currentShape.getY()][this.currentShape.getX()] = 0;
			this.currentShape.setY(this.currentShape.getY() + 1);
			this.blocks[this.currentShape.getY()][this.currentShape.getX()] = 1;
			System.out.println("testi_vk_down");
			System.out.println(this.currentShape.getY());
			printBlocks();
			break;
		case KeyEvent.VK_UP:
			this.blocks[this.currentShape.getY()][this.currentShape.getX()] = 0;
			this.currentShape.setY(this.currentShape.getY() - 1);
			this.blocks[this.currentShape.getY()][this.currentShape.getX()] = 1;
			System.out.println("testi_vk_up");
			System.out.println(this.currentShape.getY());
			printBlocks();
			break;
			
		default:
			break;
		}
		
		repaint();
		
		
	}
	
	private void setBlocks(ArrayList<Point> blockCoords) {
		for (Point p : blockCoords) {
			this.blocks[(int) p.getY() + this.currentShape.getY()][(int) p.getX() + this.currentShape.getX()] = this.currentShape.getShape();
			}
		}

	private void setToZero(ArrayList<Integer> coords) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public void keyTyped(KeyEvent e) {
		
	}


}
