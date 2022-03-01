package main;

import javax.swing.JFrame;

import core.SpaceSystem;
import graphics.MainPanel;

public class Main {

	public static void main(String[] args) {
		JFrame okno = new JFrame();
		okno.setTitle("Hynek Moudrý, A21B0216P");
		okno.setSize(640, 480);
		
		okno.add(new MainPanel(new SpaceSystem())); //prida komponentu
		okno.pack(); //udela resize okna dle komponent
		
		okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		okno.setLocationRelativeTo(null); //vycentrovat na obrazovce
		okno.setVisible(true);
	}

}
