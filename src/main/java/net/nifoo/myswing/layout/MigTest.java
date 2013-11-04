package net.nifoo.myswing.layout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class MigTest {

	public static void main(String args[]) {
		JFrame frame = new JFrame("MigLayout Form");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//		JPanel panel = new JPanel();
		//		panel.setLayout(new MigLayout("insets 2","[grow][]","[grow][][][]"));
		//		
		//		panel.add(new JScrollPane(historyArea),"growx,growy");
		//		panel.add(rightPanel,"wrap,w 140!,span 1 4,growy");
		//		panel.add(tb,"growx,wrap");
		//		panel.add(new JScrollPane(inputArea),"growx,h 80!,wrap");
		//		panel.add(closeButton,"split 2,h 24!,align right");
		//		panel.add(sendButton,"h 80!,h 24!");

		//		panel.add(new JLabel("Label1"), cc.xy(1, 1));
		//		panel.add(new JTextField(), cc.xywh(3, 1, 3, 1));
		//		panel.add(new JLabel("Label2"), cc.xy(1, 3));
		//		panel.add(new JTextField(), cc.xy(3, 3));
		//		panel.add(new JLabel("Label3"), cc.xy(1, 5));
		//		panel.add(new JTextField(), cc.xy(3, 5));
		//		panel.add(new JButton("/u2026"), cc.xy(5, 5));

		JPanel panel = new JPanel(new MigLayout("", "[right, 50][]40[right][grow]", "[]10[grow]"));

		panel.add(new JLabel("First Name"), "cell 0 0");
		panel.add(new JTextField(10), "cell 1 0");

		JLabel label_2 = new JLabel("Last Name Name");
		panel.add(label_2, "cell 2 0");
		JTextField textField_1 = new JTextField(10);
		panel.add(textField_1, "cell 3 0");

		JLabel label_1 = new JLabel("Adress");
		panel.add(label_1, "cell 0 1");
		JTextArea textField = new JTextArea();
		panel.add(textField, "grow, span");

		frame.getContentPane().add(panel);
		frame.setSize(653, 412);
		//frame.pack();
		frame.setVisible(true);

	}
}
