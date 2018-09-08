package com.csc361.g1;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.graphics.GL10;
import com.csc361.g1.game.WorldController;
import com.csc361.g1.game.WorldRenderer;

public class CanyonBunnyMain implements ApplicationListener{
	private static final String TAG = CanyonBunnyMain.class.getName();
	
	private WorldController worldController;
	private WorldRenderer worldRenderer;
	
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
		// Sets the clear green sscreen color to: Cornflower Blue
	}
	@Override public void resize(int width, int height) {}
	@Override public void pause() {}
	@Override public void resume() {}
	@Override public void dispose() {}
}
