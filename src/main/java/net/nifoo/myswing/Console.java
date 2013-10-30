package net.nifoo.myswing;

import java.awt.Container;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Console {

	public static void run(JFrame frame, int width, int height) {
		frame.setTitle(parseTitle(frame));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setBounds(100, 100, 450, 300);
		frame.setSize(width, height);
		frame.pack();
		frame.setVisible(true);
	}

	public static void run(JApplet applet, int width, int height) {
		JFrame frame = new JFrame();
		frame.setTitle(parseTitle(applet));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(applet);
		frame.setBounds(100, 100, 450, 300);
		frame.setSize(width, height);

		applet.init();
		applet.start();

		frame.pack();
		frame.setVisible(true);
	}

	public static void run(JPanel panel, int width, int height) {
		JFrame frame = new JFrame();
		frame.setTitle(parseTitle(panel));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(panel);
		//frame.setBounds(100, 100, 450, 300);
		frame.setSize(width, height);

		frame.pack();
		frame.setVisible(true);
	}

	private static String parseTitle(Container container) {
		String title = container.getName();
		return title;
	}
}
