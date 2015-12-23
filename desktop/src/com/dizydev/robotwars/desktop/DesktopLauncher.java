package com.dizydev.robotwars.desktop;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.dizydev.robotwars.RobotWars;

// Desktop launcher class
public class DesktopLauncher {
	// Main method
	public static void main (String[] arg) {
		// Libgdx configuration parameters
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		// Set the title
		config.title = "Robot Wars";
		// Foreground FPS
		config.foregroundFPS = 60;
		// Background FPS
		config.backgroundFPS = 60;
		// Window not resizable
		config.resizable = false;
		config.vSyncEnabled = true;
		// Launch the application
		new LwjglApplication(new RobotWars(), config);
	}
}
