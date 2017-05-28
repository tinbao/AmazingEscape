package com.swen30006.driving.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.swen30006.driving.Simulation;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.backgroundFPS = 0;
		config.foregroundFPS = 0;
		config.width = 1920/2;
		config.height = 1080/2;
		new LwjglApplication(new Simulation(), config);
	}
}
