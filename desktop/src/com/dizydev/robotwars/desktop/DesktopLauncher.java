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
		// Start application in full screen
		config.fullscreen = true;
		// Launch the application
		new LwjglApplication(new RobotWars(), config);
	}
}
