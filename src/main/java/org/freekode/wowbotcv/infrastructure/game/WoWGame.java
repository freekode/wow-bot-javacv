package org.freekode.wowbotcv.infrastructure.game;

import org.freekode.wowbotcv.domain.game.Controller;
import org.freekode.wowbotcv.domain.game.CoordinatesCalculator;
import org.freekode.wowbotcv.domain.game.Game;
import org.freekode.wowbotcv.domain.game.ImageProvider;

public class WoWGame extends Game {

	WoWGame(
			CoordinatesCalculator coordinatesCalculator,
			ImageProvider imageProvider,
			Controller controller) {
		super(coordinatesCalculator, imageProvider, controller);
	}
}
