package org.urssg.retrogine.display;

import java.awt.*;
import java.awt.geom.Area;
import java.util.ArrayList;

public class DrawingLayer implements RenderLayer {

    ArrayList<Shape> renderPool = new ArrayList<>();
    private boolean active;

    @Override
    public void draw(Graphics2D g, int width, int height) {
        if (active) {
            g.setColor(Color.RED);
            for (Shape object : renderPool) {
                g.draw(object);
            }
        }
    }

    public void add(Shape obj) {
         renderPool.add(obj);
    }

    public void remove(Shape obj) {
        renderPool.remove(obj);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
