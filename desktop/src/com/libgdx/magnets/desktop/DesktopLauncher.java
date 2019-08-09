package com.libgdx.magnets.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.libgdx.magnets.Constants;
import com.libgdx.magnets.MagnetsGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Constants.GAME_WIDTH;
		config.height = Constants.GAME_HEIGHT;
		new LwjglApplication(new MagnetsGame(), config);
	}
}
