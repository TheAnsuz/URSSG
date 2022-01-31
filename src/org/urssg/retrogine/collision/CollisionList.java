package org.urssg.retrogine.collision;

import java.awt.*;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.List;

public class CollisionList {
    public static final List<Collider> collisions = new ArrayList<>();
    public static final Area sceneCollision = new Area();

    public static void addToSceneCollision(Shape s) {
        sceneCollision.add(new Area(s));
    }
}
