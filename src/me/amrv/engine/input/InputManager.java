package me.amrv.engine.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class InputManager extends KeyAdapter {

    private boolean enabled = true;

    private final List<Integer> right = new ArrayList<>();
    private final List<Integer> left = new ArrayList<>();
    private final List<Integer> up = new ArrayList<>();
    private final List<Integer> down = new ArrayList<>();
    private final List<Integer> fire = new ArrayList<>();

    private boolean leftPressed, rightPressed, upPressed, downPressed, firePressed;

    @Override
    public void keyPressed(KeyEvent e) {
        if (!enabled) return;
        setPressed(e.getKeyCode(), true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (!enabled) return;
        setPressed(e.getKeyCode(), false);
    }

    private void setPressed(int input, boolean pressed) {
        if (right.contains(input)) rightPressed = pressed;
        if (left.contains(input)) leftPressed = pressed;
        if (up.contains(input)) upPressed = pressed;
        if (down.contains(input)) downPressed = pressed;
    }


    // Constructor, setters and adders/removers
    public InputManager() {
        this.right.add(KeyEvent.VK_RIGHT);
        this.left.add(KeyEvent.VK_LEFT);
        this.up.add(KeyEvent.VK_UP);
        this.down.add(KeyEvent.VK_DOWN);
        // Fire
    }

    public InputManager(int right, int left, int up, int down) {
        this.right.add(right);
        this.left.add(left);
        this.up.add(up);
        this.down.add(down);
        // Fire
    }

    public void addRightKeybind(int keybind) {
        if (!right.contains(keybind)) right.add(keybind);
    }

    public void addLeftKeybind(int keybind) {
        if (!left.contains(keybind)) left.add(keybind);
    }

    public void addUpKeybind(int keybind) {
        if (!up.contains(keybind)) up.add(keybind);
    }

    public void addDownKeybind(int keybind) {
        if (!down.contains(keybind)) down.add(keybind);
    }

    public void addFireKeybind(int keybind) {
        if (!fire.contains(keybind)) fire.add(keybind);
    }

    public void removeRightKeybind(int keybind) {
        if (right.contains(keybind)) right.remove(keybind);
    }

    public void removeLeftKeybind(int keybind) {
        if (left.contains(keybind)) left.remove(keybind);
    }

    public void removeUpKeybind(int keybind) {
        if (up.contains(keybind)) up.remove(keybind);
    }

    public void removeDownKeybind(int keybind) {
        if (down.contains(keybind)) down.remove(keybind);
    }

    public void removeFireKeybind(int keybind) {
        if (fire.contains(keybind)) fire.remove(keybind);
    }


    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public boolean isUpPressed() {
        return upPressed;
    }

    public boolean isDownPressed() {
        return downPressed;
    }

    public boolean isFirePressed() {
        return firePressed;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
