package org.freekode.wowbotcv.infrastructure.game;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import org.bytedeco.javacv.Java2DFrameUtils;
import org.bytedeco.opencv.opencv_core.Mat;
import org.freekode.wowbotcv.domain.game.ImageProvider;

public class FileImageProvider implements ImageProvider {

	private final String filename;

	public FileImageProvider(String filename) {
		this.filename = filename;
	}

	@Override
	public Mat getImage(Rectangle rectangle) {
		try {
			InputStream file = getClass().getClassLoader().getResourceAsStream(filename);
			BufferedImage image = ImageIO.read(file);
			image = cutImage(rectangle, image);
			return Java2DFrameUtils.toMat(image);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private BufferedImage cutImage(Rectangle rectangle, BufferedImage image) {
		return image.getSubimage((int) rectangle.getX(), (int) rectangle.getY(), (int) rectangle.getWidth(), (int) rectangle.getHeight());
	}

	@Override
	public void saveImage(Mat mat) {
		File file = new File("out.png");
		try {
			ImageIO.write(Java2DFrameUtils.toBufferedImage(mat), "png", file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
