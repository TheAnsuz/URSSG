package me.amrv.engine.entity;

import me.amrv.engine.game.GameState;
import me.amrv.engine.game.Update;
import me.amrv.engine.input.InputManager;
import me.amrv.engine.collision.Collidable;
import me.amrv.engine.collision.Collider;

public class Player extends PhysicsObject implements Update, Collidable {
    private final Collider objCollider = new Collider(x - 1, y - 1, width + 2, height + 2, this);
    public GameState state;

    public Player(int x, int y, int speed, int width, int height) {
        super(x, y, width, height, speed);
        Collider sceneCollider = new Collider(this);
        sceneCollider.setLayer(Collider.Layer.PLAYER);
        setCollider(sceneCollider);
        setObjCollisionDetector(objCollider);
        objCollider.setActive(false);
    }

    public void setState(GameState state) {
        this.state = state;
    }

    private InputManager inputManager;

    private boolean canJump = true;

    @Override
    public void update() {
        //TODO:: THIS IS A TEST FOR MOVING GAMEOBJECTS AND THEIR COLLISIONS
        state.moveColliderTest.translate(1, 0);

        if (inputManager != null) {
            int horizontalInput = !inputManager.isRightPressed() ? (!inputManager.isLeftPressed() ? 0 : -1) : 1;
            if (horizontalInput != 0) moveHorizontal(horizontalInput);

            if (inputManager.isUpPressed()) {
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
    public void onCollide(Collider col) {

        System.out.println("Collision with: " + col);
    }


    public InputManager getInputManager() {
        return inputManager;
    }

    public void setInputManager(InputManager inputManager) {
        this.inputManager = inputManager;
    }
}