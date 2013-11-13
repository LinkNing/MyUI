package net.nifoo.myswing.render.tab;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.util.Map;

import javax.swing.Icon;

import com.google.common.collect.Maps;

public class ColorIcon implements Icon {

	static final int BOX = 10;
	
	static final Map<String, Color> COLORS = Maps.newHashMap();

	static {
		COLORS.put("red", Color.red);
		COLORS.put("yellow", Color.yellow);
		COLORS.put("blue", Color.blue);
		COLORS.put("black", Color.black);
		COLORS.put("white", Color.white);
	}

	private Color color;

	public ColorIcon(Color c) {
		color = c;
	}

	public String getColorName() {
		for (Map.Entry<String, Color> entry : COLORS.entrySet()) {
			if (entry.getValue().equals(color))
				return entry.getKey();
		}

		return "blank";
	}

	public void paintIcon(Component c, Graphics g, int x, int y) {
		Color old = g.getColor();
		g.setColor(color);
		g.fillRect(x, y, BOX, BOX);
		g.setColor(Color.black);
		g.drawRect(x, y, BOX, BOX);
		g.setColor(old);
	}

	public int getIconWidth() {
		return BOX;
	}

	public int getIconHeight() {
		return BOX;
	}

	public Color getColor() {
		return color;
	}

}
