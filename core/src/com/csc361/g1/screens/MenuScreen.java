/*
 * MenuScreen class holds all of the properties for the menu
 */

package com.csc361.g1.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

//UI Imports
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
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

import com.csc361.g1.util.CharacterSkin;
import com.csc361.g1.util.GamePreferences;
import com.csc361.g1.util.AudioManager;

// Chapter 12 Imports
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class MenuScreen extends AbstractGameScreen {

	private static final String TAG = MenuScreen.class.getName();

	private Stage stage;
	private Skin skinCanyonBunny;

	private Skin skinLibgdx;

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
	public MenuScreen(Game game) {
		super(game);
	}

	public void render(float deltaTime) {
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if (debugEnabled) {
			debugRebuildStage -= deltaTime;
			if (debugRebuildStage <= 0) {
				debugRebuildStage = DEBUG_REBUILD_INTERVAL;
				rebuildStage();
			}
		}
		stage.act(deltaTime);
		stage.draw();
		// Book version that was removed
		// Table.drawDebug(stage);
		// My version to fix it
		stage.setDebugAll(true);

		// Line below is a debug that starts the game with any click inside the screen
		// if(Gdx.input.isTouched())
		// game.setScreen(new GameScreen(game));
	}

	/*
	 * Method resizes the screen
	 */
	public void resize(int width, int height) {
		// Book version that does not work
		// stage.setViewport(Constants.VIEWPORT_GUI_WIDTH,
		// Constants.VIEWPORT_GUI_HEIGHT, false);
		// My version
		stage.getViewport();
	}

	/*
	 * Method makes the screen visible
	 */
	public void show() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		rebuildStage();
	}

	/*
	 * Method hides the screen from view
	 */
	public void hide() {
		stage.dispose();
		skinCanyonBunny.dispose();
		skinLibgdx.dispose();
	}

	/*
	 * Method pauses the screen
	 */
	public void pause() {

	}

	/*
	 * ========== Chapter 7 Stage Building Code Below ==========
	 */

	/*
	 * Method builds the menu screen.
	 */
	private void rebuildStage() {
		skinCanyonBunny = new Skin(Gdx.files.internal(Constants.SKIN_CANYONBUNNY_UI),
				new TextureAtlas(Constants.TEXTURE_ATLAS_UI));
		skinLibgdx = new Skin(Gdx.files.internal(Constants.SKIN_LIBGDX_UI),
				new TextureAtlas(Constants.TEXTURE_ATLAS_LIBGDX_UI));

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
	private Table buildBackgroundLayer() {
		Table layer = new Table();
		// Background
		imgBackground = new Image(skinCanyonBunny, "background");
		layer.add(imgBackground);
		return layer;
	}

	/*
	 * Method builds the object layer of the menu screen
	 */
	private Table buildObjectsLayer() {
		Table layer = new Table();
		// Coins
		imgCoins = new Image(skinCanyonBunny, "coins");
		layer.addActor(imgCoins);
		// Chapter 12 change to coins
		imgCoins.setOrigin(imgCoins.getWidth() / 2, imgCoins.getHeight() / 2);
		imgCoins.addAction(sequence(moveTo(135, -20), scaleTo(0, 0), fadeOut(0), delay(2.5f),
				parallel(moveBy(0, 100, 0.5f, Interpolation.swingOut), scaleTo(1.0f, 1.0f, 0.25f, Interpolation.linear),
						alpha(1.0f, 0.5f))));

		// Bunny
		imgBunny = new Image(skinCanyonBunny, "bunny");
		layer.addActor(imgBunny);
		// Chapter 12 change to bunny
		imgBunny.addAction(sequence(moveTo(655, 510), delay(4.0f), moveBy(-70, -100, 0.5f, Interpolation.fade),
				moveBy(-100, -50, 0.5f, Interpolation.fade), moveBy(-150, -300, 1.0f, Interpolation.elasticIn)));
		return layer;
	}

	/*
	 * Method builds the logo layer of the menu screen
	 */
	private Table buildLogosLayer() {
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
	private Table buildControlsLayer() {
		Table layer = new Table();
		layer.right().bottom();

		// Play Button
		btnMenuPlay = new Button(skinCanyonBunny, "play");
		layer.add(btnMenuPlay);
		btnMenuPlay.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				onPlayClicked();
			}
		});
		layer.row();

		// Options Button
		btnMenuOptions = new Button(skinCanyonBunny, "options");
		layer.add(btnMenuOptions);
		btnMenuOptions.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				onOptionsClicked();
			}
		});

		if (debugEnabled)
			layer.debug();
		return layer;
	}

	// Playbutton Clicked
	private void onPlayClicked() {
		game.setScreen(new GameScreen(game));
	}

	// Options button clicked
	private void onOptionsClicked() {
		loadSettings();
		btnMenuPlay.setVisible(false);
		btnMenuOptions.setVisible(false);
		winOptions.setVisible(true);
	}

	/*
	 * Method builds the options window layer of the menu screen
	 */
	private Table buildOptionsWindowLayer() {
		Table layer = new Table();
		winOptions = new Window("Options", skinLibgdx);

		// Audio Settings: Sound/Music CheckBox and Volume Slider
		winOptions.add(buildOptWinAudioSettings()).row();

		// Character Skin: Selection Box (White, Gray, Brown)
		winOptions.add(buildOptWinSkinSelection()).row();

		// Debug: Show FPS Counter
		winOptions.add(buildOptWinDebug()).row();

		// Separator and Buttons (Save, Cancel)
		winOptions.add(buildOptWinButtons()).pad(10, 0, 10, 0);

		// Make options window slightly transparent
		winOptions.setColor(1, 1, 1, 0.8f);

		// Hide options window by default
		winOptions.setVisible(false);
		if (debugEnabled)
			winOptions.debug();

		// Let TableLayout recalculate widget sizes and positions
		winOptions.pack();

		// Move options window to bottom right corner
		winOptions.setPosition(Constants.VIEWPORT_GUI_WIDTH - winOptions.getWidth() - 50, 50);
		return winOptions;
	}

	/*
	 * ========== Chapter 7 Personal Settings Methods Below ==========
	 */

	/*
	 * Method loads preferences
	 */
	private void loadSettings() {
		GamePreferences prefs = GamePreferences.instance;
		prefs.load();
		chkSound.setChecked(prefs.sound);
		sldSound.setValue(prefs.volSound);
		chkMusic.setChecked(prefs.music);
		sldMusic.setValue(prefs.volMusic);
		// Old Version in book
		// selCharSkin.setSelection(prefs.charSkin);
		// My Fix
		selCharSkin.setSelected(prefs.charSkin);
		onCharSkinSelected(prefs.charSkin);
		chkShowFpsCounter.setChecked(prefs.showFpsCounter);
	}

	/*
	 * Method saves preferences
	 */
	private void saveSettings() {
		GamePreferences prefs = GamePreferences.instance;
		prefs.sound = chkSound.isChecked();
		prefs.volSound = sldSound.getValue();
		prefs.music = chkMusic.isChecked();
		prefs.volMusic = sldMusic.getValue();
		// Book version
		// prefs.charSkin = selCharSkin.getSelectionIndex();
		// My Fix
		prefs.charSkin = selCharSkin.getSelectedIndex();
		prefs.showFpsCounter = chkShowFpsCounter.isChecked();
		prefs.save();
	}

	/*
	 * Selected Skin Method
	 */
	private void onCharSkinSelected(int index) {
		CharacterSkin skin = CharacterSkin.values()[index];
		imgCharSkin.setColor(skin.getColor());
	}

	/*
	 * Save Button Click
	 */
	private void onSaveClicked() {
		saveSettings();
		onCancelClicked();
		// chapter 10 below
		AudioManager.instance.onSettingsUpdated();
	}

	/*
	 * Cancel Button Click
	 */
	private void onCancelClicked() {
		btnMenuPlay.setVisible(true);
		btnMenuOptions.setVisible(true);
		winOptions.setVisible(false);
		// chapter 10 below
		AudioManager.instance.onSettingsUpdated();
	}

	/*
	 * Methods builds the audio settings table
	 */
	private Table buildOptWinAudioSettings() {
		Table tbl = new Table();

		// Title: "Audio"
		tbl.pad(10, 10, 0, 10);
		tbl.add(new Label("Audio", skinLibgdx, "default-font", Color.ORANGE)).colspan(3);
		tbl.row();
		tbl.columnDefaults(0).padRight(10);
		tbl.columnDefaults(1).padRight(10);

		// Checkbox, "Sound" label, sound volume slider
		chkSound = new CheckBox("", skinLibgdx);
		tbl.add(chkSound);
		tbl.add(new Label("Sound", skinLibgdx));
		sldSound = new Slider(0.0f, 1.0f, 0.1f, false, skinLibgdx);
		tbl.add(sldSound);
		tbl.row();

		// Checkbox, "Music" label, music volume slider
		chkMusic = new CheckBox("", skinLibgdx);
		tbl.add(chkMusic);
		tbl.add(new Label("Music", skinLibgdx));
		sldMusic = new Slider(0.0f, 1.0f, 0.1f, false, skinLibgdx);
		tbl.add(sldMusic);
		tbl.row();
		return tbl;
	}

	/*
	 * Method builds the skin selection table
	 */
	private Table buildOptWinSkinSelection() {
		Table tbl = new Table();

		// Title: "Character Skin"
		tbl.pad(10, 10, 0, 10);
		tbl.add(new Label("Character Skin", skinLibgdx, "default-font", Color.ORANGE)).colspan(2);
		tbl.row();

		// Drop down box filled with skin items
		// Book Version
		// selCharSkin = new SelectBox(CharacterSkin.values(), skinLibgdx);
		// My Version
		selCharSkin = new SelectBox(skinLibgdx);
		selCharSkin.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				onCharSkinSelected(((SelectBox) actor).getSelectedIndex());
			}
		});
		tbl.add(selCharSkin).width(120).padRight(20);

		// Skin preview image
		imgCharSkin = new Image(Assets.instance.bunny.head);
		tbl.add(imgCharSkin).width(50).height(50);
		return tbl;
	}

	/*
	 * Method builds debug options table
	 */
	private Table buildOptWinDebug() {
		Table tbl = new Table();

		// Title: "Debug"
		tbl.pad(10, 10, 0, 10);
		tbl.add(new Label("Debug", skinLibgdx, "default-font", Color.RED)).colspan(3);
		tbl.row();
		tbl.columnDefaults(0).padRight(10);
		tbl.columnDefaults(1).padRight(10);

		// Checkbox, "Show FPS Counter" label
		chkShowFpsCounter = new CheckBox("", skinLibgdx);
		tbl.add(new Label("Show FPS Counter", skinLibgdx));
		tbl.add(chkShowFpsCounter);
		tbl.row();
		return tbl;
	}

	/*
	 * Method is the save and cancel buttons at the bottom of the options window
	 */
	private Table buildOptWinButtons() {
		Table tbl = new Table();

		// Separator
		Label lbl = null;
		lbl = new Label("", skinLibgdx);
		lbl.setColor(0.75f, 0.75f, 0.75f, 1);
		lbl.setStyle(new LabelStyle(lbl.getStyle()));
		lbl.getStyle().background = skinLibgdx.newDrawable("white");
		tbl.add(lbl).colspan(2).height(1).width(220).pad(0, 0, 0, 1);
		tbl.row();
		lbl = new Label("", skinLibgdx);
		lbl.setColor(0.5f, 0.5f, 0.5f, 1);
		lbl.setStyle(new LabelStyle(lbl.getStyle()));
		lbl.getStyle().background = skinLibgdx.newDrawable("white");
		tbl.add(lbl).colspan(2).height(1).width(220).pad(0, 1, 5, 0);
		tbl.row();

		// Save Button with event handler
		btnWinOptSave = new TextButton("Save", skinLibgdx);
		tbl.add(btnWinOptSave).padRight(30);
		btnWinOptSave.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				onSaveClicked();
			}
		});

		// Cancel Button with event handler
		btnWinOptCancel = new TextButton("Cancel", skinLibgdx);
		tbl.add(btnWinOptCancel);
		btnWinOptCancel.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				onCancelClicked();
			}
		});
		return tbl;
	}

	private void showMenuButtons (boolean visible) {
		float moveDuration = 1.0f;
		Interpolation moveEasing = Interpolation.swing;
		float delayOptionsButton = 0.25f;
		float moveX = 300 * (visible ? -1 : 1);
		float moveY = 0 * (visible ? -1 : 1);
		final Touchable touchEnabled = visible ? Touchable.enabled
		: Touchable.disabled;
		btnMenuPlay.addAction(
		moveBy(moveX, moveY, moveDuration, moveEasing));
		btnMenuOptions.addAction(sequence(delay(delayOptionsButton),
				moveBy(moveX, moveY, moveDuration, moveEasing)));
	}
}
