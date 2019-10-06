package org.freekode.wowbotcv.infrastructure;

import java.awt.Rectangle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.freekode.wowbotcv.domain.game.Game;

public class Fishing {

	private final Logger log = LogManager.getLogger(Fishing.class.getName());

	private static final float THRESHOLD = 0.7f;

	private static final Rectangle FISHING_REGION = new Rectangle(320, 300, 800, 300);

	private final Game game;

	public Fishing(Game game) {
		this.game = game;
	}

	public boolean findBobber() {
		try {
			game.getRegionImage(FISHING_REGION);


			return true;
		} catch (Exception e) {
			log.error(e);
			return false;
		}
	}
}
