/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package me.amrv.console;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author marruiad
 */
public abstract class ConsoleStyle {
    
	protected ConsoleColorModel model;
	protected Color background;
	protected Color foreground;
	protected Rectangle cursor;
	protected int blink;
	protected RenderingHints rendering;
	
	public final void loadModel(int width, int height) {
		this.background = background() == null ? Color.BLACK : background();
		this.foreground = foreground() == null ? Color.WHITE : foreground();
		this.model = new ConsoleColorModel();
		this.cursor = cursorForm(width, height) == null ? new Rectangle(width,height) : cursorForm(width, height);
		specialColors(this.model);
		this.rendering = new RenderingHints(RenderingHints.KEY_TEXT_ANTIALIASING, antialiasing() == null ? Antialiasing.DEFAULT : antialiasing().value);
	}
	
	public enum Antialiasing {
		DEFAULT(RenderingHints.VALUE_TEXT_ANTIALIAS_DEFAULT),
		ENABLED(RenderingHints.VALUE_TEXT_ANTIALIAS_ON),
		DISABLED(RenderingHints.VALUE_TEXT_ANTIALIAS_OFF),
		GASP(RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
		
		private final Object value;
		
		private Antialiasing(Object value) {
			this.value = value;
		}
	}
	
	public abstract Color background();
	
	public abstract Color foreground();
	
	public abstract Color cursor();
	
	public abstract Antialiasing antialiasing();
	
	public abstract ConsoleColorModel specialColors(ConsoleColorModel model);
	
	public abstract Rectangle cursorForm(int width, int height);
	
    final class ConsoleColorModel {
    	
    	private final Map<Integer,Color> map = new HashMap<>();
    	
    	ConsoleColorModel() {
    		
		}
    	
    	public ConsoleColorModel add(int code, Color color) {
    		if (color == null)
    			throw new NullPointerException("Color model cant be null (" + code + ") ");
    		if (code < 0 || code > Character.MAX_VALUE)
    			throw new StringIndexOutOfBoundsException(code);
    		map.put(Integer.valueOf(Character.MAX_HIGH_SURROGATE+code), color);
    		return this;
    	}
    	
    	public boolean contains(char c) {
//    		System.out.println("> "+c+" = "+Integer.valueOf(c)+" <");
    		return map.containsKey(Integer.valueOf(c));
    	}
    	
    	public Color getColorModel(char c) {
    		return map.containsKey(Integer.valueOf(c)) ? map.get(Integer.valueOf(c)) : foreground;
    	}
    }
}
