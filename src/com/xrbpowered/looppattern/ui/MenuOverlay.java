package com.xrbpowered.looppattern.ui;

import com.xrbpowered.looppattern.LoopPattern;
import com.xrbpowered.zoomui.GraphAssist;
import com.xrbpowered.zoomui.UIContainer;

import static com.xrbpowered.looppattern.ui.BoxLabel.boxHeight;

import java.awt.Color;

public abstract class MenuOverlay extends UIContainer {

	public static final int boxWidth = 512;
	public static final int buttonWidth = 480;
	public static final int gap = 16;
	public static final int baseTop = 80;
	
	public class Box extends UIContainer {
		public Box() {
			super(MenuOverlay.this);
		}
		
		@Override
		protected void paintSelf(GraphAssist g) {
			g.setColor(new Color(0x33000000 | 0xffffff & BoxStyle.darkColor.getRGB(), true));
			g.setStroke(16f);
			g.graph.drawRoundRect(-1, -1, (int)getWidth()+1, (int)getHeight()+1, boxHeight, boxHeight);
			g.setColor(BoxStyle.fgColor);
			g.setStroke(4f);
			g.graph.drawRoundRect(-1, -1, (int)getWidth()+1, (int)getHeight()+1, boxHeight, boxHeight);
			g.setColor(BoxStyle.bgColor);
			g.graph.fillRoundRect(0, 0, (int)getWidth(), (int)getHeight(), boxHeight, boxHeight);
			
			if(title!=null) {
				g.setColor(BoxStyle.darkColor);
				g.setFont(Fonts.font);
				g.drawString(title, getWidth()/2, 16+boxHeight/2, GraphAssist.CENTER, GraphAssist.CENTER);
			}
			paintBox(g);
		}
	}
	
	public MenuOverlay prev;
	public Box box;
	public String title;
	
	public MenuOverlay(String title) {
		super(LoopPattern.root);
		this.title = title;
		
		box = new Box();
		int buttons = createButtons();
		box.setSize(boxWidth, boxHeight(buttons));
		
		prev = LoopPattern.menu;
		if(prev!=null)
			prev.setVisible(false);
		LoopPattern.menu = this;
	}
	
	public ClickButton createButton(final int index, float top) {
		ClickButton button = new ClickButton(box, buttonLabel(index), buttonStyle(index)) {
			@Override
			public void onAction() {
				MenuOverlay.this.onAction(index);
			}
		};
		button.setSize(buttonWidth, boxHeight);
		button.setLocation(boxWidth/2-buttonWidth/2, top+(boxHeight+gap)*index);
		return button;
	}

	protected int createButtons(int count, float top) {
		for(int i=0; i<count; i++)
			createButton(i, top);
		return count;
	}

	protected abstract int createButtons();
	protected abstract String buttonLabel(int index);
	protected abstract ButtonStyle buttonStyle(int index);
	protected abstract void onAction(int index);

	protected int boxHeight(int buttons) {
		return baseTop + buttons*(boxHeight+gap) + 16;
	}

	protected void paintBox(GraphAssist g) {
	}
	
	@Override
	public void layout() {
		box.setLocation(getWidth()/2-box.getWidth()/2, getHeight()/2-box.getHeight()/2);
		super.layout();
	}
	
	public void dismiss() {
		if(prev!=null)
			prev.setVisible(true);
		LoopPattern.menu = prev;
		getParent().removeChild(this);
		repaint();
	}
	
	@Override
	public boolean isInside(float x, float y) {
		return true;
	}
	
	@Override
	public boolean onMouseDown(float x, float y, Button button, int mods) {
		return true;
	}

}
