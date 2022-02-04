package org.urssg.retrogine.game;

import org.urssg.retrogine.display.DrawingLayer;
import org.urssg.retrogine.entity.GameObject;

import java.awt.*;
import java.awt.geom.Dimension2D;

public class Camera implements Updatable {
    private int camSpeed = 2;
    private int x;
    private int y;

    private int initialXOffset;
    private int initialYOffset;

    private int xOffset = 0;
    private int yOffset = 0;

    private boolean moveX = true;
    private boolean moveY = false;

    private GameObject focus;

    private final DrawingLayer wireframeLayer = new DrawingLayer();

    public Camera(int initialXOffset, int initialYOffset) {
        this.initialXOffset = initialXOffset;
        this.initialYOffset = initialYOffset;
    }

    public DrawingLayer getWireframeLayer() {
        return wireframeLayer;
    }

    @Override
    public void update() {
        int xTarget = -focus.x + focus.width / 2 + initialXOffset - xOffset;

        if (moveX && (x > xTarget + 20 || x < xTarget - 20)) {
            x += Math.abs(x - xTarget) > 4 ? 4 * Math.signum(xTarget - x) : (xTarget - x);
        }


        wireframeLayer.setOffset(x, y);
    }

    public void setFocus(GameObject focus) {
        this.focus = focus;
        x = focus.x;
        initialYOffset = focus.y;
    }

    public boolean isMoveX() {
        return moveX;
    }

    public void setMoveX(boolean moveX) {
        this.moveX = moveX;
    }

    public boolean isMoveY() {
        return moveY;
    }

    public void setMoveY(boolean moveY) {
        this.moveY = moveY;
    }

    public int getxOffset() {
        return xOffset;
    }

    public void setxOffset(int xOffset) {
        this.xOffset = xOffset;
    }

    public int getyOffset() {
        return yOffset;
    }

    public void setyOffset(int yOffset) {
        this.yOffset = yOffset;
    }

    public void setInitialOffset(int x, int y) {
        this.initialXOffset = x;
        this.initialYOffset = y;
    }
}
