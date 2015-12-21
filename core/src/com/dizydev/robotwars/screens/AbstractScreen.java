package com.dizydev.robotwars.screens;
import com.badlogic.gdx.Screen;
import com.dizydev.robotwars.RobotWars;

// Abstract screen class
public abstract class AbstractScreen implements Screen {

	// Game reference
	protected com.dizydev.robotwars.RobotWars game;
	// Are we currently paused?
	protected boolean paused = false;
	
	// Main constructor
	public AbstractScreen (RobotWars game) {
		// Initialize the game reference
		this.game = game;
	}

	// On screen show
	public void show () {
		// Set paused to false
		paused = false;
		// If we are the load screen
		if (this instanceof LoadScreen) {
			// Call initial load method
			load();
		}
		// Initialize this screen
		init();
	}

	// On screen pause
	public void pause () {
		// Set paused to true
		paused = true;
	}

	// On screen resume
	public void resume () {
		// Set paused to false
		paused = false;
	}

	// On screen hide
	public void hide () {
		// Set paused to true
		paused = true;
	}

	// Screen render method
	public void render (float delta) {
		// If we are not paused
		if (!paused) {
			// Update this screen
			update(delta);
			// Draw this screen
			draw(delta);
		}
	}

	// Screen load assets method
	public abstract void load ();

	// Screen initialize method
	public abstract void init ();

	// Screen update logic method
	public abstract void update (float delta);

	// Screen draw method
	public abstract void draw (float delta);

	// Screen resize method
	public abstract void resize (int width, int height);

	// Screen dispose method
	public abstract void dispose ();
}
