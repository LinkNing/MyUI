package net.nifoo.myswing.databinding;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JToggleButton;
import javax.swing.UIManager;

import net.nifoo.myswing.Console;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Binding;
import org.jdesktop.beansbinding.BindingGroup;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.beansbinding.Converter;
import org.jdesktop.beansbinding.ELProperty;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;

public class ConverterDemo extends JFrame {
	JLabel lightBulb;
	JToggleButton lightSwitch;
	BindingGroup bindingGroup;
	Converter<Boolean, Color> converter;

	/** Creates new form ButtonBinding */
	public ConverterDemo() {
		converter = new Converter<Boolean, Color>() {

			@Override
			public Color convertForward(Boolean isSelected) {
				return isSelected ? Color.YELLOW : Color.BLUE;
			}

			@Override
			public Boolean convertReverse(Color arg0) {
				return arg0 == Color.YELLOW;
			}
		};
		initComponents();
	}

	private void initComponents() {

		lightBulb = new javax.swing.JLabel("Color on me");
		lightSwitch = new javax.swing.JToggleButton("Change Color");

		getContentPane().add(lightBulb, BorderLayout.CENTER);
		getContentPane().add(lightSwitch, BorderLayout.SOUTH);

		//		Binding binding = Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ_WRITE,
		//
		//		lightSwitch, ELProperty.create("${selected}"),
		//
		//		lightBulb, BeanProperty.create("background"));
		//
		//		binding.setConverter(converter);
		//
		//		binding.bind();
		initDataBindings();

		//		lightSwitch.
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e1) {
			// impossible
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Console.run(new ConverterDemo(), 800, 600);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	protected void initDataBindings() {
		BeanProperty<JToggleButton, Boolean> jToggleButtonBeanProperty = BeanProperty.create("selected");
		BeanProperty<JLabel, Color> jLabelBeanProperty = BeanProperty.create("foreground");

		AutoBinding<JToggleButton, Boolean, JLabel, Color> autoBinding =

		Bindings.createAutoBinding(UpdateStrategy.READ_WRITE, //
				lightSwitch, jToggleButtonBeanProperty, //
				lightBulb, jLabelBeanProperty);

		autoBinding.setConverter(converter);

		autoBinding.bind();
	}
}