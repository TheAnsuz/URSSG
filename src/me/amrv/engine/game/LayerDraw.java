package me.amrv.engine.game;

import me.amrv.engine.Drawer;
import me.amrv.engine.window.WindowRender;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class LayerDraw implements Drawer {

    ArrayList<Shape> renderPool = new ArrayList<>();

    @Override
    public void render(WindowRender render, int width, int height) {
        render.setColor(Color.black);
        render.fillRectangle(0, 0, width, height);

        render.setColor(Color.RED);
        for (Shape object : renderPool) {
            render.drawShape(object);
        }
    }

    public void add(Shape obj) {
         renderPool.add(obj);
    }

    public void remove(Shape obj) {
        renderPool.remove(obj);
    }
}
