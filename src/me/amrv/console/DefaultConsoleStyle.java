/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package me.amrv.console;

import java.awt.Color;
import java.awt.Rectangle;

/**
 *
 * @author marruiad
 */
public final class DefaultConsoleStyle extends ConsoleStyle {

	@Override
	public Color background() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Color foreground() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Color cursor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConsoleColorModel specialColors(ConsoleColorModel model) {
		// TODO Auto-generated method stub
		return model.add(1, Color.BLUE).add(2, Color.GREEN).add(3, Color.CYAN).add(4, Color.RED).add(5, Color.MAGENTA).add(6, Color.YELLOW).add(7, Color.WHITE);
	}

	@Override
	public Rectangle cursorForm(int width, int height) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Antialiasing antialiasing() {
		// TODO Auto-generated method stub
		return Antialiasing.ENABLED;
	}

	
    
}
