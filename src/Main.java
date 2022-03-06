import javax.swing.JFrame;

import core.SpaceSystem;
import data.SpaceData;
import data.parsers.CSVParser;
import data.parsers.IDataParser;
import graphics.MainPanel;

public class Main {

	public static void main(String[] args) {
		JFrame window = new JFrame();
		IDataParser dataParser = new CSVParser("data/simple2.csv");
		SpaceData spaceData = new SpaceData(dataParser);
		SpaceSystem spaceSystem = new SpaceSystem(spaceData);
		MainPanel mainPanel = new MainPanel(spaceSystem);
		
		window.setTitle("Hynek Moudrý, A21B0216P");
		window.setSize(640, 480);
		
		window.add(mainPanel);
		window.pack();
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}
}
