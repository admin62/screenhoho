package com.component;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class TxtListener implements DocumentListener{
	JTextArea txtIn;
	static final String SLEEP_TERM = "1";
	
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
		System.out.println("check() txtIn"+txtIn.getText());
		if (txtIn.getText().length() > 3 || !txtIn.getText().matches("\\d*")) { // if the text is not a three-digit number
			txtIn.setText(txtIn.getText().substring(0, txtIn.getText().length() - 1)); // remove the last character
		}
		
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
				if (txtIn.getText().isEmpty() || txtIn.getText().length() <= 0) txtIn.setText("1");
			}
		});
	}
}
