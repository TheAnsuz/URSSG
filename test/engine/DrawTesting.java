package engine;

import java.awt.*;
import java.awt.geom.Area;

import me.amrv.engine.Drawer;
import me.amrv.engine.window.WindowRender;

public class DrawTesting implements Drawer {

    @Override
    public void render(WindowRender render, int width, int height) {


        /*Random rand = new Random();

        for (int y = 10; y < 600; y += 25) {
            int a = rand.nextInt(800);

            for (int x = 15; x < 1000; x += 80) {
                int color = rand.nextInt(6);
                Color c;
                switch (color) {
                    case 0:
                        c = Color.WHITE;
                        break;
                    case 1:
                        c = Color.RED;
                        break;
                    case 2:
                        c = Color.BLUE;
                        break;
                    case 3:
                        c = Color.GREEN;
                        break;
                    case 4:
                        c = Color.MAGENTA;
                        break;
                    default:
                        c = Color.YELLOW;
                        break;
                }

                render.setColor(c);
                render.drawLine(x + a, y - a, width, height);
                Rectangle rect = new Rectangle(x - a, y, 80 - (y / x), 120 + a);
                render.setColor(c);
                render.setStroke(15);
                render.drawRectangle(rect);

                render.setColor(c);
                render.setStroke(5);
                render.drawOval(rect);

                render.setColor(c);
                render.setStroke(2, WindowRender.StrokeCorner.ROUND);
                render.drawLine(x, y, 20 + (y / x), 90 + a);

                render.cangasDeOnis(new Point(x, 4), new Point(180 + (y / x), 40), new Point(320, 10 + a));
            }
        }*/

        Area a = new Area();

        Rectangle r1 = new Rectangle(150, 200, 210, 250);
        Rectangle r2 = new Rectangle(300, 240, 210, 250);
        //render.drawRectangle(r1);
        a.add(new Area(r1));
        a.add(new Area(r2));
        render.drawShape(a);

        Rectangle r3 = new Rectangle(380, 100, 80, 80);
        render.setColor(Color.RED);
        render.drawShape(r3);
        Area a3 = new Area(r3);


        //System.out.println(a.getBounds2D().intersects(a3.getBounds2D()));
    }
}