import java.awt.Point;
import java.util.ArrayList;

public class Shape {
	
	private int shape;
	private int x;
	private int y;
	private ArrayList<ArrayList<Point>> coords;
	private int rotation;
	private boolean hasFinishedFalling;
	
	// shape defines which shape the shape is, x is the main x-coordinate and y is the main y-coordinate
	public Shape(int shape) {
		this.shape = shape;
		this.x = 4;
		this.y = 0;
		this.coords = new ArrayList<ArrayList<Point>>();
		this.initShape(this.shape);
		this.rotation = 0;
		this.hasFinishedFalling = false;
	}
	
	/**
	 * Creates a shape by defining its different rotations coordinates
	 * @param shape Shapes number
	 */
	
	private void initShape(int shape) {
		switch (shape) {
		case 0: //Empty shape
			this.createRotationArrayLists(1);
			this.coords.get(0).add(new Point(0,0));
		case 1: // I
			this.createRotationArrayLists(2);
			this.coords.get(0).add(new Point(0,0));
			this.coords.get(0).add(new Point(-1,0));
			this.coords.get(0).add(new Point(1,0));
			this.coords.get(0).add(new Point(2,0));
			
			this.coords.get(1).add(new Point(0,-1));
			this.coords.get(1).add(new Point(0,0));
			this.coords.get(1).add(new Point(0,1));
			this.coords.get(1).add(new Point(0,2));
			break;
		case 2: // O
			this.createRotationArrayLists(1);
			this.coords.get(0).add(new Point(0,0));
			this.coords.get(0).add(new Point(1,0));
			this.coords.get(0).add(new Point(0,1));
			this.coords.get(0).add(new Point(1,1));
			break;
		case 3: // J
			this.createRotationArrayLists(4);
			this.coords.get(0).add(new Point(-1,0));
			this.coords.get(0).add(new Point(0,0));
			this.coords.get(0).add(new Point(1,0));
			this.coords.get(0).add(new Point(1,1));
			
			this.coords.get(1).add(new Point(0,-1));
			this.coords.get(1).add(new Point(0,0));
			this.coords.get(1).add(new Point(0,1));
			this.coords.get(1).add(new Point(-1,1));
			
			this.coords.get(2).add(new Point(-1,-1));
			this.coords.get(2).add(new Point(-1,0));
			this.coords.get(2).add(new Point(0,0));
			this.coords.get(2).add(new Point(1,0));
			
			this.coords.get(3).add(new Point(0,-1));
			this.coords.get(3).add(new Point(1,-1));
			this.coords.get(3).add(new Point(0,0));
			this.coords.get(3).add(new Point(0,1));
			break;
		case 4: //L
			this.createRotationArrayLists(4);
			this.coords.get(0).add(new Point(-1,1));
			this.coords.get(0).add(new Point(-1,0));
			this.coords.get(0).add(new Point(0,0));
			this.coords.get(0).add(new Point(1,0));
			
			this.coords.get(1).add(new Point(0,1));
			this.coords.get(1).add(new Point(0,0));
			this.coords.get(1).add(new Point(0,-1));
			this.coords.get(1).add(new Point(-1,-1));
			
			this.coords.get(2).add(new Point(-1,0));
			this.coords.get(2).add(new Point(0,0));
			this.coords.get(2).add(new Point(1,0));
			this.coords.get(2).add(new Point(1,-1));
			
			this.coords.get(3).add(new Point(0,-1));
			this.coords.get(3).add(new Point(0,0));
			this.coords.get(3).add(new Point(0,1));
			this.coords.get(3).add(new Point(1,1));
			break;
		case 5: //S
			this.createRotationArrayLists(2);
			this.coords.get(0).add(new Point(-1,1));
			this.coords.get(0).add(new Point(0,1));
			this.coords.get(0).add(new Point(0,0));
			this.coords.get(0).add(new Point(1,0));
			
			this.coords.get(1).add(new Point(0,-1));
			this.coords.get(1).add(new Point(0,0));
			this.coords.get(1).add(new Point(1,0));
			this.coords.get(1).add(new Point(1,1));
			break;
		case 6: //T
			this.createRotationArrayLists(4);
			this.coords.get(0).add(new Point(-1,0));
			this.coords.get(0).add(new Point(0,0));
			this.coords.get(0).add(new Point(0,1));
			this.coords.get(0).add(new Point(1,0));
			
			this.coords.get(1).add(new Point(0,-1));
			this.coords.get(1).add(new Point(0,0));
			this.coords.get(1).add(new Point(-1,0));
			this.coords.get(1).add(new Point(0,1));
			
			this.coords.get(2).add(new Point(0,-1));
			this.coords.get(2).add(new Point(-1,0));
			this.coords.get(2).add(new Point(0,0));
			this.coords.get(2).add(new Point(1,0));
			
			this.coords.get(3).add(new Point(0,-1));
			this.coords.get(3).add(new Point(1,0));
			this.coords.get(3).add(new Point(0,0));
			this.coords.get(3).add(new Point(0,1));
			break;
		case 7: // Z
			this.createRotationArrayLists(2);
			this.coords.get(0).add(new Point(-1,0));
			this.coords.get(0).add(new Point(0,0));
			this.coords.get(0).add(new Point(0,1));
			this.coords.get(0).add(new Point(1,1));
			
			this.coords.get(1).add(new Point(0,0));
			this.coords.get(1).add(new Point(1,0));
			this.coords.get(1).add(new Point(1,-1));
			this.coords.get(1).add(new Point(0,1));
			break;
		default:
			break;
		}
	}
	
	/**
	 * Returns the coordinates of a shapes specific rotation
	 * @param rotation The wanted rotation
	 * @return Point arraylist where one Point is the coordinates of a shapes block
	 */
	
	public ArrayList<Point> getCoords(int rotation) {
		ArrayList<Point> returnedCoords = new ArrayList<>();
		for(Point p : this.coords.get(rotation)) {
			returnedCoords.add(p);
		}
		return returnedCoords;
	}
	
	/**
	 * Creates the ArrayLists for the different rotations
	 * @param rotations Amount of rotations
	 */
	
	private void createRotationArrayLists(int rotations) {
		for (int i = 0; i < rotations; i++) {
			this.coords.add(new ArrayList<Point>());
		}
	}
	
	/**
	 * Changes the rotation to the next rotation
	 */
	
	public void changeRotation() {
		if (this.rotation < this.coords.size() - 1) {
			this.rotation++;
		} else {
			this.rotation = 0;
		}
	}
	
	/**
	 * Gives the next rotation without changing the current rotation
	 * @return The next rotation
	 */
	
	public int nextRotation() {
		if (this.rotation < this.coords.size() - 1) {
			return this.rotation + 1;
		} else {
			return 0;
		}
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}

	public int getShape() {
		return this.shape;
	}
			
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getRotation() {
		return this.rotation;
	}
	
	public boolean hasFinishedFalling() {
		return this.hasFinishedFalling;
	}
	
	public void finishedFalling() {
		this.hasFinishedFalling = true;
	}
	
	
	

}
