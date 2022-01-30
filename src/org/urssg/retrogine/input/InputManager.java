package org.urssg.retrogine.input;

public class InputManager {

	private final boolean[] inputs;
	
	public InputManager() {
		inputs = new boolean[Key.getMaxCode()];
	}
	
	public boolean isPressed(Key key) {
		return inputs[key.code];
	}
	
	public boolean isPressed(Key[] keys) {
		for (Key key : keys)
			if (inputs[key.code])
				return true;
		return false;
	}
	
	public boolean isPressed(int[] codes) {
		for (int code : codes)
			if (isPressed(code))
				return true;
		return false;
	}
	
	public boolean isPressed(int code) {
		return code > 0 && code < inputs.length ? inputs[code] : false;
	}
	
	public void setPressed(Key key, boolean pressed) {
		inputs[key.code] = pressed;
	}
	
	public void setPressed(int code, boolean pressed) {
		if (code > 0 && code < inputs.length)
			inputs[code] = pressed;
	}
}
