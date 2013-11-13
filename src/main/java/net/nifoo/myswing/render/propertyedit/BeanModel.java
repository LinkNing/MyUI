package net.nifoo.myswing.render.propertyedit;

import java.util.List;

import javax.swing.table.AbstractTableModel;


//自定义的TableModel
public class BeanModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	private List<BeanProperty> properties;

	public BeanModel(List<BeanProperty> properties) {
		this.properties = properties;
	}

	public int getRowCount() {
		return properties.size();
	}

	//属性表的行数 return properties.size(); } 
	public int getColumnCount() {
		//属性表的列数 
		return 2;
	}

	public String getColumnName(int columnIndex) {
		//属性表的列名：property, value 
		return columnIndex == 0 ? "property" : "value";
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		//第二列属性值可编辑 
		return columnIndex == 1;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		//获取值，第一列用属性显示名，第二列用属性值 
		BeanProperty property = properties.get(rowIndex);
		return columnIndex == 0 ? property.getDisplayName() : property.getValue();
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		//只有第二列可编辑，设置第二列到属性值
		if (columnIndex == 1)
			properties.get(rowIndex).setValue(aValue);
	}

}
