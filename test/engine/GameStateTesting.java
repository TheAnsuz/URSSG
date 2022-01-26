package engine;

import me.amrv.engine.game.GameState;
import me.amrv.engine.window.Window;

public class GameStateTesting {
    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl","true");
        Window window = new Window("Test");

        window.getDrawer().setAntialiasing(true);
//      window.getDrawer().setVsync(true);
        window.getDrawer().setHighQualityRending(true);
        GameState mainState = new GameState(window, GameState.State.GAME);
    }
}