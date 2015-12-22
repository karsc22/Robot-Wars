package com.dizydev.robotwars.screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.dizydev.robotwars.RobotWars;

// Start screen class
public class StartScreen extends AbstractScreen {

	private Stage stage;
	private Table table;
	private Skin skin;

	// Main constructor
	public StartScreen(RobotWars game) {
		// Call to super constructor
		super(game);
	}

	// Load screen assets
	public void load () {
		game.manager.load("uiskin.json", Skin.class);
//		game.manager.load("uiskin.atlas", TextureAtlas.class);
	}

	// Initialize objects
	public void init () {
		// Initialize the interface skin
		skin = game.manager.get("uiskin.json", Skin.class);
		// Initialize the stage
		stage = new Stage();
		// Initialize the table
		table = new Table();
//		// TODO - Table debugging
//		table.setDebug(true);
		table.setFillParent(true);
		// Add the table to the stage
		stage.addActor(table);
		
		final TextButton newgame = new TextButton("New Game", skin);
		newgame.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y){
            	// TODO - Text button on click event
            }
        });
		table.add(newgame).width(400).height(50).padBottom(10);
		table.row();
		
		final TextButton settings = new TextButton("Settings", skin);
		settings.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y){
            	// TODO - Text button on click event
            }
        });
		table.add(settings).width(400).height(50).padBottom(10);
		table.row();
		
		final TextButton credit = new TextButton("Credit", skin);
		credit.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y){
            	// TODO - Text button on click event
            }
        });
		table.add(credit).width(400).height(50).padBottom(10);
		table.row();
		
		final TextButton exit = new TextButton("Exit", skin);
		exit.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y){
            	// Exit the game
            	Gdx.app.exit();
            }
        });
		table.add(exit).width(400).height(50).padBottom(10);
		
		// Set the screen input processor
		Gdx.input.setInputProcessor(stage);
	}

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

	// Dispose screen assets
	public void dispose () {
		// TODO - Dispose of assets
	}
}
