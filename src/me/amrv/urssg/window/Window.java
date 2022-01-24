/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package me.amrv.urssg.window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

/**
 *
 * @author marruiad
 */
public class Window {

    private final JFrame frame;
    private final Canvas canvas;
    private float state;
    protected boolean shouldClose = false;
    private boolean fullScreen = false;
    private Rectangle last;
    protected int refreshRate = 16;
    private final WindowThread thread;
    private final WindowEvents eventHandler;
    private final WindowDraw drawer;
    private BufferStrategy buffer;
    private Graphics graphics;

    public Window() {
        frame = new JFrame("Ventana");
        eventHandler = new WindowEvents(this);
        canvas = new Canvas();
        final int height = (int) (Toolkit.getDefaultToolkit().getScreenSize().height * 0.6f);
        final int width = (int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.6f);
        thread = new WindowThread(this);
        drawer = new WindowDraw(canvas);

        canvas.setFocusable(false);
        canvas.setBackground(Color.BLACK);

        frame.setSize(width, height);
        frame.setPreferredSize(frame.getSize());
        frame.setLocationRelativeTo(null);
        frame.addWindowListener(eventHandler);

        last = new Rectangle(frame.getLocation().x, frame.getLocation().y, frame.getSize().width, frame.getSize().height);

        frame.setVisible(true);
        thread.start();
    }

    protected JFrame get() {
        return frame;
    }

    protected WindowThread getThread() {
        return thread;
    }

    protected WindowDraw getDrawer() {
        return drawer;
    }

    protected Canvas getCanvas() {
        return canvas;
    }
    
    protected void createGraphics() {
        canvas.createBufferStrategy(2);
        buffer = canvas.getBufferStrategy();

    }

    protected Graphics getGraphics() {
        return buffer.getDrawGraphics();

    }

    protected void render() {
        if (buffer != null)
            buffer.show();
    }

    public void addDrawer(Drawer drawer) {
        if (canvas.getBufferStrategy() == null)
            createGraphics();
        this.drawer.add(drawer);
    }

    public void removeDrawer(Drawer drawer) {
        this.drawer.remove(drawer);
    }

    public void setSize(Dimension dim) {
        frame.setSize(dim);
    }

    public void setSize(int width, int height) {
        frame.setSize(width, height);
    }

    public Dimension getSize() {
        return frame.getSize();
    }

    public void setFullScreen(boolean fullscreen) {
        frame.dispose();
        frame.setUndecorated(fullscreen);
        if (fullscreen) {
            last = new Rectangle(frame.getLocation().x, frame.getLocation().y, frame.getSize().width, frame.getSize().height);
            final Rectangle bounds = frame.getGraphicsConfiguration().getBounds();
            frame.setLocation(bounds.x, bounds.y);
            frame.setSize(bounds.width, bounds.height);
        } else {
            frame.setLocation(last.x, last.y);
            frame.setSize(last.width, last.height);
        }
        frame.setVisible(true);
        this.fullScreen = fullscreen;
    }

    public boolean isFullScreen() {
        return fullScreen;
    }

    public void close() {
        shouldClose = true;
        thread.stop();
        frame.setVisible(false);
        state = -1f;
        if (canvas.getBufferStrategy() != null)
            canvas.getBufferStrategy().dispose();
        frame.dispose();
    }

    public boolean shouldClose() {
        return shouldClose;
    }

    public int getFullState() {
        return (int) state;
    }

    public float getState() {
        return state;
    }

    public void setState(float state) {
        this.state = state;
    }

}
