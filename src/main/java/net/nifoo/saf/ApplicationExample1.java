package net.nifoo.saf;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

import org.jdesktop.application.Application;

public class ApplicationExample1 extends Application {
	JFrame mainFrame = null;

	@Override
	protected void startup() {
		mainFrame = new JFrame(" Hello World ");
		mainFrame.addWindowListener(new MainFrameListener());
		mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		JLabel label = new JLabel("Hello World", JLabel.CENTER);
		label.setFont(new Font("LucidaSans", Font.PLAIN, 32));
		mainFrame.add(label, BorderLayout.CENTER);

		mainFrame.pack();
		mainFrame.setLocationRelativeTo(null); // center the window
		mainFrame.setVisible(true);
	}

	private class MainFrameListener extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			exit(e);
		}
	}

	public static void main(String[] args) {
		launch(ApplicationExample1.class, args);
	}
}
