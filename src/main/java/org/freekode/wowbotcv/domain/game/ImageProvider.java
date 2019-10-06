package org.freekode.wowbotcv.domain.game;

import java.awt.Rectangle;
import org.bytedeco.opencv.opencv_core.Mat;

public interface ImageProvider {

	Mat getImage(Rectangle rectangle);

	void saveImage(Mat mat);
}
