package org.freekode.wowbotcv.util;

import org.bytedeco.opencv.opencv_core.Point;

public class ValuePoint {

	private final Point point;

	private final float value;

	ValuePoint(Point point, float value) {
		this.point = point;
		this.value = value;
	}

	public Point getPoint() {
		return point;
	}

	public float getValue() {
		return value;
	}

	public boolean isGreaterThan(ValuePoint valuePoint) {
		return value > valuePoint.value;
	}
}
