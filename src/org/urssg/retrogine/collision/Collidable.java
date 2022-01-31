package org.urssg.retrogine.collision;

public interface Collidable {
    void onCollisionEnter(Collider col);

    void onCollisionLeave(Collider col);
}
