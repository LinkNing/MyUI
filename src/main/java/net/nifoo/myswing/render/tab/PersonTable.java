package net.nifoo.myswing.render.tab;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.EventObject;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.event.CellEditorListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import net.miginfocom.swing.MigLayout;
import net.nifoo.myswing.Console;

import org.jdesktop.swingx.autocomplete.ComboBoxCellEditor;
import org.joda.time.DateTime;

public class PersonTable extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextArea info = new JTextArea();

	public PersonTable() {
		init();
	}

	public void init() {
		this.setName("Person Table");
		this.setLayout(new BorderLayout());

		table = new JTable();
		table.setModel(new PersonTableModel());
		table.setDefaultRenderer(String.class, new BoardTableCellRenderer());
		table.setDefaultRenderer(Person.class, new PersonRender());
		table.setDefaultRenderer(Color.class, new ColorCell());
		//		table.setDefaultRenderer(Color.class, new ColorRenderer());
		table.setDefaultEditor(Color.class, new ColorCell());
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);

		TableColumnModel colModel = table.getColumnModel();
		colModel.getColumn(0).setMaxWidth(25);

		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, BorderLayout.CENTER);

		// 监听数据改变
		table.getModel().addTableModelListener(new TableModelListener() {

			public void tableChanged(TableModelEvent e) {
				int row = e.getFirstRow();
				int column = e.getColumn();
				TableModel model = (TableModel) e.getSource();
				String columnName = model.getColumnName(column);
				Object data = model.getValueAt(row, column);
				String msg = String.format("[%d,%d %s] %s \n", row, column, columnName, data.toString());
				info.append(msg);
			}
		});

		// 排序
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
		table.setRowSorter(sorter);

		JPanel pane = new JPanel();
		pane.setLayout(new MigLayout("", "[]20[]20[grow]", "[]20[100]"));

		Color[] colors = new Color[] { Color.red, Color.yellow, Color.blue };

		JComboBox<Color> ci = new JComboBox<>(colors);
		ci.setRenderer(new ColorRenderer());
		pane.add(ci);

		JList<Color> colorList = new JList<>(colors);
		colorList.setCellRenderer(new ColorRenderer());
		pane.add(colorList, "wrap");

		pane.add(new JScrollPane(info), "grow, span");

		add(pane, BorderLayout.SOUTH);
	}

	public static void main(String args[]) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Console.run(new PersonTable(), 800, 600);
			}
		});

	}
}

class PersonTableModel extends DefaultTableModel {
	private static final long serialVersionUID = 1L;

	public static enum Column {
		Checked, Order, Person, Color1, Color2
	};

	Object[][] values = { { Boolean.TRUE, 1, new Person(1, "Jim", DateTime.now().toDate()), "yellow", Color.red },
			{ Boolean.FALSE, 3, new Person(2, "Hook", DateTime.parse("2002-03-04").toDate()), "blue", Color.black },
			{ Boolean.TRUE, 2, new Person(3, "Sum", DateTime.parse("2001-02-28").toDate()), "black", Color.blue } };
	String[] columnNames = { "", //
			Column.Order.name(), //
			Column.Person.name(), //
			Column.Color1.name(), //
			Column.Color2.name() //
	};

	public PersonTableModel() {
		setDataVector(values, columnNames);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

	//	public Object getValueAt(int rowIndex, int columnIndex) {
	//		return values[rowIndex][columnIndex];
	//	}

	//	public String getColumnName(int column) {
	//		return columnNames[column];
	//	}

	/*
	 * Don't need to implement this method unless your table's editable.
	 */
	//	public boolean isCellEditable(int row, int col) {
	//		if (col == 0) {
	//			return true;
	//		} else {
	//			return false;
	//		}
	//	}
}

class BoardTableCellRenderer extends DefaultTableCellRenderer {

	private static final long serialVersionUID = 1L;

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int col) {

		Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
		String s = table.getModel().getValueAt(row, col).toString();

		Color color = ColorIcon.COLORS.get(s);

		if (color != null) {
			c.setBackground(color);
			setToolTipText(color.toString());
		}

		return c;
	}
}

class ColorCell implements TableCellEditor, TableCellRenderer {
	DefaultCellEditor editor;

	private JComboBox<Color> component;

	public ColorCell() {
		Color[] colors = new Color[] { Color.red, Color.yellow, Color.blue, Color.green, Color.white, Color.black };
		component = new JComboBox<>(colors);
		component.setRenderer(new ColorRenderer());

		editor = new ComboBoxCellEditor(component);
	}

	@Override
	public Object getCellEditorValue() {
		return editor.getCellEditorValue();
	}

	@Override
	public boolean isCellEditable(EventObject anEvent) {
		return editor.isCellEditable(anEvent);
	}

	@Override
	public boolean shouldSelectCell(EventObject anEvent) {
		//return editor.shouldSelectCell(anEvent);
		return false;
	}

	@Override
	public boolean stopCellEditing() {
		return editor.stopCellEditing();
	}

	@Override
	public void cancelCellEditing() {
		editor.cancelCellEditing();
	}

	@Override
	public void addCellEditorListener(CellEditorListener l) {
		editor.addCellEditorListener(l);
	}

	@Override
	public void removeCellEditorListener(CellEditorListener l) {
		editor.removeCellEditorListener(l);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		component.setSelectedItem(value);
		return component;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		return editor.getTableCellEditorComponent(table, value, isSelected, row, column);
	}

}

class PersonRender extends DefaultTableCellRenderer {

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		Person person = (Person) value;

		//				StringBuilder info = new StringBuilder();
		//				info.append("id: ").append(person.getId()).append("\n");
		//				info.append("name: ").append(person.getName()).append("\n");
		//				info.append("birthday: ").append(person.getBirthday()).append("\n");

		JPanel personPanel = new JPanel();
		personPanel.add(new JLabel(person.getId().toString()));
		personPanel.add(new JLabel(person.getName()));
		//personPanel.add(new JLabel(person.getId().toString()));
		//JLabel personInfo = new JLabel(info.toString());
		//personInfo.setBounds(0, 0, 40, 100);

		//return table.getCellRenderer(row, column);
		return personPanel;
	}
}
