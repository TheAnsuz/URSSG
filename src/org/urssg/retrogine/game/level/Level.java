package org.urssg.retrogine.game.level;

import org.urssg.retrogine.collision.Collider;
import org.urssg.retrogine.display.DrawingLayer;
import org.urssg.retrogine.entity.GameObject;
import org.urssg.retrogine.entity.PhysicsObject;
import org.urssg.retrogine.entity.Player;
import org.urssg.retrogine.game.Camera;
import org.urssg.retrogine.game.Updatable;
import org.urssg.retrogine.game.UpdateThread;

import java.awt.*;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.List;

public class Level {
    private final List<Collider> collisions = new ArrayList<>();
    private Area sceneCollision;

    private final Camera cam;

    protected Player player;
    public UpdateThread thread1 = new UpdateThread(60);

    public Level(Camera cam) {
        this.cam = cam;
    }

    public void setLevelCollision(Polygon col) {
        sceneCollision = new Area(col);
        cam.getWireframeLayer().add(col);
    }

    public void addLevelObject(GameObject obj) {
        if (obj instanceof PhysicsObject) {
            PhysicsObject objPO = (PhysicsObject) obj;
            addToWireframe(obj);
            if (objPO.getCollider() != null) {
                collisions.add(objPO.getCollider());
                addToWireframe(objPO.getCollider());
            }
            if (objPO.getObjCollisionDetector() != null) {
                collisions.add(objPO.getObjCollisionDetector());
                addToWireframe(objPO.getObjCollisionDetector());
            }
            //addToWireframe(objPO.getGroundChecker());
        }
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

    public void addToWireframe(Shape shape) {
        cam.getWireframeLayer().add(shape);
    }

    public void removeFromRender(DrawingLayer layer, Shape shape) {
        layer.remove(shape);
    }

    public void startPlayer(Player player) {
        this.player = player;
        thread1.add(player);

        addToWireframe(player);
        addToWireframe(player.getGroundChecker());
    }

    public List<Collider> getCollisions() {
        return collisions;
    }

    public Area getSceneCollision() {
        return sceneCollision;
    }
}
