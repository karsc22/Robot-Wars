package com.dizydev.robotwars.map;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;



public class MapRenderer {

	// temporary shape renderer until we have map tiles
	static ShapeRenderer sr = new ShapeRenderer();
	static final int size = 48;
	public static void render(Map map, Batch batch) {
		sr.setProjectionMatrix(batch.getProjectionMatrix());
		sr.setTransformMatrix(batch.getTransformMatrix());
		sr.begin(ShapeType.Filled);
		Color color = Color.BLACK;
		for (int x = 0; x < map.width; x++) {
			for (int y = 0; y < map.width; y++) {
				MapCell cell = map.get(x, y);
				if (cell == null || cell == MapCell.OUT_OF_BOUNDS) {
					color = Color.BLACK;
				} else if (cell == MapCell.LAVA) {
					color = Color.RED;
				} else if (cell == MapCell.WALL) {
					color = Color.GRAY;
				} else if (cell == MapCell.START_LOCATION) {
					color = Color.GREEN;
				} else if (cell == MapCell.OPEN) {
					color = Color.WHITE;
				}
				sr.setColor(color);
				sr.rect(x*size, y*size, size, size);
			}
		}
		sr.end();

	}
}
