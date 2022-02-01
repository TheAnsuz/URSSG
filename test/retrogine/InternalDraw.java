package retrogine;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;
import org.urssg.retrogine.display.Display;
import org.urssg.retrogine.display.RenderLayer;
import org.urssg.retrogine.display.WindowConfiguration;
import org.urssg.retrogine.ios.ImageReader;

public class InternalDraw {

    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "false");

        WindowConfiguration config = WindowConfiguration.defaultConfig();
        config.addRenderLayer(new InternalDrawer());
        config.setSize(400, 400);
        config.setTitle("Ventana de prueba de renderizado");
        config.setInternalSize(165, 100);
        Display display = new Display(config);

        display.setTitle("Ventana");
        display.keepAspectRatio(true);
        display.setQueueRendering(false);
    }

}

class InternalDrawer implements RenderLayer {

    private Random rnd = new Random();

    @Override
    public void draw(Graphics2D g, int width, int height) {
        g.setColor(Color.red);
        g.fillRect(rnd.nextInt(width), rnd.nextInt(height), rnd.nextInt(width), rnd.nextInt(height));
        g.setColor(Color.green);
        g.drawRect(0, 0, width, height);
        g.setColor(Color.orange);
        g.drawRect((int) (width * .2), (int) (height * .2), (int) (width * .8), (int) (height * .8));

        g.drawImage(ImageReader.getDefaultImage(16, 16), 10, 10, null);
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
