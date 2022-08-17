package com.xrbpowered.looppattern.ui;

import java.awt.Font;

import com.xrbpowered.zoomui.GraphAssist;
import com.xrbpowered.zoomui.UIContainer;
import com.xrbpowered.zoomui.UIElement;

public class BoxLabel extends UIElement {

	public static final int boxHeight = 32;

	public String label;
	public BoxStyle style;
	public Font font = Fonts.font;
	public int paddingX = 0;

	public BoxLabel(UIContainer parent, String label, BoxStyle style) {
		super(parent);
		this.label = label;
		this.style = style;
	}

	public String getLabel() {
		return label;
	}
	
	@Override
	public void paint(GraphAssist g) {
		paintBoxLabel(g, this, paddingX, getLabel(), font, style);
	}
	
	@Override
	public boolean onMouseDown(float x, float y, Button button, int mods) {
		return true;
	}

	public static void paintBoxLabel(GraphAssist g, UIElement e, int paddingX, String label, Font font, BoxStyle style) {
		if(style.borderColor!=null) {
			g.setColor(style.borderColor);
			g.setStroke(4f);
			g.graph.drawRoundRect(paddingX-1, (int)e.getHeight()/2-boxHeight/2-1,
					(int)e.getWidth()-paddingX*2+1, boxHeight+1, boxHeight, boxHeight);
		}
		if(style.boxColor!=null) {
			g.setColor(style.boxColor);
			g.graph.fillRoundRect(paddingX, (int)e.getHeight()/2-boxHeight/2,
					(int)e.getWidth()-paddingX*2, boxHeight, boxHeight, boxHeight);
		}
		if(label!=null && style.labelColor!=null) {
			g.setColor(style.labelColor);
			g.setFont(Fonts.font);
			g.drawString(label, e.getWidth()/2, e.getHeight()/2, GraphAssist.CENTER, GraphAssist.CENTER);
		}
	}
}
