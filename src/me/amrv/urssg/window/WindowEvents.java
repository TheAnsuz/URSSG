/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package me.amrv.urssg.window;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 *
 * @author marruiad
 */
public class WindowEvents implements WindowListener {

    private Window window;

    protected WindowEvents(Window window) {
        this.window = window;
    }

    @Override
    public void windowOpened(WindowEvent e) {
    
    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.out.println("Closing");
        window.close();
    }

    @Override
    public void windowClosed(WindowEvent e) {
    
    }

    @Override
    public void windowIconified(WindowEvent e) {
    
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    
    }

    @Override
    public void windowActivated(WindowEvent e) {
    
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    
    }

}
