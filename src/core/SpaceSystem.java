package core;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import javax.swing.Timer;
import spaceObjects.Planet;

public class SpaceSystem {
	private final Timer timer;
	private Random r = new Random();
	
	public SpaceObject centerObject;
	private final Collection<ActionListener> listeners;
	private final List<SpaceObject> spaceObjects;
	
	private double gravitationConstant = 1;
	private double simulationTime = 0;
	
	public SpaceSystem() {
		timer = new Timer(17, e -> update(0.17));
		
		listeners = new HashSet<>();
		spaceObjects = new ArrayList<>();
		situation1();
		spaceObjects.add(centerObject);
	}

	private void situation1() {
		centerObject = new Planet("planet1", "Planet", new Location(-360, -360), new Velocity(10, -10), 1, Color.gray);
		spaceObjects.add(new Planet("sun1", "Star", new Location(), new Velocity(), 300, Color.orange));
		spaceObjects.add(new Planet("planet2", "Planet", new Location(-350, -350), new Velocity(5, -5), 5, Color.green));
		spaceObjects.add(new Planet("planet3", "Planet", new Location(-400, 440), new Velocity(5, 5), 5, Color.green));
		spaceObjects.add(new Planet("planet5", "Planet", new Location(400, -400), new Velocity(5, 5),  29, Color.cyan));
	}
	
	private void situation2() {
		centerObject = new Planet("sun1", "Star", new Location(), new Velocity(), 300, Color.orange);
		
		spaceObjects.add(new Planet("planet1", "Planet", new Location(-360, -360), new Velocity(10, -10), 1, Color.gray));
		spaceObjects.add(new Planet("planet2", "Planet", new Location(-350, -350), new Velocity(5, -5), 5, Color.green));
	}
	
	private void situationRandom() {
		centerObject = new Planet("sun1", "Star", new Location(), new Velocity(), 1000, Color.orange);
		for (int i = 0; i < 200; i++) {
			spaceObjects.add(getRandomPlanet());
		}
	}
	
	private Planet getRandomPlanet() {
		return new Planet("", "", new Location(r.nextInt(-1000, 1000), r.nextInt(-1000, 1000)), new Velocity(), r.nextInt(1, 100), Color.CYAN);
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
	
	private void update(double t) {
		double dt_min = 0.01;
		
		while (t > 0) {
			double dt = Math.min(t, dt_min);
			
			spaceObjects.forEach(object -> computeAcceleration(object));
			
			spaceObjects.forEach(object -> {
				Velocity addVelocity = new Velocity(Vector.scale(object.acceleration, dt/2));
				object.setVelocity(new Velocity(Vector.add(object.velocity, addVelocity)));
				
				Velocity addLocation = new Velocity(Vector.scale(object.velocity, dt));
				object.setLocation(new Location(Vector.add(object.location, addLocation)));
				object.setVelocity(new Velocity(Vector.add(object.velocity, addVelocity)));
				
			});
			
			t -= dt;
			simulationTime += dt;
		}
		
		listeners.forEach(listener -> listener.actionPerformed(null));
	}
	
	private void computeAcceleration(SpaceObject oi) {
		Acceleration acceleration = new Acceleration();
		
		for (SpaceObject oj : spaceObjects) {
			if (oi.equals(oj) || oi.location.equals(oj.location)) { continue; }
			
			Vector vector = Vector.subtract(oj.location, oi.location);
			double c = vector.size * vector.size * vector.size;
			vector = Vector.scale(vector, oj.weight / c);
			acceleration = new Acceleration(Vector.add(acceleration, vector));
		}
		
		acceleration = new Acceleration(Vector.scale(acceleration, gravitationConstant));
		oi.setAcceleration(acceleration);
	}
	
	public Collection<SpaceObject> getSpaceObjects() {
		return new ArrayList<>(spaceObjects);
	}
}
