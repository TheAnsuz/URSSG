package org.urssg.retrogine.entity;

import org.urssg.retrogine.collision.Collidable;
import org.urssg.retrogine.collision.Collider;
import org.urssg.retrogine.game.Updatable;

public class Bullet extends PhysicsObject implements Updatable, Collidable {
    private final int xDir;
    private final int yDir;
    private Collider col = new Collider(this);
    private boolean dead;

    public Bullet(int x, int y, int width, int height, int speed, int xDir, int yDir, Collider.Layer layer) {
        super(x, y, width, height, speed);
        this.xDir = (int) Math.signum(xDir);
        this.yDir = (int) Math.signum(yDir);
        col.setLayer(layer);
        col.setTrigger(true);
        setObjCollisionDetector(col);
    }

    @Override
    public void update() {
        if(!dead) {
            moveHorizontal(xDir);
            moveVertical(yDir);
            col.checkForOnCollision();
        }
    }

    @Override
    public void onCollisionEnter(Collider col) {
        System.out.println("Hola");
//        col = null;
//        setObjCollisionDetector(null);
//        dead = true;
//        CollisionList.collisions.remove(this);

    }

    @Override
    public void onCollisionLeave(Collider col) {
        System.out.println("Chocado");
    }
}
