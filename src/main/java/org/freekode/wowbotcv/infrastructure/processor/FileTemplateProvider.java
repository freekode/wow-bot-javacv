package org.freekode.wowbotcv.infrastructure.processor;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.imageio.ImageIO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bytedeco.javacv.Java2DFrameUtils;
import org.bytedeco.opencv.opencv_core.Mat;
import org.freekode.wowbotcv.domain.processor.TemplateProvider;

public class FileTemplateProvider implements TemplateProvider {

	private final Logger log = LogManager.getLogger(FileTemplateProvider.class.getName());

	private static final List<String> TEMPLATE_FILENAMES = Arrays.asList(
			"template.png", "template1.png", "template2.png", "template3.png",
			"template4.png", "template5.png", "template6.png"
	);

	private static final String TEMPLATE_DIRECTORY = "templates";

	@Override
	public List<Mat> getTemplates() {
		ClassLoader classLoader = getClass().getClassLoader();

		List<Mat> templates = new ArrayList<>();
		for (String filename : TEMPLATE_FILENAMES) {
			try {
				InputStream stream = classLoader.getResourceAsStream(TEMPLATE_DIRECTORY + "/" + filename);
				if (stream == null) {
					continue;
				}

				BufferedImage image = ImageIO.read(stream);
				templates.add(Java2DFrameUtils.toMat(image));
			} catch (Exception e) {
				log.error(e);
			}
		}

		return templates;
	}
}
