package com.dizydev.robotwars;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics.DisplayMode;
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

	// Initialization method
	public void create () {
		// Set the best display mode
		setBestDisplayMode();
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
	
	// Set the best display mode
	public void setBestDisplayMode () {
		// Array of unsorted display modes
		DisplayMode[] modes = Gdx.graphics.getDisplayModes();
		// Sort display modes by graphics and refresh rate
		Arrays.sort(modes, new Comparator<DisplayMode>() {
		    public int compare(DisplayMode a, DisplayMode b) {
		    	int aP = a.width * a.height;
		    	int bP = b.width * b.height;
		    	if (aP > bP) {
		    		return -1;
		    	}
		    	else if (aP < bP) {
		    		return 1;
		    	}
		    	else {
		    		if (a.refreshRate > b.refreshRate) {
			    		return -1;
			    	}
			    	else if (a.refreshRate < b.refreshRate) {
			    		return 1;
			    	}
		    	}
		    	return 0;
		    }
		});
		// Set display mode with best graphics and refresh rate
		Gdx.graphics.setDisplayMode(modes[0]);
	}
}
