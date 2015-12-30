package com.dizydev.robotwars.map;

public enum MapCell {
	WALL, // bots can neither traverse nor see through walls
	OPEN, // bots can see, move, and shoot freely
	START_LOCATION, // treated the same as OPEN, but defines where bots start
	OUT_OF_BOUNDS, // intended for the edges of the map.  bots should never move here
	LAVA	// bots can see and shoot over, but not move over
}