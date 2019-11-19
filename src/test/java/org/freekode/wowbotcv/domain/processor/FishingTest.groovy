package org.freekode.wowbotcv.domain.processor

import org.freekode.wowbotcv.domain.game.Controller
import org.freekode.wowbotcv.domain.game.CoordinatesCalculator
import org.freekode.wowbotcv.domain.processor.fishing.Fishing
import org.freekode.wowbotcv.infrastructure.game.FileImageProvider
import org.freekode.wowbotcv.infrastructure.game.WoWGame
import org.freekode.wowbotcv.infrastructure.processor.FileTemplateProvider
import spock.lang.Specification

import java.awt.*

class FishingTest extends Specification {

	def "simple test"() {
		given:
		def screen = new Dimension(2048, 1280)
		def coordinateCalculator = new CoordinatesCalculator(screen)
		def imageProvider = new FileImageProvider("image.jpg")
		def game = new WoWGame(coordinateCalculator, imageProvider, Mock(Controller.class))
		def templateProvider = new FileTemplateProvider()

		when:
		def fishing = new Fishing(game, templateProvider)
		def bobber = fishing.findBobberPoint()

		then:
		bobber.isPresent()
	}

}
