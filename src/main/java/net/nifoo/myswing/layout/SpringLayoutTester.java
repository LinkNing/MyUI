//<applet code=net.nifoo.myswing.Main width=400 height=100></applet>
package net.nifoo.myswing.layout;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;

import net.nifoo.myswing.Console;

public class SpringLayoutTester extends JApplet {
	public SpringLayoutTester() {
	}

	private static final long serialVersionUID = 1L;

	private static final String APPLICATION_NAME = "XX系统";

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

		Component left = new JList(new Integer[] { 1, 2, 3, 4, 5 });
		Component right = new JTextArea();
		Component component3 = new JTextArea("test");

		SpringLayout layout = new SpringLayout();
		JPanel panel = new JPanel(layout);
		panel.add(left);
		panel.add(right);
		panel.add(component3);
		
		layout.putConstraint(SpringLayout.WEST, left, 5, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, left, 25, SpringLayout.NORTH, panel);
		
		layout.putConstraint(SpringLayout.WEST, right, 20, SpringLayout.EAST, left);
		layout.putConstraint(SpringLayout.NORTH, right, 25, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.EAST, right, -10, SpringLayout.EAST, panel);
		layout.putConstraint(SpringLayout.SOUTH, right, 104, SpringLayout.NORTH, panel);
		
		layout.putConstraint(SpringLayout.WEST, component3, 5, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, component3, 90, SpringLayout.NORTH, left);
		layout.putConstraint(SpringLayout.EAST, component3, 0, SpringLayout.EAST, right);
		layout.putConstraint(SpringLayout.SOUTH, component3, -10, SpringLayout.SOUTH, panel);
		
		getContentPane().add(panel);

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize1() {
		setName(APPLICATION_NAME);

		mainPane = this.getContentPane();
		//mainPane.setLayout(new FlowLayout());

		// Content
		createContentPane(mainPane);

		// state pane
		createStatePane(mainPane);
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
					SpringLayoutTester applet = new SpringLayoutTester();
					Console.run(applet, 800, 600);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
