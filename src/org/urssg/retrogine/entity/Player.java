package org.urssg.retrogine.entity;

import org.urssg.retrogine.game.GameState;
import org.urssg.retrogine.game.Updatable;
import org.urssg.retrogine.collision.Collidable;
import org.urssg.retrogine.collision.Collider;
import org.urssg.retrogine.game.level.Level;
import org.urssg.retrogine.input.InputManager;
import org.urssg.retrogine.input.Key;

public class Player extends PhysicsObject implements Updatable, Collidable {
    private final Collider objCollider = new Collider(x - 1, y - 1, width + 2, height + 2, this, level);
    public GameState state;

    public Player(int x, int y, int speed, int width, int height, Level level) {
        super(x, y, width, height, speed, level);
        Collider sceneCollider = new Collider(this);
        sceneCollider.setLayer(Collider.Layer.PLAYER);
        setCollider(sceneCollider);
        setObjCollisionDetector(objCollider);
        objCollider.setTrigger(true);
    }

    public void setState(GameState state) {
        this.state = state;
    }

    private InputManager inputManager;

    private boolean canJump = true;

    @Override
    public void update() {
        if (inputManager != null) {
            int horizontalInput = !inputManager.isPressed(Key.D) ? (!inputManager.isPressed(Key.A) ? 0 : -1) : 1;
            if (horizontalInput != 0) moveHorizontal(horizontalInput);

            if (inputManager.isPressed(Key.SPACE)) {
                // Removes the ability to keep jump button pressed and jump constantly
                if (canJump)
                    jump();
                canJump = false;

                longJump();
            } else canJump = true;
        }

        objCollider.checkForOnCollision();
        applyGravity();
    }

    @Override
    public void onCollisionEnter(Collider col) {
        System.out.println("Collision enter with: " + col);
    }

    @Override
    public void onCollisionLeave(Collider col) {
        System.out.println("Collision leave with: " + col);
    }


    public InputManager getInputManager() {
        return inputManager;
    }

    public void setInputManager(InputManager inputManager) {
        this.inputManager = inputManager;
    }
}