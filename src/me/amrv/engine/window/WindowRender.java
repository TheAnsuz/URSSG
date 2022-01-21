package me.amrv.engine.window;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Stroke;

public class WindowRender {

	private final Graphics2D g;

	public enum StrokeCorner {
		BUTT(BasicStroke.CAP_BUTT), ROUND(BasicStroke.CAP_ROUND), SQARE(BasicStroke.CAP_SQUARE);

		private int value;

		StrokeCorner(int value) {
			this.value = value;
		}
	}

	public enum StrokeSegment {
		BEVEL(BasicStroke.JOIN_BEVEL), MITER(BasicStroke.JOIN_MITER), ROUND(BasicStroke.JOIN_ROUND);

		private int value;

		StrokeSegment(int value) {
			this.value = value;
		}
	}

	protected WindowRender(Graphics2D graphics) {
		this.g = graphics;
		this.g.setColor(Color.WHITE);
		this.g.setStroke(null);
	}

	public void setColor(Color color) {
		if (color == null)
			color = Color.WHITE;

		g.setColor(color);
	}

	public Color getColor() {
		return g.getColor();
	}

	public void setStroke(Stroke stroke) {
		g.setStroke(stroke);
	}

	public void setStroke(float thickness) {
		g.setStroke(new BasicStroke(thickness));
	}

	public void setStroke(float thickness, StrokeCorner corner) {
		g.setStroke(new BasicStroke(thickness, corner.value, 0));
	}

	public void setStroke(float thickness, StrokeSegment segment) {
		g.setStroke(new BasicStroke(thickness, 0, segment.value));
	}

	public void setStroke(float thickness, StrokeCorner corner, StrokeSegment segment) {
		g.setStroke(new BasicStroke(thickness, corner.value, segment.value));
	}

	public void drawLine(int startX, int startY, int endX, int endY) {
		g.drawLine(startX, startY, endX, endY);
	}

	public void drawLine(Point start, Point end) {
		g.drawLine(start.x, start.y, end.x, end.y);
	}

	public void drawRectangle(int startX, int startY, int endX, int endY) {
		g.drawRect(startX, startY, endX, endY);
	}

	public void drawRectangle(Point start, Point end) {
		g.drawRect(start.x, start.y, end.x - start.x, end.y - start.y);
	}

	public void drawRectangle(Rectangle rectangle) {
		g.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
	}
	
	public void fillRectangle(int startX, int startY, int endX, int endY) {
		g.fillRect(startX, startY, endX, endY);
	}

	public void fillRectangle(Point start, Point end) {
		g.fillRect(start.x, start.y, end.x - start.x, end.y - start.y);
	}

	public void fillRectangle(Rectangle rectangle) {
		g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
	}

	public void drawOval(int startX, int startY, int width, int height) {
		g.drawOval(startX, startY, width, height);
	}

	public void drawOval(Rectangle bounding) {
		g.drawOval(bounding.x, bounding.y, bounding.width, bounding.height);
	}

	public void drawOval(Point start, Point end) {
		g.drawOval(start.x, start.y, end.x - start.x, end.y - start.y);
	}
	
	public void fillOval(int startX, int startY, int width, int height) {
		g.fillOval(startX, startY, width, height);
	}

	public void fillOval(Rectangle bounding) {
		g.fillOval(bounding.x, bounding.y, bounding.width, bounding.height);
	}

	public void fillOval(Point start, Point end) {
		g.fillOval(start.x, start.y, end.x - start.x, end.y - start.y);
	}

}
