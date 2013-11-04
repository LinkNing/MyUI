//<applet code=net.nifoo.myswing.Main width=400 height=100></applet>
package net.nifoo.myswing.databinding;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;

import net.nifoo.myswing.Console;

import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Binding;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.Converter;

public class DataBindApplet extends JApplet {
	public DataBindApplet() {
	}

	private static final long serialVersionUID = 1L;

	private static final String APPLICATION_NAME = "XX系统";

	Person person = new Person("张三", new Date(), "110", "xx@xx.com", "xxxx");

	JMenu[] menu;
	Container mainPane;
	JLayeredPane toolbarPanel;
	JToolBar toolBar;
	JPanel contentPane, navPane, statePanel;
	JList navList;
	private JLabel lblName;
	private JTextField txtName;
	private JLabel lblBirthday;
	private JLabel lblPhone;
	private JLabel lblEmail;
	private JLabel lblAdress;
	private JTextField txtBirthday;
	private JTextField txtPhone;
	private JTextField txtEmail;
	private JTextArea txtAddress;
	private JSpinner spnAge;

	@Override
	public void init() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setName(APPLICATION_NAME);

		mainPane = this.getContentPane();

		// Toolbar
		createToolBar(mainPane);

		// Content
		createContentPane(mainPane);

		initDataBindings();
	}

	private void createToolBar(Container mainPane) {
		toolbarPanel = new JLayeredPane();
		toolbarPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		mainPane.add(toolbarPanel, BorderLayout.NORTH);

		toolBar = new JToolBar();
		toolbarPanel.add(toolBar);

		JButton btnShow = new JButton("show");
		toolBar.add(btnShow);
		btnShow.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// JOptionPane.showMessageDialog(null, "someone click the button[show].\n");
				// JOptionPane.showMessageDialog(null, person.getName());
				txtAddress.append(person.getName());
				txtAddress.append("\n");

				System.out.println(spnAge.getValue());
			}
		});
	}

	private void createContentPane(Container mainPane) {
		contentPane = new JPanel();
		contentPane.setBorder(new EtchedBorder());
		mainPane.add(new JScrollPane(this.contentPane), BorderLayout.CENTER);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0, 64, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		lblName = new JLabel("Name");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.gridx = 1;
		gbc_lblName.gridy = 1;
		contentPane.add(lblName, gbc_lblName);

		txtName = new JTextField();
		txtName.setText("name");
		GridBagConstraints gbc_txtName_1 = new GridBagConstraints();
		gbc_txtName_1.insets = new Insets(0, 0, 5, 5);
		gbc_txtName_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtName_1.gridx = 2;
		gbc_txtName_1.gridy = 1;
		contentPane.add(txtName, gbc_txtName_1);
		txtName.setColumns(10);

		lblBirthday = new JLabel("Age");
		GridBagConstraints gbc_lblBirthday = new GridBagConstraints();
		gbc_lblBirthday.anchor = GridBagConstraints.EAST;
		gbc_lblBirthday.insets = new Insets(0, 0, 5, 5);
		gbc_lblBirthday.gridx = 1;
		gbc_lblBirthday.gridy = 2;
		contentPane.add(lblBirthday, gbc_lblBirthday);

		txtBirthday = new JTextField();
		GridBagConstraints gbc_txtBirthday = new GridBagConstraints();
		gbc_txtBirthday.insets = new Insets(0, 0, 5, 5);
		gbc_txtBirthday.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtBirthday.gridx = 2;
		gbc_txtBirthday.gridy = 2;
		contentPane.add(txtBirthday, gbc_txtBirthday);
		txtBirthday.setColumns(10);

		spnAge = new JSpinner();
		GridBagConstraints gbc_spinner = new GridBagConstraints();
		gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner.insets = new Insets(0, 0, 5, 5);
		gbc_spinner.gridx = 3;
		gbc_spinner.gridy = 2;
		contentPane.add(spnAge, gbc_spinner);

		lblPhone = new JLabel("Phone");
		GridBagConstraints gbc_lblPhone = new GridBagConstraints();
		gbc_lblPhone.anchor = GridBagConstraints.EAST;
		gbc_lblPhone.insets = new Insets(0, 0, 5, 5);
		gbc_lblPhone.gridx = 1;
		gbc_lblPhone.gridy = 3;
		contentPane.add(lblPhone, gbc_lblPhone);

		txtPhone = new JTextField();
		GridBagConstraints gbc_txtPhone = new GridBagConstraints();
		gbc_txtPhone.anchor = GridBagConstraints.NORTH;
		gbc_txtPhone.insets = new Insets(0, 0, 5, 5);
		gbc_txtPhone.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPhone.gridx = 2;
		gbc_txtPhone.gridy = 3;
		contentPane.add(txtPhone, gbc_txtPhone);
		txtPhone.setColumns(10);

		lblEmail = new JLabel("Email");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.anchor = GridBagConstraints.EAST;
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 1;
		gbc_lblEmail.gridy = 4;
		contentPane.add(lblEmail, gbc_lblEmail);

		txtEmail = new JTextField();
		GridBagConstraints gbc_txtEmail = new GridBagConstraints();
		gbc_txtEmail.anchor = GridBagConstraints.NORTH;
		gbc_txtEmail.insets = new Insets(0, 0, 5, 5);
		gbc_txtEmail.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEmail.gridx = 2;
		gbc_txtEmail.gridy = 4;
		contentPane.add(txtEmail, gbc_txtEmail);
		txtEmail.setColumns(10);

		lblAdress = new JLabel("Adress");
		GridBagConstraints gbc_lblAdress = new GridBagConstraints();
		gbc_lblAdress.insets = new Insets(0, 0, 0, 5);
		gbc_lblAdress.gridx = 1;
		gbc_lblAdress.gridy = 5;
		contentPane.add(lblAdress, gbc_lblAdress);

		txtAddress = new JTextArea();
		GridBagConstraints gbc_txtAddress = new GridBagConstraints();
		gbc_txtAddress.insets = new Insets(0, 0, 0, 5);
		gbc_txtAddress.fill = GridBagConstraints.BOTH;
		gbc_txtAddress.gridx = 2;
		gbc_txtAddress.gridy = 5;
		contentPane.add(txtAddress, gbc_txtAddress);

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected void initDataBindings() {

		// 监听JTextFiled反馈的时机，针对_ON_ACTION_OR_FOCUS_LOST特性
		person.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				System.out.printf("[%s:%s]  %s -> %s \n", evt.getSource(), evt.getPropertyName(), evt.getOldValue(),
						evt.getNewValue());
			}
		});

		BindingGroup bindingGroup = new BindingGroup();

		//绑定 txtName 和 person
		Binding bindingName = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, //
				person, BeanProperty.create("name"), //
				txtName, BeanProperty.create("text_ON_ACTION_OR_FOCUS_LOST") //
				);

		bindingGroup.addBinding(bindingName);

		//绑定 spnAge 和 txtBirthday
		Binding bindingAge = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, //
				spnAge, BeanProperty.create("value"), //
				txtBirthday, BeanProperty.create("text_ON_FOCUS_LOST") //
				);

		// 转换 spnAge 和 txtBirthday 的值类型
		bindingAge.setConverter(new Converter<Integer, String>() {

			@Override
			public String convertForward(Integer value) {
				return value.toString();
			}

			@Override
			public Integer convertReverse(String value) {
				return Integer.valueOf(value);
			}
		});

		bindingGroup.addBinding(bindingAge);

		// note that the converter and validators must be installed before calling bind()
		//		binding.setConverter(new PositiveIntegerConverter());
		//		binding.setValidator(new YearValidator());

		// this allows us to get sync failure notifications, so they can be displayed
		//		binding.addBindingListener(this);

		bindingGroup.bind();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e1) {
			// impossible
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Console.run(new DataBindApplet(), 800, 600);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
