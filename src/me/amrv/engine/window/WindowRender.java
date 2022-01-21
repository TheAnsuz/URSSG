package me.amrv.engine.window;

import java.awt.Color;
import java.awt.Graphics2D;

public class WindowRender {

	private final Graphics2D g;
	private Color defColor = Color.WHITE;
	private int defStroke = 1;
	
	protected WindowRender(Graphics2D graphics) {
		this.g = graphics;
	}
	
	public void setColor(Color color) {
		if (color == null)
			color = Color.WHITE;
		
		defColor = color;
		g.setColor(color);
	}
	
	public Color getColor() {
		return defColor;
	}
	
	public void drawLine(int startX, int startY, int endX, int endY) {
		g.drawLine(startX, startY, endX, endY);
	}
}
