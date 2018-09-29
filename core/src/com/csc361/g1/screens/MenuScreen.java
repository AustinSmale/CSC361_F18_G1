/*
 * MenuScreen class holds all of the properties for the menu
 */

package com.csc361.g1.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;

public class MenuScreen extends AbstractGameScreen {

	private static final String TAG = MenuScreen.class.getName();
	
	/*
	 * MenuScreen method
	 */
	public MenuScreen (Game game) {
		super(game);
	}
	
	public void render (float deltaTime) {
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		if(Gdx.input.isTouched())
			game.setScreen(new GameScreen(game));
	}
	
	/*
	 * Method resizes the screen
	 */
	public void resize (int width, int height) {
		
	}
	
	/*
	 * Method makes the screen visible
	 */
	public void show () {
		
	}
	
	/*
	 * Method hides the screen from view
	 */
	public void hide () { 
		
	}
	
	/*
	 * Method pauses the screen
	 */
	public void pause () {
		
	}
}
