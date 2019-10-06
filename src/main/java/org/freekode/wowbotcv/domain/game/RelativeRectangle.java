package org.freekode.wowbotcv.domain.game;

import java.awt.Dimension;
import java.awt.Rectangle;

public class RelativeRectangle {

	private final Float xOffset;

	private final Float yOffset;

	private final float width;

	private final float height;

	private RelativeRectangle(Float xOffset, Float yOffset, float width, float height) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.width = width;
		this.height = height;
	}

	public Rectangle getRealRectangle(Dimension window) {
		double realWidth = window.getWidth() * width;
		double realHeight = window.getHeight() * height;

		double realX = xOffset == null ? (window.getWidth() - realWidth) / 2 : window.getWidth() * xOffset;
		double realY = yOffset == null ? (window.getHeight() - realHeight) / 2 : window.getHeight() * yOffset;

		return new Rectangle((int) realX, (int) realY, (int) realWidth, (int) realHeight);
	}

	public static final class RelativeRectangleBuilder {

		private Float xOffset = null;

		private Float yOffset = null;

		private float width;

		private float height;

		public RelativeRectangleBuilder(float width, float height) {
			this.width = width;
			this.height = height;
		}

		public RelativeRectangleBuilder withXOffset(float xOffset) {
			this.xOffset = xOffset;
			return this;
		}

		public RelativeRectangleBuilder withYOffset(float yOffset) {
			this.yOffset = yOffset;
			return this;
		}

		public RelativeRectangle build() {
			return new RelativeRectangle(xOffset, yOffset, width, height);
		}
	}
}
