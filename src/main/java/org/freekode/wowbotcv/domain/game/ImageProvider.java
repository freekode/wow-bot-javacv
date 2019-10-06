package org.freekode.wowbotcv.domain.game;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public interface ImageProvider {

	BufferedImage getImage(Rectangle rectangle);

	void saveImage(BufferedImage image);
}
