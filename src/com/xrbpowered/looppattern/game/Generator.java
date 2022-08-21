package com.xrbpowered.looppattern.game;

import java.util.Random;

public class Generator {

	public static final int minBlock = 8;
	
	public final Random random = new Random();
	public final Tile[][] map;
	public final int size;
	
	public Generator(Map map) {
		this.map = map.map;
		this.size = map.size;
	}

	
	public void addEdge(int i, int j, Dir d) {
		map[i][j].addEdge(d);
		map[i+d.dx][j+d.dy].addEdge(d.flip());
	}
	
	public void clear() {
		for(int i=0; i<size; i++)
			for(int j=0; j<size; j++) {
				map[i][j] = new Tile(0);
			}
	}
	
	public void scramble() {
		for(int i=0; i<size; i++)
			for(int j=0; j<size; j++) {
				map[i][j].rotate(random.nextInt(4));
			}
	}
	
	public void generateBase(int x0, int y0, int s) {
		for(int i=0; i<s; i++) {
			int x = x0+i;
			for(int j=0; j<s; j++) {
				int y = y0+j;
				if(i>0) {
					int degree = map[x-1][y].degree();
					if(random.nextInt(100)<85-degree*15)
						addEdge(x, y, Dir.west);
				}
				if(j>0) {
					int degree = map[x][y-1].degree();
					if(random.nextInt(100)<75-degree*15)
						addEdge(x, y, Dir.north);
				}
			}
		}
	}

	private void copy(int x0, int y0, int x1, int y1, int sx, int sy) {
		for(int i=0; i<sx; i++) {
			int xs = x0+i;
			int xd = x1+i;
			for(int j=0; j<sy; j++) {
				int ys = y0+j;
				int yd = y1+j;
				map[xd][yd].mask = map[xs][ys].mask;
			}
		}
	}

	private void mirrorXRight(int x0, int y0, int sx, int sy) {
		for(int i=0; i<sx; i++) {
			int xs = x0+i;
			int xd = x0+sx*2-1-i;
			for(int j=0; j<sy; j++) {
				int y = y0+j;
				map[xd][y].mask = Tile.mirrorX(map[xs][y].mask);
			}
		}
	}

	private void mirrorYDown(int x0, int y0, int sx, int sy) {
		for(int i=0; i<sx; i++) {
			int x = x0+i;
			for(int j=0; j<sy; j++) {
				int ys = y0+j;
				int yd = y0+sy*2-1-j;
				map[x][yd].mask = Tile.mirrorY(map[x][ys].mask);
			}
		}
	}

	private void mirrorXY(int x0, int y0, int x1, int y1, int sx, int sy) {
		for(int i=0; i<sx; i++) {
			int xs = x0+i;
			int xd = x1+sx-1-i;
			for(int j=0; j<sy; j++) {
				int ys = y0+j;
				int yd = y1+sy-1-j;
				map[xd][yd].mask = Tile.mirrorXY(map[xs][ys].mask);
			}
		}
	}

	private void joinH(int x0, int y, int s) {
		for(int i=0; i<s; i++) {
			int x = x0+i;
			if(random.nextInt(100)<40)
				addEdge(x, y, Dir.north);
		}
	}

	private void joinHSym(int x0, int y, int s) {
		for(int i=0; i<s; i++) {
			int x1 = x0+i;
			int x2 = x0+s-1-i;
			if(random.nextInt(100)<40) {
				addEdge(x1, y, Dir.north);
				addEdge(x2, y, Dir.north);
			}
		}
	}

	private void joinV(int x, int y0, int s) {
		for(int j=0; j<s; j++) {
			int y = y0+j;
			if(random.nextInt(100)<40)
				addEdge(x, y, Dir.west);
		}
	}

	private void joinVSym(int x, int y0, int s) {
		for(int j=0; j<s/2; j++) {
			int y1 = y0+j;
			int y2 = y0+s-1-j;
			if(random.nextInt(100)<40) {
				addEdge(x, y1, Dir.west);
				addEdge(x, y2, Dir.west);
			}
		}
	}

	private void generatePairH(int x0, int y0, int hs) {
		generateBlock(x0, y0, hs);
		int mirror = random.nextInt(3);
		switch(mirror) {
			case 1:
				copy(x0, y0, x0+hs, y0, hs, hs);
				joinV(x0+hs, y0, hs);
				break;
			case 2:
				mirrorXRight(x0, y0, hs, hs);
				joinV(x0+hs, y0, hs);
				break;
			case 3:
				mirrorXY(x0, y0, x0+hs, y0, hs, hs);
				joinVSym(x0+hs, y0, hs);
				break;
			default:
				generateBlock(x0+hs, y0, hs);
				joinV(x0+hs, y0, hs);
				break;
		}
	}

	private void generatePairV(int x0, int y0, int hs) {
		generateBlock(x0, y0, hs);
		int mirror = random.nextInt(3);
		switch(mirror) {
			case 1:
				copy(x0, y0, x0, y0+hs, hs, hs);
				joinH(x0, y0+hs, hs);
				break;
			case 2:
				mirrorYDown(x0, y0, hs, hs);
				joinH(x0, y0+hs, hs);
				break;
			case 3:
				mirrorXY(x0, y0, x0, y0+hs, hs, hs);
				joinHSym(x0, y0+hs, hs);
				break;
			default:
				generateBlock(x0, y0+hs, hs);
				joinH(x0, y0+hs, hs);
				break;
		}
	}
	
	private void generateBlock(int x0, int y0, int s, boolean top) {
		if(s<=minBlock)
			generateBase(x0, y0, s);
		else if(!top && random.nextBoolean()) {
			int hs = s/2;
			generatePairH(x0, y0, hs);
			int mirror = random.nextInt(3);
			switch(mirror) {
				case 1:
					copy(x0, y0, x0, y0+hs, s, hs);
					joinH(x0, y0+hs, s);
					break;
				case 2:
					mirrorYDown(x0, y0, s, hs);
					joinH(x0, y0+hs, s);
					break;
				case 3:
					mirrorXY(x0, y0, x0, y0+hs, s, hs);
					joinHSym(x0, y0+hs, s);
					break;
				default:
					generatePairH(x0, y0+hs, hs);
					joinH(x0, y0+hs, s);
					break;
			}
		}
		else {
			int hs = s/2;
			generatePairV(x0, y0, hs);
			int mirror = random.nextInt(3);
			if(top) mirror = 2;
			switch(mirror) {
				case 1:
					copy(x0, y0, x0+hs, y0, hs, s);
					joinV(x0+hs, y0, s);
					break;
				case 2:
					mirrorXRight(x0, y0, hs, s);
					joinV(x0+hs, y0, s);
					break;
				case 3:
					mirrorXY(x0, y0, x0+hs, y0, hs, s);
					joinVSym(x0+hs, y0, s);
					break;
				default:
					generatePairV(x0+hs, y0, hs);
					joinV(x0+hs, y0, s);
					break;
			}
		}
	}
	
	private void generateBlock(int x0, int y0, int s) {
		generateBlock(x0, y0, s, false);
	}
	
	public void generate() {
		clear();
		generateBlock(0, 0, size, true);
	}

}
