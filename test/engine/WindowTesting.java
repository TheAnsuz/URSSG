package engine;

import java.util.Scanner;

import me.amrv.engine.window.Window;

public class WindowTesting {

	public static final Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		
		try {
			Window w = new Window("Ventana");
			w.getDrawer().setAntialiasing(true);

			w.getDrawer().setHighQualityRendering(true);
			w.getDrawer().setDirectPainting(true);
			w.addDrawer(new DrawTesting(),2);

			//w.getDrawer().setVsync(true);
//			w.getDrawer().setHighQualityRending(true);
			w.addDrawer(new DrawTesting(),0);
			String command = "";
//			while (!w.shouldClose()) {
//
//				if (scan.hasNext())
//					command = scan.nextLine();
//
//				if (command.equalsIgnoreCase("exit"))
//					System.exit(0);
//
//				else if (command.equalsIgnoreCase("fullscreen")) {
//					w.setFullScreen(!w.isFullScreen());
//					System.out.println(w.getSize() + " -- " + w.getDrawer().getBounding());
//				}
//
//			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
