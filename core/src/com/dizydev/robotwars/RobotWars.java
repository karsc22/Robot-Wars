package com.dizydev.robotwars;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.dizydev.robotwars.screens.AbstractScreen;
import com.dizydev.robotwars.screens.LoadScreen;
import com.dizydev.robotwars.screens.StartScreen;

// Main game class
public class RobotWars extends Game {

	// Asset manager reference
	public AssetManager manager;

	// Dictionary of screens
	public Map<String, AbstractScreen> screens;
	
	// User preferences
	public Preferences prefs;

	// Initialization method
	public void create () {
		// Initialize the preferences
		prefs = Gdx.app.getPreferences("robotwarspreferences");
		// If the display preferences are not set
		if (!prefs.contains("display-width") && !prefs.contains("display-height")
				&& !prefs.contains("display-fullscreen")) {
			// Set the best display mode
			Gdx.graphics.setDisplayMode(sortedDisplayModes().get(0));
			// Set the display preferences
			prefs.putInteger("display-width", Gdx.graphics.getDesktopDisplayMode().width);
			prefs.putInteger("display-height", Gdx.graphics.getDesktopDisplayMode().height);
			prefs.putBoolean("display-fullscreen", true);
			prefs.flush();
		}
		// If the display preferences are set
		else {
			// Set the display mode
			Gdx.graphics.setDisplayMode(prefs.getInteger("display-width"), 
					prefs.getInteger("display-height"), prefs.getBoolean("display-fullscreen"));
		}
		
		// Initialize the asset manager
		manager = new AssetManager();
		// Initialize the dictionary of screens
		screens = new HashMap<String, AbstractScreen>();
		screens.put("loadScreen", new LoadScreen(this));
		screens.put("startScreen", new StartScreen(this));
		// Set initial screen
		setScreen("loadScreen");
	}

	// On render call
	public void render () {
		// Render the current screen
		getScreen().render(Gdx.graphics.getDeltaTime());
	}

	// On resize call
	public void resize (int width, int height) {
		// Resize the current screen
		getScreen().resize(width, height);
	}

	// On dispose call
	public void dispose () {
		// TODO - Dispose all of the screens
	}

	// On pause call
	public void pause () {
		// Pause the current screen
		getScreen().pause();
	}

	// On resume call
	public void resume () {
		// Resume the current screen
		getScreen().resume();
	}

	// Method to set the current screen
	public void setScreen (String screen) {
		// Set the current screen
		setScreen(screens.get(screen));
	}

	// Get the display modes sorted in best quality
	public ArrayList<DisplayMode> sortedDisplayModes () {
		// Array of unsorted display modes
		DisplayMode[] modes = Gdx.graphics.getDisplayModes();
		// Array list of unique display modes sorted by quality
		ArrayList<DisplayMode> sorted = new ArrayList<DisplayMode>();
		// Sort display modes by graphics and refresh rate
		Arrays.sort(modes, new Comparator<DisplayMode>() {
			// Compare method
			public int compare(DisplayMode a, DisplayMode b) {
				// Screen a size > screen b size
				if (a.width * a.height > b.width * b.height)
					// Favor screen a
					return -1;
				// Screen a size < screen b size
				else if (a.width * a.height < b.width * b.height)
					// Favor screen b
					return 1;
				// Screen a size == screen b size
				else {
					// Screen a hz > screen b hz
					if (a.refreshRate > b.refreshRate)
						// Favor screen a
						return -1;
					// Screen a hz < screen b hz
					else if (a.refreshRate < b.refreshRate)
						// Favor screen b
						return 1;
				}
				// Favor no screen
				return 0;
			}
		});
		// Loop through each display mode
		for (int i = 0; i < modes.length; i++) {
			// Display mode size contained flag
			boolean contained = false;
			// Loop through each added display mode
			for (DisplayMode mode: sorted) {
				// If the display mode size is already container
				if (mode.width == modes[i].width && mode.height == modes[i].height) {
					// Set the contained flag to true
					contained = true;
					// Break
					break;
				}
			}
			// If the display mode size is not already contained
			if (!contained)
				// Add the display mode
				sorted.add(modes[i]);
		}
		// Return the display modes
		return sorted;
	}
}
