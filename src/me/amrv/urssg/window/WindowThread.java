/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package me.amrv.urssg.window;

/**
 *
 * @author marruiad
 */
public class WindowThread implements Runnable {
    
    private final Window window;
    private final Thread thread;
    
    protected WindowThread(Window window) {
        this.window = window;
        thread = new Thread(this);
    }

    protected synchronized void start() {
        thread.start();
    }
    
    protected synchronized void stop() {
        try {
            thread.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public void run() {
        
//        while (!window.getCanvas().isDisplayable()) {
//            System.out.println("Waiting for window...");
//        }
//        
//        window.createGraphics();
        while (!window.shouldClose()) {
            window.getDrawer().update(window.getGraphics());
            
            window.render();
            try {
                Thread.sleep(window.refreshRate);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("Processing ended");
    }
}
