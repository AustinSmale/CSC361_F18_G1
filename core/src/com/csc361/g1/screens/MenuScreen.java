/*
 * MenuScreen class holds all of the properties for the menu
 */

package com.csc361.g1.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;

//UI Imports
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

//Class Imports
import com.csc361.g1.game.Assets;
import com.csc361.g1.util.Constants;

public class MenuScreen extends AbstractGameScreen {

	private static final String TAG = MenuScreen.class.getName();
	
	private Stage stage;
	private Skin skinCanyonBunny;
	
	// Menu Stuff
	private Image imgBackground;
	private Image imgLogo;
	private Image imgInfo;
	private Image imgCoins;
	private Image imgBunny;
	private Button btnMenuPlay;
	private Button btnMenuOptions;
	 
	// Options Stuff
	private Window winOptions;
	private TextButton btnWinOptSave;
	private TextButton btnWinOptCancel;
	private CheckBox chkSound;
	private Slider sldSound;
	private CheckBox chkMusic;
	private Slider sldMusic;
	private SelectBox selCharSkin;
	private Image imgCharSkin;
	private CheckBox chkShowFpsCounter;
	 
	// Debugging Stuff
	private final float DEBUG_REBUILD_INTERVAL = 5.0f;
	private boolean debugEnabled = false;
	private float debugRebuildStage;
	
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
		stage.setViewport(Constants.VIEWPORT_GUI_WIDTH, Constants.VIEWPORT_GUI_HEIGHT, false);
	}
	
	/*
	 * Method makes the screen visible
	 */
	public void show () {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		rebuildStage();
	}
	
	/*
	 * Method hides the screen from view
	 */
	public void hide () { 
		stage.dispose();
		skinCanyonBunny.dispose();
	}
	
	/*
	 * Method pauses the screen
	 */
	public void pause () {
		
	}
	
	/*
	 *  ========== Chapter 7 Stage Building Code Below ==========
	 */
	
	/*
	 * Method builds the menu screen.
	 */
	private void rebuildStage () {
		skinCanyonBunny = new Skin(Gdx.files.internal(Constants.SKIN_CANYONBUNNY_UI), new TextureAtlas(Constants.TEXTURE_ATLAS_UI));
		
		// First, Build All Layers
		Table layerBackground = buildBackgroundLayer();
		Table layerObjects = buildObjectsLayer();
		Table layerLogos = buildLogosLayer();
		Table layerControls = buildControlsLayer();
		Table layerOptionsWindow = buildOptionsWindowLayer();
		
		// Then, Assemble stage for menu screen
		stage.clear();
		Stack stack = new Stack();
		stage.addActor(stack);
		stack.setSize(Constants.VIEWPORT_GUI_WIDTH, Constants.VIEWPORT_GUI_HEIGHT);
		stack.add(layerBackground);
		stack.add(layerObjects);
		stack.add(layerLogos);
		stack.add(layerControls);
		stage.addActor(layerOptionsWindow);
	}
	
	/*
	 * Method builds the background layer of the menu screen
	 */
	private Table buildBackgroundLayer () {
		Table layer = new Table();
		// Background
		imgBackground = new Image(skinCanyonBunny, "background");
		layer.add(imgBackground);
		return layer;
	}
	
	/*
	 * Method builds the object layer of the menu screen
	 */
	private Table buildObjectsLayer () {
		Table layer = new Table();
		// Coins
		imgCoins = new Image(skinCanyonBunny, "coins");
		layer.addActor(imgCoins);
		imgCoins.setPosition(135, 80);
		
		// Bunny
		imgBunny = new Image(skinCanyonBunny, "bunny");
		layer.addActor(imgBunny);
		imgBunny.setPosition(355, 40);
		return layer;
	}
	
	/*
	 * Method builds the logo layer of the menu screen
	 */
	private Table buildLogosLayer () {
		Table layer = new Table();
		layer.left().top();
		
		// Game Logo
		imgLogo = new Image(skinCanyonBunny, "logo");
		layer.add(imgLogo);
		layer.row().expandY();
		
		// Info Logos
		imgInfo = new Image(skinCanyonBunny, "info");
		layer.add(imgInfo).bottom();
		if (debugEnabled)
			layer.debug();
		return layer;
	}
	
	/*
	 * Method builds the control layer of the menu screen
	 */
	private Table buildControlsLayer () {
		Table layer = new Table();
		return layer;
	}
	
	/*
	 * Method builds the options window layer of the menu screen
	 */
	private Table buildOptionsWindowLayer () {
		Table layer = new Table();
		return layer;
	}
}
