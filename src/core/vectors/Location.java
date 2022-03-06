package core.vectors;

public class Location extends Vector {
	public Location(double x, double y) {
		super(x, y);
	}
	
	public Location(Vector vector) {
		super(vector);
	}
	
	public Location() {
		super();
	}
	
	public double getDistance(Location location) {
		return subtract(this, location).size;
	}
}
