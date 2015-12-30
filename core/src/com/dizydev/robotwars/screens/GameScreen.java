package com.dizydev.robotwars.screens;

import com.dizydev.robotwars.RobotWars;
import com.dizydev.robotwars.map.Map;
import com.dizydev.robotwars.map.MapRenderer;

public class GameScreen extends AbstractScreen{

	Map map;
	public GameScreen(RobotWars game) {
		super(game);
		map = new Map();
	}

	@Override
	public void draw(float delta) {
		super.draw(delta);
		MapRenderer.render(map, stage.getBatch());
	}


}
