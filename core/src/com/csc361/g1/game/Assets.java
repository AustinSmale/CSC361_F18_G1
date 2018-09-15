/*
 * Allen Crigger
 * Assets Class
 * 
 */

package com.csc361.g1.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.utils.Disposable;

import com.csc361.g1.util.Constants;

public class Assets implements Disposable, AssetErrorListener {
	public static final String TAG = Assets.class.getName();
	public static final Assets instance = new Assets();
	private AssetManager assetManager;
	
	//Singleton Pattern: prevents instantiation from the other classes.
	private Assets () {
		
	}
	
	public void init (AssetManager assetManager) {
		this.assetManager = assetManager;
		 
		//Set asset manager error handler
		assetManager.setErrorListener(this);
		
		//Load texture atlas
		assetManager.load(Constants.TEXTURE_ATLAS_OBJECTS, TextureAtlas.class);
		
		//Start loading assets and wait until finished
		assetManager.finishLoading();
		Gdx.app.debug(TAG, "# of assets loaded: " + assetManager.getAssetNames().size);
		for (String a : assetManager.getAssetNames()) 
			Gdx.app.debug(TAG, "asset: " + a);
	}
	
	//Disposes of the assetManager
	@Override
	public void dispose () {
		assetManager.dispose();
	}
	
	//Error for when the asset cannot be loaded.
	@Override
	public void error (String filename, Class type, Throwable throwable) {
		Gdx.app.error(TAG, "Couldn't load asset '" + filename + "'", (Exception)throwable);
	}
	
	/*
	 *    ===== Assets Below =====
	 */
	
	//BunnyHead Asset
	public class AssetBunny {
		 public final AtlasRegion head;
		 public AssetBunny (TextureAtlas atlas) {
			 head = atlas.findRegion("bunny_head");
		 }
	}
}
