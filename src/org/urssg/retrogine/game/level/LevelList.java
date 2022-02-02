package org.urssg.retrogine.game.level;

import org.urssg.retrogine.collision.Collider;
import org.urssg.retrogine.game.Updatable;

import java.awt.*;
import java.util.List;

public enum LevelList {
    LEVEL1(Level1.sceneCollision, Level1.collisions, Level1.updatables),
    LEVEL2(Level1.sceneCollision, Level1.collisions, Level1.updatables);

    private final Polygon sceneCollision;
    private final java.util.List<Collider> collisions;
    private final java.util.List<Updatable> updatables;

    LevelList(Polygon sceneCollision, List<Collider> collisions, List<Updatable> updatables) {
        this.sceneCollision = sceneCollision;
        this.collisions = collisions;
        this.updatables = updatables;
    }

    public Polygon getSceneCollision() {
        return sceneCollision;
    }

    public java.util.List<Collider> getCollisions() {
        return collisions;
    }

    public List<Updatable> getUpdatables() {
        return updatables;
    }
}
