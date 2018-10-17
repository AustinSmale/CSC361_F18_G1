package com.csc361.g1.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.csc361.g1.game.WorldController;
import com.csc361.g1.game.WorldRenderer;
import com.csc361.g1.util.GamePreferences;

/**
 * The Game Screen in Canyon Bunny
 * 
 * @author Austin
 *
 */
public class GameScreen extends AbstractGameScreen {
	private static final String TAG = GameScreen.class.getName();
	private WorldController worldController;
	private WorldRenderer worldRenderer;
	private boolean paused;

	/**
	 * Constructor of GameScreen, sends super class the game
	 * 
	 * @param game
	 */
	public GameScreen(Game game) {
		super(game);
	}

	/**
	 * Render in the game screen
	 */
	@Override
	public void render(float deltaTime) {
		// Do not update game world when paused.
		if (!paused) {
			// Update game world by the time that has passed
			// since last rendered frame.
			worldController.update(deltaTime);
		}
		// Sets the clear screen color to: Cornflower Blue
		Gdx.gl.glClearColor(0x64 / 255.0f, 0x95 / 255.0f, 0xed / 255.0f, 0xff / 255.0f);
		// Clears the screen
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// Render game world to screen
		worldRenderer.render();
	}

	/**
	 * Resize the game screen if the user changes the size of the game
	 */
	@Override
	public void resize(int width, int height) {
		worldRenderer.resize(width, height);
	}

	/**
	 * Show the game using the world controller, need to add a constructor
	 */
	@Override
	public void show() {
		GamePreferences.instance.load();
		worldController = new WorldController(game);
		worldRenderer = new WorldRenderer(worldController);
		Gdx.input.setCatchBackKey(true);
	}

	/**
	 * Hide the screen if the use minimizes the game
	 */
	@Override
	public void hide() {
		// dispose of the worldController when the game is hidden, Chapter 11
		worldController.dispose();
		worldRenderer.dispose();
		Gdx.input.setCatchBackKey(false);
	}

	/**
	 * If the user pauses set paused to true so nothing updates
	 */
	@Override
	public void pause() {
		paused = true;
	}

	/**
	 * If the user resumes then continue to update the game
	 */
	@Override
	public void resume() {
		super.resume();
		// Only called on Android!
		paused = false;
	}
}