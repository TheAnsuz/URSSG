/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package me.amrv.console;

import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 *
 * @author marruiad
 */
public class Console {

    public final ConsolePanel panel;
    private final JFrame container;

    public Console() {
        container = new JFrame();
        container.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        container.pack();
        panel = new ConsolePanel(new Font(Font.MONOSPACED, Font.PLAIN, 12),80,16);
        container.add(panel);
        container.pack();
        container.setLocationRelativeTo(null);
        container.setVisible(true);
        System.out.println(panel.getSize());
        System.out.println(container.getSize());
    }

}
