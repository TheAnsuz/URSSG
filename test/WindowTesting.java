import java.awt.Dimension;

import me.amrv.easygamelib.Window;

public class WindowTesting {

	public static void main(String[] args) {
		try {
			Window window = new Window("Titulo", new Dimension(640,480));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
