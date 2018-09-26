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
	
	//Sets the clouds length and initializes
	public Clouds (float length) {
		this.length = length;
		init();
	}
	
	//Initialization
	private void init () {
		dimension.set(3.0f, 1.5f);
		regClouds = new Array<TextureRegion>();
		regClouds.add(Assets.instance.levelDecoration.cloud01);
		regClouds.add(Assets.instance.levelDecoration.cloud02);
		regClouds.add(Assets.instance.levelDecoration.cloud03);
		int distFac = 5;
		int numClouds = (int)(length / distFac);
		clouds = new Array<Cloud>(2 * numClouds);
		for (int i = 0; i < numClouds; i++) {
			Cloud cloud = spawnCloud();
			cloud.position.x = i * distFac;
			clouds.add(cloud);
		}
	}
	
	//Spawn the clouds
	private Cloud spawnCloud () {
		Cloud cloud = new Cloud();
		cloud.dimension.set(dimension);
		
		//Select random cloud image
		cloud.setRegion(regClouds.random());
		
		//Position
		Vector2 pos = new Vector2();
		pos.x = length + 10; //Position after end of level
		pos.y += 1.75; //Base position
		
		//Random additional position
		pos.y += MathUtils.random(0.0f, 0.2f) * (MathUtils.randomBoolean() ? 1 : -1);
		cloud.position.set(pos);
		return cloud;
	}
	
	//Method below are implements of AbstractGameObject
	@Override
	public void render (SpriteBatch batch) {
		for (Cloud cloud : clouds)
			cloud.render(batch);
	}
}
