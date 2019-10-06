package org.freekode.wowbotcv.infrastructure.game;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.freekode.wowbotcv.domain.game.ImageProvider;

public class ScreenshotImageProvider implements ImageProvider {

	private final Logger log = LogManager.getLogger(ScreenshotImageProvider.class.getName());

	private Robot robot;

	public ScreenshotImageProvider() {
		try {
			robot = new Robot();
		} catch (Exception e) {
			log.error(e);
		}
	}

	@Override
	public BufferedImage getImage(Rectangle windowRectangle) {
		BufferedImage image = robot.createScreenCapture(windowRectangle);
		saveImage(image);
		return image;
	}

	@Override
	public void saveImage(BufferedImage image) {
		File file = new File("screenshot.png");
		try {
			ImageIO.write(image, "png", file);
		} catch (IOException e) {
			log.error(e);
		}
	}
}
