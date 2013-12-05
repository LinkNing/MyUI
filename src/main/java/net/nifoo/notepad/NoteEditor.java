package net.nifoo.notepad;

import java.awt.BorderLayout;
import java.awt.Event;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.StyledDocument;
import javax.swing.text.StyledEditorKit;

import org.jdesktop.application.ApplicationContext;
import org.jdesktop.application.View;

public class NoteEditor extends JPanel {
	private static final long serialVersionUID = 1L;

	private ApplicationContext applicationContent;

	private View mainView;

	private Actions actions;

	private TextPaneStatus statusBar;

	JTextPane textPane = new JTextPane();

	EditActions editActions = new EditActions();

	public NoteEditor(ApplicationContext applicationContent, View mainView, Actions actions) {
		super(new BorderLayout());

		this.applicationContent = applicationContent;
		this.mainView = mainView;
		this.actions = actions;

		initActions();
		initInputMap();
		initComponents();
		this.applicationContent.getResourceMap(getClass()).injectComponents(this);
		//      bind(); 
		//      installRenderers(); 
		//		init();
	}

	private ApplicationContext getContext() {
		return applicationContent;
	}

	/**
	 * Register the actions that this class can handle.
	 */
	protected void initActions() {
		//		ActionMap map = getActionMap();
		//		map.put("copy", textPane.getActionMap().get(DefaultEditorKit.copyAction));
		//		map.put("cut", textPane.getActionMap().get(DefaultEditorKit.cutAction));
		//		map.put("paste", textPane.getActionMap().get(DefaultEditorKit.pasteAction));

		ActionMap textPaneActions = textPane.getActionMap();
		ActionMap actionMap = getContext().getActionMap(getClass(), this);

		installActions(actionMap, textPaneActions);
		installActions(actions.getCategory("File"), textPaneActions);
		installActions(actions.getActionMap(editActions), textPaneActions);

		textPaneActions.put("bold", new StyledEditorKit.BoldAction());
		textPaneActions.put("italic", new StyledEditorKit.ItalicAction());
		textPaneActions.put("underline", new StyledEditorKit.UnderlineAction());

	}

	private void installActions(ActionMap actionMap, ActionMap target) {
		if (actionMap == null || actionMap.keys() == null)
			return;

		for (Object key : actionMap.keys()) {
			target.put(key, actionMap.get(key));
		}
	}

	private void initComponents() {
		this.add(new JScrollPane(textPane), BorderLayout.CENTER);
		textPane.getDocument().addUndoableEditListener(editActions.getUndoableEditListener());
		textPane.getDocument().addDocumentListener(new MyDocumentListener());
		textPane.addCaretListener(new MyCaretListener());

		StyledDocument styledDoc = textPane.getStyledDocument();
		if (styledDoc instanceof AbstractDocument) {
			AbstractDocument doc = (AbstractDocument) styledDoc;
			doc.setDocumentFilter(new MyDocumentFilter());
		}

		statusBar = new TextPaneStatus(applicationContent);
		textPane.addFocusListener(new StatusBarRender());
	}

	private void initInputMap() {
		InputMap inputMap = textPane.getInputMap();
		KeyStroke key = KeyStroke.getKeyStroke(KeyEvent.VK_B, Event.CTRL_MASK);
		inputMap.put(key, DefaultEditorKit.selectionPreviousWordAction);
	}

	protected class MyDocumentListener implements DocumentListener {
		public void insertUpdate(DocumentEvent e) {
			displayEditInfo(e);
		}

		public void removeUpdate(DocumentEvent e) {
			displayEditInfo(e);
		}

		public void changedUpdate(DocumentEvent e) {
			displayEditInfo(e);
		}

		private void displayEditInfo(DocumentEvent e) {
			Document document = (Document) e.getDocument();
			int changeLength = e.getLength();
			statusBar.setNote(e.getType().toString() + ": " + changeLength + " character"
					+ ((changeLength == 1) ? ". " : "s. ") + " Text length = " + document.getLength() + ".");
		}
	}

	class MyDocumentFilter extends DocumentFilter {
		@Override
		public void insertString(FilterBypass fb, int offs, String str, AttributeSet attrs) throws BadLocationException {
			if ((fb.getDocument().getLength() + str.length()) <= 512)
				super.insertString(fb, offs, str, attrs);
			else
				Toolkit.getDefaultToolkit().beep();
		}

		@Override
		public void replace(FilterBypass fb, int offs, int length, String str, AttributeSet attrs)
				throws BadLocationException {
			if ((fb.getDocument().getLength() + str.length()) <= 512)
				super.replace(fb, offs, length, str, attrs);
			else
				Toolkit.getDefaultToolkit().beep();
		}
	}

	class StatusBarRender extends FocusAdapter {
		JComponent oldStaBar;

		@Override
		public void focusGained(FocusEvent e) {
			oldStaBar = mainView.getStatusBar();
			mainView.setStatusBar(statusBar);
		}

		@Override
		public void focusLost(FocusEvent e) {
			mainView.setStatusBar(oldStaBar);
		}
	}

	class MyCaretListener implements CaretListener {
		private String newline = "\n";

		public void caretUpdate(CaretEvent e) {
			//Get the location in the text
			int dot = e.getDot();
			int mark = e.getMark();
			if (dot == mark) { // no selection
				try {
					Rectangle caretCoords = textPane.modelToView(dot);
					//Convert it to view coordinates
					setText("caret: text position: " + dot + ", view location = [" + caretCoords.x + ", "
							+ caretCoords.y + "]" + newline);
				} catch (BadLocationException ble) {
					setText("caret: text position: " + dot + newline);
				}
			} else if (dot < mark) {
				setText("selection from: " + dot + " to " + mark + newline);
			} else {
				setText("selection from: " + mark + " to " + dot + newline);
			}
		}
	}

	private void setText(String info) {
		statusBar.setCaret(info);
	}

}
