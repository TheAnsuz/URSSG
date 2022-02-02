package org.urssg.retrogine.entity;

import org.urssg.retrogine.game.level.Level;

import java.awt.Rectangle;

public abstract class GameObject extends Rectangle {

    protected Level level;

    protected GameObject() {
        this(0, 0, 0, 0);
    }

    protected GameObject(GameObject obj) {
        this(obj.x, obj.y, obj.width, obj.height);
    }

    protected GameObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Level getLevel() {
        return level;
    }
}
