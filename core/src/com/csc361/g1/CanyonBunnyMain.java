package com.csc361.g1;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.csc361.g1.game.Assets;
import com.csc361.g1.game.WorldController;
import com.csc361.g1.game.WorldRenderer;
import com.csc361.g1.screens.MenuScreen;
import com.csc361.g1.util.AudioManager;
import com.csc361.g1.util.GamePreferences;

/**
 * Main class to render game
 * 
 * @author Austin Smale
 *
 */
public class CanyonBunnyMain extends Game {
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

		// Chapter 10
		// Load preferences for audio settings and start playing music
		GamePreferences.instance.load();
		AudioManager.instance.play(Assets.instance.music.song01);

		// Start the game at the menu screen
		setScreen(new MenuScreen(this));
	}
}
