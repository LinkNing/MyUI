//<applet code=net.nifoo.myswing.Main width=400 height=100></applet>
package net.nifoo.myswing;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

import javax.swing.Action;
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
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class MainApplet extends JApplet {
	public MainApplet() {
	}

	private static final long serialVersionUID = 1L;

	private static final String APPLICATION_NAME = "XX系统";

	JMenu[] menu;
	Container mainPane;
	JLayeredPane toolbarPanel;
	JToolBar toolBar;
	JPanel contentPane, navPane, btnPane;
	JLabel lblName, lblMsg;
	JTextField txtName;
	JTextArea txtMsg;
	JList navList;

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

		JButton btnNewButton = new JButton("show");
		toolBar.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				txtMsg.append("someone click the button[show].\n");
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
		contentPane.setLayout(new BorderLayout());
		contentPane.setBorder(new EtchedBorder());
		mainPane.add(new JScrollPane(this.contentPane), BorderLayout.CENTER);

		JTabbedPane tabsPane = new JTabbedPane();
		contentPane.add(tabsPane);

		JPanel tab1 = new JPanel();
		tabsPane.addTab("One", tab1);

		JPanel tab2 = new JPanel();
		tabsPane.addTab("Two", tab2);
		tab1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		tab1.add(lblName);

		txtName = new JTextField(30);
		tab1.add(txtName);

		txtName.setDocument(new PlainDocument() {

			@Override
			public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
				super.insertString(offs, str.toUpperCase(), a);
			}

		});

		txtName.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				txtMsg.append(txtName.getText());
				txtMsg.append("\n");
				txtName.setText("");
			}
		});

		lblMsg = new JLabel("Message");
		lblMsg.setHorizontalAlignment(SwingConstants.LEFT);
		tab1.add(lblMsg);

		txtMsg = new JTextArea(20, 40);
		tab1.add(new JScrollPane(txtMsg));
	}

	private void createStatePane(Container mainPane) {
		btnPane = new JPanel();
		mainPane.add(btnPane, BorderLayout.SOUTH);
		btnPane.setBorder(new TitledBorder("buttons"));

		BasicArrowButton btnIcon = new BasicArrowButton(BasicArrowButton.NORTH);
		btnIcon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(MainApplet.this, "hello");
			}
		});
		btnPane.add(btnIcon);
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
					MainApplet applet = new MainApplet();
					Console.run(applet, 800, 600);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
