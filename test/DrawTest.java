
import java.awt.Color;
import me.amrv.urssg.window.Drawer;
import me.amrv.urssg.window.WindowRender;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author marruiad
 */
public class DrawTest implements Drawer {
    @Override
    public void draw(WindowRender render, int width, int height) {
        System.out.println("Dibujado");
        render.setColor(Color.RED);
        render.line(0, 0, width, height);
    }
    
}
