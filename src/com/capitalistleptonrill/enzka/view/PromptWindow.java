package com.capitalistleptonrill.enzka.view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class PromptWindow extends JPanel {

	private static final long serialVersionUID = 1L;

	
	/**
	 * Creates a PromptWindow which asks for a color
	 */
	public static String askWildColor(JFrame frame) {
		Object[] possibilities = {"Red", "Green", "Blue", "Yellow"};
        String s = (String)JOptionPane.showInputDialog(
                            frame,
                            "Choose a color:\n",
                            "Prompt",
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            possibilities,
                            "ham");

        return s;
	}
	
	/**
	 * Creates a PromptWindow which asks to pass to the next player
	 */
	public void askToPass() {
	    JOptionPane.showMessageDialog(this,
	        "Pass to the next player and press OK",
	        "End of Turn",
	        JOptionPane.PLAIN_MESSAGE);
	}
}
