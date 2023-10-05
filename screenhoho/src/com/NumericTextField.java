package com;

import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class NumericTextField extends JTextField {
	private static final long serialVersionUID = 1L;

	public NumericTextField() {
		((AbstractDocument) getDocument()).setDocumentFilter(new DocumentFilter() {
			@Override
			public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
				String string = fb.getDocument().getText(0, fb.getDocument().getLength()) + text;

				if (string.length() > 0 && string.matches("\\d+") && Integer.parseInt(string) <= 999 && Integer.parseInt(string) >= 1) {
					super.replace(fb, offset, length, text, attrs); // Accept input
				} else if (string.length() == 0 || string.isEmpty() || string.isBlank()) { 
					// If the field is empty set a default value of "1"
					super.replace(fb, offset,length,"1",attrs);
				}
			}
		});
	}
}