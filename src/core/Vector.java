package core;

import java.util.Objects;

public class Vector {
	public final double x;
	public final double y;
	public final double size;
	
	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
		this.size = Math.sqrt(x * x + y * y);
	}
	
	public Vector(Vector originalVector) {
		this(originalVector.x, originalVector.y);
	}
	
	public Vector() {
		this(0, 0);
	}
	
	public static Vector add(Vector vector1, Vector vector2) {
		return new Vector(vector1.x + vector2.x, vector1.y + vector2.y);
	}
	
	public static Vector subtract(Vector vector1, Vector vector2) {
		return new Vector(vector1.x - vector2.x, vector1.y - vector2.y);
	}
	
	public static Vector scale(Vector vector, double scale) {
		return new Vector(vector.x * scale, vector.y * scale);
	}
	
	public double getSize() {
		return size;
	}
	
	@Override
	public String toString() {
		return String.format("[%.1f; %.1f]", x, y);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(size, x, y);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Vector)) {
			return false;
		}
		Vector other = (Vector) obj;
		return Double.doubleToLongBits(size) == Double.doubleToLongBits(other.size)
				&& Double.doubleToLongBits(x) == Double.doubleToLongBits(other.x)
				&& Double.doubleToLongBits(y) == Double.doubleToLongBits(other.y);
	}
}
