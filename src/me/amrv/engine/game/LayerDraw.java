package me.amrv.engine.game;

import me.amrv.engine.Drawer;
import me.amrv.engine.entity.GameObject;
import me.amrv.engine.window.WindowRender;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class LayerDraw implements Drawer {

    ArrayList<GameObject> renderPool = new ArrayList<>();

    @Override
    public void render(WindowRender render, int width, int height) {
        render.setColor(Color.black);
        render.fillRectangle(0, 0, width, height);


        for (GameObject object : renderPool) {
            render.setColor(object.getColor());
            render.fillShape(new Rectangle(object.getX(), object.getY(), object.getWidth(), object.getHeight()));
            render.setColor(Color.WHITE);

            Area a = new Area(new Rectangle(300, 200, 100, 100));
            a.add(new Area(new Rectangle(350, 240, 100, 300)));
            render.drawShape(a);
        }
    }

    public void add(GameObject go) { if (!renderPool.contains(go)) renderPool.add(go); }
    public void remove(GameObject go) {
        renderPool.remove(go);
    }
}
