package net.nifoo.notepad;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.jdesktop.application.ApplicationContext;

public class MenuBar extends JMenuBar {
	private static final long serialVersionUID = 1L;

	private ApplicationContext applicationContent;
	private Actions actions;

	private final JMenu mnFile = new JMenu("File");
	private final JMenuItem mntmOpen = new JMenuItem("Open");
	private final JMenuItem mntmClose = new JMenuItem("Close");
	private final JMenuItem mntmExit = new JMenuItem("Exit");
	
	private final JMenu mnEdit = new JMenu("Edit");
	private final JMenuItem mntmCopy = new JMenuItem("Copy");
	private final JMenuItem mntmCut = new JMenuItem("Cut");
	private final JMenuItem mntmPaste = new JMenuItem("Paste");
	private final JMenuItem mntmUpperCase = new JMenuItem("UpperCase");
	private final JMenuItem mntmLowerCase = new JMenuItem("LowerCase");
	

	public MenuBar(ApplicationContext applicationContent, Actions actions) {
		this.applicationContent = applicationContent;
		this.actions = actions;

		init();
	}

	public void init() {

		mnFile.setName("menuFile");
		mntmOpen.setAction(getAction("open"));
		mntmClose.setAction(getAction("close"));
		mntmExit.setAction(getAction("quit"));
		
		mnFile.add(mntmOpen);
		mnFile.add(mntmClose);
		mnFile.addSeparator();
		
		mnFile.add(mntmExit);
		this.add(mnFile);

		
		mnEdit.setName("menuEdit");
		mnEdit.add(getAction("copy"));
		mnEdit.add(getAction("cut"));
		mnEdit.add(getAction("paste"));
		mnEdit.addSeparator();
		
		mnEdit.add(getAction("upperCase"));
		mnEdit.add(getAction("lowerCase"));
		
		this.add(mnEdit);

	}

	private ApplicationContext getContext() {
		return applicationContent;
	}

	private javax.swing.Action getAction(String actionName) {
		return actions.getAction(actionName);
	}

}
