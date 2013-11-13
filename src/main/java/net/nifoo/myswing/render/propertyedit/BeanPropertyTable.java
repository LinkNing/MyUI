package net.nifoo.myswing.render.propertyedit;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class BeanPropertyTable extends JTable {
	private static final long serialVersionUID = 1L;

	private List<BeanProperty> properties;

	public BeanPropertyTable() {
		properties = new ArrayList<BeanProperty>();
	}

	public void setProperties(List<BeanProperty> properties) {
		if (properties != null) {
			this.properties = properties;
			setModel(new BeanModel(properties));
		}
	}

	//覆盖父类的getCellRenderer提供个性化的渲染器
	public TableCellRenderer getCellRenderer(int row, int column) {
		if (column == 0)
			//第一列使用继承的渲染器
			return super.getCellRenderer(row, column);
		else
			//第二列使用属性对象自己提供的渲染器，注意Support类实现了TableCellRenderer
			return properties.get(row).getSupport();
	}

	@Override
	public TableCellEditor getCellEditor(int row, int column) {
		if (column == 0)
			//第一列使用继承的渲染器
			return super.getCellEditor(row, column);
		else
			//第二列使用属性对象自己提供的渲染器，注意Support类实现了TableCellRenderer
			return properties.get(row).getSupport();
	}
}
