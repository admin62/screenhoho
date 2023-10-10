package com;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class GraphicWindow extends JFrame {
	static final long serialVersionUID = 1L;

	static final Integer FRAME_WIDTH = 300;
	static final Integer FRAME_HEIGHT = 200;

	static final Integer BUTTON_WIDTH = 150;
	static final Integer BUTTON_HEIGHT = 100;
	
	static final String SLEEP_TERM = "570"; // 초 단위
	static final String FONT_FAMILY = "맑은 고딕";

	JPanel panel;
	JButton swcButton;
	JLabel lbl;
	JTextArea txtIn;

	public GraphicWindow() throws AWTException {
		setupFrame();
		setupPanel();
		add(panel);
        
		setupTxtIn();
		setupLabel();
		setupButton();
		
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		
		setVisible(true);
	}

	private void setupFrame() {
		//setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("화면호호기");
		setResizable(false);
        
		getContentPane().setBackground(Color.WHITE);
	}

	private void setupPanel() {
		panel = new JPanel();
		panel.setLayout(null); // disable layout manager
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, getContentPane().getWidth(), getContentPane().getHeight());
	}

	private void setupButton() throws AWTException {
		swcButton = new JButton("실행중");
		swcButton.addActionListener(new SwcListener(txtIn));
		swcButton.setFont(new Font(FONT_FAMILY, Font.PLAIN, 15));
		
		Insets insets = swcButton.getInsets();
		int buttonWidthWithInsets = BUTTON_WIDTH + insets.left; // 왜인지는 모르겠으나 inset 오차가 양쪽중 하나만 있음.
		int buttonHeightWithInsets = BUTTON_HEIGHT + insets.top + insets.bottom; // bottom 은 포기
		
		// Calculate the position to center the button
		int xPos = (FRAME_WIDTH - buttonWidthWithInsets) / 2;
		int yPos = (FRAME_HEIGHT - buttonHeightWithInsets) / 2;
		System.out.println("xPos "+xPos);
		System.out.println("yPos "+yPos);
		System.out.println("this.getWidth() "+this.getWidth());
		System.out.println("this.getHeight() "+this.getHeight());
		System.out.println("buttonWidthWithInsets "+buttonWidthWithInsets);
		System.out.println("buttonHeightWithInsets "+buttonHeightWithInsets);
		System.out.println("inset "+insets.left);
		System.out.println("inset "+insets.right);
		System.out.println("inset "+swcButton.getInsets());

		swcButton.setBounds(xPos, 50, BUTTON_WIDTH, BUTTON_HEIGHT);

		panel.add(swcButton); // Add button to the panel
	}
   
	private void setupLabel() {
		lbl = new JLabel("초 후에 커서를 움직입니다.");
		lbl.setFont(new Font(FONT_FAMILY, Font.PLAIN, 15));
		lbl.setBounds(50, 0, 200, 20);
		
		panel.add(lbl);
	}
	
	private void setupTxtIn() {
		txtIn = new JTextArea();
		txtIn.setBounds(0, 0, 50, 20);
		txtIn.setFont(new Font(FONT_FAMILY, Font.PLAIN, 15));
		txtIn.setText(SLEEP_TERM);

		TxtListener listener = new TxtListener(txtIn);
		txtIn.getDocument().addDocumentListener(listener);

		Border lineBorder = BorderFactory.createLineBorder(Color.gray, 1);
		txtIn.setBorder(lineBorder);
		
		panel.add(txtIn);
	}
}
