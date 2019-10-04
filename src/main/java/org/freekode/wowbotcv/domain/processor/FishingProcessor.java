package org.freekode.wowbotcv.domain.processor;

import java.util.List;
import java.util.stream.Collectors;
import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.opencv_core.Mat;

public class FishingProcessor {

	private final List<Mat> templates;

	public FishingProcessor(List<String> templatesFilenames) {
		templates = getTemplates(templatesFilenames);
	}

	private List<Mat> getTemplates(List<String> templatesFilenames) {
		return templatesFilenames
			.stream()
			.map(filename -> opencv_imgcodecs.imread(filename, opencv_imgcodecs.IMREAD_GRAYSCALE))
			.collect(Collectors.toList());
	}

}
