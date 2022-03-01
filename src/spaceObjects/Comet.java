package spaceObjects;

import java.awt.Color;
import java.awt.Graphics;

import core.Location;
import core.SpaceObject;
import core.Velocity;

public class Comet extends SpaceObject {
	public final Color color;
	
	public Comet(String name, String type, Location location, Velocity velocity, double weight, Color color) {
		super(name, type, location, velocity, weight);
		this.color = color;
	}

	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub

	}

}
