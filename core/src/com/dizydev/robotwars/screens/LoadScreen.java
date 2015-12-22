package com.dizydev.robotwars.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.dizydev.robotwars.Constants;
import com.dizydev.robotwars.RobotWars;

// Load screen class
public class LoadScreen extends AbstractScreen {

	// Rendering objects
	private SpriteBatch batch;
	private Texture logo;
	private TextureRegion gear;
	private float gearRotation = 0;
	private OrthographicCamera camera;
	private Viewport viewport;

	// Main constructor
	public LoadScreen(RobotWars game) {
		// Call to super constructor
		super(game);
	}

	// Load screen assets
	public void load () {
		// Load all screen assets
		game.manager.load("logo.png", Texture.class);
		game.manager.load("gear.png", Texture.class);
		// Force assets to load
		game.manager.finishLoading();
	}

	// Initialize objects
	public void init () {
		// Initialize rendering objects
		batch = new SpriteBatch();
		logo = game.manager.get("logo.png", Texture.class);
		gear = new TextureRegion(game.manager.get("gear.png", Texture.class));
		camera = new OrthographicCamera();
		viewport = new FitViewport(Constants.VIRTUAL_WIDTH, Constants.VIRTUAL_HEIGHT, camera);
		camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
		// Load all other screens
		for (AbstractScreen screen: game.screens.values()) {
			// If that screen is not this screen
			if (screen != this) {
				// Load that screen
				screen.load();
			}
		}
	}

	// Update logic
	public void update (float delta) {
		// Update the camera
		camera.update();
		// Load and check for finished loading
		if (game.manager.update()) {
			// Switch to the start screen
			game.setScreen("startScreen");
		}
	}

	// Draw logic
	public void draw(float delta) {
		// Clear the screen
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// Draw textures
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
		// Draw robot wars logo in middle of screen
		batch.draw(logo, camera.viewportWidth / 2 - logo.getWidth() / 2, 
				camera.viewportHeight / 2 - logo.getHeight() / 2);
		
		// Draw rotating gear loading icon
		gearRotation -= 16 * Math.PI * delta;
		int yPos = (int) (camera.viewportHeight - logo.getHeight()) / 4;
		int gearW = gear.getRegionWidth();
		int gearH = gear.getRegionHeight();
		batch.draw(gear, camera.viewportWidth / 2 - gearW / 2, 
				yPos - gearH / 2, gearW / 2, gearH / 2, 
				gearW, gearH, 1, 1, gearRotation);
	
		batch.end();
	}

	// Screen resized
	public void resize (int width, int height) {
		// Update viewport and center camera
		viewport.update(width, height, true);
	}

	// Dispose screen assets
	public void dispose () {
		// TODO - Dispose of assets
	}
}
