package spaceObjects;

import java.awt.Color;
import java.awt.Graphics;

import core.vectors.Location;
import core.vectors.Velocity;

public class Planet extends SpaceObject {
	private static final double DENSITY = 1;
	private static final double MASS_CONSTANT = 4 / (Math.PI * DENSITY);
	
	public final double radius;
	public final double diameter;
	
	public Color color = Color.ORANGE;
	
	public Planet(String name, Location location, Velocity velocity, double weight) {
		super(name, location, velocity, weight);

		this.radius = Math.sqrt(Math.abs(weight) * MASS_CONSTANT);
		this.diameter = 2 * radius;
		this.size = radius;
	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(color);
		g.fillOval(
				(int)((location.x - radius)*scale),
				(int)((location.y - radius)*scale),
				(int)(diameter*scale),
				(int)(diameter*scale));
	}
}
