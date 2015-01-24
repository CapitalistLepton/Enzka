package com.capitalistleptonrill.enzka.controller;

import com.capitalistleptonrill.enzka.Enzka;
import com.capitalistleptonrill.enzka.model.Card;
import com.capitalistleptonrill.enzka.model.Deck;
import com.capitalistleptonrill.enzka.view.EnzkaWindow;
import com.capitalistleptonrill.enzka.view.PromptWindow;

public class ViewController implements IndexListener{
	
	private EnzkaWindow main;
	private static final int WINDOW_X = 10;
	private static final int WINDOW_Y = 20;
	private static final int WINDOW_WIDTH = 1000;
	private static final int WINDOW_HEIGHT = 600;
	private static final int CARD_WIDTH = 120;
	private static final int CARD_HEIGHT = (int)(1.5*CARD_WIDTH);
	
	private Deck currentHand;
	private Card currentDiscard;
	private Enzka master;
	
	public ViewController(Enzka creator) {
		main = new EnzkaWindow(WINDOW_X, WINDOW_Y, WINDOW_WIDTH, WINDOW_HEIGHT, this);
		master = creator;
	}
	
	public void displayHand(Deck hand, Card discard) {
		main.drawDiscardPile(250, 100, CARD_WIDTH, CARD_HEIGHT, discard.toString() + ".png");
		for(int i = 0; i < hand.getLength(); i++) {
			main.drawCardButton((CARD_WIDTH+10)*i+50, 350, CARD_WIDTH, CARD_HEIGHT, hand.showCard(i).toString() + ".png");
		}
		currentHand = hand;
		currentDiscard = discard;
	}

	@Override
	public void valueChanged(int index) {
		if(index == -2) {
			master.cardDrawn();
		} else if(currentDiscard.matches(currentHand.showCard(index))) {
			main.clearScreen();
			/*PromptWindow next = new PromptWindow((WINDOW_WIDTH - WINDOW_X)/2, (WINDOW_HEIGHT - WINDOW_Y)/2, 250, 100);
			while(!next.actionCompleted) {
				try {
					Thread.sleep(50);
				}catch(InterruptedException ex) {}
			}*/
			master.cardGiven(currentHand.getCard(index));
		}
	}
}