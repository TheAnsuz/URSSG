package org.urssg.retrogine.entity;

import org.urssg.retrogine.game.level.Level;

import java.awt.Rectangle;

public abstract class GameObject extends Rectangle {

    protected final Level level;

    protected GameObject(Level level) {
        this(0, 0, 0, 0, level);
    }

    protected GameObject(GameObject obj) {
        this(obj.x, obj.y, obj.width, obj.height, obj.level);
    }

    protected GameObject(int x, int y, int width, int height, Level level) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.level = level;
    }

    public Level getLevel() {
        return level;
    }
}
