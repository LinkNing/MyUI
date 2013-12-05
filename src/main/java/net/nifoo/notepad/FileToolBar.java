package net.nifoo.notepad;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JToolBar;

import org.jdesktop.application.ApplicationContext;

public class FileToolBar extends JToolBar {
	private static final long serialVersionUID = 1L;

	private ApplicationContext applicationContent;
	private Actions actions;

	public FileToolBar(ApplicationContext applicationContent, Actions actions) {
		super("File commands");

		this.applicationContent = applicationContent;
		this.actions = actions;

		init();
	}

	private void init() {
		this.add(new JButton(getAction("open")));
		this.add(new JButton(getAction("close")));
	}

	private ApplicationContext getContext() {
		return applicationContent;
	}

	protected void addImpl(Component comp, Object constraints, int index) {
		comp.setFocusable(false);
		super.addImpl(comp, constraints, index);
	}

	private javax.swing.Action getAction(String actionName) {
		return actions.getAction(actionName);
	}

}
