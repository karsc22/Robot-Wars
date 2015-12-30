package com.dizydev.robotwars.screens;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.dizydev.robotwars.RobotWars;

// Start screen class
public class StartScreen extends AbstractScreen {

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
	}

	// Initialize objects
	public void init () {
		// Initialize the interface skin
		skin = game.manager.get("uiskin.json", Skin.class);
		// Initialize the stage
		stage = new Stage();
		// Initialize the table
		table = new Table();
		table.setFillParent(true);
		// Add the table to the stage
		stage.addActor(table);
		// Set the screen input processor
		Gdx.input.setInputProcessor(stage);
		// Initialize the main menu
		init_mainmenu();
	}

	// Initialize the main menu
	public void init_mainmenu () {
		// Add the game logo
		table.add(new Image(game.manager.get("logo.png", Texture.class))).padBottom(10);
		// Add a row to the table
		table.row();

		// New game button
		final TextButton newgame = new TextButton("New Game", skin);
		newgame.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y){
				game.setScreen("gameScreen");
			}
		});
		table.add(newgame).width(400).height(50).padBottom(10);
		table.row();

		// Settings button
		final TextButton settings = new TextButton("Settings", skin);
		settings.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y){
				// Clear the table
				table.clear();
				// Initialize the settings menu
				init_settingsmenu();
			}
		});
		table.add(settings).width(400).height(50).padBottom(10);
		table.row();

		// Credit button
		final TextButton credit = new TextButton("Credit", skin);
		credit.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y){
				// TODO - Text button on click event
			}
		});
		table.add(credit).width(400).height(50).padBottom(10);
		table.row();

		// Exit button
		final TextButton exit = new TextButton("Exit", skin);
		exit.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y){
				// Exit the game
				Gdx.app.exit();
			}
		});
		table.add(exit).width(400).height(50).padBottom(10);
	}

	// Initialize the settings menu
	public void init_settingsmenu () {
		// Add the game logo
		table.add(new Image(game.manager.get("logo.png", Texture.class))).padBottom(10);
		// Add a row to the table
		table.row();

		// Array of display modes sorted in quality order
		ArrayList<DisplayMode> modes = game.sortedDisplayModes();
		// Select box object
		final SelectBox<DisplayMode> modes_box = new SelectBox<DisplayMode>(skin);
		// Set the modes box items
		DisplayMode[] modes_arr = new DisplayMode[modes.size()];
		modes_arr = modes.toArray(modes_arr);
		modes_box.setItems(modes_arr);
		// Set the selected display mode
		for (DisplayMode mode: modes_arr) {
			if (mode.width == Gdx.graphics.getWidth() 
					&& mode.height == Gdx.graphics.getHeight()) {
				modes_box.setSelected(mode);
				break;
			}
		}
		// Add the select box to the table
		table.add(modes_box).width(400).height(50).padBottom(10);
		// Add a row to the table
		table.row();

		// Full screen check box
		final CheckBox fullscreen = new CheckBox("Full Screen", skin);
		// Set current full screen state
		fullscreen.setChecked(Gdx.graphics.isFullscreen());
		// Add the check box to the stage
		table.add(fullscreen).width(400).height(50).padBottom(10);
		// Add a row to the table
		table.row();

		// Save button
		final TextButton save = new TextButton("Save", skin);
		save.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y){
				// Set the desired display mode
				Gdx.graphics.setDisplayMode(modes_box.getSelected().width, modes_box.getSelected().height, fullscreen.isChecked());
				// Set the display preferences
				game.prefs.putInteger("display-width", modes_box.getSelected().width);
				game.prefs.putInteger("display-height", modes_box.getSelected().height);
				game.prefs.putBoolean("display-fullscreen", fullscreen.isChecked());
				game.prefs.flush();
			}
		});
		table.add(save).width(400).height(50).padBottom(10);
		// Add a row to the table
		table.row();

		// Back button
		final TextButton back = new TextButton("Back", skin);
		back.addListener(new ClickListener(){
			public void clicked(InputEvent event, float x, float y){
				// Clear the table
				table.clear();
				// Initialize the main menu
				init_mainmenu();
			}
		});
		table.add(back).width(400).height(50).padBottom(10);
	}
}
