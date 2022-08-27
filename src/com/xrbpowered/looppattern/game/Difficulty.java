package com.xrbpowered.looppattern.game;

public enum Difficulty {

	beginner("BEGINNER", 8),
	skilled("SKILLED", 16),
	adept("ADEPT", 32),
	masterful("MASTERFUL", 64),
	insane("INSANE", 128);
	
	public final String title;
	public final int size;
	
	private Difficulty(String title, int size) {
		this.title = title;
		this.size = size;
	}
	
	public Difficulty next() {
		return values()[(ordinal()+1)%values().length];
	}
	
	public static Difficulty forSize(int size) {
		for(Difficulty diff : values()) {
			if(diff.size==size)
				return diff;
		}
		return null;
	}
	
}
