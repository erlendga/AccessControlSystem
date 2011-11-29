package no.ntnu.item.arctis.acsystem.registerandderegistergui;

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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


import no.ntnu.item.arctis.library.proxies.Message;
import no.ntnu.item.arctis.runtime.Block;

public class RegisterandDeregisterGUI extends Block {

	private JFrame frame;

	private JButton registerBtn;
	private JButton deregisterBtn;
	JScrollPane scrollpane = new JScrollPane();
	JTextArea output = new JTextArea(40, 80);

	public no.ntnu.item.arctis.library.discovery.discoverservice.RegisterEntry entry;

	// Instance parameter. Edit only in overview page.
	public final java.lang.String service;

	public void printMessage(Message msg){
		String sender = "Sender: " + msg.getSender().toString();
		String receiver = "Receiver: " + msg.getReceiver().toString();
		String paylod = "Payload: " + msg.getPayload().toString();
		output.append("\n"+sender+"\n"+receiver+"\n"+paylod+"\n");
		output.setCaretPosition(output.getDocument().getLength());
	}
	
	public void initGUI() {

		frame = new JFrame(""+service+"");

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
		
		
		scrollpane.setSize(40, 80);
		frame.getContentPane().add(scrollpane);
		output.setEditable(false);
		scrollpane.getViewport().add(output);
		

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

	public void disableDereg() {
		deregisterBtn.setEnabled(false);
		registerBtn.setEnabled(true);
	}

	public void disableReg() {
		registerBtn.setEnabled(false);
		deregisterBtn.setEnabled(true);
	}

	// Do not edit this constructor.
	public RegisterandDeregisterGUI(java.lang.String service) {
	    this.service = service;
	}
}
