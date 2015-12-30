package com.dizydev.robotwars.map;

public class MapConfig {
	
	protected int numBots;

	protected int width;
	protected int height;
	
	protected boolean symmetricX;
	protected boolean symmetricY;
	
	protected float wallDensity;
	protected float lavaDensity;
	
	protected long seed;
	
	public MapConfig() {
		// set some default values
		numBots = 2;

		width = 40;
		height = 20;

		symmetricX = true;
		symmetricY = true;
		
		wallDensity = 0.1f;
		lavaDensity = 0.05f;
		
		seed = System.currentTimeMillis();
	}
	
}
