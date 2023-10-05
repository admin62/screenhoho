package com;

import java.awt.AWTException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class SwcListner implements ActionListener {
	MouseMover msMv;
	NumericTextField txtIn;
	
	public SwcListner(NumericTextField txtIn) throws AWTException {
		this.txtIn = txtIn;
		
		msMv = new MouseMover(txtIn);
		msMv.main();
	}
	
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		
		if(!msMv.ifMouseMoving()) {
			System.out.println("현재 작동중 아님");
			button.setText("실행중");
			try {
				msMv.moveMouse();
			} catch (AWTException e1) {
				e1.printStackTrace();
				System.out.println(e1.getMessage());
			}
		}
		else if(msMv.ifMouseMoving()){
			System.out.println("현재 작동중");
			button.setText("중단됨");
			msMv.stopMouse();
		}
	}
}
