package org.freekode.wowbotcv.infrastructure;

import javax.swing.KeyStroke;

public enum KeyCode {
	ONE('1');

	private final int keyCode;

	KeyCode(char c) {
		keyCode = KeyStroke.getKeyStroke(c, 0).getKeyCode();
	}

	public int getKeyCode() {
		return keyCode;
	}
}
