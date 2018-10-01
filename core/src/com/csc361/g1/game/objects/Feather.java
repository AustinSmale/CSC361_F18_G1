/*
 * Allen Crigger
 * Class contains the feather object.
 */

package com.csc361.g1.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.csc361.g1.game.Assets;

public class Feather extends AbstractGameObject {

	private TextureRegion regFeather;
	public boolean collected;
	
	//Feather method call initialization
	public Feather () {
		init();
	}
	
	//init method sets the properties of the feather
	private void init () {
		dimension.set(0.5f, 0.5f);
		regFeather = Assets.instance.feather.feather;
		
		// Set bounding box for collision detection
		bounds.set(0, 0, dimension.x, dimension.y);
		collected = false;
	}
	
	//Render method renders the feather as it should be
	public void render (SpriteBatch batch) {
		if (collected)
			return;
		TextureRegion reg = null;
		reg = regFeather;
		batch.draw(reg.getTexture(), position.x, position.y, origin.x, origin.y, dimension.x, dimension.y, scale.x, scale.y,
		rotation, reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(), reg.getRegionHeight(), false, false);
	}
	
	//getScore method returns 250 points.
	public int getScore() {
		return 250;
	}
}
