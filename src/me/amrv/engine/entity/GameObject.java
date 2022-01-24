package me.amrv.engine.entity;

import me.amrv.engine.game.LayerDraw;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class GameObject {
    private final AtomicInteger x;
    private final AtomicInteger y;
    private int speed;
    private final AtomicInteger width;
    private final AtomicInteger height;
    private final LayerDraw layer;

    public GameObject(int x, int y, int width, int height, LayerDraw layer) {
        this.x = new AtomicInteger(x);
        this.y = new AtomicInteger(y);
        this.width = new AtomicInteger(width);
        this.height = new AtomicInteger(height);
        this.layer = layer;
    }

    public int getX() {
        return x.get();
    }

    public void setX(int x) {
        this.x.set(x);
    }

    public void addX(int x) {
        this.x.addAndGet(x);
    }

    public int getY() {
        return y.get();
    }

    public void setY(int y) {
        this.y.set(y);
    }

    public void addY(int y) {
        this.y.addAndGet(y);
    }

    public int getSpeed() { return speed; }

    public void setSpeed(int speed) { this.speed = speed; }

    public int getWidth() {
        return width.get();
    }

    public void setWidth(int width) {
        this.width.set(width);
    }

    public int getHeight() {
        return height.get();
    }

    public void setHeight(int height) {
        this.height.set(height);
    }
}
