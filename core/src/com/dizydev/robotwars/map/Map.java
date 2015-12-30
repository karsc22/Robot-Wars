package com.dizydev.robotwars.map;

import java.util.Random;

public class Map {
	
	protected MapConfig config;
	protected int width;
	protected int height;
	
	private MapCell[] map;
	
	public Map() {
		this(new MapConfig());
	}

	public Map(MapConfig config) {
		this.config = config;
		this.width = config.width;
		this.height = config.height;
		map = new MapCell[config.width * config.height];
		createMap();
	}
	
	public void createMap() {
		
		Random random = new Random(config.seed);
		int maxX = width;
		int maxY = height;
		if (config.symmetricX) {
			maxX = width/2;
		}
		if (config.symmetricY) {
			maxY = height/2;
		}
		for (int x = 0; x < maxX; x++) {
			for (int y = 0; y < maxY; y++) {
				float val = random.nextFloat(); 

				// TODO: densities will only be approximate, but close enough for now
				if (val < config.lavaDensity) {
					setSymmetric(x, y, MapCell.LAVA);
				} else if (val < config.lavaDensity + config.wallDensity) {
					setSymmetric(x, y, MapCell.WALL);
				} else {
					setSymmetric(x, y, MapCell.OPEN);
				}
			}
		}
		
		// TODO: check config.numBots
		set(width/10, height/2, MapCell.START_LOCATION);
		set(width - width/10, height/2, MapCell.START_LOCATION);
	}
	
	private void setSymmetric(int x, int y, MapCell cell) {
		set(x, y, cell);
		if (config.symmetricX) {
			set(width - x - 1, y, cell);
		}
		if (config.symmetricY) {
			set(x, height - y -1, cell);
		}
		if (config.symmetricX && config.symmetricY) {
			set(width - x - 1, height - y - 1, cell);
		}
	}
	
	public MapCell get(int x, int y) {
		if (x >= 0 && x < width && y >=0 && y < height) {
			return map[x + y*width];
		}
		return null;
	}
	
	public void set(int x, int y, MapCell cell) {
		if (x >= 0 && x < width && y >=0 && y < height) {
			map[x + y*width] = cell;
		} else {
			throw new IllegalArgumentException("(" + x + ", " + y + ") is out of bounds");
		}
	}
	
}