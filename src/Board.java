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
		this.currentShape = new Shape(5);
		this.blocks = new int[20][10];
		this.setBlocks(this.currentShape.getCoords(this.currentShape.getRotation()));
		this.blocks[0][0] = 1;
		this.blocks[0][1] = 2;
		this.blocks[0][2] = 3;
		this.blocks[0][3] = 4;
		this.blocks[0][4] = 5;
		this.blocks[0][5] = 6;
		this.blocks[0][6] = 7;
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for (int i = 0; i < this.blocks.length; i++) {
			for (int j = 0; j < this.blocks[i].length; j++) {
				if (this.blocks[i][j] != 0) {
					g.setColor(this.getColor(this.blocks[i][j]));
					g.fillRect(j * this.blockSize, i * this.blockSize, this.blockSize - 1, this.blockSize - 1);
				}
			}
		}
		
	}
	
	private Color getColor(int number) {
		switch (number) {
		case 1:
			return new Color(51, 204, 255); //light blue
		case 2:
			return new Color(255,255,0); //yellow
		case 3:
			return new Color(0,0,204); //dark blue
		case 4:
			return new Color(255,153,0); //orange
		case 5:
			return new Color(0,255,51); //light green
		case 6:
			return new Color(255,0,0); //red
		case 7:
			return new Color(102,0,153); //purple
		default:
			return new Color(255,255,255); //white
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
		System.out.println();
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
			this.moveToSide(-1);
			printBlocks();
			break;
		case KeyEvent.VK_RIGHT:
			this.moveToSide(1);
			printBlocks();
			break;
		case KeyEvent.VK_DOWN:
			this.moveDown();
			printBlocks();
			break;
		case KeyEvent.VK_UP:
			this.rotateBlock();
			//printBlocks();
			break;
			
		default:
			break;
		}
		
		repaint();
		
		
	}
	private ArrayList<Point> getTestCoordinates(ArrayList<Point> blockCoords, int direction, boolean rotation) {
		ArrayList<Point> oldCoords = new ArrayList<>();
		System.out.println("old");
		for(Point p : blockCoords) {
			System.out.println("(" + ((int) p.getY() + this.currentShape.getY()) + " , " + ((int) p.getX() + this.currentShape.getX()) + ")");
			oldCoords.add(new Point(((int) p.getX() + this.currentShape.getX()), ((int) p.getY() + this.currentShape.getY())));
		}
		ArrayList<Point> newCoords = new ArrayList<>();
		System.out.println("new");
		if (rotation) {
			ArrayList<Point> rotatedCoords = this.currentShape.getCoords(this.currentShape.nextRotation());
			for(Point p : rotatedCoords) {
				newCoords.add(new Point((int) p.getX() + this.currentShape.getX(), (int) p.getY() + this.currentShape.getY()));
				System.out.println("(" + ((int) p.getX()) + " , " + (int) p.getY() + ")");
			}
		}else if(direction == 0) {
			for(Point p : blockCoords) {
				newCoords.add(new Point(((int) p.getX() + this.currentShape.getX()), ((int) p.getY() + this.currentShape.getY() + 1)));
				System.out.println("(" + ((int) p.getY() + this.currentShape.getY() + 1) + " , " + ((int) p.getX() + this.currentShape.getX()) + ")");
			}
		} else {
			for(Point p : blockCoords) {
				System.out.println("(" + ((int) p.getY() + this.currentShape.getY()) + " , " + ((int) p.getX() + this.currentShape.getX() + direction) + ")");
				newCoords.add(new Point(((int) p.getX() + this.currentShape.getX() + direction), ((int) p.getY() + this.currentShape.getY())));
			}
		}
		
		System.out.println("check");
		ArrayList<Point> testCoords = new ArrayList<>();
		for(Point np : newCoords) {
			boolean contains = false;
			for(Point op : oldCoords) {
				if(op.getX() == np.getX() && op.getY() == np.getY()) {
					contains = true;
				}
			}
			if (!contains) {
				testCoords.add(new Point((int) np.getX(), (int) np.getY()));
			}
		}
		for(Point p : testCoords) {
			System.out.println("(" + ((int) p.getY()) + " , " + ((int) p.getX()) + ")");
		}
		
		return testCoords;
	}

	
	private void moveToSide(int direction) {
		ArrayList<Point> testCoords = this.getTestCoordinates(this.currentShape.getCoords(this.currentShape.getRotation()), direction, false);
		
		for (Point p : testCoords) {
			int x = (int) p.getX();
			int y = (int) p.getY();
			if (x > 9 || x < 0) {
				return;
			}
			
			if (this.blocks[y][x] != 0) {
				return;
			}
		}

		this.setToZero(this.currentShape.getCoords(this.currentShape.getRotation()));
		this.currentShape.setX(this.currentShape.getX() + direction);
		this.setBlocks(this.currentShape.getCoords(this.currentShape.getRotation()));
	}
	
	private void moveDown() {
		ArrayList<Point> testCoords = this.getTestCoordinates(this.currentShape.getCoords(this.currentShape.getRotation()), 0, false);
		
		for (Point p : testCoords) {
			int x = (int) p.getX();
			int y = (int) p.getY();
			if (y > 19) {
				return;
			}
			
			if (this.blocks[y][x] != 0) {
				return;
			}
		}
		
		this.setToZero(this.currentShape.getCoords(this.currentShape.getRotation()));
		this.currentShape.setY(this.currentShape.getY() + 1);
		this.setBlocks(this.currentShape.getCoords(this.currentShape.getRotation()));
	}
	
	private void rotateBlock() {
		ArrayList<Point> testCoords = this.getTestCoordinates(this.currentShape.getCoords(this.currentShape.getRotation()), 0, true);
		for (Point p : testCoords) {
			int x = (int) p.getX();
			int y = (int) p.getY();
			
			if (y > 19 || y < 0) {
				return;
			}
			
			if (x > 9 || x < 0) {
				return;
			}
			
			if (this.blocks[y][x] != 0) {
				return;
			}
		}
		this.setToZero(this.currentShape.getCoords(this.currentShape.getRotation()));
		this.currentShape.changeRotation();
		this.setBlocks(this.currentShape.getCoords(this.currentShape.getRotation()));
	}
	
	private void setBlocks(ArrayList<Point> blockCoords) {
		for (Point p : blockCoords) {
			this.blocks[(int) p.getY() + this.currentShape.getY()][(int) p.getX() + this.currentShape.getX()] = this.currentShape.getShape();
			}
		}

	private void setToZero(ArrayList<Point> blockCoords) {
		for (Point p : blockCoords) {
			this.blocks[(int) p.getY() + this.currentShape.getY()][(int) p.getX() + this.currentShape.getX()] = 0;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {		
	}
	

	@Override
	public void keyTyped(KeyEvent e) {		
	}


}
