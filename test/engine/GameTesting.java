package engine;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

public class GameTesting {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		Canvas canvas = new Canvas();
		
		final Dimension dim = new Dimension(640,480);
		frame.getContentPane().setBackground(Color.RED);
		frame.setSize(dim);
		frame.setLocationRelativeTo(null);
		canvas.setSize(dim);
		canvas.setPreferredSize(dim);
		canvas.setMaximumSize(dim);
		canvas.setMinimumSize(dim);
		canvas.setBackground(Color.BLACK);
		frame.add(canvas);
		frame.setVisible(true);
		canvas.createBufferStrategy(1);
		canvas.getBufferStrategy().getDrawGraphics().setColor(Color.WHITE);
		canvas.getBufferStrategy().getDrawGraphics().drawLine(0, 0, canvas.getWidth(), canvas.getHeight());
		canvas.getBufferStrategy().show();
		
		
	}

}
