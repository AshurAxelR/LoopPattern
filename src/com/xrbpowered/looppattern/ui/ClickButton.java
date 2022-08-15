package com.xrbpowered.looppattern.ui;

import com.xrbpowered.zoomui.GraphAssist;
import com.xrbpowered.zoomui.UIContainer;
import com.xrbpowered.zoomui.base.UIButtonBase;

public class ClickButton extends UIButtonBase {

	public String label;
	public BoxStyle style, hoverStyle;
	public int paddingX = 0;
	
	public ClickButton(UIContainer parent, String label, BoxStyle style, BoxStyle hoverStyle) {
		super(parent);
		this.label = label;
		this.style = style;
		this.hoverStyle = hoverStyle;
	}

	public ClickButton(UIContainer parent, String label) {
		this(parent, label, BoxStyle.button, BoxStyle.buttonHover);
	}


	@Override
	public void paint(GraphAssist g) {
		BoxLabel.paintBoxLabel(g, this, paddingX, label, Fonts.font, hover ? hoverStyle : style);
	}


}
