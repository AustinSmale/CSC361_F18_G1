package com.csc361.g1;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.csc361.g1.game.WorldController;
import com.csc361.g1.game.WorldRenderer;

public class CanyonBunnyMain implements ApplicationListener{
	private static final String TAG = CanyonBunnyMain.class.getName();
	
	private WorldController worldController;
	private WorldRenderer worldRenderer;
	private boolean paused;
	
	@Override 
	public void create() {
		// Set Libgdx log level to DEBUG
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		// Initialize controller and renderer
		worldController = new WorldController();
		worldRenderer = new WorldRenderer(worldController);
	}
	
	@Override 
	public void render() {
		// Update game world by the time that has passed
		// since last rendered frame.
		worldController.upadte(Gdx.graphics.getDeltaTime());
		// Sets the clear green screen color to: Cornflower Blue
		Gdx.gl.glClearColor(0x64/255.0f, 0x95/255.0f, 0xed/255.0f, 0xff/255.0f);
		// Clears the screen
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// Render game world to screen
		worldRenderer.render();
	}
	
	@Override 
	public void resize(int width, int height) {
		worldRenderer.resize(width, width);
	}
	
	@Override public void pause() {}
	@Override public void resume() {}
	
	@Override 
	public void dispose() {
		worldRenderer.dispose();
	}
}
