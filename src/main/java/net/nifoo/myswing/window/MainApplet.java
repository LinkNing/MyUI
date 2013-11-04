//<applet code=net.nifoo.myswing.Main width=400 height=100></applet>
package net.nifoo.myswing.window;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import net.nifoo.myswing.Console;

public class MainApplet extends JApplet {
	public MainApplet() {
	}

	private static final long serialVersionUID = 1L;

	private static final String APPLICATION_NAME = "XX系统";

	ActionMap actionMap;
	JMenu[] menu;
	JPopupMenu popMenu;
	MouseListener popupListener;
	Container mainPane;
	JComponent toolbarPanel;
	JToolBar toolBar;
	JPanel contentPane, navPane, statePane;
	JTabbedPane tabsPane;
	JLabel lblName, lblMsg;
	JTextField txtName;
	JTextArea txtMsg;
	JList<String> navList;

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

		actionMap = createActionMap();

		popMenu = createPopupMenu();

		// Menu
		setJMenuBar(createMenuBar());

		// Toolbar
		toolbarPanel = createToolBar();
		mainPane.add(toolbarPanel, BorderLayout.PAGE_START);

		// navigate pane
		navPane = createNavigatePane();

		// Content
		contentPane = createContentPane();

		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, navPane, new JScrollPane(this.contentPane));
		split.setDividerSize(8);
		split.setOneTouchExpandable(true);
		mainPane.add(split, BorderLayout.CENTER);

		// state pane
		statePane = createStatePane();
		mainPane.add(statePane, BorderLayout.SOUTH);

	}

	private ActionMap createActionMap() {
		ActionMap actionMap = new ActionMap();

		Action actGreet = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(MainApplet.this, "hello");
			}
		};
		actGreet.putValue(AbstractAction.NAME, "Greet");
		actGreet.putValue(AbstractAction.MNEMONIC_KEY, KeyEvent.VK_G);
		actGreet.putValue(AbstractAction.ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_G, ActionEvent.CTRL_MASK));

		actionMap.put("greet", actGreet);

		return actionMap;
	}

	private Action getAction(String actionName) {
		return actionMap.get(actionName);
	}

	private JMenuBar createMenuBar() {
		List<JMenu> menus = createMenu();
		JMenuBar menuBar = new JMenuBar();
		for (JMenu m : menus) {
			menuBar.add(m);
		}

		return menuBar;
	}

	private List<JMenu> createMenu() {
		JMenu[] menu = new JMenu[] { new JMenu("File"), new JMenu("Edit"), new JMenu("View") };

		JMenuItem mniNew = new JMenuItem("New");
		mniNew.setMnemonic(KeyEvent.VK_N);
		mniNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		menu[0].add(mniNew);

		JMenuItem mniGreed = new JMenuItem();
		mniGreed.setAction(getAction("greet"));
		menu[0].add(mniGreed);

		menu[0].addSeparator();

		menu[0].add(new JMenuItem("Exit"));

		return Arrays.asList(menu);
	}

	private JComponent createToolBar() {
		JLayeredPane toolbarPanel = new JLayeredPane();
		toolbarPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		toolBar = new JToolBar();
		toolbarPanel.add(toolBar, JLayeredPane.PALETTE_LAYER);

		JButton btnNewButton = new JButton("show");
		toolBar.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				txtMsg.append("someone click the button[show].\n");
			}
		});

		return toolbarPanel;
	}

	private JPanel createNavigatePane() {
		JPanel navPanel = new JPanel();
		navPanel.setSize(new Dimension(100, 300));
		navPanel.setBorder(new EtchedBorder());
		// navPane.setLayout(new BoxLayout(navPane, BoxLayout.Y_AXIS));
		navPanel.setLayout(new BorderLayout());

		JLabel navTitle = new JLabel();
		navTitle.setText("请选择: ");
		navPanel.add(navTitle, BorderLayout.NORTH);

		navList = new JList(new String[] { "One", "Two", "Three", "Four", "Five" });
		navPanel.add(navList, BorderLayout.CENTER);

		return navPanel;
	}

	private JPanel createContentPane() {
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		contentPane.setBorder(new EtchedBorder());

		tabsPane = new JTabbedPane();
		contentPane.add(tabsPane);

		JPanel tab1 = new JPanel();
		tabsPane.addTab("One", tab1);
		tab1.addMouseListener(popupListener);

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

		return contentPane;
	}

	private JPanel createStatePane() {
		JPanel btnPane = new JPanel();
		btnPane.setBorder(new TitledBorder("buttons"));

		JButton btnGreet = new JButton("Hello");
		btnGreet.setAction(getAction("greet"));
		btnPane.add(btnGreet);

		JButton btnIcon = new BasicArrowButton(BasicArrowButton.NORTH);
		btnIcon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(MainApplet.this, "hello");
			}
		});
		btnPane.add(btnIcon);
		// btnPane.add(new BasicArrowButton(BasicArrowButton.PREVIOUS));

		return btnPane;
	}

	private JPopupMenu createPopupMenu() {
		final JPopupMenu popup = new JPopupMenu();
		JMenuItem menuItem = new JMenuItem("A popup menu item");
		//menuItem.addActionListener(this);
		popup.add(menuItem);
		menuItem = new JMenuItem("Another popup menu item");
		//menuItem.addActionListener(this);
		popup.add(menuItem);

		popupListener = new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		};

		return popup;
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
