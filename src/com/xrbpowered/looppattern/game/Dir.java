package com.xrbpowered.looppattern.game;

public enum Dir {

	north(0, -1),
	east(1, 0),
	south(0, 1),
	west(-1, 0);

	public final int dx, dy;
	
	private Dir(int dx, int dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	public int mask() {
		return 1<<ordinal();
	}
	
	public Dir cw() {
		return values()[(ordinal()+1)%4];
	}

	public Dir flip() {
		return values()[(ordinal()+2)%4];
	}

	public Dir ccw() {
		return values()[(ordinal()+3)%4];
	}

}