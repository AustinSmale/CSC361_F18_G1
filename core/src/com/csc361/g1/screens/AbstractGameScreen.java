package com.csc361.g1.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.csc361.g1.game.Assets;

/**
 * The abstract game class that holds methods that each game screen must have.
 * These are common actions that are executed in each game screen
 * 
 * @author Austin Smale
 *
 */
public abstract class AbstractGameScreen implements Screen {
	protected Game game;

	/**
	 * Constructor that sets our game to the game we want to use
	 * @param game
	 */
	public AbstractGameScreen(Game game) {
		this.game = game;
	}

	public abstract void render(float deltaTime);

	public abstract void resize(int width, int height);

	public abstract void show();

	public abstract void hide();

	public abstract void pause();

	/**
	 * Initialize the assets in the game
	 */
	public void resume() {
		Assets.instance.init(new AssetManager());
	}

	/**
	 * Dispose of the asset objects in the game
	 */
	public void dispose() {
		Assets.instance.dispose();
	}

}
