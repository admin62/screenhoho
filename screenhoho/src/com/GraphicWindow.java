package com;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GraphicWindow extends JFrame {
    private static final long serialVersionUID = 1L;

    private static final int FRAME_WIDTH = 300;
    private static final int FRAME_HEIGHT = 200;

    private static final int BUTTON_WIDTH = 150;
    private static final int BUTTON_HEIGHT = 100;

    private JPanel panel;
    private JButton swcButton;

    public GraphicWindow() throws AWTException {
        setupFrame();
        setupPanel();
        add(panel);
        setVisible(true);
        
        // Now that the frame is visible, we can setup the button.
        setupButton();
   }

    private void setupFrame() {
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("화면 호호기");
        setResizable(false);
        
        getContentPane().setBackground(Color.WHITE);
   }

   private void setupPanel() {
       panel = new JPanel();
       panel.setLayout(null); // disable layout manager
       panel.setBackground(Color.WHITE);
   }

   private void setupButton() throws AWTException {
	    swcButton = new JButton("실행중");
	    swcButton.addActionListener(new SwcListner());
	    swcButton.setFont(new Font("맑은 고딕", Font.PLAIN, 15));

	    // Calculate the position to center the button
	    int xPos = (getWidth() - getInsets().left - getInsets().right - BUTTON_WIDTH) / 2;
	    int yPos = (getHeight() - getInsets().top - getInsets().bottom - BUTTON_HEIGHT) / 2;

	    swcButton.setBounds(xPos, yPos, BUTTON_WIDTH, BUTTON_HEIGHT);

	    panel.add(swcButton); // Add button to the panel
	}

}
