package me.amrv.engine.entity;

import me.amrv.engine.collision.Collider;
import me.amrv.engine.collision.CollisionList;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public abstract class PhysicsObject extends GameObject {
    // These are the default values that feel right for the player
    protected int speed = 8;
    protected final float gravity = 2f;
    protected int maxVerticalSpeed = 30;
    protected float jumpForce = -17f;
    protected float longJumpReduction = -1.6f;


    private final Line2D groundChecker = new Line2D.Double();
    private Collider collider;
    private Collider objCollisionDetector;

    protected PhysicsObject(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    protected PhysicsObject(int x, int y, int width, int height, int speed) {
        super(x, y, width, height);
        this.speed = speed;
    }

    protected void setCollider(Collider collider) {
        this.collider = collider;
    }

    public Collider getObjCollisionDetector() {
        return objCollisionDetector;
    }

    public void setObjCollisionDetector(Collider objCollisionDetector) {
        this.objCollisionDetector = objCollisionDetector;
    }

    protected float fallingSpeed;

    protected void applyGravity() {
//        if (isGrounded() && fallingSpeed > 0) {
//            fallingSpeed = 0;
//            return;
//        }

        moveVertical((int) fallingSpeed);
        if (fallingSpeed < maxVerticalSpeed)
            fallingSpeed += gravity;
    }

    protected void jump() {
        if (isGrounded()) fallingSpeed = jumpForce;
    }

    protected void longJump() {
        if (fallingSpeed < -11f) fallingSpeed += longJumpReduction;
    }


    // Moving horizontally and vertically have to be separated into different methods because
    // the intersections would give wrong values for width in vertical and height in horizontal
    protected void moveHorizontal(int horizontalInput) {
        collider.translate(horizontalInput * speed, 0);

        if (isColliding()) {
            int inputSign = (int) Math.signum(horizontalInput);
            collider.translate(-inputSign * collider.getSceneCollisionArea().width, 0);
            collider.translate(-inputSign * collider.getObjectCollision().width, 0);
        }

        setLocation(collider.x, collider.y);
        objCollisionDetector.setLocation(x - 1, y - 1);
        setGroundChecker();
    }

    protected void moveVertical(int verticalInput) {
        collider.translate(0, verticalInput);

        if (isColliding()) {
            int inputSign = (int) Math.signum(verticalInput);
            collider.translate(0, -inputSign * collider.getSceneCollisionArea().height);
            collider.translate(0, -inputSign * collider.getObjectCollision().height);
            // This line makes it so that when jumping and bumping against a ceiling, you
            // don't float until the jump time ends
            fallingSpeed = 0;
        }

        setLocation(collider.x, collider.y);
        objCollisionDetector.setLocation(x - 1, y - 1);
        setGroundChecker();
    }

    public Line2D getGroundChecker() {
        return groundChecker;
    }

    protected void setGroundChecker() {
        this.groundChecker.setLine(new Point2D.Float(x + 1, y + height), new Point2D.Float(x + width - 1, y + height));
    }

    protected boolean isGrounded() {
        return CollisionList.sceneCollision.contains(groundChecker.getP1()) || CollisionList.sceneCollision.contains(groundChecker.getP2());
    }

    protected synchronized boolean isColliding() {
        return collider.isColliding();
    }

    public Collider getCollider() {
        return collider;
    }
}
