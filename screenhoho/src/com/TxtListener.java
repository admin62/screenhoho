package com;

import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class TxtListener implements DocumentListener{
	NumericTextField txtIn;
	static final String SLEEP_TERM = "1";
	
	public TxtListener(NumericTextField txtIn) {
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
