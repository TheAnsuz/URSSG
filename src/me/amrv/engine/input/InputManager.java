package me.amrv.engine.input;

import me.amrv.engine.entity.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputManager implements KeyListener {

    private final Player player;

    private static final int MOVE_RIGHT = KeyEvent.VK_D;
    private static final int MOVE_LEFT = KeyEvent.VK_A;
    private static final int MOVE_JUMP = KeyEvent.VK_SPACE;

    public InputManager(Player player) {
        this.player = player;
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case MOVE_RIGHT:
                player.moveHorizontal(1);
                break;
            case MOVE_LEFT:
                player.moveHorizontal(-1);
                break;
            case MOVE_JUMP:
                System.out.println("Jump!");
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { }
}
