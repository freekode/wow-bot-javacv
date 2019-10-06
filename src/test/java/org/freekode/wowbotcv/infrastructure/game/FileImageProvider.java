package org.freekode.wowbotcv.infrastructure.game;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import org.freekode.wowbotcv.domain.game.ImageProvider;

public class FileImageProvider implements ImageProvider {

	private final String filename;

	public FileImageProvider(String filename) {
		this.filename = filename;
	}

	@Override
	public BufferedImage getImage(Rectangle rectangle) {
		try {
			InputStream file = getClass().getClassLoader().getResourceAsStream(filename);
			BufferedImage bufferedImage = ImageIO.read(file);
			return bufferedImage.getSubimage((int) rectangle.getX(), (int) rectangle.getY(), (int) rectangle.getWidth(), (int) rectangle.getHeight());
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void saveImage(BufferedImage image) {
		File file = new File("out.png");
		try {
			ImageIO.write(image, "png", file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
