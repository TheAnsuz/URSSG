package me.amrv.engine.game;

import me.amrv.engine.entity.Player;
import me.amrv.engine.input.InputManager;
import me.amrv.engine.collision.Collider;
import me.amrv.engine.collision.CollisionList;
import me.amrv.engine.window.Window;

import java.awt.*;

public class GameState {
    public enum State {
        MAIN_MENU, GAME, MENU
    }

    private final Window window;
    private State state;
    private final LayerDraw wireframeLayer = new LayerDraw();

    private final Player player;

    //TODO:: THIS IS A TEST FOR MOVING GAMEOBJECTS AND THEIR COLLISIONS
    public final Collider moveColliderTest = new Collider(200, 50, 20, 20, null);

    public GameState(Window window, State state) {
        this.window = window;
        this.state = state;

        player = new Player(100, 100, 8, 80, 80);
        player.setState(this);
        UpdateThread thread1 = new UpdateThread(60);

        thread1.start();
        thread1.addToPool(player);

        CollisionList.addToSceneCollision(new Rectangle(300, 200, 100, 100));
        CollisionList.addToSceneCollision(new Rectangle(350, 240, 100, 300));
        CollisionList.addToSceneCollision(new Rectangle(50, 50, 50, 50));
        CollisionList.addToSceneCollision(new Rectangle(0, 450, 900, 50));
        CollisionList.addToSceneCollision(new Polygon(new int[]{900, 1100, 1400}, new int[]{450, 400, 400}, 3));

        CollisionList.collisions.add(new Collider(600, 350, 40, 40, null, Collider.Layer.ENEMY, true));
        CollisionList.collisions.add(new Collider(650, 350, 40, 40, null));

        //TODO:: THIS IS A TEST FOR MOVING GAMEOBJECTS AND THEIR COLLISIONS
        CollisionList.collisions.add(moveColliderTest);

        startDrawer();
        addInputManager();
    }

    private void startDrawer() {
        window.addDrawer(wireframeLayer, 10);

        wireframeLayer.add(player);
        wireframeLayer.add(player.getCollider());
        wireframeLayer.add(player.getGroundChecker());
        wireframeLayer.add(player.getObjCollisionDetector());
        wireframeLayer.add(CollisionList.sceneCollision);

        //fillLayer.add(CollisionList.collisions.get(1));
        CollisionList.collisions.forEach(wireframeLayer::add);
    }

    private void addInputManager() {
        InputManager inputManager = new InputManager();
        window.addInputManager(inputManager);
        player.setInputManager(inputManager);
    }
}
