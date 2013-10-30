//<applet code=net.nifoo.myswing.Main width=400 height=100></applet>
package net.nifoo.myswing.databinding;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;

import net.nifoo.myswing.Console;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Bindings;

public class DataBindApplet extends JApplet {
	public DataBindApplet() {
	}

	private static final long serialVersionUID = 1L;

	private static final String APPLICATION_NAME = "XX系统";

	Person thisPerson = new Person("张三", new Date(), "110", "xx@xx.com", "xxxx");

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
		//mainPane.setLayout(new FlowLayout());

		// Menu
		createMenu(mainPane);

		// Toolbar
		createToolBar(mainPane);

		// navigate pane
		createNavigatePane(mainPane);

		// Content
		createContentPane(mainPane);

		// state pane
		createStatePane(mainPane);

		initDataBindings();
	}

	private void createMenu(Container mainPane) {
		menu = new JMenu[] { new JMenu("File"), new JMenu("Edit"), new JMenu("View") };
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		for (JMenu m : menu) {
			menuBar.add(m);
		}

		menu[0].add(new JMenuItem("New"));
		menu[0].add(new JMenuItem("Exit"));
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
				//JOptionPane.showMessageDialog(null, "someone click the button[show].\n");
				JOptionPane.showMessageDialog(null, thisPerson.getName());
			}
		});
	}

	private void createNavigatePane(Container mainPane) {
		navPane = new JPanel();
		mainPane.add(navPane, BorderLayout.WEST);
		navPane.setSize(new Dimension(100, 300));
		navPane.setBorder(new EtchedBorder());
		// navPane.setLayout(new BoxLayout(navPane, BoxLayout.Y_AXIS));
		navPane.setLayout(new BorderLayout());

		JLabel navTitle = new JLabel();
		navTitle.setText("请选择: ");
		navPane.add(navTitle, BorderLayout.NORTH);

		navList = new JList(new Integer[] { 1, 2, 3, 4, 5 });
		navList.setVisibleRowCount(10);
		navList.setSize(100, 300);
		navPane.add(navList, BorderLayout.CENTER);
	}

	private void createContentPane(Container mainPane) {
		contentPane = new JPanel();
		contentPane.setBorder(new EtchedBorder());
		mainPane.add(new JScrollPane(this.contentPane), BorderLayout.CENTER);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
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
		gbc_txtName_1.insets = new Insets(0, 0, 5, 0);
		gbc_txtName_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtName_1.gridx = 2;
		gbc_txtName_1.gridy = 1;
		contentPane.add(txtName, gbc_txtName_1);
		txtName.setColumns(10);

		lblBirthday = new JLabel("Birthday");
		GridBagConstraints gbc_lblBirthday = new GridBagConstraints();
		gbc_lblBirthday.anchor = GridBagConstraints.EAST;
		gbc_lblBirthday.insets = new Insets(0, 0, 5, 5);
		gbc_lblBirthday.gridx = 1;
		gbc_lblBirthday.gridy = 2;
		contentPane.add(lblBirthday, gbc_lblBirthday);

		txtBirthday = new JTextField();
		GridBagConstraints gbc_txtBirthday = new GridBagConstraints();
		gbc_txtBirthday.insets = new Insets(0, 0, 5, 0);
		gbc_txtBirthday.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtBirthday.gridx = 2;
		gbc_txtBirthday.gridy = 2;
		contentPane.add(txtBirthday, gbc_txtBirthday);
		txtBirthday.setColumns(10);

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
		gbc_txtPhone.insets = new Insets(0, 0, 5, 0);
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
		gbc_txtEmail.insets = new Insets(0, 0, 5, 0);
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
		gbc_txtAddress.fill = GridBagConstraints.BOTH;
		gbc_txtAddress.gridx = 2;
		gbc_txtAddress.gridy = 5;
		contentPane.add(txtAddress, gbc_txtAddress);

	}

	private void createStatePane(Container mainPane) {

		statePanel = new JPanel();
		mainPane.add(statePanel, BorderLayout.SOUTH);
		// btnPane.add(new BasicArrowButton(BasicArrowButton.PREVIOUS));
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DataBindApplet applet = new DataBindApplet();
					Console.run(applet, 800, 600);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	protected void initDataBindings() {
		BeanProperty<Person, String> personBeanProperty = BeanProperty.create("name");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty.create("text_ON_ACTION_OR_FOCUS_LOST");
		AutoBinding<Person, String, JTextField, String> autoBinding = Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, thisPerson, personBeanProperty, txtName, jTextFieldBeanProperty);
		autoBinding.bind();
	}
}
