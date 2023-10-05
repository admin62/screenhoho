package com;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.awt.Robot;

import javax.swing.JTextArea;

public class MouseMover extends Thread {
	static boolean run;
	Robot rb;
	PointerInfo pt;
	static JTextArea txtIn;
	
	public MouseMover(JTextArea txtIn) {
		System.out.println("MouseMover()");
		MouseMover.txtIn = txtIn;
		run = true;
		
		try {
			rb = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	public void moveMouse() throws AWTException {
		System.out.println("작동");
		run = true;
		this.main();
	}
	
	public void stopMouse() {
		System.out.println("중단");
		run = false;
	}
	
	public boolean ifMouseMoving() {
		System.out.println("작동상태:"+run);
		return run;
	}
	
    private static class SingletonHelper {
        private static final MouseMover INSTANCE = new MouseMover(txtIn);
    }
    
    public static MouseMover getInstance(){
    	System.out.println("getInstance");
        return SingletonHelper.INSTANCE;
    }
    
    public void main() {
    	Thread trd = new MouseMover(txtIn);
    	trd.start();
    }
    
    @Override
    public void run() {
		while(run) {
			pt = MouseInfo.getPointerInfo();
			rb.mouseMove(pt.getLocation().x, pt.getLocation().y + 1);
			try {
				Thread.sleep(10); //0.01초 대기
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			pt = MouseInfo.getPointerInfo();
			rb.mouseMove(pt.getLocation().x, pt.getLocation().y - 1);
			
			System.out.println("MouseMover "+pt.getLocation().x + "," + pt.getLocation().y);
			
			Integer sleepDuration = 0;
			try {
				// Parse the text from txtIn as an integer and use it as the sleep duration.
				sleepDuration = txtIn.getText().isEmpty() ? 1000 : Integer.parseInt(txtIn.getText());
				Thread.sleep(sleepDuration*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
			
			System.out.println("waiting for " + sleepDuration + " sec");
		}
    }
}
