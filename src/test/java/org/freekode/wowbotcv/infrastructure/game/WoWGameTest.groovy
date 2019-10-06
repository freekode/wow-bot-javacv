package org.freekode.wowbotcv.infrastructure.game

import org.freekode.wowbotcv.domain.game.Controller
import org.freekode.wowbotcv.domain.game.CoordinatesCalculator
import org.freekode.wowbotcv.domain.game.Game
import org.freekode.wowbotcv.domain.game.RelativeRectangle
import spock.lang.Specification

import java.awt.*

class WoWGameTest extends Specification {

	def "get image"() {
		given:
//		CoordinatesCalculator coordinateCalculator = new CoordinatesCalculator(
//				new Dimension(3360, 2100), new Dimension(1440, 900), 22)
		def screen = new Dimension(2048, 1280)
		CoordinatesCalculator coordinateCalculator = new CoordinatesCalculator(screen)

		def imageProvider = new FileImageProvider("image.jpg")
		Game game = new WoWGame(coordinateCalculator, imageProvider, Mock(Controller.class))

		when:
		RelativeRectangle fishingRegion = new RelativeRectangle.RelativeRectangleBuilder(0.42, 0.30).withYOffset(0.40).build()
		def image = game.getRegionImage(fishingRegion)

		then:
		imageProvider.saveImage(image)
	}

}
