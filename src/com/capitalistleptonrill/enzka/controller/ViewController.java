package com.capitalistleptonrill.enzka.controller;

import com.capitalistleptonrill.enzka.model.Card;
import com.capitalistleptonrill.enzka.model.Deck;
import com.capitalistleptonrill.enzka.view.EnzkaWindow;
import com.capitalistleptonrill.enzka.view.PromptWindow;

public class ViewController {
	
	private EnzkaWindow main;
	private static final int WINDOW_X = 10;
	private static final int WINDOW_Y = 20;
	private static final int WINDOW_WIDTH = 1000;
	private static final int WINDOW_HEIGHT = 600;
	private static final int CARD_WIDTH = 120;
	private static final int CARD_HEIGHT = (int)(1.5*CARD_WIDTH);
	
	public ViewController() {
		main = new EnzkaWindow(WINDOW_X, WINDOW_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
	}
	
	public Card displayHand(Deck hand, Card discard) {
		main.drawDiscardPile(250, 100, CARD_WIDTH, CARD_HEIGHT, discard.toString() + ".png");
		for(int i = 0; i < hand.getLength(); i++) {
			main.drawCardButton((CARD_WIDTH+10)*i+50, 350, CARD_WIDTH, CARD_HEIGHT, hand.showCard(i).toString() + ".png");
		}
		int index = main.getSelectedButton();
		while(index == -1 || !(discard.matches(hand.showCard(Math.max(0, index))))){
			try {
				Thread.sleep(50);
			}catch(InterruptedException ex) {}
			index = main.getSelectedButton();
		}
		
		if(index == -2) {
			return null;
		}
		
		main.clearScreen();
		
		PromptWindow next = new PromptWindow((WINDOW_WIDTH - WINDOW_X)/2, (WINDOW_HEIGHT - WINDOW_Y)/2, 250, 100);
		while(!next.actionCompleted) {
			try {
				Thread.sleep(50);
			}catch(InterruptedException ex) {}
		}
		//pop off card to game engine
		return hand.getCard(index);
	}
}