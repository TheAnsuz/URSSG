package me.amrv.engine.physics;

import me.amrv.engine.entity.GameObject;

import java.awt.*;
import java.awt.geom.Area;

public class Collider extends Rectangle {

    public Collider() {
        this(0, 0, 0, 0);
    }

    public Collider(GameObject obj) {
        this(obj.getX(), obj.getY(), obj.getWidth(), obj.getHeight());
    }

    public Collider(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

    public boolean isColliding() {
        Area a = new Area(new Rectangle(300, 200, 100, 100));
        a.add(new Area(new Rectangle(350, 240, 100, 300)));

        return a.intersects(this);
    }
}
