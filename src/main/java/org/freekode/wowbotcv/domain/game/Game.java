package org.freekode.wowbotcv.domain.game;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

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

	public BufferedImage getWindowImage() {
		Rectangle window = coordinatesCalculator.getWindow();
		return imageProvider.getImage(window);
	}

	public BufferedImage getRegionImage(Rectangle rectangle) {
		Rectangle region = coordinatesCalculator.getRegion(rectangle);
		return imageProvider.getImage(region);
	}

	public BufferedImage getRegionImage(RelativeRectangle relativeRectangle) {
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
