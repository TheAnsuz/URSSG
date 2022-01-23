package me.amrv.engine.entity;

import me.amrv.engine.game.LayerDraw;

import java.util.concurrent.atomic.AtomicInteger;

public class Player extends GameObject{

    public Player(int x, int y, int width, int height, LayerDraw layer) {
        super(x, y, width, height, layer);
    }

    public void moveHorizontal(int direction) {
        addX(10 * direction / Math.abs(direction));
    }
}