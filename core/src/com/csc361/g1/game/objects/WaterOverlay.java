package com.csc361.g1.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.csc361.g1.game.Assets;

/**
 * The water overlay to go over the ground in the game. Extends
 * AbstractGameObject
 * 
 * @author Austin Smale
 *
 */
public class WaterOverlay extends AbstractGameObject {
	private TextureRegion regWaterOverlay;
	private float length;

	/**
	 * Constructor that creates an overlay to the length
	 * 
	 * @param length
	 *            the length of the overlay
	 */
	public WaterOverlay(float length) {
		this.length = length;
		init();
	}

	/**
	 * Set the length, origin, and texture
	 */
	private void init() {
		dimension.set(length * 10, 3);
		regWaterOverlay = Assets.instance.levelDecoration.waterOverlay;
		origin.x = -dimension.x / 2;
	}

	/**
	 * Draw the overlay using the batch. Required method that AbstractGameObject
	 * required
	 */
	@Override
	public void render(SpriteBatch batch) {
		TextureRegion reg = null;
		reg = regWaterOverlay;
		batch.draw(reg.getTexture(), position.x + origin.x, position.y + origin.y, origin.x, origin.y, dimension.x,
				dimension.y, scale.x, scale.y, rotation, reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(),
				reg.getRegionHeight(), false, false);
	}
}