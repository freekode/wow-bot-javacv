package org.freekode.wowbotcv.domain.game;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import org.bytedeco.opencv.opencv_core.Mat;

public abstract class Game {

	private final CoordinatesCalculator coordinatesCalculator;

	private final ImageProvider imageProvider;

	private final Controller controller;

	public Game(
			CoordinatesCalculator coordinateCalculator,
			ImageProvider imageProvider,
			Controller controller) {
		this.coordinatesCalculator = coordinateCalculator;
		this.imageProvider = imageProvider;
		this.controller = controller;
	}

	public Mat getWindowImage() {
		Rectangle window = coordinatesCalculator.getWindow();
		return imageProvider.getImage(window);
	}

	public Mat getRegionImage(RelativeRectangle relativeRectangle) {
		Rectangle region = coordinatesCalculator.getRegion(relativeRectangle);
		return imageProvider.getImage(region);
	}

	public void mouse(int x, int y) {
		controller.mouse(x, y);
	}

	public void key(int keyCode) {
		controller.key(keyCode);
	}
}
