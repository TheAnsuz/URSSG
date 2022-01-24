package me.amrv.engine.game;

import java.util.ArrayList;
import java.util.List;

public class UpdateThread implements Runnable {

    private final Thread thread = new Thread(this);
    private boolean paused;

    private final List<Update> poolToUpdate = new ArrayList<Update>();

    @Override
    public void run() {
        while (true) if (!paused) {
            poolToUpdate.forEach(Update::update);
        }
    }

    public void addObjectToUpdatePool(Update obj) {
        poolToUpdate.add(obj);
    }

    public void removeObjectFromUpdatePool(Update obj) {
        poolToUpdate.remove(obj);
    }

    public void start() {
        thread.start();
    }

    public void stop() {
        paused = true;
    }

    public void resume() {
        paused = false;
    }
}