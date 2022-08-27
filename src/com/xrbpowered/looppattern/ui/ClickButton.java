package com.xrbpowered.looppattern.ui;

import com.xrbpowered.looppattern.ui.res.ButtonStyle;
import com.xrbpowered.looppattern.ui.res.Fonts;
import com.xrbpowered.zoomui.GraphAssist;
import com.xrbpowered.zoomui.UIContainer;
import com.xrbpowered.zoomui.base.UIButtonBase;

public class ClickButton extends UIButtonBase {

	public String label;
	public ButtonStyle style;
	public int paddingX = 0;
	
	public ClickButton(UIContainer parent, String label, ButtonStyle style) {
		super(parent);
		this.label = label;
		this.style = style;
	}

	public ClickButton(UIContainer parent, String label) {
		this(parent, label, ButtonStyle.button);
	}

	public String getLabel() {
		return label;
	}

	@Override
	public void paint(GraphAssist g) {
		BoxLabel.paintBoxLabel(g, this, paddingX, getLabel(), Fonts.font, style.get(hover));
	}


}
