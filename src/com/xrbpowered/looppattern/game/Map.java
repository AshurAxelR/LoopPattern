package com.xrbpowered.looppattern.game;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Map {

	public final GameSettings settings;
	public final int size;
	public final int total;
	
	public final Tile[][] map;
	public int connected;
	
	public Map(GameSettings settings) {
		this.settings = settings;
		this.size = settings.difficulty.size;
		this.total = size*size;
		this.map = new Tile[size][size];
	}
	
	public boolean isComplete() {
		return connected==total;
	}
	
	public boolean isConnected(int i, int j) {
		Tile tile = map[i][j];
		for(Dir d : Dir.values()) {
			int di = i+d.dx;
			int dj = j+d.dy;
			if(tile.hasEdge(d) && (!isInside(di, dj) || !map[di][dj].hasEdge(d.flip()))) {
				tile.connected = false;
				return false;
			}
		}
		tile.connected = true;
		return true;
	}

	public int countConnected() {
		int count = 0;
		for(int i=0; i<size; i++)
			for(int j=0; j<size; j++) {
				if(isConnected(i, j))
					count++;
			}
		connected = count;
		return count;
	}
	
	public Map generate() {
		Generator gen = new Generator(this);
		gen.generate(settings.symmetry);
		gen.scramble();
		countConnected();
		System.out.println("Generated new level.");
		return this;
	}

	public void save(String path) {
		try {
			DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(new File(path)), 4100));
			settings.save(out);
			for(int i=0; i<size; i++)
				for(int j=0; j<size; j++) {
					out.writeByte(map[i][j].mask);
				}
			out.close();
			System.out.println("Game state saved.");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Map load(String path) {
		try {
			DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(new File(path)), 4100));
			GameSettings settings = GameSettings.load(in);
			Map map = new Map(settings);
			for(int i=0; i<map.size; i++)
				for(int j=0; j<map.size; j++) {
					map.map[i][j] = new Tile(in.readByte()&15);
				}
			in.close();
			map.countConnected();
			System.out.println("Game state loaded.");
			return map;
		}
		catch (IOException e) {
			return new Map(GameSettings.defaultSettings).generate();
		}
	}
	
	public boolean isInside(int i, int j) {
		return i>=0 && j>=0 && i<size && j<size;
	}
	
}
