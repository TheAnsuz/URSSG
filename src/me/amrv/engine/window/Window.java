package me.amrv.engine.window;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JFrame;

import me.amrv.engine.Drawer;
import me.amrv.engine.WindowUtils;

/**
 * The window is the base of any game
 * 
 * @author Adrian
 *
 */
public class Window {

	protected final JFrame frame;
	private final WindowDrawer drawer;
	private final WindowThread thread;
	private boolean fullscreen;
	private final Rectangle last;
	private boolean shouldClose;
	private boolean alwaysOnTop;

	public Window(String title) {
		frame = new JFrame(title);
		frame.setSize(WindowUtils.getScreenPercent(frame.getGraphicsConfiguration(), 60f));
		frame.setLocationRelativeTo(null);
		frame.requestFocus();
		last = new Rectangle(frame.getLocation(), frame.getSize());

		final Canvas screen = new Canvas();
		screen.setSize(frame.getSize());
		frame.add(screen);
		frame.setVisible(true);

		drawer = new WindowDrawer(screen);
		thread = new WindowThread(drawer);
		thread.start();

	}

	public void addDrawer(Drawer drawer) {
		this.drawer.addDrawer(drawer);
	}
	
	public void addDrawer(Drawer drawer, int index) {
		this.drawer.addDrawer(drawer, index);
	}
	
	public void addDrawer(Drawer drawer, int index, boolean overlap) {
		this.drawer.addDrawer(drawer, index, overlap);
	}
	
	public void removeDrawer(Drawer drawer) {
		this.drawer.removeDrawer(drawer);
	}
	
	public void removeDrawerLayer(int layer) {
		this.drawer.removeLayer(layer);
	}
	
	public void removeDrawers() {
		this.drawer.removeDrawers();
	}
	
	public boolean isFullScreen() {
		return fullscreen;
	}

	public void setFullScreen(boolean fullscreen) {
		if (frame.isFocused())
		frame.setAlwaysOnTop(true);
		frame.dispose();
		frame.setUndecorated(fullscreen);
		if (fullscreen) {
			last.setLocation(frame.getLocation());
			last.setSize(frame.getSize());

			final Rectangle loc = WindowUtils.getScreenBounds(frame.getGraphicsConfiguration());
			frame.setLocation(loc.getLocation());
			frame.setSize(loc.getSize());
			
		} else {
			frame.setLocation(last.getLocation());
			frame.setSize(last.getSize());
		}
		frame.setVisible(true);
		frame.setAlwaysOnTop(alwaysOnTop);
		this.fullscreen = fullscreen;
	}

	public void setAlwaysOnTop(boolean alwaysOnTop) {
		this.alwaysOnTop = alwaysOnTop;
		this.frame.setAlwaysOnTop(alwaysOnTop);
	}
	
	public boolean isAlwaysOnTop() {
		return alwaysOnTop;
	}
	
	public void setSize(int width, int height) {
		setSize(new Dimension(width, height));
	}

	public void setSize(Dimension size) {
		frame.setSize(size);
	}

	public Dimension getSize() {
		return frame.getSize();
	}

	public WindowDrawer getDrawer() {
		return drawer;
	}

	public boolean shouldClose() {
		return shouldClose;
	}

	public void close() {
		frame.setVisible(false);
		frame.dispose();
	}
}
