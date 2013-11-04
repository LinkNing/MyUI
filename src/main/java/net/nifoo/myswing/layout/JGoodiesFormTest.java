package net.nifoo.myswing.layout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class JGoodiesFormTest {

	public static void main(String args[]) {
		JFrame frame = new JFrame("JGoodies Form");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		FormLayout layout = new FormLayout("right:pref, 6dlu, 50dlu, 4dlu, center:50dlu", // columns
				"pref, 3dlu, pref, 3dlu, pref"); // rows
		CellConstraints cc = new CellConstraints();

		JPanel panel = new JPanel(layout);
		panel.add(new JLabel("Label1"), cc.xy(1, 1));
		panel.add(new JTextField(), cc.xywh(3, 1, 3, 1));
		panel.add(new JLabel("Label2"), cc.xy(1, 3));
		panel.add(new JTextField(), cc.xy(3, 3));
		panel.add(new JLabel("Label3"), cc.xy(1, 5));
		panel.add(new JTextField(), cc.xy(3, 5));
		panel.add(new JButton("按钮!"), cc.xy(5, 5));

		panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));

		frame.getContentPane().add(panel);

		frame.setSize(353, 159);
		//frame.pack();
		frame.setVisible(true);
	}
}
