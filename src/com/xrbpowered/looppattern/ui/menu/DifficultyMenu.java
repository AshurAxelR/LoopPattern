package com.xrbpowered.looppattern.ui.menu;

import com.xrbpowered.looppattern.game.Difficulty;
import com.xrbpowered.looppattern.ui.res.ButtonStyle;

public class DifficultyMenu extends MenuOverlay {

	public DifficultyMenu() {
		super("DIFFICULTY");
	}

	@Override
	protected int createButtons() {
		return createButtons(Difficulty.values().length, baseTop);
	}

	@Override
	protected String buttonLabel(int index) {
		return Difficulty.values()[index].title;
	}

	@Override
	protected ButtonStyle buttonStyle(int index) {
		return ButtonStyle.button;
	}

	@Override
	protected void onAction(int index) {
		NewGameMenu.difficultyOption = Difficulty.values()[index];
		dismiss();
	}

}
