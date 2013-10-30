package net.nifoo.myswing;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.SystemColor;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.UIManager;

public class MDIApplication extends JFrame {

	JMenuBar menuBar = new JMenuBar();;
	JDesktopPane desktopPane = new JDesktopPane();

	public MDIApplication() {
		setTitle("XX程序");

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.WEST);
		panel.setLayout(new BorderLayout(0, 0));

		JTree tree = new JTree();
		panel.add(tree);

		desktopPane.setBackground(SystemColor.control);
		getContentPane().add(desktopPane, BorderLayout.CENTER);

		JInternalFrame internalFrame = new JInternalFrame("New JInternalFrame");
		internalFrame.setBounds(166, 124, 380, 252);
		desktopPane.add(internalFrame);

		JInternalFrame inFrame = new JInternalFrame("页签1");
		inFrame.setIconifiable(true);
		inFrame.setMaximizable(true);
		inFrame.setClosable(true);
		inFrame.setBounds(49, 25, 449, 281);
		desktopPane.add(inFrame);

		getContentPane().add(menuBar, BorderLayout.NORTH);

		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNew = new JMenuItem("New");
		mnNewMenu.add(mntmNew);

		JMenuItem mntmOpen = new JMenuItem("Open");
		mnNewMenu.add(mntmOpen);

		JMenuItem mntmSave = new JMenuItem("Save");
		mnNewMenu.add(mntmSave);

		JMenuItem mntmClose = new JMenuItem("Close");
		mnNewMenu.add(mntmClose);

		JMenuItem menuItem = new JMenuItem("-");
		mnNewMenu.add(menuItem);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mnNewMenu.add(mntmExit);

		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		inFrame.setVisible(true);
		internalFrame.setVisible(true);
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
					JFrame window = new MDIApplication();
					Console.run(window, 800, 600);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
