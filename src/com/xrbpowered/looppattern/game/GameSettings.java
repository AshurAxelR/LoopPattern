package com.xrbpowered.looppattern.game;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class GameSettings {

	public static final GameSettings defaultSettings = new GameSettings(Difficulty.beginner, true);
	
	public final Difficulty difficulty;
	public final boolean symmetry;

	public GameSettings(Difficulty difficulty, boolean symmetry) {
		this.difficulty = difficulty;
		this.symmetry = symmetry;
	}
	
	public void save(DataOutputStream out) throws IOException {
		int size = difficulty.size & 0xffff;
		if(symmetry)
			size |= 0x10000;
		out.writeInt(size);
	}
	
	public static GameSettings load(DataInputStream in) throws IOException {
		int size = in.readInt();
		Difficulty diff = Difficulty.forSize(size & 0xffff);
		if(diff==null)
			throw new IOException();
		boolean sym = (size & 0x10000)!=0;
		return new GameSettings(diff, sym);
	}
	
}
