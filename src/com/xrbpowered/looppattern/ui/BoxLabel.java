package com.xrbpowered.looppattern.ui;

import java.awt.Color;
import java.awt.Font;

import com.xrbpowered.zoomui.GraphAssist;
import com.xrbpowered.zoomui.UIContainer;
import com.xrbpowered.zoomui.UIElement;

public class BoxLabel extends UIElement {

	public String label;
	public Color boxColor, labelColor;
	public Font font = Fonts.font;
	public int paddingX = 0;

	public BoxLabel(UIContainer parent, String label, Color boxColor, Color labelColor) {
		super(parent);
		this.label = label;
		this.boxColor = boxColor;
		this.labelColor = labelColor;
	}

	public String getLabel() {
		return label;
	}
	
	@Override
	public void paint(GraphAssist g) {
		ClickButton.paintBoxLabel(g, this, boxColor, paddingX, getLabel(), font, labelColor);
	}

}
