package net.nifoo.notepad;

import java.awt.event.ActionEvent;

import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.JTextComponent;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

import org.apache.commons.lang3.StringUtils;
import org.jdesktop.application.Action;
import org.jdesktop.beans.AbstractBean;

public class EditActions extends AbstractBean {

	private UndoManager undo = new UndoManager();

	private boolean canUndo = false;

	private boolean canRedo = false;

	@Action
	public void upperCase(ActionEvent e) {
		JTextComponent txt = getTextComponent(e);
		if (txt != null) {
			String info = txt.getSelectedText();
			if (StringUtils.isNotBlank(info)) {
				txt.replaceSelection(info.toUpperCase());
			}
		}
	}

	@Action
	public void lowerCase(ActionEvent e) {
		JTextComponent txt = getTextComponent(e);
		if (txt != null) {
			String info = txt.getSelectedText();
			if (StringUtils.isNotBlank(info)) {
				txt.replaceSelection(info.toLowerCase());
			}
		}
	}

	@Action(enabledProperty = "canUndo")
	public void undo(ActionEvent e) {
		try {
			undo.undo();
		} catch (CannotUndoException ex) {
			System.out.println("Unable to undo: " + ex);
			ex.printStackTrace();
		}
		updateUndoState();
	}

	@Action(enabledProperty = "canRedo")
	public void redo(ActionEvent e) {
		try {
			undo.redo();
		} catch (CannotRedoException ex) {
			System.out.println("Unable to redo: " + ex);
			ex.printStackTrace();
		}
		updateUndoState();
	}

	private void updateUndoState() {
		setCanUndo(undo.canUndo());
		setCanRedo(undo.canRedo());
	}

	public boolean isCanUndo() {
		return canUndo;
	}

	public void setCanUndo(boolean canUndo) {
		boolean oldValue = this.canUndo;
		this.canUndo = canUndo;
		firePropertyChange("canUndo", oldValue, this.canUndo);
	}

	public boolean isCanRedo() {
		return canRedo;
	}

	public void setCanRedo(boolean canRedo) {
		boolean oldValue = this.canRedo;
		this.canRedo = canRedo;
		firePropertyChange("canRedo", oldValue, this.canRedo);
	}

	public UndoableEditListener getUndoableEditListener() {
		return new UndoableEditListener() {
			public void undoableEditHappened(UndoableEditEvent e) {
				undo.addEdit(e.getEdit());
				updateUndoState();
			}
		};
	}

	/**
	 * Determines the component to use for the action.
	 * This if fetched from the source of the ActionEvent
	 * if it's not null and can be narrowed.  Otherwise,
	 * the last focused component is used.
	 *
	 * @param e the ActionEvent
	 * @return the component
	 */
	protected final JTextComponent getTextComponent(ActionEvent e) {
		if (e != null) {
			Object o = e.getSource();
			if (o instanceof JTextComponent) {
				return (JTextComponent) o;
			}
		}
		return null;
	}

	protected final <T> T getSourceComponent(Class<T> clazz, ActionEvent e) {
		if (e != null) {
			Object o = e.getSource();
			if (clazz.isInstance(o)) {
				return (T) o;
			}
		}
		return null;
	}

}
