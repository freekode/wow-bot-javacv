package org.freekode.wowbotcv.infrastructure.game;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bytedeco.javacv.Java2DFrameUtils;
import org.bytedeco.opencv.opencv_core.Mat;
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
	public Mat getImage(Rectangle windowRectangle) {
		BufferedImage image = robot.createScreenCapture(windowRectangle);
		return Java2DFrameUtils.toMat(image);
	}

	@Override
	public void saveImage(Mat mat) {
		File file = new File("screenshot.png");
		try {
			ImageIO.write(Java2DFrameUtils.toBufferedImage(mat), "png", file);
		} catch (IOException e) {
			log.error(e);
		}
	}
}
