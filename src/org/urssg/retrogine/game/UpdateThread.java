package org.urssg.retrogine.game;

import java.util.ArrayList;
import java.util.List;

public class UpdateThread implements Runnable {

    private final int ticksPerSecond;

    public UpdateThread(int ticksPerSecond) {this.ticksPerSecond = ticksPerSecond;}

    private final Thread thread = new Thread(this);
    private boolean loop = true;

    private final List<Update> pool = new ArrayList<>();

    @Override
    public void run() {
        final int mspt = 1000 / ticksPerSecond;

        double previous = System.currentTimeMillis();
        double lag = 0;
        while (loop) {
            double current = System.currentTimeMillis();
            double elapsed = current - previous;
            previous = current;
            lag += elapsed;

            while (lag >= mspt) {
                pool.forEach(Update::update);
                lag -= mspt;
            }
        }
    }

    public void addToPool(Update obj) { if (!pool.contains(obj)) pool.add(obj); }

    public void removeFromPool(Update obj) { pool.remove(obj); }

    public void start() {
        thread.setPriority(5);
        thread.start();
    }

    public void stop() {
        loop = false;
    }
}
