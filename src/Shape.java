import java.awt.Point;
import java.util.ArrayList;

public class Shape {
	
	private int shape;
	private int x;
	private int y;
	private ArrayList<Point> coords;
	
	// shape defines which shape the shape is, x is the main x-coordinate and y is the main y-coordinate
	public Shape(int shape) {
		this.shape = shape;
		this.x = 4;
		this.y = 1;
		this.coords = new ArrayList<>();
		this.setCoords(this.shape);
	}
	
	private void setCoords(int shape) {
		switch (shape) {
		case 1:
			this.coords.add(new Point(0,0));
			this.coords.add(new Point(-1,0));
			this.coords.add(new Point(1,0));
			this.coords.add(new Point(2,0));
			break;
		case 2:
			this.coords.add(new Point(0,0));
			this.coords.add(new Point(1,0));
			this.coords.add(new Point(0,1));
			this.coords.add(new Point(1,1));
			break;
		case 3:
			this.coords.add(new Point(-1,0));
			this.coords.add(new Point(0,0));
			this.coords.add(new Point(1,0));
			this.coords.add(new Point(1,1));
			break;
		case 4:
			this.coords.add(new Point(-1,1));
			this.coords.add(new Point(-1,0));
			this.coords.add(new Point(0,0));
			this.coords.add(new Point(1,0));
			break;
		case 5:
			this.coords.add(new Point(-1,1));
			this.coords.add(new Point(0,1));
			this.coords.add(new Point(0,0));
			this.coords.add(new Point(1,0));
			break;
		case 6:
			this.coords.add(new Point(-1,0));
			this.coords.add(new Point(0,0));
			this.coords.add(new Point(0,1));
			this.coords.add(new Point(1,0));
			break;
		case 7:
			this.coords.add(new Point(-1,0));
			this.coords.add(new Point(0,0));
			this.coords.add(new Point(0,1));
			this.coords.add(new Point(1,1));
			break;
		default:
			break;
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
	
	public ArrayList<Point> getCoords() {
		return this.coords;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	

}
