/*
 * Allen Crigger
 * Assets Class
 * Holds all of the game's assets.
 */

package com.csc361.g1.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Disposable;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import com.csc361.g1.util.Constants;

public class Assets implements Disposable, AssetErrorListener {
	public static final String TAG = Assets.class.getName();
	public static final Assets instance = new Assets();
	private AssetManager assetManager;
	public AssetFonts fonts;
	public AssetSounds sounds;
	public AssetMusic music;

	/**
	 * Holds the information for all the sounds
	 */
	public class AssetSounds {
		public final Sound jump;
		public final Sound jumpWithFeather;
		public final Sound pickupCoin;
		public final Sound pickupFeather;
		public final Sound liveLost;

		/**
		 * Constructor for AssetSounds
		 * 
		 * @param am
		 */
		public AssetSounds(AssetManager am) {
			jump = am.get("sounds/jump.wav", Sound.class);
			jumpWithFeather = am.get("sounds/jump_with_feather.wav", Sound.class);
			pickupCoin = am.get("sounds/pickup_coin.wav", Sound.class);
			pickupFeather = am.get("sounds/pickup_feather.wav", Sound.class);
			liveLost = am.get("sounds/live_lost.wav", Sound.class);
		}
	}

	/**
	 * Holds the music for the game
	 * 
	 */
	public class AssetMusic {
		public final Music song01;

		public AssetMusic(AssetManager am) {
			song01 = am.get("music/keith303_-_brand_new_highscore.mp3", Music.class);
		}
	}

	// Singleton Pattern: prevents instantiation from the other classes.
	private Assets() {

	}

	public AssetBunny bunny;
	public AssetRock rock;
	public AssetGoldCoin goldCoin;
	public AssetFeather feather;
	public AssetLevelDecoration levelDecoration;

	/**
	 * Initializes the assets in the game
	 * 
	 * @param assetManager
	 */
	public void init(AssetManager assetManager) {
		this.assetManager = assetManager;

		// set asset manager error handler
		assetManager.setErrorListener(this);

		// load texture atlas
		assetManager.load(Constants.TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);

		// added sounds and music
		// load sounds
		assetManager.load("sounds/jump.wav", Sound.class);
		assetManager.load("sounds/jump_with_feather.wav", Sound.class);
		assetManager.load("sounds/pickup_coin.wav", Sound.class);
		assetManager.load("sounds/pickup_feather.wav", Sound.class);
		assetManager.load("sounds/live_lost.wav", Sound.class);

		// load music
		assetManager.load("music/keith303_-_brand_new_highscore.mp3", Music.class);

		// start loading assets and wait until finished
		assetManager.finishLoading();
		Gdx.app.debug(TAG, "# of assets loaded: " + assetManager.getAssetNames().size);
		for (String a : assetManager.getAssetNames())
			Gdx.app.debug(TAG, "asset: " + a);
		TextureAtlas atlas = assetManager.get(Constants.TEXTURE_ATLAS_OBJECTS);

		// enable texture filtering for pixel smoothing
		for (Texture t : atlas.getTextures())
			t.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		// create game resource objects
		fonts = new AssetFonts(); // added
		bunny = new AssetBunny(atlas);
		rock = new AssetRock(atlas);
		goldCoin = new AssetGoldCoin(atlas);
		feather = new AssetFeather(atlas);
		levelDecoration = new AssetLevelDecoration(atlas);
		// added in chapter 10 below
		sounds = new AssetSounds(assetManager);
		music = new AssetMusic(assetManager);
	}

	// Disposes of the assetManager and the fonts
	@Override
	public void dispose() {
		assetManager.dispose();
		fonts.defaultBig.dispose();
		fonts.defaultNormal.dispose();
		fonts.defaultSmall.dispose();
	}

	// Error for when the asset cannot be loaded.
	public void error(String filename, Class type, Throwable throwable) {
		Gdx.app.error(TAG, "Couldn't load asset '" + filename + "'", (Exception) throwable);
	}

	/*
	 * ===== Game Assets Below =====
	 */

	// BunnyHead Asset
	public class AssetBunny {
		public final AtlasRegion head;

		public AssetBunny(TextureAtlas atlas) {
			head = atlas.findRegion("bunny_head");
		}
	}

	// Edge and Middle rock assets
	public class AssetRock {
		public final AtlasRegion edge;
		public final AtlasRegion middle;

		public AssetRock(TextureAtlas atlas) {
			edge = atlas.findRegion("rock_edge");
			middle = atlas.findRegion("rock_middle");
		}
	}

	// Gold Coin Asset
	public class AssetGoldCoin {
		public final AtlasRegion goldCoin;

		public AssetGoldCoin(TextureAtlas atlas) {
			goldCoin = atlas.findRegion("item_gold_coin");
		}
	}

	// Feather Asset
	public class AssetFeather {
		public final AtlasRegion feather;

		public AssetFeather(TextureAtlas atlas) {
			feather = atlas.findRegion("item_feather");
		}
	}

	// Decoration Assets that are just here to make things look pretty.
	public class AssetLevelDecoration {
		public final AtlasRegion cloud01;
		public final AtlasRegion cloud02;
		public final AtlasRegion cloud03;
		public final AtlasRegion mountainLeft;
		public final AtlasRegion mountainRight;
		public final AtlasRegion waterOverlay;
		public final AtlasRegion carrot;
		public final AtlasRegion goal;

		public AssetLevelDecoration(TextureAtlas atlas) {
			cloud01 = atlas.findRegion("cloud01");
			cloud02 = atlas.findRegion("cloud02");
			cloud03 = atlas.findRegion("cloud03");
			mountainLeft = atlas.findRegion("mountain_left");
			mountainRight = atlas.findRegion("mountain_right");
			waterOverlay = atlas.findRegion("water_overlay");
			// Added the carrot and goal region from chapter 11
			carrot = atlas.findRegion("carrot");
			goal = atlas.findRegion("goal");
		}
	}

	@Override
	public void error(AssetDescriptor asset, Throwable throwable) {
		// TODO Auto-generated method stub

	}

	/**
	 * Font class
	 * 
	 * @author Austin Smale
	 *
	 */
	public class AssetFonts {
		// different size fonts to use
		public final BitmapFont defaultSmall;
		public final BitmapFont defaultNormal;
		public final BitmapFont defaultBig;

		/**
		 * Constructor for fonts, creates the font in three different sizes
		 */
		public AssetFonts() {
			// create three fonts using Libgdx's 15px bitmap font
			defaultSmall = new BitmapFont(Gdx.files.internal("images/arial-15.fnt"), true);
			defaultNormal = new BitmapFont(Gdx.files.internal("images/arial-15.fnt"), true);
			defaultBig = new BitmapFont(Gdx.files.internal("images/arial-15.fnt"), true);

			// set font sizes
			defaultSmall.getData().setScale(0.75f);
			defaultNormal.getData().setScale(1.0f);
			defaultBig.getData().setScale(2.0f);

			// enable linear texture filtering for smooth fonts
			defaultSmall.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
			defaultNormal.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
			defaultBig.getRegion().getTexture().setFilter(TextureFilter.Linear, TextureFilter.Linear);
		}
	}
}
