package com.capitalistleptonrill.enzka.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.capitalistleptonrill.enzka.controller.ViewController;

public class PromptWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JButton button;

	/**
	 * Value of whether the user has pressed the button or typed in input
	 */
	public boolean actionCompleted = false;

	public String color = "";

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
		textField.setActionCommand("textEntered");

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

	/**
	 * Creates a PromptWindow which asks for a color
	 *
	 * @param x -x coordinate of the window
	 * @param y -y coordinate of the window
	 * @param width -width of the window
	 * @param height -height of the window
	 * @param question -string to ask the user to get desired input
	 */
	public PromptWindow(int x, int y, int width, int height, ViewController master) {
		super("Prompt");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(x, y, width, height);
		this.setLayout(new FlowLayout());

		JLabel label = new JLabel("What color do you want?");

		JRadioButton redButton = new JRadioButton("Red");
		redButton.setActionCommand("Red");
		redButton.addActionListener(this);
		redButton.setVisible(true);

		JRadioButton blueButton = new JRadioButton("Blue");
		blueButton.setActionCommand("Blue");
		blueButton.addActionListener(this);

		JRadioButton greenButton = new JRadioButton("Green");
		greenButton.setActionCommand("Green");
		greenButton.addActionListener(this);

		JRadioButton yellowButton = new JRadioButton("Yellow");
		yellowButton.setActionCommand("Yellow");
		yellowButton.addActionListener(this);

		ButtonGroup group = new ButtonGroup();
	    group.add(redButton);
	    group.add(blueButton);
	    group.add(greenButton);
	    group.add(yellowButton);

	    this.getContentPane().add(label);
        add(label);
		add(redButton);
		add(blueButton);
		add(greenButton);
		add(yellowButton);
		this.setVisible(true);
	}

	public PromptWindow() {
	    JOptionPane.showMessageDialog(this,
	        "Pass to the next player and press OK",
	        "End of Turn",
	        JOptionPane.PLAIN_MESSAGE);
	}

	public PromptWindow(int bla) {
		//Add the ubiquitous "Hello World" label.
        JLabel label = new JLabel("Hello World");
        this.getContentPane().add(label);

        //Display the window.
        this.pack();
        this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("buttonPressed")) {
			actionCompleted = true;
			this.dispose();
		} else if(e.getActionCommand().equals("Red")) {	
			actionCompleted = true;
			color = "Red";
			this.dispose();
		} else if(e.getActionCommand().equals("Blue")) {
			actionCompleted = true;
			color = "Blue";
			this.dispose();
		} else if(e.getActionCommand().equals("Green")) {
			actionCompleted = true;
			color = "Green";
			this.dispose();
		} else if(e.getActionCommand().equals("Yellow")) {
			actionCompleted = true;
			color = "Yellow";
			this.dispose();
		}
	}
}
