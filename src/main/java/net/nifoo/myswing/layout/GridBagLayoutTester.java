//<applet code=net.nifoo.myswing.Main width=400 height=100></applet>
package net.nifoo.myswing.layout;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.List;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;

import net.nifoo.myswing.Console;

public class GridBagLayoutTester extends JApplet {
	public GridBagLayoutTester() {
	}

	private static final long serialVersionUID = 1L;

	private static final String APPLICATION_NAME = "XX系统";

	JMenu[] menu;
	Container mainPane;
	JPanel contentPane, navPane;
	JLabel lblName, lblMsg;
	JTextField txtName;
	JTextArea txtMsg;

	@Override
	public void init() {
		initialize();
	}

	private void initialize() {
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		
		panel.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.NORTHEAST;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		Button button1 = new Button("Next");
		panel.add(button1, constraints);
		
		TextField text1 = new TextField();
		text1.setColumns(10);
		constraints.gridx = 1;
		panel.add(text1, constraints.clone());
		
		Label label1 = new Label("First Name:");
		constraints.gridx = 2;
		panel.add(label1, constraints.clone());
		
		TextArea textArea1 = new TextArea("This is some text in a text area");
		textArea1.setRows(2);
		textArea1.setColumns(10);		
		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.weightx = 0.5;
		panel.add(textArea1, constraints.clone());
		
		List list1 = new List();
		list1.add("FirstItem");
		list1.add("SecondItem");
		list1.add("ThirdItem");
		list1.add("FourthItem");
		list1.add("FifthItem");
		constraints.gridx = 1;
		constraints.gridwidth = 2;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 1.0;
		constraints.weighty = 1.0;
		panel.add(list1, constraints.clone());
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize1() {
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
	}

	private void createNavigatePane(Container mainPane) {
		navPane = new JPanel();
		mainPane.add(navPane, BorderLayout.WEST);
		navPane.setSize(new Dimension(100, 300));
		navPane.setBorder(new EtchedBorder());
		// navPane.setLayout(new BoxLayout(navPane, BoxLayout.Y_AXIS));
		navPane.setLayout(new BorderLayout());
	}

	private void createContentPane(Container mainPane) {
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		contentPane.setBorder(new EtchedBorder());
		mainPane.add(new JScrollPane(this.contentPane), BorderLayout.CENTER);

		JPanel tab1 = new JPanel();
		contentPane.add(tab1, BorderLayout.CENTER);
		GridBagLayout gbl_tab1 = new GridBagLayout();
		gbl_tab1.columnWidths = new int[] { 87, 24, 186, 42, 286, 0 };
		gbl_tab1.rowHeights = new int[] { 406, 0 };
		gbl_tab1.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_tab1.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		tab1.setLayout(gbl_tab1);

		//		JPanel tab2 = new JPanel();
		//		contentPane.add("Two", tab2);

		lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.WEST;
		gbc_lblName.insets = new Insets(0, 0, 0, 5);
		gbc_lblName.gridx = 1;
		gbc_lblName.gridy = 0;
		tab1.add(lblName, gbc_lblName);

		txtName = new JTextField(30);
		GridBagConstraints gbc_txtName = new GridBagConstraints();
		gbc_txtName.anchor = GridBagConstraints.WEST;
		gbc_txtName.insets = new Insets(0, 0, 0, 5);
		gbc_txtName.gridx = 2;
		gbc_txtName.gridy = 0;
		tab1.add(txtName, gbc_txtName);
		txtName.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				txtMsg.append(txtName.getText());
				txtMsg.append("\n");
				txtName.setText("");
			}
		});

		lblMsg = new JLabel("Message");
		lblMsg.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblMsg = new GridBagConstraints();
		gbc_lblMsg.anchor = GridBagConstraints.WEST;
		gbc_lblMsg.insets = new Insets(0, 0, 0, 5);
		gbc_lblMsg.gridx = 3;
		gbc_lblMsg.gridy = 0;
		tab1.add(lblMsg, gbc_lblMsg);

		txtMsg = new JTextArea(20, 40);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridx = 4;
		gbc.gridy = 0;
		JScrollPane scrollPane = new JScrollPane(txtMsg);
		tab1.add(scrollPane, gbc);
	}

	private void createStatePane(Container mainPane) {
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
					GridBagLayoutTester applet = new GridBagLayoutTester();
					Console.run(applet, 800, 600);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
