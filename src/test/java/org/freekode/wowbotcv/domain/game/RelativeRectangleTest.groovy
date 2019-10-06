package org.freekode.wowbotcv.domain.game

import spock.lang.Specification

import java.awt.*

class RelativeRectangleTest extends Specification {
	def "100% fill"() {
		given:
		def window = new Dimension(100, 100)
		def relativeRectangle = new RelativeRectangle.RelativeRectangleBuilder(1, 1).build()

		when:
		def rectangle = relativeRectangle.getRealRectangle(window)

		then:
		rectangle.getWidth() == 100
		rectangle.getHeight() == 100
		rectangle.getX() == 0
		rectangle.getY() == 0
	}

	def "with offsets"() {
		given:
		def window = new Dimension(100, 100)
		def relativeRectangle = new RelativeRectangle.RelativeRectangleBuilder(0.5, 0.4)
				.withXOffset(0.2)
				.withYOffset(0.1)
				.build()

		when:
		def rectangle = relativeRectangle.getRealRectangle(window)

		then:
		rectangle.getWidth() == 50
		rectangle.getHeight() == 40
		rectangle.getX() == 20
		rectangle.getY() == 10
	}
}
