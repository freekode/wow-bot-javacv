package org.freekode.wowbotcv.domain.game;

import java.awt.Dimension;
import java.awt.Rectangle;

public class CoordinatesCalculator {

	private final Dimension screen;

	private final Dimension window;

	private final Rectangle windowRectangle;

	private final int headerHeight;

	public CoordinatesCalculator(Dimension screen, Dimension window, int headerHeight) {
		this.screen = screen;
		this.window = window;
		this.headerHeight = headerHeight;
		this.windowRectangle = calculateWindowRectangle();
	}

	public CoordinatesCalculator(Dimension screen) {
		this.screen = screen;
		this.window = screen;
		this.headerHeight = 0;
		this.windowRectangle = calculateWindowRectangle();
	}

	private Rectangle calculateWindowRectangle() {
		return new Rectangle(0, headerHeight, (int) window.getWidth(), (int) window.getHeight());
	}

	Rectangle getWindow() {
		return windowRectangle;
	}

	Rectangle getRegion(Rectangle region) {
		Rectangle windowRectangle = getWindow();
		return new Rectangle(
				(int) (windowRectangle.getX() + region.getX()),
				(int) (windowRectangle.getY() + region.getY()),
				(int) region.getWidth(),
				(int) region.getHeight()
		);
	}

	Rectangle getRegion(RelativeRectangle relativeRectangle) {
		Rectangle windowRectangle = getWindow();
		Rectangle realRectangle = relativeRectangle.getRealRectangle(window);

		return new Rectangle(
				(int) (windowRectangle.getX() + realRectangle.getX()),
				(int) (windowRectangle.getY() + realRectangle.getY()),
				(int) realRectangle.getWidth(),
				(int) realRectangle.getHeight()
		);
	}
}
