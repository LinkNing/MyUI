package net.nifoo.notepad;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileFilter;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.AbstractFileFilter;
import org.apache.commons.lang3.StringUtils;
import org.jdesktop.application.Action;

public class FileActions {

	/**
	 * Load the specified file into the textPane or popup an error
	 * dialog if something goes wrong.  
	 */
	@Action
	public void open(ActionEvent e) {
		if (e == null || !(e.getSource() instanceof JTextPane)) {
			return;
		}

		JTextPane textPane = (JTextPane) e.getSource();
		JFileChooser chooser = new JFileChooser();
		chooser.addChoosableFileFilter(new MyFileFilter("txt"));
		chooser.addChoosableFileFilter(new MyFileFilter("rtf"));

		int option = chooser.showOpenDialog(null);
		if (option == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			try {
				textPane.setPage(file.toURI().toURL());
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "打开文件出错", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * Replace the contents of the textPane with the value of the
	 * "defaultText" resource.  
	 */
	@Action
	public void close(ActionEvent e) {
		JTextPane textPane = getSourceComponent(JTextPane.class, e);
		if (textPane != null) {
			textPane.setText("");
		}
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

	class MyFileFilter extends FileFilter {
		String[] extensions;

		public MyFileFilter(String extension) {
			this.extensions = extension.split(",");
		}

		@Override
		public String getDescription() {
			StringBuilder desc = new StringBuilder();
			for (String ex : extensions) {
				desc.append("*.").append(ex).append(" | ");
			}
			return desc.substring(0, desc.length() - 2);
		}

		@Override
		public boolean accept(File f) {
			if (f.isDirectory()) {
				return false;
			}

			String extension = FilenameUtils.getExtension(f.getName());
			for (String ex : extensions) {
				if (StringUtils.equals(extension, ex)) {
					return true;
				}
			}

			return false;
		}
	}

}