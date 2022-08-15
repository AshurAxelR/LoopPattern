package com.xrbpowered.looppattern.game;

public class Tile {

	public int mask;
	public boolean connected;
	
	public Tile(int m) {
		this.mask = m;
	}
	
	public Tile() {
		this(0);
	}
	
	public boolean hasEdge(Dir d) {
		return (mask&d.mask())!=0;
	}
	
	public void addEdge(Dir d) {
		mask |= 1<<d.ordinal();
	}
	
	public int degree() {
		int c = 0;
		for(int d=1; d<=8; d<<=1)
			if((mask&d)!=0) c++;
		return c;
	}

	public void rotate(int n) {
		mask = ((mask<<n)&15) | ((mask>>(4-n))&15);
	}

	public void cw() {
		rotate(1);
	}

	public void ccw() {
		rotate(3);
	}

}
