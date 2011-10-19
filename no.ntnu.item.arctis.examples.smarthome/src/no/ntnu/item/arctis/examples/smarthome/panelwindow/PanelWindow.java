package no.ntnu.item.arctis.examples.smarthome.panelwindow;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.midi.Instrument;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Synthesizer;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import no.ntnu.item.arctis.runtime.Block;


public class PanelWindow extends Block {

	private JFrame frame;
	
	private JTextArea textArea;
	
	private JLabel readyLabel, goLabel, pinLabel, deniedLabel;
	private JButton[] keys;
	
	private int pin, index;
	
	private String pinStr="";
	
	private Receiver synthRcvr;
	
	private void initSound() {
	try {
			
			int instrumentNumber = 99;//74; //97
			//ShortMessage myMsg = new ShortMessage();
			// Play the note Middle C (60) moderately loud
			// (velocity = 93)on channel 4 (zero-based).
			//myMsg.setMessage(ShortMessage.NOTE_ON, 4, 84, 93); 
			Synthesizer synth = MidiSystem.getSynthesizer();
			synth.open();
			Instrument[] instruments = synth.getAvailableInstruments();
			int index = 0;
//			for(Instrument i:instruments) {
//				System.out.println(index++ + i.getName());
//			}
			synth.loadInstrument(instruments[instrumentNumber]);
			MidiChannel[] channels = synth.getChannels();
			int bank = instruments[instrumentNumber].getPatch().getBank();
			int program = instruments[instrumentNumber].getPatch().getProgram();
			
			channels[4].programChange(bank, program);
			synthRcvr = synth.getReceiver();
		} catch (MidiUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void soundKey() {
		try {
			ShortMessage myMsg = new ShortMessage();
			// 2 was 4
			myMsg.setMessage(ShortMessage.NOTE_ON, 2, 84, 93); 
			synthRcvr.send(myMsg, -1);
		} catch (InvalidMidiDataException e) {
			e.printStackTrace();
		}
	}
	
	private void soundGo() {
			try {
			ShortMessage myMsg = new ShortMessage();
			myMsg.setMessage(ShortMessage.NOTE_ON, 4, 72, 93); 
			synthRcvr.send(myMsg, -1);
		} catch (InvalidMidiDataException e) {
			e.printStackTrace();
		}
	}
	
	private void soundDenied() {
			try {
			ShortMessage myMsg = new ShortMessage();
			myMsg.setMessage(ShortMessage.NOTE_ON, 4, 48, 93); 
			synthRcvr.send(myMsg, -1);
		} catch (InvalidMidiDataException e) {
			e.printStackTrace();
		}
	}

	public void show() {
			initSound();
			frame = new JFrame("Panel");
	
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
			
			readyLabel = addIcon(panel, 0, "icon-card");
			pinLabel = addIcon(panel, 1, "icon-pin");
			deniedLabel = addIcon(panel, 2, "icon-denied");
			goLabel = addIcon(panel, 3, "icon-go");
	
			keys = new JButton[10];
		
			keys[1] = addNumericButton(1, 0, 1); 
			keys[2] = addNumericButton(2, 1, 1); 
			keys[3] = addNumericButton(3, 2, 1); 
	
			keys[4] = addNumericButton(4, 0, 2); 
			keys[5] = addNumericButton(5, 1, 2); 
			keys[6] = addNumericButton(6, 2, 2); 
	
			keys[7] = addNumericButton(7, 0, 3); 
			keys[8] = addNumericButton(8, 1, 3); 
			keys[9] = addNumericButton(9, 2, 3); 
	
			keys[0] = addNumericButton(0, 1, 4);
			
			frame.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					sendToBlock("CLOSED"); 
				}
			});
			
			frame.setVisible(true);
			frame.pack();
			showReady();

	}
	
	public void showReady() {
		readyLabel.setEnabled(true);
		pinLabel.setEnabled(false);
		deniedLabel.setEnabled(false);
		goLabel.setEnabled(false);
	}
	
	public void showPin() {
		readyLabel.setEnabled(false);
		pinLabel.setEnabled(true);
		deniedLabel.setEnabled(false);
		goLabel.setEnabled(false);
	}
	
	public void showDenied() {
		soundDenied();
		readyLabel.setEnabled(false);
		pinLabel.setEnabled(false);
		deniedLabel.setEnabled(true);
		goLabel.setEnabled(false);
		
		
	}
	
	public void showGo() {
		soundGo();
		readyLabel.setEnabled(false);
		pinLabel.setEnabled(false);
		deniedLabel.setEnabled(false);
		goLabel.setEnabled(true);
		
	}
	
	private JLabel addIcon(JPanel parent, int index, String file) {
		Image warnImage = ImageLoader.getImage(PanelWindow.class, file + ".jpg");
		Icon warnIcon = new ImageIcon(warnImage);
		JLabel label = new JLabel(warnIcon);
		label.setDisabledIcon(new ImageIcon(ImageLoader.getImage(PanelWindow.class, file + "-disabled.jpg")));
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL; 
		c.weightx = 0.25;
		c.gridx = index;
		c.gridy = 0;
		c.insets = new Insets(8, 8, 8, 8);
		parent.add(label, c);
		return label;
	}
	
	private JButton addNumericButton(final int number, int x, int y) {
		JButton button = new JButton("" + number);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendToBlock("KEY", number);
				soundKey();
			}
		});
		button.setEnabled(false);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL; 
		c.weightx = 0.33;
		c.gridx = x;
		c.gridy = y;
		frame.getContentPane().add(button, c);
		return button;
	}

	public void appendString(String string) {
		textArea.append("" + string + "\n");
	}

	public void hide() {
		frame.setVisible(false);
	}

	public static void main(String[] args) {
		PanelWindow p = new PanelWindow();
		p.show();
	}

	public void enableKeys() {
		for(JButton key: keys) {
			key.setEnabled(true);
		}
	}

	public void disableKeys() {
		for(JButton key: keys) {
			key.setEnabled(false);
		}
	}
	
	public void addToPin(int key) {
		double d = (Math.pow(2, index)) * pin;
		pin = (int) (key + d);  
		++ index;
		
		pinStr = pinStr+key;
	}
	
	public void resetPin() {
		pin = 0;
		index = 0;
	}

	public int getPin() {
		
		int val = Integer.valueOf(pinStr);
		pinStr = ""; // reset 	
		return val; //pin;
	}

	public void close() {
			sendToBlock("CLOSED");
	}

}

final class ImageLoader {

  private ImageLoader() {
  }

  public static Image getImage(Class relativeClass, String filename) {
    Image returnValue = null;
    InputStream is = relativeClass.getResourceAsStream(filename);
    if (is != null) {
      BufferedInputStream bis = new BufferedInputStream(is);
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      try {
        int ch;
        while ((ch = bis.read()) != -1) {
          baos.write(ch);
        }
        returnValue = Toolkit.getDefaultToolkit().createImage(
            baos.toByteArray());
      } catch (IOException exception) {
        System.err.println("Error loading: " + filename);
      }
    }
    return returnValue;
  }
}