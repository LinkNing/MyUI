package net.nifoo.notepad;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import org.jdesktop.application.ApplicationContext;

public class TextPaneStatus extends StatusBar {
	private static final long serialVersionUID = 1L;

	private JLabel lblCaret = new JLabel();

	private JLabel lblNote = new JLabel();

	public TextPaneStatus(ApplicationContext applicationContent) {
		super(applicationContent);
		setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

		add(Box.createHorizontalGlue());
		add(lblNote);
		add(Box.createHorizontalStrut(5));
		add(new JSeparator(SwingConstants.VERTICAL));
		add(Box.createHorizontalStrut(5));
		add(lblCaret);
	}

	public TextPaneStatus(ApplicationContext applicationContent, int i) {
		super(applicationContent);
		setLayout(new FlowLayout(FlowLayout.RIGHT));

		add(lblNote);
		JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
		separator.setPreferredSize(new Dimension(1, getHeight()));;
		add(separator);
		//add(new JSeparator(SwingConstants.VERTICAL));
		add(lblCaret);
	}

	public void setCaret(String info) {
		lblCaret.setText(info);
	}

	public void setNote(String note) {
		lblNote.setText(note);
	}

}
