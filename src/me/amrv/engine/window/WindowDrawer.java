package me.amrv.engine.window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import me.amrv.engine.Drawer;

public class WindowDrawer {

	private final Canvas canvas;
	private BufferStrategy buffer;
	protected final Map<Integer, List<Drawer>> drawers = new TreeMap<>();
	protected int zIndex = 0;

	// Graphics option
	private boolean directPainting = false;
	private Rectangle bounds;
	private int imageType = BufferedImage.TYPE_4BYTE_ABGR;
	private final RenderingHints hints;
	
	// Disposable rendered image
	private BufferedImage rootImage;

	protected WindowDrawer(Canvas canvas) {
		this.canvas = canvas;
		this.bounds = canvas.getBounds();
		this.canvas.setBackground(Color.BLACK);
		this.canvas.createBufferStrategy(2);
		this.buffer = this.canvas.getBufferStrategy();
		this.hints = new RenderingHints(RenderingHints.KEY_ALPHA_INTERPOLATION,
				RenderingHints.VALUE_ALPHA_INTERPOLATION_DEFAULT);
	}

	public synchronized void setVsync(boolean vsync) {
		this.canvas.createBufferStrategy((vsync) ? 2 : 1);
		this.buffer = this.canvas.getBufferStrategy();
	}

	public void setAntialiasing(boolean value) {
		hints.put(RenderingHints.KEY_ANTIALIASING,
				(value) ? RenderingHints.VALUE_ANTIALIAS_ON : RenderingHints.VALUE_ANTIALIAS_OFF);
	}

	public void setHighQualityInterpolation(boolean value) {
		hints.put(RenderingHints.KEY_ALPHA_INTERPOLATION, (value) ? RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY
				: RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);
	}

	public void setHighQualityColoring(boolean value) {
		hints.put(RenderingHints.KEY_COLOR_RENDERING,
				(value) ? RenderingHints.VALUE_COLOR_RENDER_QUALITY : RenderingHints.VALUE_COLOR_RENDER_SPEED);
	}

	public void setDithering(boolean value) {
		hints.put(RenderingHints.KEY_DITHERING,
				(value) ? RenderingHints.VALUE_DITHER_ENABLE : RenderingHints.VALUE_DITHER_DISABLE);
	}

	public void setHighQualityFonts(boolean value) {
		hints.put(RenderingHints.KEY_FRACTIONALMETRICS,
				(value) ? RenderingHints.VALUE_FRACTIONALMETRICS_ON : RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
	}

	public enum Interpolation {
		NEAREST(RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR),
		BICUBIC(RenderingHints.VALUE_INTERPOLATION_BICUBIC), BILINEAR(RenderingHints.VALUE_INTERPOLATION_BILINEAR);

		private Object value;

		Interpolation(Object value) {
			this.value = value;
		}
	}

	public void setInterpolation(Interpolation value) {
		hints.put(RenderingHints.KEY_INTERPOLATION, value.value);
	}

	public void setHighQualityRendering(boolean value) {
		hints.put(RenderingHints.KEY_RENDERING,
				(value) ? RenderingHints.VALUE_RENDER_QUALITY : RenderingHints.VALUE_RENDER_SPEED);
	}

	public void setDirectPainting(boolean directPainting) {
		this.directPainting = directPainting;
	}

	public void setHighQualityBounding(boolean value) {
		hints.put(RenderingHints.KEY_STROKE_CONTROL,
				(value) ? RenderingHints.VALUE_STROKE_NORMALIZE : RenderingHints.VALUE_STROKE_PURE);
	}

	public void setTextAntialising(boolean value) {
		hints.put(RenderingHints.KEY_TEXT_ANTIALIASING,
				(value) ? RenderingHints.VALUE_TEXT_ANTIALIAS_ON : RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
	}

	public enum Contrast {
		GASP(RenderingHints.VALUE_TEXT_ANTIALIAS_GASP), HBGR(RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HBGR),
		HRGB(RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB), VBGR(RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_VBGR),
		VRGB(RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_VRGB);

		private Object value;

		private Contrast(Object value) {
			this.value = value;
		}
	}

	public void setLCDContrast(Contrast value) {
		hints.put(RenderingHints.KEY_TEXT_LCD_CONTRAST, value.value);
	}

	protected void addDrawer(Drawer drawer) {
		addDrawer(drawer, zIndex, false);
	}

	protected void addDrawer(Drawer drawer, int index) {
		addDrawer(drawer, index, false);
	}

	protected void addDrawer(Drawer drawer, int index, boolean overlap) {
		if (drawer == null)
			return;

		final Integer val = Integer.valueOf(index);
		if (drawers.containsKey(val)) {
			if (overlap)
				drawers.get(val).add(0, drawer);
			else
				drawers.get(val).add(drawer);
		} else {
			if (index > zIndex)
				zIndex = index;
			drawers.put(val, new ArrayList<Drawer>(Arrays.asList(drawer)));
		}
	}

	protected void removeLayer(int layer) {
		if (drawers.containsKey(Integer.valueOf(layer)))
			drawers.remove(Integer.valueOf(layer));
	}

	protected void removeDrawer(Drawer drawer) {
		if (drawer == null)
			return;
		drawers.forEach((i, value) -> {
			if (!value.isEmpty() || value.contains(drawer))
				value.remove(drawer);
		});
	}

	protected void removeDrawers() {
		drawers.clear();
	}

	protected Graphics getGraphics() {
		return buffer.getDrawGraphics();
	}

	protected Graphics2D getGraphics2D() {
		if (directPainting) {
			Graphics2D g = (Graphics2D) buffer.getDrawGraphics();
			g.setRenderingHints(hints);
			return g;
		}
		rootImage = new BufferedImage(bounds.width, bounds.height, imageType);
		final Graphics2D g = rootImage.createGraphics();
		g.setRenderingHints(hints);
		return g;

	}

	protected void updateGraphics() {
		if (!directPainting) {
			buffer.getDrawGraphics().drawImage(rootImage, 0, 0, canvas.getWidth(), canvas.getHeight(), canvas);
		}
		buffer.show();
	}

	public void setBounding(Rectangle size) {
		if (size == null)
			return;
		this.bounds = size;
	}

	public Rectangle getBounding() {
		return bounds;
	}
}
