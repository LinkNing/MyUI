package net.nifoo.myswing.render.propertyedit;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.UIManager;

import net.nifoo.myswing.Console;

public class PropertyDemo extends JPanel {
	private static final long serialVersionUID = 1L;

	public PropertyDemo() {
		BeanPropertyTable table = new BeanPropertyTable();
		List<BeanProperty> props = new ArrayList<BeanProperty>();
		for (int i = 0; i < 2; i++) {
			//添加一个textfield属性，其渲染编辑组件是JTextField 
			props.add(new BeanProperty("textfield" + i, new TextFieldCell(new JTextField())));

			//添加一个combo属性，其渲染编辑组件是JComboBox 
			JComboBox<String> cb = new JComboBox<String>();
			cb.addItem("true");
			cb.addItem("false");
			props.add(new BeanProperty("combobox" + i, new ComboBoxCell(cb)));

			//添加一个checkbox属性，其渲染编辑组件是 JCheckBox 
			props.add(new BeanProperty("checkbox" + i, new CheckBoxCell(new JCheckBox())));

			//添加一个spinner属性，其渲染编辑组件是 JSpinner 
			props.add(new BeanProperty("spinner" + i, new SpinnerCell(new JSpinner())));
		}
		//设置这些属性数组到属性表 
		table.setProperties(props);
		add(new JScrollPane(table), BorderLayout.CENTER);
	}

	public static void main(String args[]) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Console.run(new PropertyDemo(), 600, 800);
			}
		});
	}
}

class CheckBoxCell extends TableCellSupport<JCheckBox> {

	public CheckBoxCell(JCheckBox cb) {
		super(cb);
		cb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//被选中时需要触发编辑停止事件，一般直接调用父类的stopCellEditing即可，那儿已经负责了有效性检查，事件触发。
				stopCellEditing();
			}
		});
	}

	protected void setValueTo(JCheckBox component, Object value) {
		//认为value值是Boolean类型的
		component.setSelected(value == null ? false : (Boolean) value);
	}

	protected Object getValueFrom(JCheckBox component) {
		//返回当前选中状态的布尔值，用Boolean封装 
		return component.isSelected();
	}
}

//在SpinnerCell类中继承并覆盖了checkComponentValue，检查整型值是否小于零，小于零就报错： 
class SpinnerCell extends TableCellSupport<JSpinner> {

	public SpinnerCell(JSpinner component) {
		super(component);
	}

	protected void checkComponentValue(JSpinner component) throws Exception {
		Integer i = (Integer) component.getValue();
		if (i.intValue() < 0)
			throw new Exception("Cannot be negative!");
	}

	@Override
	protected void setValueTo(JSpinner component, Object value) {
		component.setValue(value == null ? 0 : value);
	}

	@Override
	protected Object getValueFrom(JSpinner component) {
		return component.getValue();
	}
}

class TextFieldCell extends TableCellSupport<JTextField> {

	public TextFieldCell(JTextField component) {
		super(component);
	}

	@Override
	protected void setValueTo(JTextField component, Object value) {
		component.setText(value == null ? "XX" : (String) value);
	}

	@Override
	protected Object getValueFrom(JTextField component) {
		return component.getText();
	}

}

class ComboBoxCell extends TableCellSupport<JComboBox> {

	public ComboBoxCell(JComboBox component) {
		super(component);
	}

	@Override
	protected void setValueTo(JComboBox component, Object value) {
		component.setSelectedItem(value == null ? "false" : "true");
	}

	@Override
	protected Object getValueFrom(JComboBox component) {
		return component.getSelectedItem();
	}
}