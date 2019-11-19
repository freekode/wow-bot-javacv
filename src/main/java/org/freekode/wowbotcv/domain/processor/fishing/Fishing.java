package org.freekode.wowbotcv.domain.processor.fishing;

import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Size;
import org.freekode.wowbotcv.domain.game.Game;
import org.freekode.wowbotcv.domain.game.RelativeRectangle;
import org.freekode.wowbotcv.domain.processor.TemplateProvider;
import org.freekode.wowbotcv.util.PointUtils;
import org.freekode.wowbotcv.util.ValuePoint;

public class Fishing {

	private final Logger log = LogManager.getLogger(Fishing.class.getName());

	private static final float THRESHOLD = 0.7f;

	private static final RelativeRectangle FISHING_REGION = new RelativeRectangle.RelativeRectangleBuilder(0.42f, 0.30f)
			.withYOffset(0.40f)
			.build();

	private final List<Mat> templates;

	private final Game game;

	public Fishing(Game game, TemplateProvider templateProvider) {
		this.game = game;
		this.templates = templateProvider.getTemplates();
	}

	public Optional<Bobber> findBobber() {
		Mat regionImage = game.getRegionImage(FISHING_REGION);

		Bobber bobber = getBobber(regionImage, templates);

		return Optional.ofNullable(bobber);
	}

	private Bobber getBobber(Mat image, List<Mat> templates) {
		Bobber bobber = null;
		for (Mat template : templates) {
			ValuePoint bobberPoint = findBobberPoint(image, template);
			if (bobberPoint == null) {
				continue;
			}

			if (bobber == null) {
				bobber = new Bobber(template, bobberPoint);
				continue;
			}

			if (bobberPoint.isGreaterThan(bobber.getValuePoint())) {
				bobber = new Bobber(template, bobberPoint);
			}
		}
		return bobber;
	}

	private ValuePoint findBobberPoint(Mat image, Mat template) {
		try {
			Mat grayedImage = getGrayedMat(image);
			Mat grayedTemplate = getGrayedMat(template);
			Mat result = getPrepareResult(grayedImage, grayedTemplate);

			opencv_imgproc.matchTemplate(grayedImage, grayedTemplate, result, opencv_imgproc.TM_CCOEFF_NORMED);

			List<ValuePoint> pointsAboveThreshold = PointUtils.getPointsFromMatAboveThreshold(result, THRESHOLD);
			return PointUtils.getTopPoint(pointsAboveThreshold).orElse(null);
		} catch (Exception e) {
			log.error(e);
			return null;
		}
	}

	private Mat getGrayedMat(Mat source) {
		Mat destination = new Mat(source.size(), opencv_core.CV_8UC1);
		opencv_imgproc.cvtColor(source, destination, opencv_imgproc.COLOR_BGR2GRAY);
		return destination;
	}

	private Mat getPrepareResult(Mat source, Mat template) {
		Size size = new Size(source.cols() - template.cols() + 1, source.rows() - template.rows() + 1);
		return new Mat(size, opencv_core.CV_32FC1);
	}

}
