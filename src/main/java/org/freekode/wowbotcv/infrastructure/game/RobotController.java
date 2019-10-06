package org.freekode.wowbotcv.infrastructure.game;

import java.awt.AWTException;
import java.awt.Robot;
import org.freekode.wowbotcv.domain.game.Controller;

public class RobotController implements Controller {

	private static final int DELAY_MS = 50;

	private final Robot robot;

	public RobotController() throws AWTException {
		robot = new Robot();
	}

	@Override
	public void mouse(int x, int y) {
		robot.mouseMove(x, y);
		robot.delay(DELAY_MS);
	}

	@Override
	public void key(int keyCode) {
		robot.keyPress(keyCode);
		robot.delay(DELAY_MS);
		robot.keyRelease(keyCode);
		robot.delay(DELAY_MS);
	}
}
