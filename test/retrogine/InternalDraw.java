package retrogine;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;
import org.urssg.retrogine.display.Display;
import org.urssg.retrogine.display.RenderLayer;
import org.urssg.retrogine.display.WindowConfiguration;
import org.urssg.retrogine.ios.ResourceReader;

public class InternalDraw {

    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "false");

        WindowConfiguration config = WindowConfiguration.defaultConfig();
        config.addRenderLayer(new InternalDrawer());
        config.setSize(400, 400);
        config.setTitle("Ventana de prueba de renderizado");
        config.setInternalSize(150, 150);
        Display display = new Display(config);

        display.keepAspectRatio(true);
        display.setDoubleBuffered(true);
        display.setQueueRendering(false);
    }

}

class InternalDrawer implements RenderLayer {

    private Random rnd = new Random();
    private BufferedImage img = ResourceReader.loadImage("test/ismael.png", 640, 640);

    @Override
    public void draw(Graphics2D g, int width, int height) {
        g.setColor(Color.red);
        g.fillRect(rnd.nextInt(width), rnd.nextInt(height), rnd.nextInt(width), rnd.nextInt(height));
        g.setColor(Color.green);
        g.drawRect(0, 0, width, height);
        g.setColor(Color.orange);
        g.drawRect((int) (width * .2), (int) (height * .2), (int) (width * .8), (int) (height * .8));

//        g.drawImage(ResourceReader.getDefaultImage(16, 16), 10, 10, null);
        g.drawImage(img, 10, 10, null);
//        for (int z = 0; z < 1; z++) {
//            g.setColor(Color.red.darker().darker());
//            for (int y = 0; y <= height; y++)
//                for (int x = 0; x <= width; x++)
//                    g.fillRect(x, y, 1, 1);
//        }

        g.setColor(Color.blue);
        g.fillRect(rnd.nextInt(width), rnd.nextInt(height), rnd.nextInt(width), rnd.nextInt(height));
    }
}
