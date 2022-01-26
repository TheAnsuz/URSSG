package me.amrv.engine.entity;

import me.amrv.engine.game.LayerDraw;
import me.amrv.engine.game.Update;

public class Player extends GameObject implements Update {

    public Player(int x, int y, int width, int height, LayerDraw layer) {
        super(x, y, width, height, layer);
    }

    public void moveHorizontal(int direction) {
        addX(10 * direction / Math.abs(direction));
    }

    @Override
    public void update() {
        System.out.println("I´m " + this);
    }

    @Override
    public String toString() {
        return "Player: x " + getX() + ", y " + getY();
    }
}
