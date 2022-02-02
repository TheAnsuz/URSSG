package org.urssg.retrogine.game.level;

import org.urssg.retrogine.collision.Collider;
import org.urssg.retrogine.collision.CollisionList;
import org.urssg.retrogine.display.DrawingLayer;
import org.urssg.retrogine.entity.PhysicsObject;
import org.urssg.retrogine.entity.Player;
import org.urssg.retrogine.game.Camera;
import org.urssg.retrogine.game.Updatable;
import org.urssg.retrogine.game.UpdateThread;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Level {
    private final List<Collider> collisions = new ArrayList<>();
    private Polygon sceneCollision;

    private Camera cam;

    protected Player player;
    public UpdateThread thread1 = new UpdateThread(60);

    public Level(Camera cam) {
        this.cam = cam;
    }

    public void setLevelCollision(Polygon col) {
        sceneCollision = col;
        cam.getWireframeLayer().add(col);
    }

    public void addLevelObject(PhysicsObject obj) {
        collisions.add(obj.getCollider());
        collisions.add(obj.getObjCollisionDetector());
    }

    public void removeLevelObject(PhysicsObject obj) {
        collisions.remove(obj.getCollider());
        collisions.remove(obj.getObjCollisionDetector());
    }

    public void addToUpdateThread(UpdateThread thread, Updatable obj) {
        thread.add(obj);
    }

    public void removeFromUpdateThread(UpdateThread thread, Updatable obj) {
        thread.remove(obj);
    }

    public void addToRender(DrawingLayer layer, Shape shape) {
        layer.add(shape);
    }

    public void removeFromRender(DrawingLayer layer, Shape shape) {
        layer.remove(shape);
    }

    public void setPlayer(Player player) {
        this.player = player;
        player.setLevel(this);
        thread1.add(player);

        cam.getWireframeLayer().add(player);
        cam.getWireframeLayer().add(player.getGroundChecker());
    }

    public List<Collider> getCollisions() {
        return collisions;
    }

    public Polygon getSceneCollision() {
        return sceneCollision;
    }
}
