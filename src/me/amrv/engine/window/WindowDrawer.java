package me.amrv.engine.window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

import me.amrv.engine.Drawer;

public class WindowDrawer {

	private final Canvas canvas;
	private Rectangle bounds;
	private BufferStrategy buffer;
	protected final List<Drawer> drawers = new ArrayList<>();
	private final RenderingHints hints;

	protected WindowDrawer(Canvas canvas) {
		this.canvas = canvas;
		this.bounds = this.canvas.getBounds();
		this.canvas.setBackground(Color.BLACK);
		this.canvas.createBufferStrategy(2);
		this.buffer = this.canvas.getBufferStrategy();
		this.hints = new RenderingHints(RenderingHints.KEY_ALPHA_INTERPOLATION,RenderingHints.VALUE_ALPHA_INTERPOLATION_DEFAULT);
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
		hints.put(RenderingHints.KEY_ALPHA_INTERPOLATION,
				(value) ? RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY
						: RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);
	}

	public void setHighQualityRending(boolean value) {
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
		BICUBIC(RenderingHints.VALUE_INTERPOLATION_BICUBIC), 
		BILINEAR(RenderingHints.VALUE_INTERPOLATION_BILINEAR);

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

	// Only works for Java 9...
//	public enum ResolutionType {
//		BASE(RenderingHints.VALUE_RESOLUTION_VARIANT_BASE),
//		DPI(RenderingHints.VALUE_RESOLUTION_VARIANT_DPI_FIT), 
//		DPI_EXTRA(RenderingHints.VALUE_RESOLUTION_VARIANT_SIZE_FIT);
//
//		private Object value;
//
//		ResolutionType(Object value) {
//			this.value = value;
//		}
//	}
//	
//	public void setResolutionType(ResolutionType value) {
//		getGraphics2D().setRenderingHint(RenderingHints.KEY_RESOLUTION_VARIANT, value.value);
//	}
	
	public void setHighQualityBounding(boolean value) {
		hints.put(RenderingHints.KEY_STROKE_CONTROL,
				(value) ? RenderingHints.VALUE_STROKE_NORMALIZE : RenderingHints.VALUE_STROKE_PURE);
	}
	
	
	public void setTextAntialising(boolean value) {
		hints.put(RenderingHints.KEY_TEXT_ANTIALIASING, (value) ? RenderingHints.VALUE_TEXT_ANTIALIAS_ON : RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
	}
	
	public enum Contrast {
		GASP(RenderingHints.VALUE_TEXT_ANTIALIAS_GASP),
		HBGR(RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HBGR),
		HRGB(RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB),
		VBGR(RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_VBGR),
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
		if (drawer == null)
			return;
		if (drawers.isEmpty() || !drawers.contains(drawer))
			drawers.add(drawer);
	}

	protected void removeDrawer(Drawer drawer) {
		if (drawer == null)
			return;
		if (!drawers.isEmpty() && drawers.contains(drawer))
			drawers.remove(drawer);
	}

	protected void removeDrawers() {
		drawers.clear();
	}

	protected Graphics getGraphics() {
		return buffer.getDrawGraphics();
	}

	protected Graphics2D getGraphics2D() {
		Graphics2D g = (Graphics2D) buffer.getDrawGraphics();
		g.setRenderingHints(hints);
		return g;
	}

	protected void updateGraphics() {
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
