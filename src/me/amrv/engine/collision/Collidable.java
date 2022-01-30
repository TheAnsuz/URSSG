package me.amrv.engine.collision;

public interface Collidable {
    void onCollisionEnter(Collider col);

    void onCollisionLeave(Collider col);
}
