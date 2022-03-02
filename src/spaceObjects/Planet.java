package spaceObjects;

import java.awt.Color;
import java.awt.Graphics;

import core.Location;
import core.Velocity;

public class Planet extends SpaceObject {
	private static final double DENSITY = 1;
	private static final double MASS_CONSTANT = 4 / (Math.PI * DENSITY);
	
	private final int radiusInt;
	private final int diameterInt;
	public final double radius;
	
	public Color color = Color.orange;
	
	public Planet(String name, Location location, Velocity velocity, double weight) {
		super(name, location, velocity, weight);

		this.radius = Math.sqrt(weight * MASS_CONSTANT);
		this.radiusInt = (int)radius;
		this.diameterInt = 2*radiusInt;
	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(color);
		g.fillOval((int)location.x - radiusInt, (int)location.y - radiusInt, diameterInt, diameterInt);
		g.setColor(Color.white);
	}

}
