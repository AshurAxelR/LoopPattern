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

	public static final BoxStyle button = new BoxStyle(fgColor, Color.WHITE, Color.WHITE);
	public static final BoxStyle buttonHover = new BoxStyle(new Color(0x668844), Color.WHITE, Color.WHITE);
	public static final BoxStyle redButton = new BoxStyle(new Color(0xcc8866), Color.WHITE, Color.WHITE);
	public static final BoxStyle redButtonHover = new BoxStyle(new Color(0xaa6644), Color.WHITE, Color.WHITE);
	public static final BoxStyle borderText = new BoxStyle(bgColor, fgColor, Color.WHITE);
	public static final BoxStyle white = new BoxStyle(Color.WHITE, fgColor, Color.WHITE);
	
}
