package com.xrbpowered.looppattern.ui;

import java.awt.Color;

public class BoxStyle {

	public Color boxColor, labelColor, borderColor;
	
	public BoxStyle(Color boxColor, Color labelColor, Color borderColor) {
		this.boxColor = boxColor;
		this.labelColor = labelColor;
		this.borderColor = borderColor;
	}

	public BoxStyle(Color boxColor, Color labelColor) {
		this(boxColor, labelColor, null);
	}

	public static final Color fgColor = new Color(0x88aa66);
	public static final Color bgColor = new Color(0xeef5dd);
	public static final Color darkColor = new Color(0x668844);

	public static final BoxStyle borderText = new BoxStyle(bgColor, fgColor, Color.WHITE);
	public static final BoxStyle white = new BoxStyle(Color.WHITE, fgColor, Color.WHITE);
	
}
