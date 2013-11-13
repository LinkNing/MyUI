package net.nifoo.myswing.render.propertyedit;

public class BeanProperty {
	//属性显示名称，属性表格第一列显示名
	private String displayName;

	//属性的值，属性表格第二列的值
	private Object value;

	//渲染和编辑属性值用的渲染编辑器（Support类） 
	private TableCellSupport support;

	public BeanProperty(String displayName, TableCellSupport support) {
		this.displayName = displayName;
		this.support = support;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public TableCellSupport getSupport() {
		return support;
	}
}