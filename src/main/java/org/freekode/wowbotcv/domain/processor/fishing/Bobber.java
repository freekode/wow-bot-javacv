package org.freekode.wowbotcv.domain.processor.fishing;

import org.bytedeco.opencv.opencv_core.Mat;
import org.freekode.wowbotcv.util.ValuePoint;

public class Bobber {

	private final Mat template;

	private final ValuePoint valuePoint;

	public Bobber(Mat template, ValuePoint valuePoint) {
		this.template = template;
		this.valuePoint = valuePoint;
	}

	public Mat getTemplate() {
		return template;
	}

	public ValuePoint getValuePoint() {
		return valuePoint;
	}

}
