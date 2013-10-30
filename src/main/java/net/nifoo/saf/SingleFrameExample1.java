package net.nifoo.saf;

import java.awt.Font;
import java.util.EventObject;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.jdesktop.application.Resource;
import org.jdesktop.application.SingleFrameApplication;

public class SingleFrameExample1 extends SingleFrameApplication {
	
	@Resource(key="info")
	private String info;
	
	public void setInfo(String info){
		this.info = info;
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public void startup() {
		addExitListener(new CheckedExitListener());

		JLabel label = new JLabel(" Hello World$ ");
		label.setName("label");
		label.setFont(new Font("LucidaSans", Font.PLAIN, 32));

		show(label);

	}

	private class CheckedExitListener implements ExitListener {
		public boolean canExit(EventObject e) {
			System.out.println(info);
			int option = JOptionPane.showConfirmDialog(null, "Really Exit?");
			return option == JOptionPane.YES_OPTION;
		}

		public void willExit(EventObject e) {
		}
	};

	public static void main(String[] args) {
		launch(SingleFrameExample1.class, args);
	}
}