package com.xrbpowered.looppattern;

import java.awt.event.KeyEvent;

import com.xrbpowered.looppattern.game.Map;
import com.xrbpowered.looppattern.ui.ControlLayer;
import com.xrbpowered.looppattern.ui.MapView;
import com.xrbpowered.looppattern.ui.menu.MainMenu;
import com.xrbpowered.looppattern.ui.menu.MenuOverlay;
import com.xrbpowered.looppattern.ui.res.ButtonStyle;
import com.xrbpowered.looppattern.ui.res.Fonts;
import com.xrbpowered.zoomui.KeyInputHandler;
import com.xrbpowered.zoomui.UIContainer;
import com.xrbpowered.zoomui.base.UILayersContainer;
import com.xrbpowered.zoomui.swing.SwingFrame;
import com.xrbpowered.zoomui.swing.SwingWindowFactory;

public class LoopPattern extends UILayersContainer implements KeyInputHandler {

	public static final String savePath = "save.dat";
	
	public static Map map = Map.load(savePath);
	
	public static LoopPattern root;
	public static MapView mapView;
	public static ControlLayer controls;
	public static MenuOverlay menu = null;
	
	public LoopPattern(UIContainer parent) {
		super(parent);
		mapView = new MapView(this);
		controls = new ControlLayer(this);
		getBase().setFocus(this);
		
		Fonts.createFonts();
	}

	@Override
	public boolean onKeyPressed(char c, int code, int mods) {
		switch(code) {
			case KeyEvent.VK_ESCAPE:
				if(menu!=null)
					menu.dismiss();
				else
					getBase().getWindow().requestClosing();
				break;
			case KeyEvent.VK_F4:
				if(mods==modAltMask && menu==null)
					getBase().getWindow().requestClosing();
				break;
			case KeyEvent.VK_HOME:
				mapView.center();
				repaint();
				break;
		}
		return true;
	}

	@Override
	public void onFocusGained() {
	}

	@Override
	public void onFocusLost() {
	}

	public static void updateUI() {
		boolean done = map.isComplete();
		controls.completeText.setVisible(done);
		controls.completeButton.setVisible(done);
		controls.percentText.style = done ? ButtonStyle.redButton.normal : ButtonStyle.button.normal;
		mapView.repaint();
	}
	
	public static void main(String[] args) {
		SwingFrame frame = new SwingFrame(SwingWindowFactory.use(1), null, 1024, 600, false, true) {
			@Override
			public boolean onClosing() {
				new MainMenu();
				repaint();
				return false;
			}
			@Override
			public void onClose() {
				map.save(savePath);
				super.onClose();
			}
		};
		frame.maximize();
		root = new LoopPattern(frame.getContainer());
		updateUI();
		frame.show();
	}

}
