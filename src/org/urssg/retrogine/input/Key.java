package org.urssg.retrogine.input;

public enum Key {
	// Special keys
	ESC(27), SHIFT(16), ALT(18), CTRL(17), DEL(8), SUPR(127), ENTER(10), SPACE(32), CAPS(20), PAUSE(19), INSERT(155),
	FIN(35), START(36), RE_PAG(33), AV_PAG(34), UNDEFINED(0),

	// Numbers
	ZERO(48), ONE(49), TWO(50), THREE(51), FOUR(52), FIVE(53), SIX(54), SEVEN(55), EIGHT(56), NINE(57),

	// Letters
	A(65), B(66), C(67), D(68), E(69), F(70), G(71), H(72), I(73), J(74), K(75), L(76), M(77), N(78), O(79), P(80),
	Q(81), R(82), S(83), T(84), U(85), V(86), W(87), X(88), Y(89), Z(90),

	// Function letters
	F1(112), F2(113), F3(114), F4(115), F5(116), F6(117), F7(118), F8(119), F9(120), F10(121), F11(122), F12(123),

	// Symbols
	UP(38), DOWN(40), LEFT(37), RIGHT(39), ARROW(153), PLUS(521), TILDE(222), BANG(518), COMMA(44), DOT(46), LINE(45),;

	public final int code;
	private static int max = -1;

	public static int getMaxCode() {
		if (max < 0)
			for (Key key : Key.values())
				if (key.code > max)
					max = key.code;

		return max;
	}

	Key(int code) {
		this.code = code;
	}
}
