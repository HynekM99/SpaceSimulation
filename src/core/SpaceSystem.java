package core;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import javax.swing.Timer;

import data.SpaceData;
import spaceObjects.SpaceObject;

public class SpaceSystem {
	private final Timer timer;
	
	public SpaceObject centerObject;
	private final Collection<ActionListener> listeners;
	private final List<SpaceObject> spaceObjects;
	
	private final double gravitationConstant;
	
	private double simulationTime = 0;
	
	public SpaceSystem(SpaceData spaceData) {
		listeners = new HashSet<>();
		timer = new Timer(10, e -> update(spaceData.timeStep / 100));
		gravitationConstant = spaceData.gravitationalConstant;
		spaceObjects = spaceData.spaceObjects;
		centerObject = spaceObjects.get(0);
	}
	
	private void update(double t) {
		double dt_min = 0.01;
		
		while (t > 0) {
			double dt = Math.min(t, dt_min);
			
			spaceObjects.forEach(object -> computeAcceleration(object));
			
			spaceObjects.forEach(object -> {
				Velocity addVelocity = new Velocity(Vector.scale(object.getAcceleration(), dt/2));
				object.setVelocity(new Velocity(Vector.add(object.getVelocity(), addVelocity)));
				
				Velocity addLocation = new Velocity(Vector.scale(object.getVelocity(), dt));
				object.setLocation(new Location(Vector.add(object.getLocation(), addLocation)));
				object.setVelocity(new Velocity(Vector.add(object.getVelocity(), addVelocity)));
				
			});
			
			t -= dt;
			simulationTime += dt;
		}
		
		listeners.forEach(listener -> listener.actionPerformed(null));
	}
	
	private void computeAcceleration(SpaceObject oi) {
		Acceleration acceleration = new Acceleration();
		
		for (SpaceObject oj : spaceObjects) {
			if (oi.equals(oj) || oi.getLocation().equals(oj.getLocation())) { continue; }
			
			Vector vector = Vector.subtract(oj.getLocation(), oi.getLocation());
			double c = vector.size * vector.size * vector.size;
			vector = Vector.scale(vector, oj.weight / c);
			acceleration = new Acceleration(Vector.add(acceleration, vector));
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
		return new ArrayList<>(spaceObjects);
	}
}
