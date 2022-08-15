package com.xrbpowered.looppattern.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import com.xrbpowered.looppattern.game.Dir;
import com.xrbpowered.looppattern.game.Tile;

public abstract class TileImage {

	public static final int size = 32;
	
	public static BufferedImage[] imagesCon;
	public static BufferedImage[] imagesDiscon;
	
	private static void drawPattern(Graphics2D g2, Tile tile, int degree) {
		if(degree==0)
			return;
		else if(degree==1) {
			g2.drawOval(size/2-8, size/2-8, 16, 16);
			if(tile.hasEdge(Dir.north))
				g2.drawLine(size/2, 0, size/2, size/2-8);
			else if(tile.hasEdge(Dir.south))
				g2.drawLine(size/2, size, size/2, size/2+8);
			else if(tile.hasEdge(Dir.west))
				g2.drawLine(0, size/2, size/2-8, size/2);
			else if(tile.hasEdge(Dir.east))
				g2.drawLine(size, size/2, size/2+8, size/2);
		}
		else if(degree==2 && tile.hasEdge(Dir.north) && tile.hasEdge(Dir.south))
			g2.drawLine(size/2, 0, size/2, size);
		else if(degree==2 && tile.hasEdge(Dir.east) && tile.hasEdge(Dir.west))
			g2.drawLine(0, size/2, size, size/2);
		else {
			if(tile.hasEdge(Dir.north) && tile.hasEdge(Dir.west))
				g2.drawOval(-size/2, -size/2, size, size);
			if(tile.hasEdge(Dir.north) && tile.hasEdge(Dir.east))
				g2.drawOval(size/2, -size/2, size, size);
			if(tile.hasEdge(Dir.south) && tile.hasEdge(Dir.west))
				g2.drawOval(-size/2, size/2, size, size);
			if(tile.hasEdge(Dir.south) && tile.hasEdge(Dir.east))
				g2.drawOval(size/2, size/2, size, size);
		}
	}

	public static BufferedImage[] createImages(boolean connected) {
		BufferedImage[] images = new BufferedImage[16];
		for(int m=0; m<16; m++) {
			BufferedImage img = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);

			Graphics2D g2 = img.createGraphics();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
			g2.setBackground(new Color(0, true));
			g2.clearRect(0, 0, size, size);

			Tile tile = new Tile(m);
			int degree = tile.degree();
			if(degree==1) {
				g2.setColor(Color.WHITE);
				g2.fillOval(size/2-8, size/2-8, 16, 16);
			}
			else if(degree==4) {
				g2.setColor(connected ? new Color(0x88aa66) : new Color(0xbb9966));
				g2.fillOval(size/2-8, size/2-8, 16, 16);
			}
			g2.setColor(connected ? new Color(0x88aa66) : new Color(0xbb9966));
			g2.setStroke(new BasicStroke(8));
			drawPattern(g2, tile, degree);
			g2.setColor(connected ? new Color(0xccddbb) : new Color(0xddddcc));
			g2.setStroke(new BasicStroke(4));
			drawPattern(g2, tile, degree);

			images[m] = img; 
		}
		return images;
	}

	public static void createImages() {
		imagesCon = createImages(true);
		imagesDiscon = createImages(false);
	}
	
}
