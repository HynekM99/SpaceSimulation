package spaceObjects;

import java.awt.Color;
import java.awt.Graphics;

import core.Location;
import core.SpaceObject;
import core.Velocity;

public class Planet extends SpaceObject {
	private static final double MASS_CONSTANT = 4 / (Math.PI * 1);
	
	private final int radiusInt;
	private final int diameterInt;
	public final Color color;
	public final double radius;
	
	public Planet(String name, String type, Location location, Velocity velocity, double weight, Color color) {
		super(name, type, location, velocity, weight);
		this.color = color;
		this.radius = Math.sqrt(weight * MASS_CONSTANT);
		this.radiusInt = (int)radius;
		this.diameterInt = 2*radiusInt;
	}

	@Override
	public void paintComponent(Graphics g) {
		g.setColor(color);
		g.fillOval((int)location.x - radiusInt, (int)location.y - radiusInt, diameterInt, diameterInt);
		g.setColor(Color.white);
//		g.drawString(location.toString(), (int)location.x, (int)location.y);
	}

}
