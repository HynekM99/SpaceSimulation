package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;

import javax.swing.JPanel;
import javax.swing.KeyStroke;

import core.SpaceSystem;

public class MainPanel extends JPanel implements ActionListener, KeyListener {
	private final SpaceSystem spaceModel;
	
	public MainPanel(SpaceSystem spaceModel) {
		this.spaceModel = spaceModel;
		this.spaceModel.addEventListener(this);
		this.setBackground(Color.BLACK);
		this.setPreferredSize(new Dimension(1500, 1500));
		this.addKeyListener(this);
		this.setFocusable(true);
		this.requestFocusInWindow();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		final int cx = this.getWidth()/2;
		final int cy = this.getHeight()/2;
		
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
		AffineTransform originalTransform = g2d.getTransform();
		
		g2d.setTransform(new AffineTransform());
		g2d.translate(cx, cy);
		g2d.translate(-(int)spaceModel.centerObject.getLocation().x, -(int)spaceModel.centerObject.getLocation().y);
		
		spaceModel.getSpaceObjects().forEach(object -> object.paintComponent(g));
		
		String time = String.format("%.2f", spaceModel.getSimulationTime());
		FontMetrics metrics = g.getFontMetrics(g.getFont());
		int textWidth = metrics.stringWidth(time);
		g.setColor(Color.white);
		g.drawString(time, 0, 0);
		
		g2d.setTransform(originalTransform);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() != KeyEvent.VK_SPACE) {
			return;
		}

		if (spaceModel.simulationRunning()) {
			spaceModel.stop();
		}
		else {
			spaceModel.start();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}
