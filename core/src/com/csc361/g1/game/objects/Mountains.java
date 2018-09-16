/*
 *  Allen Crigger
 *  Mountain class defines all of the properties of the mountain object
 */

package com.csc361.g1.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.csc361.g1.game.Assets;

public class Mountains extends AbstractGameObject {
	private TextureRegion regMountainLeft;
	private TextureRegion regMountainRight;
	private int length;
	
	//Mountain method sets length and initializes
	public Mountains (int length) {
		this.length = length;
		init();
	}
	
	//Initializes the mountain
	private void init () {
		dimension.set(10, 2);
		regMountainLeft = Assets.instance.levelDecoration.mountainLeft;
		regMountainRight = Assets.instance.levelDecoration.mountainRight;
		 
		//Shift mountain and extend length
		origin.x = -dimension.x * 2;
	}
}
