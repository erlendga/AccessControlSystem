package no.ntnu.item.arctis.examples.smarthome.home1;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import no.ntnu.item.arctis.runtime.Block;

public class Home1 extends Block {

	public void op() {
		JFrame frame = new JFrame("regg");;
		JButton registerBtn;
		JButton deregisterBtn;
		
		
		frame.getContentPane().setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		c.insets = new Insets(10, 10, 10, 10);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, c);
		panel.setLayout(new GridBagLayout());
		panel.setBorder(BorderFactory.createLoweredBevelBorder());
		panel.setBackground(Color.BLACK);

		registerBtn = new  JButton("Register");
		registerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendToBlock("Register"); //, number);
			}
		});
		frame.getContentPane().add(registerBtn);

		deregisterBtn = new JButton("Deregister");
		deregisterBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendToBlock("Deregister"); //, number);
			}
		});
		deregisterBtn.setEnabled(false);
		frame.getContentPane().add(deregisterBtn);

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				sendToBlock("Closed"); 
			}
		});

		frame.setVisible(true);
		frame.pack();
	}

	// Do not edit this constructor.
	public Home1() {
	}
}