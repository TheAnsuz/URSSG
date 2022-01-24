package me.amrv.engine.game;

import me.amrv.engine.entity.Player;
import me.amrv.engine.input.InputManager;
import me.amrv.engine.window.Window;

public class GameState {
    public enum State {
        MAIN_MENU, GAME, MENU
    }

    Window window;
    State state;
    LayerDraw playerLayer = new LayerDraw();

    public Player player;

    public GameState(Window window, State state) {
        this.window = window;
        this.state = state;

        player = new Player(100, 100, 80, 180, playerLayer);
        UpdateThread thread1 = new UpdateThread();
        thread1.start();

        thread1.addObjectToUpdatePool(player);

        startDrawer();
        addInputManager();
    }

    private void startDrawer() {
        window.addDrawer(playerLayer, 0);
        playerLayer.addObject(player);
    }

    private void addInputManager() {
        window.addInputManager(new InputManager(player));
    }
}