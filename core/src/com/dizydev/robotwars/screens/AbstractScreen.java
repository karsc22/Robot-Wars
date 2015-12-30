package com.dizydev.robotwars.screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.dizydev.robotwars.RobotWars;

// Abstract screen class
public abstract class AbstractScreen implements Screen {

	// Game reference
	protected com.dizydev.robotwars.RobotWars game;
	// Are we currently paused?
	protected boolean paused = false;
	
	protected Stage stage;
	
	// Main constructor
	public AbstractScreen (RobotWars game) {
		// Initialize the game reference
		this.game = game;

		stage = new Stage();
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
	public void load() {}

	// Screen initialize method
	public void init() {}

	// Update logic
	public void update (float delta) {
		// Act the stage
		stage.act(delta);
	}

	// Draw logic
	public void draw(float delta) {
		// Clear the screen
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Draw the stage
		stage.draw();
	}

	// Screen resized
	public void resize (int width, int height) {
		// Update the stage viewport
		stage.getViewport().update(width, height, true);
	}

	// Screen dispose method
	public void dispose () {}
}
