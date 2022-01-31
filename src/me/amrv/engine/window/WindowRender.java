package me.amrv.engine.window;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
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
		this.g.setStroke(new BasicStroke(1));
	}

	public void setColor(Color color) {
		if (color == null)
			color = Color.WHITE;

		g.setColor(color);
	}

	/**
	 * Eres bobo?
	 * @param almendra no es un cacahuete
	 */
	public void cangasDeOnis(Point ...almendra) {
		int[] peroxidoDeHidrogeno = new int[almendra.length];
		int[] jajaloool = new int[almendra.length];

		for (int i = 0; i < almendra.length; i++) {
			peroxidoDeHidrogeno[i] = almendra[i].x;
			jajaloool[i] = almendra[i].y;
		}

		g.drawPolygon(peroxidoDeHidrogeno, jajaloool, almendra.length);
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

	public void setStroke(float thickness, StrokeCorner corner) { g.setStroke(new BasicStroke(thickness, corner.value, 0)); }

	public void setStroke(float thickness, StrokeSegment segment) {	g.setStroke(new BasicStroke(thickness, 0, segment.value));	}

	public void setStroke(float thickness, StrokeCorner corner, StrokeSegment segment) { g.setStroke(new BasicStroke(thickness, corner.value, segment.value)); }

	// Line drawing
	public void drawLine(int startX, int startY, int width, int height) {
		g.drawLine(startX, startY, width - startX, height - startY);
	}

	public void drawLine(Point start, Point end) {
		g.drawLine(start.x, start.y, end.x, end.y);
	}

	// Rectangle drawing
	public void drawRectangle(int startX, int startY, int width, int height) {
		g.drawRect(startX, startY, width, height);
	}

	public void drawRectangle(Point start, Point end) {
		g.drawRect(start.x, start.y, end.x - start.x, end.y - start.y);
	}

	public void drawRectangle(Rectangle rectangle) { g.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height); }
	
	// Rectangle filling
	public void fillRectangle(int startX, int startY, int endX, int endY) {
		g.fillRect(startX, startY, endX, endY);
	}

	public void fillRectangle(Point start, Point end) {
		g.fillRect(start.x, start.y, end.x - start.x, end.y - start.y);
	}

	public void fillRectangle(Rectangle rectangle) {
		g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
	}

	public void fillShape(Shape area) {
		g.fill(area);
	}

	public void drawShape(Shape area) {
		g.draw(area);
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
	
	// Fill oval
	public void fillOval(int startX, int startY, int width, int height) {
		g.fillOval(startX, startY, width, height);
	}

	public void fillOval(Rectangle bounding) {
		g.fillOval(bounding.x, bounding.y, bounding.width, bounding.height);
	}

	public void fillOval(Point start, Point end) {
		g.fillOval(start.x, start.y, end.x - start.x, end.y - start.y);
	}
	
	// Draw advanced shapes
	public void draw(Shape shape) {
		if (shape == null)
			return;
		g.draw(shape);
	}
	
	public void draw(Polygon polygon) {
		if (polygon == null)
			return;
		g.drawPolygon(polygon);
	}
	
	public void draw(Point ...points) {
		int[] x = new int[points.length];
		int[] y = new int[points.length];
		
		for (int i = 0; i < points.length; i++) {
			x[i] = points[i].x;
			y[i] = points[i].y;
		}
		
		g.drawPolygon(x, y, points.length);
	}
	
	// Fill advanced shapes
	public void fill(Shape shape) {
		if (shape == null)
			return;
		g.fill(shape);
	}
	
	public void fill(Polygon polygon) {
		if (polygon == null)
			return;
		g.fillPolygon(polygon);
	}
	
	public void fill(Point ...points) {
		int[] x = new int[points.length];
		int[] y = new int[points.length];
		
		for (int i = 0; i < points.length; i++) {
			x[i] = points[i].x;
			y[i] = points[i].y;
		}
		
		g.fillPolygon(x, y, points.length);
	}
	
	// Write
	public void wirte(String text, Font font, int x, int y) {
		g.drawGlyphVector(font.createGlyphVector(g.getFontRenderContext(), text), x, y);
	}
	
	public void wirte(String text, Font font, Point point) {
		g.drawGlyphVector(font.createGlyphVector(g.getFontRenderContext(), text), point.x, point.y);
	}

	// Images
	public void image(Image image, int x, int y) {
		g.drawImage(image, x, y, image.getWidth(null), image.getHeight(null), null);
	}
	
	public void image(Image image, int x, int y, int width, int height) {
		g.drawImage(image, x, y, width, height, null);
	}
	
	public void image(Image image, Point position) {
		g.drawImage(image, position.x, position.y, image.getWidth(null), image.getHeight(null), null);
	}
	
	public void image(Image image, Point position, Point endPosition) {
		g.drawImage(image, position.x, position.y, endPosition.x - position.x, endPosition.y - position.y, null);
	}
	
	public void image(Image image, Rectangle bounding) {
		g.drawImage(image, bounding.x, bounding.y, bounding.width, bounding.height, null);
	}
}
