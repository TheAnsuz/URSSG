package me.amrv.engine.game;

import java.util.ArrayList;
import java.util.List;

public class UpdateThread implements Runnable {

    private final int TICKS_PER_SECOND;
    public UpdateThread (int ticksPerSecond) { this.TICKS_PER_SECOND = ticksPerSecond; }

    private final Thread thread = new Thread(this);
    private boolean loop = true;

    private final List<Update> poolToUpdate = new ArrayList<>();

    @Override
    public void run() {
        final int mspt = 1000 / TICKS_PER_SECOND;

        double previous = System.currentTimeMillis();
        double lag = 0;
        while (loop) {
            double current = System.currentTimeMillis();
            double elapsed = current - previous;
            previous = current;
            lag += elapsed;

            while (lag >= mspt) {
                poolToUpdate.forEach(Update::update);
                lag -= mspt;
            }
        }
    }

    public void addToUpdatePool(Update obj) {
        poolToUpdate.add(obj);
    }

    public void removeFromUpdatePool(Update obj) {
        poolToUpdate.remove(obj);
    }

    public void start() {
        thread.setPriority(5);
        thread.start();
    }

    public void kill() {
        loop = false;
    }
}