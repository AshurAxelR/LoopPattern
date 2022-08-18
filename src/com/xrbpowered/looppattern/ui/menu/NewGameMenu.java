package com.xrbpowered.looppattern.ui.menu;

import static com.xrbpowered.looppattern.LoopPattern.map;

import com.xrbpowered.looppattern.LoopPattern;
import com.xrbpowered.looppattern.game.Difficulty;
import com.xrbpowered.looppattern.game.Map;
import com.xrbpowered.looppattern.ui.res.ButtonStyle;

public class NewGameMenu extends MenuOverlay {

	public NewGameMenu() {
		super("SELECT DIFFICULTY");
	}

	@Override
	protected int createButtons() {
		return createButtons(Difficulty.values().length+1, baseTop);
	}

	@Override
	protected String buttonLabel(int index) {
		return index<Difficulty.values().length ? Difficulty.values()[index].title : "CANCEL";
	}

	@Override
	protected ButtonStyle buttonStyle(int index) {
		return index<Difficulty.values().length ? ButtonStyle.button : ButtonStyle.redButton;
	}

	@Override
	protected void onAction(int index) {
		if(index<Difficulty.values().length) {
			Difficulty diff = Difficulty.values()[index];
			map = new Map(diff).generate();
			LoopPattern.updateUI();
			LoopPattern.mapView.center();
			dismissAll();
		}
		else {
			dismiss();
		}
	}

}
