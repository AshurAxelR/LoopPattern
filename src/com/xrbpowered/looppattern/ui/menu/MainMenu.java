package com.xrbpowered.looppattern.ui.menu;

import com.xrbpowered.looppattern.ui.res.ButtonStyle;

public class MainMenu extends MenuOverlay {

	public static final String[] buttons = {"RESUME", "START NEW", "SAVE AND EXIT"};
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
				new NewGameMenu();
				break;
			case 2:
				getBase().getWindow().close();
				break;
		}
	}

}
