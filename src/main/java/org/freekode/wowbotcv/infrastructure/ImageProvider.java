package org.freekode.wowbotcv.infrastructure;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ImageProvider {

	private final Logger log = LogManager.getLogger(ImageProvider.class.getName());

	public ImageProvider() {
	}

	public BufferedImage getScreenshot(Rectangle rectangle) {
		return getScreenshot(rectangle, false, null);
	}

	public BufferedImage getScreenshot(Rectangle rectangle, boolean writeImage, String fileName) {
		try {
			Robot robot = new Robot();
			BufferedImage image = robot.createScreenCapture(rectangle);

			if (writeImage) {
				File file = new File("images/" + fileName + ".png");
				ImageIO.write(image, "png", file);
			}

			return image;
		} catch (Exception e) {
			log.error("cutImage exception", e);
		}

		return null;
	}
}
