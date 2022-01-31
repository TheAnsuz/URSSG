package engine;

import org.urssg.retrogine.game.GameState;
import org.urssg.retrogine.display.WindowConfiguration;

public class GameStateTesting {
    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "true");
        WindowConfiguration window = WindowConfiguration.defaultConfig();

        GameState mainState = new GameState(window, GameState.State.GAME);
    }
}