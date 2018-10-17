package com.csc361.g1.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.csc361.g1.game.Assets;

public class Carrot extends AbstractGameObject {
	private TextureRegion regCarrot;

	public Carrot() {
		init();
	}

	/**
	 * Initialize the carrot and set the bounds, image, and dimensions of it
	 */
	private void init() {
		dimension.set(0.25f, 0.5f);

		regCarrot = Assets.instance.levelDecoration.carrot;

		// Set bounding box for collision detection
		bounds.set(0, 0, dimension.x, dimension.y);
		origin.set(dimension.x / 2, dimension.y / 2);
	}

	/**
	 * render in the carrots to the game world
	 */
	public void render(SpriteBatch batch) {
		TextureRegion reg = null;
		reg = regCarrot;
		batch.draw(reg.getTexture(), position.x - origin.x, position.y - origin.y, origin.x, origin.y, dimension.x,
				dimension.y, scale.x, scale.y, rotation, reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(),
				reg.getRegionHeight(), false, false);
	}
}