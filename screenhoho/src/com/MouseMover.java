package com;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.awt.Robot;

public class MouseMover extends Thread {
	static boolean run;
	Robot rb;
	PointerInfo pt;
	
	public MouseMover() {
		System.out.println("MouseMover()");
		run = true;
		try {
			rb = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
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
        private static final MouseMover INSTANCE = new MouseMover();
    }
    
    public static MouseMover getInstance(){
    	System.out.println("getInstance");
        return SingletonHelper.INSTANCE;
    }
    
    public void main() {
    	Thread trd = new MouseMover();
    	trd.start();
    }
    
    @Override
    public void run() {
		while(run) {
			pt = MouseInfo.getPointerInfo();
			rb.mouseMove(pt.getLocation().x, pt.getLocation().y + 1);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} //0.01초 대기
			pt = MouseInfo.getPointerInfo();
			rb.mouseMove(pt.getLocation().x, pt.getLocation().y - 1);
			
			System.out.println("MouseMover "+pt.getLocation().x + "," + pt.getLocation().y);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} //570초 대기
			
			System.out.println("waiting for 570000 millisec");
		}
    }
}
