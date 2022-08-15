package com.xrbpowered.looppattern.game;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class Map {

	public int size;
	public int total;
	
	public Tile[][] map;
	public int connected;
	
	public Map(int size) {
		this.size = size;
		this.total = size*size;
		map = new Tile[size][size];
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
	
	public void addEdge(int i, int j, Dir d) {
		map[i][j].addEdge(d);
		map[i+d.dx][j+d.dy].addEdge(d.flip());
	}
	
	public Map generate() {
		Random random = new Random();
		for(int i=0; i<size; i++)
			for(int j=0; j<size; j++) {
				map[i][j] = new Tile(0);
				if(i>0) {
					int degree = map[i-1][j].degree();
					if(random.nextInt(100)<85-degree*15)
						addEdge(i, j, Dir.west);
				}
				if(j>0) {
					int degree = map[i][j-1].degree();
					if(random.nextInt(100)<75-degree*15)
						addEdge(i, j, Dir.north);
				}
			}
		
		for(int i=0; i<size; i++)
			for(int j=0; j<size; j++) {
				map[i][j].rotate(random.nextInt(4));
			}
		
		countConnected();
		System.out.println("Generated new level.");
		return this;
	}

	public void save(String path) {
		try {
			DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(new File(path)), 4100));
			out.writeInt(size);
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
			int size = in.readInt(); 
			if(size>256)
				throw new IOException();
			Map map = new Map(size);
			for(int i=0; i<size; i++)
				for(int j=0; j<size; j++) {
					map.map[i][j] = new Tile(in.readByte()&15);
				}
			in.close();
			map.countConnected();
			System.out.println("Game state loaded.");
			return map;
		}
		catch (IOException e) {
			return new Map(32).generate();
		}
	}
	
	public boolean isInside(int i, int j) {
		return i>=0 && j>=0 && i<size && j<size;
	}
	
}
