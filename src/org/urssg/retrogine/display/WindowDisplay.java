package org.urssg.retrogine.display;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import org.urssg.retrogine.input.InputManager;

public class WindowDisplay {

    private final JFrame screen;
    private final InternalFrame frame;

    private Dimension interiorSizeCalculated;
    private boolean fullScreen = false;
    private Rectangle lastLocation;
    private InputManager input;

    public WindowDisplay(WindowConfiguration config) {
        screen = new JFrame();
        screen.pack();

        frame = new InternalFrame(config.getInternalSize(), config.getRenderLayers(), config.isDoubleBuffer(),
                config.isQueueRendering());

        final int minWidth = config.getInternalSize().width < screen.getBounds().width ? screen.getBounds().width
                : config.getInternalSize().width + screen.getInsets().left + screen.getInsets().right;
        final int minHeight = config.getInternalSize().height + screen.getInsets().top + screen.getInsets().bottom;

        screen.setMinimumSize(new Dimension(minWidth, minHeight));
        screen.setSize(config.getSize());
        screen.setTitle(config.getTitle());
        screen.setLocationRelativeTo(null);
        screen.add(frame);
        screen.setLocationRelativeTo(null);
        screen.setAutoRequestFocus(false);
        screen.setResizable(config.isManualResizing());
        input = new InputManager();

        lastLocation = screen.getBounds();
        frame.calculateBounds(getInsideSize());

        screen.addComponentListener(new WindowListener());
        screen.addKeyListener(new WindowKeyInput());

        screen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        screen.setVisible(true);

    }

    public void setFullScreen(boolean fullscreen) {
        if (this.fullScreen == fullscreen)
            return;

        screen.setVisible(false);
        screen.dispose();
        screen.setUndecorated(fullscreen);

        if (fullscreen) {
            lastLocation = screen.getBounds();
            screen.setBounds(screen.getGraphicsConfiguration().getBounds());

        } else {
            screen.setBounds(lastLocation);

        }

        screen.setVisible(true);
        this.fullScreen = fullscreen;
    }

    public boolean isFullScreen() {
        return fullScreen;
    }

    public InternalFrame getInternal() {
        return frame;
    }

    public JFrame getExternal() {
        return screen;
    }

    public Dimension getInsideSize() {
        if (interiorSizeCalculated == null)
            interiorSizeCalculated = new Dimension(
                    screen.getWidth() - screen.getInsets().left - screen.getInsets().right,
                    screen.getHeight() - screen.getInsets().top - screen.getInsets().bottom);
        return interiorSizeCalculated;
    }

    public void close() {
        screen.setVisible(false);
        screen.dispose();
        frame.end();

    }

    public void setInputManager(InputManager manager) {
        input = manager;
    }

    public InputManager getInputManager() {
        return input;
    }

    class WindowListener extends ComponentAdapter {

        @Override
        public void componentResized(ComponentEvent e) {
            interiorSizeCalculated = null;
            frame.calculateBounds(getInsideSize());
        }
    }

    class WindowKeyInput extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            if (input != null)
                input.setPressed(e.getKeyCode(), true);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (input != null)
                input.setPressed(e.getKeyCode(), false);
        }

    }
    
    class WindowMouseInput extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e); //To change body of generated methods, choose Tools | Templates.
        }
        
        
    }
}
