package data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import core.vectors.Location;
import core.vectors.Velocity;
import data.parsers.IDataParser;
import spaceObjects.Comet;
import spaceObjects.Planet;
import spaceObjects.SpaceObject;

public class SpaceData {
	public final List<SpaceObject> spaceObjects;
	
	public final double gravitationalConstant; 
	public final double timeStep;
	
	public SpaceData(IDataParser dataParser) {
		spaceObjects = new ArrayList<>();
		
		double timeStep = 0;
		double gravitationalConstant = 0;
		
		List<String[]> data = null;
		try {
			data = dataParser.parseData();
			
			if (data == null || data.get(0).length != 2) {
				System.out.println("Invalid csv data.");
				System.exit(1);
			}
			
			gravitationalConstant = Double.parseDouble(data.get(0)[0]);
			timeStep = Double.parseDouble(data.get(0)[1]);
		}
		catch (IOException ex) {
			System.out.println(ex.getMessage());
			System.exit(1);
		}
		
		this.timeStep = timeStep;
		this.gravitationalConstant = gravitationalConstant;
		
		data.remove(0);
		parseObjects(data);
	}
	
	private void parseObjects(List<String[]> data) {
		for (String[] row : data) {
			SpaceObject object = parseObject(row);
			if (object == null) { continue; }
			spaceObjects.add(object);
		}
	}
	
	private SpaceObject parseObject(String[] row) {
		double x = 0, y = 0, vx = 0, vy = 0, m = 0;
		
		try {
			x = Double.parseDouble(row[2]);
			y = Double.parseDouble(row[3]);
			vx = Double.parseDouble(row[4]);
			vy = Double.parseDouble(row[5]);
			m = Double.parseDouble(row[6]);
		} catch (IllegalArgumentException ex) {
			System.out.println(ex.getMessage());
			System.exit(1);
		}
		
		if (row[1].equals("Planet")) {
			return new Planet(row[0], new Location(x, y), new Velocity(vx, vy), m);
		}
		else if (row[1].equals("Comet")) {
			return new Comet(row[0], new Location(x, y), new Velocity(vx, vy), m);
		}
		return null;
	}
}
