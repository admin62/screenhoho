package com;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GraphicWindow extends JFrame {
	/**
	 * 
	 */
	static final long serialVersionUID = 1L;
	JPanel panel;
	JButton swc;
	
	public GraphicWindow() throws AWTException {
		setSize(300, 200); //크기 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("화면 호호기");
        setResizable(false);
		setLayout(new FlowLayout()); //버튼 크기 임의조정
		getContentPane().setBackground(Color.WHITE);
		
		swc = new JButton("실행중");
		swc.addActionListener(new SwcListner());
		swc.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		swc.setVisible(true);
		
		panel = new JPanel();
		panel.add(swc);
		panel.setBackground(Color.WHITE);
		
		add(panel);
		setVisible(true);
	}
}