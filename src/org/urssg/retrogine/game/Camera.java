package org.urssg.retrogine.game;

import org.urssg.retrogine.display.DrawingLayer;

public class Camera {
    private int x;
    private int y;

    private final DrawingLayer wireframeLayer = new DrawingLayer();

    public DrawingLayer getWireframeLayer() {
        return wireframeLayer;
    }
}
