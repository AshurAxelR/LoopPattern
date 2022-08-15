package com.xrbpowered.looppattern.ui;

import static com.xrbpowered.looppattern.LoopPattern.map;

import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.SwingUtilities;

import com.xrbpowered.zoomui.UIContainer;

public class ControlLayer extends UIContainer {

	public BoxLabel percentText; 
	public BoxLabel clockText; 
	
	public ControlLayer(UIContainer parent) {
		super(parent);
		percentText = new BoxLabel(this, null, ClickButton.darkColor, Color.WHITE) {
			@Override
			public String getLabel() {
				int con = map.connected;
				if(con==map.total)
					return "100%";
				else
					return String.format("%.1f%%", Math.floor(con*1000/(double)map.total)/10f);
			}
		};
		percentText.setSize(128, 64);
		percentText.paddingX = 16;

		final DateFormat fmt = new SimpleDateFormat("HH:mm");
		clockText = new BoxLabel(this, null, MapView.bgColor, ClickButton.darkColor) {
			@Override
			public String getLabel() {
				return fmt.format(new Date());
			}
		};
		clockText.setSize(128, 64);
		clockText.paddingX = 16;
		
		new Thread() {
			public void run() {
				try {
					for(;;) {
						SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
								repaint();
							}
						});
						Thread.sleep(5000);
					}
				}
				catch (InterruptedException e) {
				}
			}
		}.start();
	}
	
	@Override
	public void layout() {
		percentText.setLocation(0, getHeight()-percentText.getHeight());
		clockText.setLocation(getWidth()-clockText.getWidth(), getHeight()-clockText.getHeight());
		super.layout();
	}

}
