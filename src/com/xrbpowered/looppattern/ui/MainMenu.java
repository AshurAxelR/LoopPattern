package com.xrbpowered.looppattern.ui;

import com.xrbpowered.looppattern.LoopPattern;
import com.xrbpowered.looppattern.game.Map;

public class MainMenu extends MenuOverlay {

	public static final String[] buttons = {"RESUME", "START NEW", "EXIT GAME"};
	public static final ButtonStyle[] buttonStyles = {ButtonStyle.button, ButtonStyle.button, ButtonStyle.redButton};
	
	public MainMenu() {
		super("LOOP PATTERN");
	}

	@Override
	protected int createButtons() {
		return createButtons(buttons.length, baseTop);
	}

	@Override
	protected String buttonLabel(int index) {
		return buttons[index];
	}

	@Override
	protected ButtonStyle buttonStyle(int index) {
		return buttonStyles[index];
	}

	@Override
	protected void onAction(int index) {
		switch(index) {
			case 0:
				dismiss();
				break;
			case 1:
				LoopPattern.map = new Map(LoopPattern.map.size).generate();
				LoopPattern.updateUI();
				LoopPattern.mapView.center();
				dismiss();
				break;
			case 2:
				getBase().getWindow().close();
				break;
		}
	}

}
