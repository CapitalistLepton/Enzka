package com.capitalistleptonrill.enzka.view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class PromptWindow {
	
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
	public static void askToPass(JFrame frame) {
	    JOptionPane.showMessageDialog(frame,
	        "Pass to the next player and press OK",
	        "End of Turn",
	        JOptionPane.PLAIN_MESSAGE);
	}
}
