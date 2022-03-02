package main;

import javax.swing.JFrame;

import core.SpaceSystem;
import data.SpaceData;
import data.parsers.CSVParser;
import graphics.MainPanel;

public class Main {

	public static void main(String[] args) {
		JFrame okno = new JFrame();
		okno.setTitle("Hynek Moudrý, A21B0216P");
		okno.setSize(640, 480);
		
		okno.add(new MainPanel(new SpaceSystem(new SpaceData(new CSVParser("data/simple1.csv")))));
		okno.pack();
		
		okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		okno.setLocationRelativeTo(null);
		okno.setVisible(true);
	}

}
