package net.nifoo.saf;

import java.awt.BorderLayout;
import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.util.EventObject;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import javax.swing.JToolBar;

import org.jdesktop.application.Action;
import org.jdesktop.application.ApplicationContext;
import org.jdesktop.application.SingleFrameApplication;

public class ActionExample extends SingleFrameApplication {

	private final JPanel mainPanel = new JPanel();
	private final JPanel headPanel = new JPanel();
	private final JTextPane textPane = new JTextPane();
	private final JMenuBar menuBar = new JMenuBar();
	private final JMenu mnFile = new JMenu("File");
	private final JMenuItem mntmOpen = new JMenuItem("Open");
	private final JMenuItem mntmClose = new JMenuItem("Close");
	private final JMenuItem mntmExit = new JMenuItem("Exit");
	private final JMenu mnEdit = new JMenu("Edit");
	private final JMenuItem mntmCopy = new JMenuItem("Copy");
	private final JMenuItem mntmCut = new JMenuItem("Cut");
	private final JMenuItem mntmPaste = new JMenuItem("Paste");

	private final JToolBar toolBar = new JToolBar();
	private final JButton btnCopy = new JButton("Copy");
	private final JButton btnCut = new JButton("Cut");
	private final JButton btnPaste = new JButton("Paste");

	/**
	 * @wbp.parser.entryPoint
	 */
	public void startup() {
		addExitListener(new CheckedExitListener());

		mainPanel.setLayout(new BorderLayout());

		mainPanel.add(textPane, BorderLayout.CENTER);

		mainPanel.add(headPanel, BorderLayout.NORTH);
		headPanel.setLayout(new BoxLayout(headPanel, BoxLayout.Y_AXIS));
		menuBar.setAlignmentX(Component.LEFT_ALIGNMENT);

		headPanel.add(menuBar);

		menuBar.add(mnFile);
		mnFile.add(mntmOpen);
		mnFile.add(mntmClose);
		mnFile.add(new JSeparator());
		mnFile.add(mntmExit);

		mntmOpen.setAction(getAction("open"));
		mntmClose.setAction(getAction("close"));
		mntmExit.setAction(getAction("Exit"));

		menuBar.add(mnEdit);
		mnEdit.add(mntmCopy);
		mnEdit.add(mntmCut);
		mnEdit.add(mntmPaste);

		mntmCopy.setAction(getAction("copy"));
		mntmCut.setAction(getAction("cut"));
		mntmPaste.setAction(getAction("paste"));

		toolBar.setAlignmentX(Component.LEFT_ALIGNMENT);
		headPanel.add(toolBar);
		toolBar.add(btnCopy);
		toolBar.add(btnCut);
		toolBar.add(btnPaste);

		btnCopy.setAction(getAction("copy"));
		btnCut.setAction(getAction("cut"));
		btnPaste.setAction(getAction("paste"));

		show(mainPanel);
	}

	private javax.swing.Action getAction(String actionName) {
		ApplicationContext ac = getContext();
		return ac.getActionMap(getClass(), this).get(actionName);
	}

	/**
	 * Load the specified file into the textPane or popup an error
	 * dialog if something goes wrong.  
	 */
	@Action
	public void open() {
		JFileChooser chooser = new JFileChooser();
		int option = chooser.showOpenDialog(getMainFrame());
		if (option == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			try {
				textPane.setPage(file.toURI().toURL());
			} catch (IOException e) {
				JOptionPane.showMessageDialog(this.getMainFrame(), e.getMessage(), "打开文件出错", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * Replace the contents of the textPane with the value of the
	 * "defaultText" resource.  
	 */
	@Action
	public void close() {
		ApplicationContext ac = getContext();
		String defaultText = ac.getResourceMap(getClass()).getString("defaultText");
		textPane.setText(defaultText);
	}

	@Action(name = "Exit")
	public void exitApp() {
		exit();
	}

	private class CheckedExitListener implements ExitListener {
		public boolean canExit(EventObject e) {
			int option = JOptionPane.showConfirmDialog(null, "Really Exit?");
			return option == JOptionPane.YES_OPTION;
		}

		public void willExit(EventObject e) {
		}
	};

	public static void main(String[] args) {
		launch(ActionExample.class, args);
	}
}