package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements KeyListener{
	
	private Shape currentShape;
	private final int blockSize = 30;
	private int[][] blocks;
	private Timer timer;
	private int speed = 500;
	private Random random;
	private boolean gameOver;
	private int boardWidth;
	private int boardHeight;
	private int score;
	
	/**
	 * Board constructor, also starts the timer that moves a shape down
	 */
	public Board() {
		this.addKeyListener(this);
		this.setFocusable(true);
		this.requestFocus();
		this.blocks = new int[20][10];
		this.random = new Random();
		this.gameOver = false;
		this.boardWidth = 300;
		this.boardHeight = 600;
		this.score = 0;
		
		this.timer = new Timer(speed, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(currentShape.hasFinishedFalling()) {
					addNewShape();
					checkFullRows();
				}
				moveDown();
				
				
				repaint();
			}
			
		});		
		
		addNewShape();
		this.setBlocks(this.currentShape.getCoords(this.currentShape.getRotation()));
		this.timer.start();

	}
	/**
	 * Draws the blocks and texts in game
	 * @param g Graphics component
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.BLACK);
		g.setFont(new Font("SansSerif", Font.PLAIN, 15));
		g.drawString("Score: " + this.score, 0, 20);
		
		for (int r = 0; r < this.blocks.length; r++) {
			for (int c = 0; c < this.blocks[r].length; c++) {
				if (this.blocks[r][c] != 0) {
					g.setColor(this.getColor(this.blocks[r][c]));
					g.fillRect(c * this.blockSize, r * this.blockSize, this.blockSize - 1, this.blockSize - 1);
				}
			}
		}
		
		if (this.gameOver) {
			g.setColor(Color.BLACK);
			g.setFont(new Font("SansSerif", Font.PLAIN, 40));
			g.drawString("GAME OVER", this.boardWidth / 10, this.boardHeight / 2);
		}
		
	}
	
	/**
	 * Adds a new shape and calls checkIfGameOver method
	 */
	private void addNewShape() {
		this.currentShape = new Shape(this.random.nextInt(7) + 1);
		this.checkIfGameOver();
	}
	
	/**
	 * Checks if the game is over
	 */
	private void checkIfGameOver() {
		for (Point p : this.currentShape.getCoords(this.currentShape.getRotation())) {
			if (this.blocks[(int) p.getY() + this.currentShape.getY()][(int) p.getX() + this.currentShape.getX()] != 0) {
				this.gameOver();
			}
		}
	}
	
	/*
	 * Prints the blocks array
	 */
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

	/**
	 * Defines the actions on different key presses
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		switch(key) {
		case  KeyEvent.VK_LEFT:
			this.moveToSide(-1);
			break;
		case KeyEvent.VK_RIGHT:
			this.moveToSide(1);
			break;
		case KeyEvent.VK_DOWN:
			this.moveDown();
			break;
		case KeyEvent.VK_UP:
			this.rotateShape();
			break;
			
		default:
			break;
		}
		
		repaint();
		
		
	}
	/**
	 * Returns the coordinates which needs to be tested when trying to move a shape
	 * @param blockCoords A shapes current blocks coordinates
	 * @param direction
	 * @param rotation
	 * @return ArrayList<Point> The arraylist contains the testcoordinates
	 */
	private ArrayList<Point> getTestCoordinates(ArrayList<Point> blockCoords, int direction, boolean rotation) {
		ArrayList<Point> oldCoords = new ArrayList<>();
		for(Point p : blockCoords) {
			oldCoords.add(new Point(((int) p.getX() + this.currentShape.getX()), ((int) p.getY() + this.currentShape.getY())));
		}
		ArrayList<Point> newCoords = new ArrayList<>();
		if (rotation) {
			ArrayList<Point> rotatedCoords = this.currentShape.getCoords(this.currentShape.nextRotation());
			for(Point p : rotatedCoords) {
				newCoords.add(new Point((int) p.getX() + this.currentShape.getX(), (int) p.getY() + this.currentShape.getY()));
			}
		}else if(direction == 0) {
			for(Point p : blockCoords) {
				newCoords.add(new Point(((int) p.getX() + this.currentShape.getX()), ((int) p.getY() + this.currentShape.getY() + 1)));
			}
		} else {
			for(Point p : blockCoords) {
				newCoords.add(new Point(((int) p.getX() + this.currentShape.getX() + direction), ((int) p.getY() + this.currentShape.getY())));
			}
		}
		
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
		
		return testCoords;
	}
	
	/**
	 * Checks if there is full rows, sets full rows to zero and moves blocks down
	 */
	private void checkFullRows() {
		ArrayList<Integer> fullrows = new ArrayList<>();
		for (int i = 0; i < this.blocks.length; i++) {
			boolean fullLine = true;
			for (int j = 0; j < this.blocks[i].length; j++) {
				if (blocks[i][j] == 0) {
					fullLine = false;
				}
			}
			if (fullLine) {
				fullrows.add(i);
			}
		}
		
		for(int i : fullrows) {
			for (int j = 0; j < this.blocks[i].length; j++) {
				this.blocks[i][j] = 0;
			}
		}
		
		
		if (fullrows.size() > 0) {
			for(int i = fullrows.get(0) - 1; i > 0; i--) {
				for (int j = this.blocks[i].length - 1; j > -1; j--) {
					this.blocks[i + fullrows.size()][j] = this.blocks[i][j];
				}
			}
			this.score += 10 * fullrows.size();
		}
	}

	/**
	 * Tests if the shape can move to side and moves it if possible
	 * @param direction -1 or 1
	 */
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
	
	/**
	 * Tests if the shape can move down and moves it or ends the shapes falling
	 */
	private void moveDown() {
		ArrayList<Point> testCoords = this.getTestCoordinates(this.currentShape.getCoords(this.currentShape.getRotation()), 0, false);
		
		for (Point p : testCoords) {
			int x = (int) p.getX();
			int y = (int) p.getY();
			if (y > 19) {
				this.currentShape.finishedFalling();
				return;
			}
			
			if (this.blocks[y][x] != 0) {
				this.currentShape.finishedFalling();
				return;
			}
		}
		
		this.setToZero(this.currentShape.getCoords(this.currentShape.getRotation()));
		this.currentShape.setY(this.currentShape.getY() + 1);
		this.setBlocks(this.currentShape.getCoords(this.currentShape.getRotation()));
	}
	/**
	 * Tests if the shape can be rotated and rotates it if possible
	 */
	private void rotateShape() {
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
	
	/** 
	 * Sets a shapes value into the blocks array
	 * @param blockCoords The shapes blocks coordinates
	 */
	private void setBlocks(ArrayList<Point> blockCoords) {
		for (Point p : blockCoords) {
			this.blocks[(int) p.getY() + this.currentShape.getY()][(int) p.getX() + this.currentShape.getX()] = this.currentShape.getShape();		
			}
		}
	
	/**
	 * Changes a shapes value to zero in the blocks array
	 * @param blockCoords The shapes blocks coordinates
	 */
	private void setToZero(ArrayList<Point> blockCoords) {
		for (Point p : blockCoords) {
			this.blocks[(int) p.getY() + this.currentShape.getY()][(int) p.getX() + this.currentShape.getX()] = 0;
			}
	}
	

	/**
	 * Ends the game
	 */
	private void gameOver() {
		this.gameOver = true;
		this.timer.stop();
		this.currentShape = new Shape(0);
	}
	
	/**
	 * Get the color of a shape
	 * @param shape Shapes number
	 * @return Shapes color
	 */
	private Color getColor(int shape) {
		switch (shape) {
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
			return new Color(102,0,153); //purple
		case 7:
			return new Color(255,0,0); //red
		default:
			return new Color(255,255,255); //white
		}
	}
	
	public int[][] getBoard() {
		return this.blocks;
	}
	
	@Override 
	public Dimension getPreferredSize() {
		return new Dimension(this.boardWidth,this.boardHeight);
	}
	
	// Required but not used methods
	@Override
	public void keyReleased(KeyEvent e) {		
	}
	

	@Override
	public void keyTyped(KeyEvent e) {		
	}


}
