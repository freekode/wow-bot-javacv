package org.freekode.wowbotcv.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.bytedeco.javacpp.indexer.FloatIndexer;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point;

public class PointUtils {

	public static List<ValuePoint> getPointsFromMatAboveThreshold(Mat mat, float threshold) {
		List<ValuePoint> matches = new ArrayList<>();
		FloatIndexer indexer = mat.createIndexer();
		for (int y = 0; y < mat.rows(); y++) {
			for (int x = 0; x < mat.cols(); x++) {
				float value = indexer.get(y, x);
				if (value > threshold) {
					Point point = new Point(x, y);
					matches.add(new ValuePoint(point, value));
				}
			}
		}

		return matches;
	}

	public static Optional<ValuePoint> getTopPoint(List<ValuePoint> pointsAboveThreshold) {
		Optional<ValuePoint> optionalPoint = Optional.empty();
		for (ValuePoint point : pointsAboveThreshold) {
			if (!optionalPoint.isPresent()) {
				optionalPoint = Optional.of(point);
				continue;
			}

			if (optionalPoint.get().getValue() < point.getValue()) {
				optionalPoint = Optional.of(point);
			}
		}

		return optionalPoint;
	}

}
