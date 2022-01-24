package me.amrv.engine.entity;

import me.amrv.engine.game.LayerDraw;
import me.amrv.engine.game.Update;
import me.amrv.engine.input.InputManager;

public class Player extends GameObject implements Update {

    public Player(int x, int y, int speed, int width, int height, LayerDraw layer) {
        super(x, y, width, height, layer);
        super.setSpeed(speed);
    }
    private InputManager inputManager;

    @Override
    public void update() {
        if (inputManager != null) {
            if (inputManager.leftPressed) addX(-1 * getSpeed());
            if (inputManager.rightPressed) addX(getSpeed());
            if (inputManager.upPressed) addY(-1 * getSpeed());
            if (inputManager.downPressed) addY(getSpeed());
        }
    }

    public InputManager getInputManager() { return inputManager; }

    public void setInputManager(InputManager inputManager) { this.inputManager = inputManager; }

    @Override
    public String toString() {
        return "Player: x " + getX() + ", y " + getY();
    }
}