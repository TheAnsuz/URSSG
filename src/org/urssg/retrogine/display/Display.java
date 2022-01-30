package org.urssg.retrogine.display;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;

import org.urssg.retrogine.input.InputManager;

public class Display {

	private final WindowDisplay screen;
	
	public Display(WindowConfiguration config) {
		screen = new WindowDisplay(config);
	}
	
	public InputManager getInputManager() {
		return screen.getInputManager();
	}
	
	public void setInputManager(InputManager manager) {
		screen.setInputManager(manager);
	}
	
	public void setIcon(Image icon) {
		screen.getExternal().setIconImage(icon);
	}
	
	public Image getIcon() {
		return screen.getExternal().getIconImage();
	}
	
	public void setVisisble(boolean visible) {
		screen.getExternal().setVisible(visible);
	}
	
	public boolean isVisible() {
		return screen.getExternal().isVisible();
	} 
	
	public void setTitle(String title) {
		screen.getExternal().setTitle(title);
	}
	
	public String getTitle() {
		return screen.getExternal().getTitle();
	}
	
	public void setAlwaysOnTop(boolean alwaysOnTop) {
		screen.getExternal().setAlwaysOnTop(alwaysOnTop);
	}
	
	public boolean isAlwaysOnTop() {
		return screen.getExternal().isAlwaysOnTop();
	}
	
	public void close() {
		screen.close();
	}
	
	public void setResizable(boolean enabled) {
		screen.getExternal().setResizable(enabled);
	}
	
	public boolean isResizable() {
		return screen.getExternal().isResizable();
	}
	
	public Rectangle getBounds() {
		return screen.getExternal().getBounds();
	}
	
	public void setBounds(Rectangle bounds) {
		screen.getExternal().setBounds(bounds);
	}
	
	public void setLocation(int x, int y) {
		screen.getExternal().setLocation(x, y);
	}
	
	public void setLocation(Point location) {
		screen.getExternal().setLocation(location);
	}
	
	public Point getLocation() {
		return screen.getExternal().getLocation();
	}
	
	public void setX(int x) {
		screen.getExternal().setLocation(x, getY());
	}
	
	public int getX() {
		return screen.getExternal().getX();
	}
	
	public void setY(int y) {
		screen.getExternal().setLocation(getX(), y);
	}
	
	public int getY() {
		return screen.getExternal().getY();
	}
	
	public void setSize(Dimension size) {
		screen.getExternal().setSize(size);
	}
	
	public void setSize(int width, int height) {
		screen.getExternal().setSize(width, height);
	}
	
	public Dimension getSize() {
		return screen.getExternal().getSize();
	}
	
	public void setWidth(int width) {
		screen.getExternal().setSize(width, getHeight());
	}
	
	public int getWidth() {
		return screen.getExternal().getWidth();
	}
	
	public void setHeight(int height) {
		screen.getExternal().setSize(getWidth(), height);
	}
	
	public int getHeight() {
		return screen.getExternal().getHeight();
	}
	
	public void setFullScreen(boolean fullscreen) {
		screen.setFullScreen(fullscreen);
	}
	
	public boolean isFullScreen() {
		return screen.isFullScreen();
	}
	
	public void keepAspectRatio(boolean keep) {
		screen.getInternal().setMaintainAspectRatio(keep);
	}
	
	public boolean shouldKeepAspectRatio() {
		return screen.getInternal().shouldMaintainAspectRatio();
	}
	
	public void setFpsGoal(int fps) {
		screen.getInternal().setFPS(fps);
	}
	
	public int getFpsGoal() {
		return screen.getInternal().getFPS();
	}
	
	public void setDoubleBuffered(boolean doubleBuffered) {
		screen.getInternal().setDoubleBuffered(doubleBuffered);
	}
	
	public boolean isDoubleBuffered() {
		return screen.getInternal().isDoubleBuffered();
	}
	
	public boolean shouldQueueRendering() {
		return screen.getInternal().hasRenderQueue();
	}
	
	public void setQueueRendering(boolean enabled) {
		screen.getInternal().setRenderQueue(enabled);
	}
	
}
