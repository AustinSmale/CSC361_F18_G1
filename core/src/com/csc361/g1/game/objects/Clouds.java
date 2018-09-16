/*
 * Allen Crigger
 * Class holds all of the properties for the cloud objects
 */

package com.csc361.g1.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.csc361.g1.game.Assets;

public class Clouds extends AbstractGameObject {
	private float length;
	private Array<TextureRegion> regClouds;
	private Array<Cloud> clouds;
	
	//Cloud Class
	private class Cloud extends AbstractGameObject {
		private TextureRegion regCloud;
		public Cloud () {
			
		}
		
		//SetRegion
		public void setRegion (TextureRegion region) {
			regCloud = region;
		}
		
		//Method below are implements of AbstractGameObject
		@Override
		public void render (SpriteBatch batch) {
			TextureRegion reg = regCloud;
			batch.draw(reg.getTexture(), position.x + origin.x, position.y + origin.y, origin.x, origin.y, dimension.x, dimension.y, scale.x, scale.y, rotation, reg.getRegionX(), reg.getRegionY(), reg.getRegionWidth(), reg.getRegionHeight(), false, false);
		}
	}
}
