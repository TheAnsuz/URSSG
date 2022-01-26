package me.amrv.engine.game;

import me.amrv.engine.entity.Player;
import me.amrv.engine.input.InputManager;
import me.amrv.engine.window.Window;

import java.awt.event.KeyEvent;

public class GameState {
    public enum State {
        MAIN_MENU, GAME, MENU
    }

    private final Window window;
    private State state;
    private final LayerDraw playerLayer = new LayerDraw();

    private final Player player;

    public GameState(Window window, State state) {
        this.window = window;
        this.state = state;

        player = new Player(100, 100, 8,80, 180, playerLayer);
        UpdateThread thread1 = new UpdateThread(60);

        thread1.start();
        thread1.addToPool(player);

        startDrawer();
        addInputManager();
    }

    private void startDrawer() {
        window.addDrawer(playerLayer, 0);
        playerLayer.add(player);
    }

    private void addInputManager() {
        InputManager inputManager = new InputManager();
        inputManager.addLeftKeybind(KeyEvent.VK_A);
        inputManager.addRightKeybind(KeyEvent.VK_D);
        inputManager.addUpKeybind(KeyEvent.VK_W);
        inputManager.addDownKeybind(KeyEvent.VK_S);
        window.addInputManager(inputManager);
        player.setInputManager(inputManager);
    }
}
