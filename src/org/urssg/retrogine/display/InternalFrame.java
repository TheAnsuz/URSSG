package org.urssg.retrogine.display;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;

public final class InternalFrame extends JComponent {

    private static final long serialVersionUID = 6408254235732870474L;

    private final RenderLayer[] layers;
    private final Rectangle size; // Absolute internal frame size from 0,0 to width, height
    private final BufferedImage inside;
    private Graphics2D g2;
    private final InternalFrameLoop looper;
    // Keep aspect ratio
    private boolean maintainAspectRatio = true;
    private boolean renderQueue = false;

    protected InternalFrame(Dimension size, RenderLayer[] layers, boolean doubleBuffer, boolean renderQueue) {
        // panel size is the same as the parent, however the internal panel is processed
        // with other coordinates
        this.size = new Rectangle(size);
        super.setBorder(null);
        this.inside = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
        this.g2 = this.inside.createGraphics();
        super.setBackground(Color.BLACK);
        super.setForeground(Color.DARK_GRAY);
        super.setIgnoreRepaint(true);
        super.setDoubleBuffered(doubleBuffer);
        this.renderQueue = renderQueue;
        super.setFocusable(false);
        this.layers = layers;
        looper = new InternalFrameLoop();
        looper.start();
    }

    protected void end() {
        looper.stop();
    }

    public int getFPS() {
        return looper.fps;
    }

    public synchronized void setFPS(int fps) {
        looper.fps = fps;
    }

    @Override
    public synchronized void setDoubleBuffered(boolean doubleBuffer) {
        super.setDoubleBuffered(doubleBuffer);
    }

    public boolean shouldMaintainAspectRatio() {
        return maintainAspectRatio;
    }

    public synchronized void setMaintainAspectRatio(boolean enabled) {
        maintainAspectRatio = enabled;
    }

    public boolean hasRenderQueue() {
        return renderQueue;
    }

    public synchronized void setRenderQueue(boolean enabled) {
        this.renderQueue = enabled;
    }

    protected void calculateBounds(Dimension parentSize) {
        float width = parentSize.width;
        float height = parentSize.height;
        float x = 0;
        float y = 0;
        
        
        if (parentSize.width > parentSize.height) {
            // The screen is displaying horizontally

            width = parentSize.height / (float) size.height * size.width;
            x = parentSize.width / 2f - width / 2f;

        } else {
            // The screen is displaying vertically or cubiq
            height = parentSize.width / (float) size.width * size.height;
            y = parentSize.height / 2f - height / 2f;

        }
        size.setLocation(Math.round(x), Math.round(y));
        size.setSize(Math.round(width), Math.round(height));
        repaint();
    }

    protected void paint() {
        super.paintImmediately(0, 0, getWidth() - 1, getHeight() - 1);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());

        g2.setColor(getForeground());
        g2.fillRect(0, 0, this.inside.getWidth(), this.inside.getHeight());

        for (RenderLayer layer : layers) {
            layer.draw(g2, this.inside.getWidth() - 1, this.inside.getHeight() - 1);
        }
        boolean done = true;
        if (maintainAspectRatio)
            // Maintains aspect ratio
            done = g.drawImage(inside, size.x, size.y, size.width - 1, size.height - 1, null);
//			g.drawRect(size.x, size.y, size.width - 1, size.height - 1);
        else
            // Does not maintain aspect ratio
            done = g.drawImage(inside, 0, 0, getWidth() - 1, getHeight() - 1, null);
//			g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

        if (!done)
            System.err.println("Trouble");
    }

    class InternalFrameLoop implements Runnable {

        private final Thread thread;
        private boolean running;
        private boolean paused;
        private int fps = 60;

        private InternalFrameLoop() {
            thread = new Thread(this);
            thread.setDaemon(true);
            running = false;
            paused = false;
        }

        protected synchronized void setPause(boolean paused) {
            this.paused = paused;
        }

        protected boolean isPaused() {
            return paused;
        }

        protected synchronized void start() {
            if (running)
                return;

            running = true;
            thread.start();
        }

        protected synchronized void stop() {
            if (!running)
                return;

            try {
                running = false;
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            long last = System.currentTimeMillis();
            long rest = last; // For frame counting debug
            int frames = 0; // For frame counting debug
            double delta = 0d;
            final double milis = 1000;
            while (running) {
                if (paused)
                    continue;

                long now = System.currentTimeMillis();
                delta += (now - last) / (milis / fps);
                last = now;

                if (rest + milis < last) { // For frame counting debug
                    System.out.println("[FPS: " + frames + " / " + fps + " ]"); // For frame counting debug
                    frames = 0; // For frame counting debug
                    rest = last; // For frame counting debug
                } // For frame counting debug

                if (delta >= 1) {
                    frames++; // For frame counting debug
                    if (renderQueue)
                        repaint();
                    else
                        paint();
                    delta--;

                }
                // No operations should be done after this point
            }
            stop();
        }

    }
}
