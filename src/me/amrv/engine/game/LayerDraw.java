package me.amrv.engine.game;

import me.amrv.engine.Drawer;
import me.amrv.engine.entity.GameObject;
import me.amrv.engine.entity.Player;
import me.amrv.engine.window.WindowRender;

import java.awt.*;
import java.util.ArrayList;

public class LayerDraw implements Drawer {

    ArrayList<GameObject> objectsToRender = new ArrayList<>();

    @Override
    public void render(WindowRender render, int width, int height) {
        render.setColor(Color.black);
        render.fillRectangle(0, 0, width, height);

        render.setColor(Color.white);
        for (GameObject object : objectsToRender) {
            render.fillShape(new Rectangle(object.getX(), object.getY(), object.getWidth(), object.getHeight()));
        }

    }

    public void addObject(GameObject gameObject) {
        objectsToRender.add(gameObject);
    }
}
