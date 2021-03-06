/*
 *  Allen Crigger
 *  Mountain class defines all of the properties of the mountain object
 */

package com.csc361.g1.game.objects;

import java.awt.color.CMMException;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.csc361.g1.game.Assets;

public class Mountains extends AbstractGameObject {
	private TextureRegion regMountainLeft;
	private TextureRegion regMountainRight;
	private int length;

	// Mountain method sets length and initializes
	public Mountains(int length) {
		this.length = length;
		init();
	}

	// Initializes the mountain
	private void init() {
		dimension.set(10, 2);
		regMountainLeft = Assets.instance.levelDecoration.mountainLeft;
		regMountainRight = Assets.instance.levelDecoration.mountainRight;

		// Shift mountain and extend length
		origin.x = -dimension.x * 2;
		length += dimension.x * 2;
	}

	/**
	 * Draw the mountains in the world so that the user can see
	 * 
	 * Added parallaxSpeedX in chapter 8
	 * 
	 * @param batch
	 * @param offsetX
	 * @param offsetY
	 * @param tintColor
	 * @param parallaxSpeedX
	 */
	private void drawMountain(SpriteBatch batch, float offsetX, float offsetY, float tintColor, float parallaxSpeedX) {
		TextureRegion reg = null;
		batch.setColor(tintColor, tintColor, tintColor, 1);
		float xRel = dimension.x * offsetX;
		float yRel = dimension.y * offsetY;

		// Mountains span the whole level
		int mountainLength = 0;
		mountainLength += MathUtils.ceil(length / (2 * dimension.x) * (1 - parallaxSpeedX));
		mountainLength += MathUtils.ceil(0.5f + offsetX);
		for (int i = 0; i < mountainLength; i++) {

			// Mountain left
			reg = regMountainLeft;
			batch.draw(reg.getTexture(), origin.x + xRel + position.x * parallaxSpeedX, origin.y + yRel + position.y,
					origin.x, origin.y, dimension.x, dimension.y, scale.x, scale.y, rotation, reg.getRegionX(),
					reg.getRegionY(), reg.getRegionWidth(), reg.getRegionHeight(), false, false);
			xRel += dimension.x;

			// Mountain right
			reg = regMountainRight;
			batch.draw(reg.getTexture(), origin.x + xRel + position.x * parallaxSpeedX, origin.y + yRel + position.y,
					origin.x, origin.y, dimension.x, dimension.y, scale.x, scale.y, rotation, reg.getRegionX(),
					reg.getRegionY(), reg.getRegionWidth(), reg.getRegionHeight(), false, false);
			xRel += dimension.x;
		}
		// Reset color to white
		batch.setColor(1, 1, 1, 1);
	}

	// Method below are implements of AbstractGameObject
	@Override
	public void render(SpriteBatch batch) {
		// Distant mountains (dark gray)
		drawMountain(batch, 0.5f, 0.5f, 0.5f, 0.8f);

		// Distant mountains (gray)
		drawMountain(batch, 0.25f, 0.25f, 0.7f, 0.5f);

		// Distant mountains (light gray)
		drawMountain(batch, 0.0f, 0.0f, 0.9f, 0.3f);
	}

	/**
	 * Update the position of the mountains to give it a parallax feel
	 * 
	 * @param camPosition
	 */
	public void updateScrollPosition(Vector2 camPosition) {
		position.set(camPosition.x, camPosition.y);
	}
}
