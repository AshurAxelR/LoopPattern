package com.xrbpowered.looppattern.ui.res;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

import com.xrbpowered.zoomui.GraphAssist;

public abstract class Fonts {

	public static Font font;
	
	public static void createFonts() {
		Font fb;
		try {
			InputStream in = ClassLoader.getSystemResourceAsStream("fonts/Montserrat-Bold.ttf");
			fb = Font.createFont(Font.TRUETYPE_FONT, in);
			in.close();
		}
		catch (IOException | FontFormatException e) {
			e.printStackTrace();
			fb = new Font("Sans", Font.BOLD, 15);
		}
		font = fb.deriveFont((float)GraphAssist.ptToPixels(15f));
	}
}
