package me.amrv.engine.entity;

import me.amrv.engine.game.LayerDraw;
import me.amrv.engine.game.Update;
import me.amrv.engine.input.InputManager;
import me.amrv.engine.physics.Collider;

import java.awt.*;
import java.awt.geom.Area;

public class Player extends GameObject implements Update {

    private final Collider collider = new Collider(this);

    public Player(int x, int y, int speed, int width, int height, LayerDraw layer) {
        super(x, y, width, height, layer);
        super.setSpeed(speed);
        super.setColor(Color.GREEN);
    }

    private InputManager inputManager;


    @Override
    public void update() {
        if (inputManager != null) {
            int horizontalInput = !inputManager.isRightPressed() ? (!inputManager.isLeftPressed() ? 0 : -1) : 1;
            moveHorizontal(horizontalInput);

            int verticalInput = !inputManager.isDownPressed() ? (!inputManager.isUpPressed() ? 0 : -1) : 1;
            moveVertical(verticalInput);

//            if (inputManager.isLeftPressed()) addX(-1 * getSpeed());
//            if (inputManager.isRightPressed()) addX(getSpeed());
//            if (inputManager.isUpPressed()) addY(-1 * getSpeed());
//            if (inputManager.isDownPressed()) addY(getSpeed());

            //collider.setPos(getX() + horizontalInput, getY());
        }
    }

    private void moveHorizontal(int horizontalInput) {
        collider.translate(horizontalInput * getSpeed(), 0);

        if (collider.isColliding()) {
            for (int i = 0; collider.isColliding(); i += horizontalInput)
                collider.translate(-horizontalInput, 0);
        }
        setPos(collider.x, collider.y);
    }

    private void moveVertical(int verticalInput) {
        collider.translate(0, verticalInput * getSpeed());

        if (collider.isColliding()) {
            for (int i = 0; collider.isColliding(); i += verticalInput)
                collider.translate(0, -verticalInput);
        }
        setPos(collider.x, collider.y);
    }

    public InputManager getInputManager() {
        return inputManager;
    }

    public void setInputManager(InputManager inputManager) {
        this.inputManager = inputManager;
    }

    public Collider getCollider() {
        return collider;
    }

    @Override
    public String toString() {
        return "Player: x " + getX() + ", y " + getY();
    }
}