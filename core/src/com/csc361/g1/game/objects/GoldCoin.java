/*
 * Allen Crigger
 * Class contains the gold coin object.
 */

package com.csc361.g1.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.packtpub.libgdx.canyonbunny.game.Assets;

public class GoldCoin extends AbstractGameObject {
	private TextureRegion regGoldCoin;
	public boolean collected;
	
	//GoldCoin method call Initialization
	public GoldCoin () {
		init();
	}
	
	//init Method creates the properties of the coin
	private void init () {
		dimension.set(0.5f, 0.5f);
		regGoldCoin = Assets.instance.goldCoin.goldCoin;
		
		// Set bounding box for collision detection
		bounds.set(0, 0, dimension.x, dimension.y);
		collected = false;
	}
	
	//Render method draws the coin
	public void render (SpriteBatch batch) {
		if (collected) return;
		TextureRegion reg = null;
		reg = regGoldCoin;
		batch.draw(reg.getTexture(), position.x, position.y, origin.x, origin.y, dimension.x, dimension.y, scale.x, scale.y,
		rotation, reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(), reg.getRegionHeight(), false, false);
	}
	
	//getScore method returns 100 points
	public int getScore() {
		return 100;
	}
}
