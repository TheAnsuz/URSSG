package org.urssg.retrogine.game;

import org.urssg.retrogine.display.DrawingLayer;
import org.urssg.retrogine.entity.Player;
import org.urssg.retrogine.input.InputManager;
import org.urssg.retrogine.collision.CollisionList;
import org.urssg.retrogine.display.Display;
import org.urssg.retrogine.display.WindowConfiguration;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class GameState {
    public enum State {
        MAIN_MENU, GAME, MENU
    }

    private final WindowConfiguration window;
    private final Display display;
    private State state;
    private final DrawingLayer wireframeLayer = new DrawingLayer();

    private final Player player;

    public GameState(WindowConfiguration window, State state) {
        this.window = window;
        this.state = state;

        window.addRenderLayer(wireframeLayer);
        window.setSize(910, 600);
        window.setTitle("Game");
        window.setInternalSize(256, 160);
        window.setRenderQuality(true);

        display = new Display(window);

        display.setTitle("Ventana");
        display.setQueueRendering(true);


        player = new Player(100, 100, 3, 16, 32);
        player.setState(this);
        UpdateThread thread1 = new UpdateThread(60);

        thread1.start();
        thread1.addToPool(player);

        CollisionList.addToSceneCollision(new Rectangle(-11, 0, 10, 160));
        CollisionList.addToSceneCollision(new Rectangle(256, 0, 10, 160));

        CollisionList.addToSceneCollision(new Rectangle(-1, 140, 257, 20));
        CollisionList.addToSceneCollision(new Rectangle(120, 80, 30, 80));
        CollisionList.addToSceneCollision(new Polygon(new int[]{-1, 50, -1}, new int[]{125, 140, 140}, 3));
        CollisionList.addToSceneCollision(new Polygon(new int[]{95, 120, 120}, new int[]{140, 125, 140}, 3));
        CollisionList.addToSceneCollision(new Polygon(new int[]{210, 256, 256}, new int[]{140, 140, 106}, 3));
        CollisionList.addToSceneCollision(new Ellipse2D.Double(180, 50, 40, 20));
//        CollisionList.addToSceneCollision(new Rectangle(300, 200, 100, 100));
//        CollisionList.addToSceneCollision(new Rectangle(350, 240, 100, 300));
//        CollisionList.addToSceneCollision(new Rectangle(50, 50, 50, 50));
//        CollisionList.addToSceneCollision(new Rectangle(0, 450, 900, 50));
//        CollisionList.addToSceneCollision(new Polygon(new int[]{900, 1100, 1400, 1400, 900}, new int[]{450, 400, 400, 550, 550}, 5));
//
//        CollisionList.collisions.add(new Collider(600, 350, 40, 40, null, Collider.Layer.ENEMY, true));
//        CollisionList.collisions.add(new Collider(650, 350, 40, 30, null));

        startDrawer();
        addInputManager();
    }

    private void startDrawer() {
        wireframeLayer.add(player);
        wireframeLayer.add(player.getCollider());
        wireframeLayer.add(player.getGroundChecker());
        wireframeLayer.add(player.getObjCollisionDetector());
        wireframeLayer.add(CollisionList.sceneCollision);


        //fillLayer.add(CollisionList.collisions.get(1));
        CollisionList.collisions.forEach(wireframeLayer::add);
        wireframeLayer.setActive(true);
    }

    private void addInputManager() {
        InputManager inputManager = new InputManager();
        display.setInputManager(inputManager);
        player.setInputManager(inputManager);
    }
}
