package org.urssg.retrogine.game.level;

import org.urssg.retrogine.collision.Collider;
import org.urssg.retrogine.game.Updatable;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public final class Level1 {

    public static final List<Collider> collisions = new ArrayList<>();
    public static final Polygon sceneCollision = new Polygon(
            new int[]{
                    -11, -1,  -1,  50,  95, 120, 120, 150, 150, 210, 256, 256, 267, 267, -11
            },
            new int[]{
                      0,  0, 125, 140, 140, 125,  80,  80, 140, 140, 106,   0,   0, 160,  160
            },
            15);
    public static final List<Updatable> updatables = new ArrayList<>();
}
