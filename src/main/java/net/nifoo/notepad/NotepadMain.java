package net.nifoo.notepad;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.View;

public class NotepadMain extends SingleFrameApplication {
	private View mainView;
	private JComponent mainPane;
	private Actions actions;
	private JMenuBar menuBar;
	private List<JToolBar> toolBars;
	private JComponent statusBar;

	/**
	 * @wbp.parser.entryPoint
	 */
	public void startup() {
		addExitListener(new CheckedExitListener());

		this.mainView = getMainView();

		this.actions = createActions();
		this.menuBar = createMenuBar();
		this.toolBars = createToolBars();
		this.statusBar = createStatusBar();
		this.mainPane = createMainPanel();

		mainView.setComponent(mainPane);
		mainView.setMenuBar(menuBar);
		mainView.setToolBars(toolBars);
		mainView.getToolBar().getParent().setLayout(new FlowLayout(FlowLayout.LEFT));
		mainView.setStatusBar(statusBar);

		show(mainView);
	}

	private Actions createActions() {
		Actions actions = new Actions(getContext());

		actions.putCategory("File", new FileActions());
		// actions.putCategory("Edit", new EditActions());

		return actions;
	}

	private JMenuBar createMenuBar() {
		JMenuBar mb = new MenuBar(getContext(), actions);
		getContext().getResourceMap(mb.getClass()).injectComponents(mb);
		return mb;
	}

	private List<JToolBar> createToolBars() {
		List<JToolBar> bars = new ArrayList<>();

		bars.add(new FileToolBar(getContext(), actions));
		bars.add(new EditToolBar(getContext(), actions));

		return bars;
	}

	private JComponent createMainPanel() {
		return new NoteEditor(getContext(), getMainView(), actions);
	}

	private JPanel createStatusBar() {
		return new StatusBar(getContext());
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
		launch(NotepadMain.class, args);
	}
}