/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package me.amrv.urssg.window;

import java.awt.Canvas;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author marruiad
 */
public final class WindowDraw {

    private final Canvas canvas;
    private final List<Drawer> drawers = new ArrayList<>();

    protected WindowDraw(Canvas canvas) {
        this.canvas = canvas;
    }

    protected void add(Drawer drawer) {
        drawers.add(drawer);
    }

    protected void remove(Drawer drawer) {
        if (!drawers.isEmpty() && drawers.contains(drawer))
            drawers.remove(drawer);
    }

    protected final void update(Graphics graphics) {
        if (graphics == null)
            return;
        graphics.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        System.out.println("Drawing...");
        for (Drawer d : drawers)
            d.draw(new WindowRender(graphics), canvas.getWidth(), canvas.getHeight());
    }
}
