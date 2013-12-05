package net.nifoo.notepad;

import javax.swing.JPanel;

import org.jdesktop.application.ApplicationContext;

public class StatusBar extends JPanel {
	private static final long serialVersionUID = 1L;

	private ApplicationContext applicationContent;

	public StatusBar(ApplicationContext applicationContent) {
		this.applicationContent = applicationContent;
	}

	private ApplicationContext getContext() {
		return applicationContent;
	}

	private javax.swing.Action getAction(String actionName) {
		ApplicationContext ac = getContext();
		return ac.getActionMap().get(actionName);
	}

}
