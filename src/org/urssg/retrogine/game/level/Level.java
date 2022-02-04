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
        addToUpdateThread(thread1, cam);
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
            if (objPO.getGroundChecker() != null) addToWireframe(objPO.getGroundChecker());
        }
        if (obj instanceof Updatable) {
            Updatable objUp = (Updatable) obj;
            addToUpdateThread(thread1, objUp);
        }
    }

    public void removeLevelObject(GameObject obj) {
        if (obj instanceof PhysicsObject) {
            PhysicsObject objPO = (PhysicsObject) obj;
            addToWireframe(obj);
            if (objPO.getCollider() != null) {
                collisions.remove(objPO.getCollider());
                removeFromWireframe(objPO.getCollider());
            }
            if (objPO.getObjCollisionDetector() != null) {
                collisions.remove(objPO.getObjCollisionDetector());
                removeFromWireframe(objPO.getObjCollisionDetector());
            }
            if (objPO.getGroundChecker() != null) removeFromWireframe(objPO.getGroundChecker());
        }
        if (obj instanceof Updatable) {
            Updatable objUp = (Updatable) obj;
            removeFromUpdateThread(thread1, objUp);
        }
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

    public void removeFromWireframe(Shape shape) {
        cam.getWireframeLayer().remove(shape);
    }

    public void removeFromRender(DrawingLayer layer, Shape shape) {
        layer.remove(shape);
    }

    public void startPlayer(Player player) {
        this.player = player;
        thread1.add(player);
        cam.setFocus(player);

        addToWireframe(player);
        addToWireframe(player.getGroundChecker());
    }

    public List<Collider> getCollisions() {
        return collisions;
    }

    public Area getSceneCollision() {
        return sceneCollision;
    }

    public Camera getCam() {
        return cam;
    }
}
