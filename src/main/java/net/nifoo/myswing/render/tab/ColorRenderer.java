package net.nifoo.myswing.render.tab;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.table.TableCellRenderer;

public class ColorRenderer extends BasicComboBoxRenderer implements TableCellRenderer{
	private static final long serialVersionUID = 1L;

	public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
			boolean cellHasFocus) {

		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		JLabel lbl = (JLabel) this;
		if (value != null) {
			ColorIcon c = new ColorIcon((Color) value);
			lbl.setText(c.getColorName());
			lbl.setIcon(c);
		}
		return this;
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		JLabel lbl = (JLabel) this;
		if (value != null) {
			ColorIcon c = new ColorIcon((Color) value);
			lbl.setText(c.getColorName());
			lbl.setIcon(c);
		}
		return this;
	}

}
