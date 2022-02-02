package org.urssg.retrogine.game;

import java.util.ArrayList;
import java.util.List;

public class UpdateThread implements Runnable {

    private final int ticksPerSecond;

    public UpdateThread(int ticksPerSecond) {
        this.ticksPerSecond = ticksPerSecond;
        thread.start();
    }

    private final Thread thread = new Thread(this);
    private boolean loop = true;

    private final List<Updatable> pool = new ArrayList<>();
    // Objects to add/remove in the next iteration. Fixes concurrentModification exception
    private final ArrayList<Updatable> toAdd = new ArrayList<>();
    private final ArrayList<Updatable> toRemove = new ArrayList<>();

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
                pool.forEach(Updatable::update);
                lag -= mspt;

                if (!toAdd.isEmpty() || !toRemove.isEmpty())
                    addRemove();
            }
        }
    }

    private void addRemove() {
        pool.addAll(toAdd);
        toAdd.clear();
        pool.removeAll(toRemove);
        toRemove.clear();
    }

    public void add(Updatable obj) {
        toAdd.add(obj);
    }

    public void remove(Updatable obj) {
        toRemove.add(obj);
    }

    public void start() {
        if (!loop) {
            loop = true;
            thread.start();
        }
    }

    public void stop() {
        loop = false;
    }
}
