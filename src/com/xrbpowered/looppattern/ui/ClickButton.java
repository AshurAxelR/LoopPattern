package com.xrbpowered.looppattern.ui;

import java.awt.Color;
import java.awt.Font;

import com.xrbpowered.zoomui.GraphAssist;
import com.xrbpowered.zoomui.UIContainer;
import com.xrbpowered.zoomui.UIElement;
import com.xrbpowered.zoomui.base.UIButtonBase;

public class ClickButton extends UIButtonBase {

	public static final int boxHeight = 32;
	public static final Color darkColor = new Color(0x88aa66);
	
	public String label;
	public int paddingX = 0;
	
	public ClickButton(UIContainer parent, String label) {
		super(parent);
		this.label = label;
	}

	@Override
	public void paint(GraphAssist g) {
		paintBoxLabel(g, this, darkColor, paddingX, label, Fonts.font, Color.WHITE);
	}

	public static void paintBox(GraphAssist g, UIElement e, Color color, int paddingX) {
		g.setColor(color);
		g.graph.fillRoundRect(paddingX, (int)e.getHeight()/2-boxHeight/2,
				(int)e.getWidth()-paddingX*2, boxHeight, boxHeight, boxHeight);
	}

	public static void paintBoxLabel(GraphAssist g, UIElement e, Color boxColor, int paddingX, String label, Font font, Color labelColor) {
		paintBox(g, e, boxColor, paddingX);
		if(label!=null) {
			g.setColor(labelColor);
			g.setFont(Fonts.font);
			g.drawString(label, e.getWidth()/2, e.getHeight()/2, GraphAssist.CENTER, GraphAssist.CENTER);
		}
	}

}
