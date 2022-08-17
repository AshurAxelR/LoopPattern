package com.xrbpowered.looppattern.ui;

import java.awt.Color;

public class ButtonStyle {

	public BoxStyle normal, hover;
	
	public ButtonStyle(BoxStyle normal, BoxStyle hover) {
		this.normal = normal;
		this.hover = hover;
	}

	public BoxStyle get(boolean hover) {
		return hover ? this.hover : this.normal;
	}
	
	public static final ButtonStyle button = new ButtonStyle(
			new BoxStyle(BoxStyle.fgColor, Color.WHITE, Color.WHITE),
			new BoxStyle(BoxStyle.darkColor, Color.WHITE, Color.WHITE)
		);
	public static final ButtonStyle redButton = new ButtonStyle(
			new BoxStyle(new Color(0xcc8866), Color.WHITE, Color.WHITE), 
			new BoxStyle(new Color(0xaa6644), Color.WHITE, Color.WHITE)
		);

}
