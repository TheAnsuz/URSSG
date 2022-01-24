/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package me.amrv.urssg.window;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author marruiad
 */
public class WindowRender {
    
    private final Graphics g;
    
    protected WindowRender(Graphics graphics) {
        g = graphics;
    }
    
    public void setColor(Color color) {
        g.setColor(color);
    }
    
    public Color getColor() {
        return g.getColor();
    }
    
    public void line(int startX, int startY, int endX, int endY) {
        g.drawLine(startX, startY, endX, endY);
    }
    
}
