package core;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

import javax.swing.Timer;

import core.vectors.Acceleration;
import core.vectors.Location;
import core.vectors.Vector;
import core.vectors.Velocity;
import data.SpaceData;
import spaceObjects.SpaceObject;

public class SpaceSystem {
	private final Timer timer;
	
	private final Collection<ActionListener> listeners;
	private final List<SpaceObject> spaceObjects;
	
	private final double gravitationConstant;
	
	private double simulationTime = 0;
	public double farthestPointX;
	public double farthestPointY;
	
	public SpaceSystem(SpaceData spaceData) {
		listeners = new HashSet<>();
		timer = new Timer(10, e -> update(spaceData.timeStep / 100));
		
		gravitationConstant = spaceData.gravitationalConstant;
		spaceObjects = spaceData.spaceObjects;
		farthestPointX = spaceObjects.get(0).getLocation().x + spaceObjects.get(0).size;
		farthestPointY = spaceObjects.get(0).getLocation().y + spaceObjects.get(0).size;
	}
	
	private void update(double t) {
		double dt_min = 0.01;
		
		while (t > 0) {
			double dt = Math.min(t, dt_min);
			
			farthestPointX = spaceObjects.get(0).getLocation().x + spaceObjects.get(0).size;
			farthestPointY = spaceObjects.get(0).getLocation().y + spaceObjects.get(0).size;
			
			spaceObjects.forEach(object -> updateAcceleration(object));
			spaceObjects.forEach(object -> updateObject(object, dt));
			
			t -= dt;
			simulationTime += dt;
		}
		
		listeners.forEach(listener -> listener.actionPerformed(null));
	}
	
	private void updateObject(SpaceObject object, double timeStep) {
		Velocity velocityIncrement = new Velocity(Vector.scale(object.getAcceleration(), timeStep/2));
		object.setVelocity(new Velocity(Vector.add(object.getVelocity(), velocityIncrement)));
		
		Velocity locationIncrement = new Velocity(Vector.scale(object.getVelocity(), timeStep));
		object.setLocation(new Location(Vector.add(object.getLocation(), locationIncrement)));
		object.setVelocity(new Velocity(Vector.add(object.getVelocity(), velocityIncrement)));

		if (Math.abs(object.getLocation().x) + object.size > farthestPointX) {
			farthestPointX = Math.abs(object.getLocation().x) + object.size;
		}
		if (Math.abs(object.getLocation().y) + object.size > farthestPointY) {
			farthestPointY = Math.abs(object.getLocation().y) + object.size;
		}
	}
	
	private void updateAcceleration(SpaceObject oi) {
		Acceleration acceleration = new Acceleration();
		
		for (SpaceObject oj : spaceObjects) {
			if (oi.equals(oj) || oi.getLocation().equals(oj.getLocation())) { 
				continue;
			}
			
			Vector distanceVector = Vector.subtract(oj.getLocation(), oi.getLocation());
			double c = distanceVector.size * distanceVector.size * distanceVector.size;
			distanceVector = Vector.scale(distanceVector, oj.weight / c);
			acceleration = new Acceleration(Vector.add(acceleration, distanceVector));
		}
		
		acceleration = new Acceleration(Vector.scale(acceleration, gravitationConstant));
		oi.setAcceleration(acceleration);
	}
	
	public void addEventListener(ActionListener e) {
		listeners.add(e);
	}
	
	public void start() {
		timer.start();
	}
	
	public void stop() {
		timer.stop();
	}
	
	public boolean simulationRunning() {
		return timer.isRunning();
	}
	
	public double getSimulationTime() {
		return simulationTime;
	}
	
	public Collection<SpaceObject> getSpaceObjects() {
		return spaceObjects;
	}
}
