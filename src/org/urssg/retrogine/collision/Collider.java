package org.urssg.retrogine.collision;

import org.urssg.retrogine.entity.GameObject;
import org.urssg.retrogine.game.level.Level;

import java.awt.Rectangle;
import java.awt.geom.Area;
import java.util.LinkedList;


public class Collider extends GameObject {

    public enum Layer {
        DEFAULT, PLAYER, PLAYER_BULLET, ENEMY, ENEMY_BULLET
    }

    public Collider(GameObject obj) {
        this(obj, null);
        this.setLevel(obj.getLevel());
    }

    public Collider(GameObject obj, Collidable col) {
        this(obj.x, obj.y, obj.width, obj.height, col, obj.getLevel());
        setLevel(obj.getLevel());
    }

    public Collider(int x, int y, int width, int height, Collidable parentObject, Level level) {
        this(x, y, width, height, parentObject, Layer.DEFAULT, false, level);
    }

    public Collider(int x, int y, int width, int height, Collidable parentObject, Layer layer, boolean trigger, Level level) {
        super(x, y, width, height);
        this.layer = layer;
        this.trigger = trigger;
        this.parentObject = parentObject;
        setLevel(level);
    }


    protected Layer layer = Layer.DEFAULT;
    private Collidable parentObject;

    protected boolean trigger;
    protected boolean active = true;


    public boolean isColliding() {
        if (!active) return false;
        return checkSceneCollision() || !getObjectCollision().isEmpty();
    }

    private boolean checkSceneCollision() {
        return level.getSceneCollision().intersects(this);
    }

    public Rectangle getSceneCollisionArea() {
        Area sceneCollision = new Area();
        sceneCollision.add(new Area(level.getSceneCollision()));
        sceneCollision.intersect(new Area(this));

        return sceneCollision.getBounds();
    }

    /**
     * Detects any collision and gets the rectangle that corresponds to the intersection of this collider
     * and the area of object colliders. This can be used both for detecting collision and for getting the ammount of
     * pixels a collider is inside another.
     *
     * @return the area of intersection between this and every other GameObject
     */
    public Rectangle getObjectCollision() {
        Area areaOfCOllisions = new Area();

        for (Collider col : level.getCollisions())
            if (this.intersects(col) && col.active && !col.trigger)
                areaOfCOllisions.add(new Area(col));

        areaOfCOllisions.intersect(new Area(this));
        return areaOfCOllisions.getBounds();
    }


    private final LinkedList<Collider> currentCollisions = new LinkedList<>();

    /**
     * Detects any object that is touching the collider and sends an onCollision to the parent object
     * with all the collider information.
     */
    public void checkForOnCollision() {
        for (Collider col : level.getCollisions())
            if (intersects(col) && col.active && !col.equals(this)) {
                if (!currentCollisions.contains(col)) {
                    currentCollisions.add(col);
                    onCollisionEnter(col);
                }
            } else if (currentCollisions.contains(col)){
                onCollisionLeave(col);
                currentCollisions.remove(col);
            }
    }


    private void onCollisionEnter(Collider col) {
        if (parentObject == null) return;

        parentObject.onCollisionEnter(col);
    }

    private void onCollisionLeave(Collider col) {
        if (parentObject == null) return;

        parentObject.onCollisionLeave(col);
    }

    public Layer getLayer() {
        return layer;
    }

    public void setLayer(Layer layer) {
        this.layer = layer;
    }

    public boolean isTrigger() {
        return trigger;
    }

    public void setTrigger(boolean trigger) {
        this.trigger = trigger;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Collidable getParentObject() {
        return parentObject;
    }

    public void setParentObject(Collidable parentObject) {
        this.parentObject = parentObject;
    }
}
