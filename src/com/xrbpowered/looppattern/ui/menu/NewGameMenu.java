package com.xrbpowered.looppattern.ui.menu;

import static com.xrbpowered.looppattern.LoopPattern.map;

import com.xrbpowered.looppattern.LoopPattern;
import com.xrbpowered.looppattern.game.Difficulty;
import com.xrbpowered.looppattern.game.GameSettings;
import com.xrbpowered.looppattern.game.Map;
import com.xrbpowered.looppattern.ui.res.ButtonStyle;

public class NewGameMenu extends MenuOverlay {

	private static final int DIFFICULTY = 0;
	private static final int SYMMETRY = 1;
	private static final int START = 2;
	private static final int CANCEL = 3;

	public static Difficulty difficultyOption;
	public static boolean symmetryOption;
	
	public NewGameMenu() {
		super("START NEW GAME");
		difficultyOption = map.settings.difficulty;
		symmetryOption = map.settings.symmetry;
	}

	@Override
	protected int createButtons() {
		return createButtons(4, baseTop);
	}

	@Override
	protected String buttonLabel(int index) {
		switch (index) {
			case DIFFICULTY:
				return "DIFFICULTY: "+difficultyOption.title;
			case SYMMETRY:
				return "SYMMETRY: "+(symmetryOption ? "ON" : "OFF");
			case START:
				return "START";
			case CANCEL:
				return "CANCEL";
			default:
				return null;
		}
	}

	@Override
	protected ButtonStyle buttonStyle(int index) {
		return index!=CANCEL ? ButtonStyle.button : ButtonStyle.redButton;
	}

	@Override
	protected void onAction(int index) {
		switch (index) {
			case DIFFICULTY:
				new DifficultyMenu();
				break;
			case SYMMETRY:
				symmetryOption = !symmetryOption;
				repaint();
				break;
			case START: {
					map = new Map(new GameSettings(difficultyOption, symmetryOption)).generate();
					LoopPattern.updateUI();
					LoopPattern.mapView.center();
					dismissAll();
					break;
				}
			case CANCEL:
			default:
				dismiss();
				break;
		}
	}

}
