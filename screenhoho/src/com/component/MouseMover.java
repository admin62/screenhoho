package com.component;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalTime;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class MouseMover extends Thread {
	static boolean run;
	Robot rb;
	PointerInfo pt;
	static JTextArea txtIn;
	
	public MouseMover(JTextArea txtIn) {
		System.out.println("MouseMover()");
		MouseMover.txtIn = txtIn;
		run = true;
		pt = MouseInfo.getPointerInfo();
		
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
			PointerInfo pt2 = MouseInfo.getPointerInfo();
			System.out.println(LocalTime.now()+" MouseMover current "+pt.getLocation().x + "," + pt.getLocation().y);

			if((pt.getLocation().x == pt2.getLocation().x) && (pt.getLocation().y == pt2.getLocation().y)) {
				try {
					rb.mouseMove(pt.getLocation().x, pt.getLocation().y - 1);
					System.out.println(LocalTime.now()+" MouseMover not moved. so "+pt.getLocation().x + "," + pt.getLocation().y);
					
					Thread.sleep(10);
					rb.mouseMove(pt.getLocation().x, pt.getLocation().y + 1);
					System.out.println(LocalTime.now()+" MouseMover not moved so now move to"+pt.getLocation().x + "," + pt.getLocation().y);
					
					final int[] sleepDuration = new int[1];
					
					SwingUtilities.invokeAndWait(() -> {
						String text = txtIn.getText();
						sleepDuration[0] = text.isEmpty() ? 1 : Integer.parseInt(text);
					});
				    
				    Thread.sleep(sleepDuration[0]*1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
				} catch (InvocationTargetException e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
				}
			}
			
			pt = MouseInfo.getPointerInfo();
		}
    }
}
