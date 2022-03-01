package core;

import java.awt.Graphics;
import java.util.Objects;

public abstract class SpaceObject {
	public final String name;
	public final String type;
	public final double weight;
	
	protected Location location;
	protected Velocity velocity;
	protected Acceleration acceleration;
	
	public SpaceObject(String name, String type, Location location, Velocity velocity, double weight) {
		this.name = name;
		this.type = type;
		this.location = location;
		this.velocity = velocity;
		this.acceleration = new Acceleration();
		this.weight = weight;
	}
	
	public abstract void paintComponent(Graphics g);
	
	public void setLocation(Location location) {
		this.location = new Location(location);
	}
	
	public void setVelocity(Velocity velocity) {
		this.velocity = new Velocity(velocity);
	}
	
	public void setAcceleration(Acceleration acceleration) {
		this.acceleration = new Acceleration(acceleration);
	}
	
	public Location getLocation() {
		return new Location(location);
	}
	
	public Velocity getVelocity() {
		return new Velocity(velocity);
	}
	
	public Acceleration getAcceleration() {
		return new Acceleration(acceleration);
	}

	@Override
	public int hashCode() {
		return Objects.hash(acceleration, location, name, type, velocity, weight);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof SpaceObject)) {
			return false;
		}
		SpaceObject other = (SpaceObject) obj;
		return Objects.equals(acceleration, other.acceleration) && Objects.equals(location, other.location)
				&& Objects.equals(name, other.name) && Objects.equals(type, other.type)
				&& Objects.equals(velocity, other.velocity)
				&& Double.doubleToLongBits(weight) == Double.doubleToLongBits(other.weight);
	}
	
	
}
