package com.xrbpowered.looppattern.ui;

import static com.xrbpowered.looppattern.LoopPattern.map;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.SwingUtilities;

import com.xrbpowered.looppattern.LoopPattern;
import com.xrbpowered.looppattern.game.Map;
import com.xrbpowered.looppattern.ui.menu.MenuOverlay;
import com.xrbpowered.looppattern.ui.res.BoxStyle;
import com.xrbpowered.looppattern.ui.res.ButtonStyle;
import com.xrbpowered.zoomui.UIContainer;

public class ControlLayer extends UIContainer {

	public BoxLabel percentText; 
	public BoxLabel clockText;
	public BoxLabel completeText;
	
	public ClickButton completeButton;
	
	public ControlLayer(UIContainer parent) {
		super(parent);
		percentText = new BoxLabel(this, null, ButtonStyle.button.normal) {
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
		clockText = new BoxLabel(this, null, BoxStyle.borderText) {
			@Override
			public String getLabel() {
				return fmt.format(new Date());
			}
		};
		clockText.setSize(128, 64);
		clockText.paddingX = 16;

		completeText = new BoxLabel(this, "PATTERN COMPLETE", ButtonStyle.redButton.normal);
		completeText.setSize(MenuOverlay.buttonWidth, 64);
		completeText.paddingX = 16;

		completeButton = new ClickButton(this, "NEXT PATTERN") {
			@Override
			public void onAction() {
				map = new Map(map.difficulty).generate();
				LoopPattern.updateUI();
			}
		};
		completeButton.setSize(MenuOverlay.buttonWidth, 64);
		completeButton.paddingX = 16;
		
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
		clockText.setLocation(getWidth()-clockText.getWidth(), percentText.getY());
		completeText.setLocation(getWidth()/2-completeText.getWidth()/2, 0);
		completeButton.setLocation(completeText.getX(), percentText.getY());
		super.layout();
	}

}
