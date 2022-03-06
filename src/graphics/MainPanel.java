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

import spaceObjects.SpaceObject;
import core.SpaceSystem;

@SuppressWarnings("serial")
public class MainPanel extends JPanel implements ActionListener, KeyListener {
	private final SpaceSystem spaceSystem;
	
	public MainPanel(SpaceSystem spaceModel) {
		this.spaceSystem = spaceModel;
		this.spaceSystem.addEventListener(this);
		this.setBackground(Color.BLACK);
		this.setPreferredSize(new Dimension(1500, 1500));
		this.addKeyListener(this);
		this.setFocusable(true);
		this.requestFocusInWindow();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		final Graphics2D g2d = (Graphics2D)g;
		final AffineTransform originalTransform = g2d.getTransform();
		final int cx = this.getWidth()/2;
		final int cy = this.getHeight()/2;
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setTransform(new AffineTransform());
		g2d.translate(cx, cy);
		
		for (SpaceObject object : spaceSystem.getSpaceObjects()) {
			
			double scaleX = cx / spaceSystem.farthestPointX;
			double scaleY = cy / spaceSystem.farthestPointY;
			
			object.scale = Math.min(scaleX, scaleY);
			object.paintComponent(g);
		}
		
		String time = String.format("%.2f", spaceSystem.getSimulationTime());
		FontMetrics metrics = g.getFontMetrics(g.getFont());
		int textWidth = metrics.stringWidth(time);
		int textHeight = (int)metrics.getStringBounds(time, g2d).getHeight();
		
		g2d.setTransform(originalTransform);
		g.setColor(Color.white);
		g.drawString(time, this.getWidth() - textWidth, textHeight);
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
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (spaceSystem.simulationRunning()) {
				spaceSystem.stop();
			}
			else {
				spaceSystem.start();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}
