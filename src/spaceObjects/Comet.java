package spaceObjects;

import java.awt.Color;
import java.awt.Graphics;

import core.Location;
import core.Velocity;

public class Comet extends SpaceObject {
	public Color color;
	
	public Comet(String name, Location location, Velocity velocity, double weight) {
		super(name, location, velocity, weight);
	}

	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub

	}

}
