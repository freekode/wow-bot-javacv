package org.freekode.wowbotcv;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.indexer.FloatIndexer;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_core.Size;
import static org.bytedeco.opencv.global.opencv_imgproc.GaussianBlur;

public class MainTest {

	public static void main(String[] args) {
		String imageFile = "image.jpg";
		String templateFile = "template.png";

		//		test(imageFile);

		run(imageFile, templateFile);

	}

	private static void run(String imageFile, String templateFile) {
		//read in image default colors
		Mat sourceColor = opencv_imgcodecs.imread(imageFile);
		Mat sourceGrey = new Mat(sourceColor.size(), opencv_core.CV_8UC1);
		opencv_imgproc.cvtColor(sourceColor, sourceGrey, opencv_imgproc.COLOR_BGR2GRAY);
		//load in template in grey
		Mat template = opencv_imgcodecs.imread(templateFile, opencv_imgcodecs.IMREAD_GRAYSCALE);//int = 0
		//Size for the result image
		Size size = new Size(sourceGrey.cols() - template.cols() + 1, sourceGrey.rows() - template.rows() + 1);
		Mat result = new Mat(size, opencv_core.CV_32FC1);
		opencv_imgproc.matchTemplate(sourceGrey, template, result, opencv_imgproc.TM_CCOEFF_NORMED);

		DoublePointer minVal = new DoublePointer(1);
		DoublePointer maxVal = new DoublePointer(1);
		Point min = new Point(1);
		Point max = new Point(1);
		opencv_core.minMaxLoc(result, minVal, maxVal, min, max, null);
		opencv_imgproc.rectangle(sourceColor, new Rect(max.x(), max.y(), template.cols(), template.rows()), randColor(), 2, 0, 0);

		opencv_imgcodecs.imwrite("out.png", sourceColor);

		getPointsFromMatAboveThreshold(result, 0.7f);
	}

	private static Scalar randColor() {
		int b, g, r;
		b = ThreadLocalRandom.current().nextInt(0, 255 + 1);
		g = ThreadLocalRandom.current().nextInt(0, 255 + 1);
		r = ThreadLocalRandom.current().nextInt(0, 255 + 1);
		return new Scalar(b, g, r, 0);
	}

	public static List<Point> getPointsFromMatAboveThreshold(Mat m, float t) {
		List<Point> matches = new ArrayList<>();
		FloatIndexer indexer = m.createIndexer();
		for (int y = 0; y < m.rows(); y++) {
			for (int x = 0; x < m.cols(); x++) {
				if (indexer.get(y, x) > t) {
					System.out.println("(" + x + "," + y + ") = " + indexer.get(y, x));
					matches.add(new Point(x, y));
				}
			}
		}
		return matches;
	}
}
