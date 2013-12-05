package net.nifoo.notepad;

import java.awt.Component;

import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import org.jdesktop.application.ApplicationContext;

public class EditToolBar extends JToolBar {
	private static final long serialVersionUID = 1L;

	private ApplicationContext applicationContent;
	private Actions actions;

	public EditToolBar(ApplicationContext applicationContent, Actions actions) {
		super("Edit commands");
		this.applicationContent = applicationContent;
		this.actions = actions;

		init();
	}

	private void init() {

		this.add(getAction("undo"));
		this.add(getAction("redo"));
		this.addSeparator(); 

		this.add(getAction("copy"));
		this.add(getAction("cut"));
		this.add(getAction("paste"));
		
		this.addSeparator();

		this.add(new JToggleButton(getAction("bold")));
		this.add(new JToggleButton(getAction("italic")));
		this.add(new JToggleButton(getAction("underline")));

		this.addSeparator();
		this.add(getAction("upperCase"));
		this.add(getAction("lowerCase"));
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
