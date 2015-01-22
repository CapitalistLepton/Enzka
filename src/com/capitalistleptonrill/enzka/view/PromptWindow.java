package com.capitalistleptonrill.enzka.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class PromptWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JButton button;
	
	/**
	 * Value of whether the user has pressed the button or typed in input
	 */
	public boolean actionCompleted = false;
	
	/**
	 * Creates a PromptWindow which has a JTextField
	 * 
	 * @param x -x coordinate of the window
	 * @param y -y coordinate of the window
	 * @param width -width of the window
	 * @param height -height of the window
	 * @param question -string to ask the user to get desired input
	 */
	public PromptWindow(int x, int y, int width, int height, String question) {
		super("Prompt");
		this.setBounds(x, y, width, height);
		
		JLabel label = new JLabel(question);
		textField = new JTextField(20);
		textField.addActionListener(this);
		textField.setActionCommand("textEnetered");
        
        add(label);
		add(textField);
		this.setVisible(true);
	}
	
	/**
	 * Creates a PromptWindow which asks to pass to the next player
	 * 
	 * @param x -x coordinate of the window
	 * @param y -y coordinate of the window
	 * @param width -width of the window
	 * @param height -height of the window
	 */
	public PromptWindow(int x, int y, int width, int height) {
		super("Prompt");
		this.setBounds(x, y, width, height);
		this.setLayout(new FlowLayout());
		
		JLabel label = new JLabel("Pass to next player and press continue");
		button = new JButton("Continue");
		button.setActionCommand("buttonPressed");
		button.addActionListener(this);

		add(label);
		add(button);
		this.validate();
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("buttonPressed")) {
			actionCompleted = true;	
			this.dispose();
		}
	}
}