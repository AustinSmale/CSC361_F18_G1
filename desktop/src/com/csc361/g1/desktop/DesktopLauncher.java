package com.csc361.g1.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;
import com.csc361.g1.CanyonBunnyMain;

/**
 * Runner class
 * 
 * @author Austin Smale
 *
 */

public class DesktopLauncher {
	private static boolean rebuildAtlas = false;
	private static boolean drawDebugOutline = false;

	public static void main(String[] arg) {
		// if you need to rebuild the atlas settings
		if (rebuildAtlas) {
			Settings settings = new Settings();
			settings.maxWidth = 1024;
			settings.maxHeight = 1024;
			settings.debug = drawDebugOutline;
			// process the texture and add it to a pack
			TexturePacker.process(settings, "assets-raw/images", "../core/assets/images", "canyonbunny.atlas");
			TexturePacker.process(settings, "assets-raw/images-ui",
					"../core/assets/images",
					"canyonbunny-ui.pack");
		}
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "CanyonBunny";
		cfg.width = 800;
		cfg.height = 480;
		new LwjglApplication(new CanyonBunnyMain(), cfg);
	}
}
