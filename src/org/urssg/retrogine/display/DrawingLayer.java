package org.urssg.retrogine.display;

import java.awt.*;
import java.util.ArrayList;

public class DrawingLayer implements RenderLayer {

    private final ArrayList<Shape> renderPool = new ArrayList<>();
    // Objects to add/remove in the next iteration. Fixes concurrentModification exception
    private final ArrayList<Shape> toAdd = new ArrayList<>();
    private final ArrayList<Shape> toRemove = new ArrayList<>();

    private boolean active = true;

    private int xOffset;
    private int yOffset;

    @Override
    public void draw(Graphics2D g, int width, int height) {
        if (!toAdd.isEmpty() || !toRemove.isEmpty())
            addRemove();

        if (active && !renderPool.isEmpty()) {
            g.setColor(Color.RED);

            for (Shape shape : renderPool) {
                if (shape instanceof Polygon) {
                    Polygon obj = (Polygon) shape;
                    Polygon objTranslated = new Polygon(obj.xpoints, obj.ypoints, obj.npoints);

                    objTranslated.translate(xOffset, yOffset);
                    g.draw(objTranslated);
                }
                if (shape instanceof Rectangle) {
                    Rectangle obj = (Rectangle) shape;
                    Rectangle objTranslated = new Rectangle(obj.x, obj.y, obj.width, obj.height);

                    objTranslated.translate(xOffset, yOffset);
                    g.draw(objTranslated);
                }
            }
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

    public void setOffset(int x, int y) {
        xOffset = x;
        yOffset = y;
    }
}
