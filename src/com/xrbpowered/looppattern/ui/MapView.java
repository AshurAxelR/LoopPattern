package com.xrbpowered.looppattern.ui;

import static com.xrbpowered.looppattern.LoopPattern.map;

import java.awt.image.BufferedImage;

import com.xrbpowered.looppattern.LoopPattern;
import com.xrbpowered.looppattern.game.Tile;
import com.xrbpowered.looppattern.ui.res.BoxStyle;
import com.xrbpowered.zoomui.GraphAssist;
import com.xrbpowered.zoomui.UIContainer;
import com.xrbpowered.zoomui.UIElement;
import com.xrbpowered.zoomui.base.UIPanView;

public class MapView extends UIElement {

	private static boolean firstLayout = true;
	
	public MapView(UIContainer parent) {
		super(new UIPanView(parent) {
			@Override
			protected void paintSelf(GraphAssist g) {
				g.fill(this, BoxStyle.bgColor);
			}
			@Override
			public void layout() {
				if(firstLayout)
					LoopPattern.mapView.center();
				firstLayout = false;
				super.layout();
			}
		});
		setSize(map.size*TileImage.size, map.size*TileImage.size);
		
		TileImage.createImages();
	}
	
	private void paintMap(GraphAssist g)  {
		for(int i=0; i<map.size; i++)
			for(int j=0; j<map.size; j++) {
				Tile tile = map.map[i][j];
				BufferedImage[] images = tile.connected ? TileImage.imagesCon : TileImage.imagesDiscon;
				g.graph.drawImage(images[tile.mask], TileImage.size*i, TileImage.size*j, null);
			}
	}
	
	@Override
	public void paint(GraphAssist g) {
		paintMap(g);
	}

	@Override
	public boolean onMouseDown(float x, float y, Button button, int mods) {
		if(button==Button.left) {
			if(!map.isComplete()) {
				int i = (int)(x/TileImage.size);
				int j = (int)(y/TileImage.size);
				map.map[i][j].cw();
				map.countConnected();
				LoopPattern.updateUI();
			}
			return true;
		}
		else
			return super.onMouseDown(x, y, button, mods);
	}

	public void center() {
		setSize(map.size*TileImage.size, map.size*TileImage.size);
		UIPanView root = (UIPanView) getParent();
		root.setPan(-root.getWidth()/2+getWidth()/2, -root.getHeight()/2+getHeight()/2);
	}

}
