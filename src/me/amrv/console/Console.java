/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package me.amrv.console;

import java.awt.Font;
import javax.swing.JFrame;

/**
 *
 * @author marruiad
 */
public class Console {

    public final ConsolePanel panel;
    private final JFrame container;

    public Console() {
        container = new JFrame();
        container.pack();
        panel = new ConsolePanel(new Font(Font.MONOSPACED, Font.BOLD, 8),80,16);
        container.add(panel);
        container.pack();
        container.setLocationRelativeTo(null);
        container.setVisible(true);
        System.out.println(panel.getSize());
        System.out.println(container.getSize());
    }

}
