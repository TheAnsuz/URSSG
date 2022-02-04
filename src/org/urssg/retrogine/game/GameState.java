package org.urssg.retrogine.game;

import org.urssg.retrogine.collision.Collider;
import org.urssg.retrogine.display.DrawingLayer;
import org.urssg.retrogine.entity.PhysicsObject;
import org.urssg.retrogine.entity.Player;
import org.urssg.retrogine.game.level.Level;
import org.urssg.retrogine.display.Display;
import org.urssg.retrogine.display.WindowConfiguration;
import org.urssg.retrogine.game.level.Level1;
import org.urssg.retrogine.game.level.LevelList;
import org.urssg.retrogine.input.InputManager;

public class GameState {

    private WindowConfiguration window;
    private Display display;
    private final DrawingLayer wireframeLayer = new DrawingLayer();

    private Level currentLevel;
    private Player player;
    private Camera cam;

    public GameState() {
        createWindow();

        currentLevel = new Level(cam);
        wireframeLayer.add(Level1.sceneCollision);

        startLevel(LevelList.LEVEL1);
        addInputManager();
    }

    private void startLevel(LevelList level) {
        player = new Player(70, 100, 3, 16, 32, currentLevel);
        currentLevel.startPlayer(player);


        currentLevel.setLevelCollision(level.getSceneCollision());
        level.getUpdatables().forEach(updatable -> currentLevel.addToUpdateThread(currentLevel.thread1, updatable));
        level.getCollisions().forEach(collider -> currentLevel.addLevelObject(collider));
        PhysicsObject ph = new PhysicsObject(30, 50, 10, 10, 0, currentLevel);
        ph.setCollider(new Collider(ph));
        currentLevel.addLevelObject(ph);
    }

    private void createWindow() {
        window = WindowConfiguration.defaultConfig();
        cam = new Camera(window.getInternalSize().width / 2, window.getInternalSize().height / 2);
        window.addRenderLayer(cam.getWireframeLayer());
        window.setSize(910, 600);
        window.setTitle("Game");
        window.setInternalSize(256, 160);
        window.setRenderQuality(true);

        display = new Display(window);

        display.setTitle("Ventana");
        display.setQueueRendering(true);
    }


    private void addInputManager() {
        InputManager inputManager = new InputManager();
        display.setInputManager(inputManager);
        player.setInputManager(inputManager);
    }
}
