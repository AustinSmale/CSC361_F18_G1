/*
 * Allen Crigger
 * Rock class defines all of the properties of the rock object
 */

package com.csc361.g1.game.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.csc361.g1.game.Assets;

public class Rock extends AbstractGameObject {
	private TextureRegion regEdge;
	private TextureRegion regMiddle;
	private int length;
	
	//Rock Method
	public Rock () {
		 init();
	}
	
	//Initializes the rock's features
	private void init () {
		dimension.set(1, 1.5f);
		regEdge = Assets.instance.rock.edge;
		regMiddle = Assets.instance.rock.middle;
		//Start length of this rock
		setLength(1);
	}
	
	//Sets the length of the rock
	public void setLength (int length) {
		this.length = length;
	}
	
	//Increases the overall length of the rock by a fixed length
	public void increaseLength (int amount) {
		setLength(length + amount);
	}
}
