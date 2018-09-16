package com.csc361.g1;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.csc361.g1.game.Assets;
import com.csc361.g1.game.WorldController;
import com.csc361.g1.game.WorldRenderer;

/**
 * Main class to render game
 * @author Austin Smale
 *
 */
public class CanyonBunnyMain implements ApplicationListener{
	private static final String TAG = CanyonBunnyMain.class.getName();
	
	private WorldController worldController;
	private WorldRenderer worldRenderer;
	private boolean paused;
	
	/**
	 * Initialize the window and game  
	 */
	@Override 
	public void create() {
		// Set Libgdx log level to DEBUG
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		
		// Load Assets
		Assets.instance.init(new AssetManager());
		
		// Initialize controller and renderer
		worldController = new WorldController();
		worldRenderer = new WorldRenderer(worldController);
		// Game world is active on start
		paused = false;
	}
	
	/**
	 * Render the game
	 */
	@Override 
	public void render() {
		// Do not update game world when paused
		if(!paused) {
			// Update game world by the time that has passed
			// since last rendered frame.
			worldController.update(Gdx.graphics.getDeltaTime());			
		}
		// Sets the clear green screen color to: Cornflower Blue
		Gdx.gl.glClearColor(0x64/255.0f, 0x95/255.0f, 0xed/255.0f, 0xff/255.0f);
		// Clears the screen
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// Render game world to screen
		worldRenderer.render();
	}
	
	/**
	 * Resize the window if the user changes it
	 */
	@Override 
	public void resize(int width, int height) {
		worldRenderer.resize(width, width);
	}
	
	/**
	 * if the user pauses the game
	 */
	@Override 
	public void pause() {
		paused = true;
	}
	
	/**
	 * if the user resumes the game
	 */
	@Override public void resume() {
		paused = false;
	}
	
	@Override 
	public void dispose() {
		worldRenderer.dispose();
		Assets.instance.dispose();
	}
}
