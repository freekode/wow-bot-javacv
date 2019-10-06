package org.freekode.wowbotcv.domain.processor;

import java.util.List;
import org.bytedeco.opencv.opencv_core.Mat;

public interface TemplateProvider {

	List<Mat> getTemplates();
}
