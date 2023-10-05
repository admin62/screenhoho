package com;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.NumberFormatter;

public class TxtListener implements DocumentListener{
	JTextArea txtIn;
	static final String SLEEP_TERM = "1000";
	
	public TxtListener(JTextArea txtIn) {
		this.txtIn = txtIn;
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		check();
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		check();
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		check();
	}
	
	public void check() {
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){
				if (txtIn.getText().length() > 3 || !txtIn.getText().matches("\\d*")) { // if the text is not a three-digit number
					txtIn.setText(txtIn.getText().substring(0, txtIn.getText().length() - 1)); // remove the last character
					
					if(txtIn.getText().length() < 1 || txtIn.getText().isEmpty()) {
						txtIn.setText(SLEEP_TERM);
					}
				}
			}
		});
	}
}
