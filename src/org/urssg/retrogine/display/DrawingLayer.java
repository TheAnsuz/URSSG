package org.urssg.retrogine.display;

import java.awt.*;
import java.util.ArrayList;

public class DrawingLayer implements RenderLayer {

    private final ArrayList<Shape> renderPool = new ArrayList<>();
    // Objects to add/remove in the next iteration. Fixes concurrentModification exception
    private final ArrayList<Shape> toAdd = new ArrayList<>();
    private final ArrayList<Shape> toRemove = new ArrayList<>();

    private boolean active = true;

    @Override
    public void draw(Graphics2D g, int width, int height) {
        if (!toAdd.isEmpty() || !toRemove.isEmpty())
            addRemove();

        if (active && !renderPool.isEmpty()) {
            g.setColor(Color.RED);
            for (Shape object : renderPool)
                g.draw(object);
        }
    }

    private void addRemove() {
        renderPool.addAll(toAdd);
        toAdd.clear();
        renderPool.removeAll(toRemove);
        toRemove.clear();
    }

    public void add(Shape obj) {
        toAdd.add(obj);
    }

    public void remove(Shape obj) {
        toRemove.add(obj);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
