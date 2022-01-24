/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package me.amrv.console;

import java.awt.Color;

/**
 *
 * @author marruiad
 */
public final class DefaultConsoleStyle  implements ConsoleStyle {

    @Override
    public Color background() {
        return Color.BLACK;
    }

    @Override
    public Color foreground() {
        return Color.WHITE;
    }
    
    
    
}
