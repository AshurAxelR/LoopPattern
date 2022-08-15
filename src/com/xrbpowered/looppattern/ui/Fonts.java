package com.xrbpowered.looppattern.ui;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

import com.xrbpowered.zoomui.GraphAssist;

public abstract class Fonts {

	public static Font font;
	public static Font textFont;
	
	public static void createFonts() {
		String fontPathFormat = "fonts/Montserrat-%s.ttf";
		Font f, fb;
		try {
			InputStream in = ClassLoader.getSystemResourceAsStream(String.format(fontPathFormat, "Medium"));
			f = Font.createFont(Font.TRUETYPE_FONT, in);
			in.close();
			in = ClassLoader.getSystemResourceAsStream(String.format(fontPathFormat, "Bold"));
			fb = Font.createFont(Font.TRUETYPE_FONT, in);
			in.close();
		}
		catch (IOException | FontFormatException e) {
			e.printStackTrace();
			f = new Font("Sans", Font.PLAIN, 15);
			fb = new Font("Sans", Font.BOLD, 15);
		}
		font = fb.deriveFont((float)GraphAssist.ptToPixels(15f));
		textFont = f.deriveFont((float)GraphAssist.ptToPixels(12f));
	}
}
