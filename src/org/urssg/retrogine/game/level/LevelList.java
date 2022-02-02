package org.urssg.retrogine.game.level;

import org.urssg.retrogine.entity.GameObject;
import org.urssg.retrogine.game.Updatable;

import java.awt.*;
import java.util.List;

public enum LevelList {
    LEVEL1(Level1.sceneCollision, Level1.objects, Level1.updatables),
    LEVEL2(Level1.sceneCollision, Level1.objects, Level1.updatables);

    private final Polygon sceneCollision;
    private final java.util.List<GameObject> objects;
    private final java.util.List<Updatable> updatables;

    LevelList(Polygon sceneCollision, List<GameObject> objects, List<Updatable> updatables) {
        this.sceneCollision = sceneCollision;
        this.objects = objects;
        this.updatables = updatables;
    }

    public Polygon getSceneCollision() {
        return sceneCollision;
    }

    public java.util.List<GameObject> getCollisions() {
        return objects;
    }

    public List<Updatable> getUpdatables() {
        return updatables;
    }
}
