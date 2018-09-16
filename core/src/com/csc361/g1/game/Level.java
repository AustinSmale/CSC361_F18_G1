/*
 * Allen Crigger
 * Level loader that reads and implements the image data.
 */
package com.csc361.g1.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

//Class Imports
import com.csc361.g1.game.objects.AbstractGameObject;
import com.csc361.g1.game.objects.Clouds;
import com.csc361.g1.game.objects.Mountains;
import com.csc361.g1.game.objects.Rock;
import com.csc361.g1.game.objects.WaterOverlay;

public class Level {
	public static final String TAG = Level.class.getName();
	
	//Colors
	public enum BLOCK_TYPE {
		EMPTY(0, 0, 0), // black
		ROCK(0, 255, 0), // green
		PLAYER_SPAWNPOINT(255, 255, 255), // white
		ITEM_FEATHER(255, 0, 255), // purple
		ITEM_GOLD_COIN(255, 255, 0); // yellow
		private int color;
		
		private BLOCK_TYPE (int r, int g, int b) {
			color = r << 24 | g << 16 | b << 8 | 0xff;
		}
		
		public boolean sameColor (int color) {
			return this.color == color;
		}
		
		public int getColor () {
			return color;
		}
	}
	
	//Objects
	public Array<Rock> rocks;
	
	//Decoration
	public Clouds clouds;
	public Mountains mountains;
	public WaterOverlay waterOverlay;
	
	public Level (String filename) {
		init(filename);
	}
	
	private void init (String filename) {
		
	}
	
	public void render (SpriteBatch batch) {
		
	}
}
