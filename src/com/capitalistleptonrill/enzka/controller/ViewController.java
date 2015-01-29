package com.capitalistleptonrill.enzka.controller;

import com.capitalistleptonrill.enzka.Enzka;
import com.capitalistleptonrill.enzka.model.Card;
import com.capitalistleptonrill.enzka.model.Deck;
import com.capitalistleptonrill.enzka.model.EnzkaActions;
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

	private void changeDiscardPileColor() {
		PromptWindow window = new PromptWindow(500,200,300,200,this);
		while(!window.actionCompleted) {
			try {
				Thread.sleep(50);
			}catch(InterruptedException ex) {}
		}
		System.out.println(window.color);
		//Card blank = new Card(window.color, 0);
		//master.cardGiven(blank);//put a 0 card of that color on the discard pile
	}
	
	@Override
	public void valueChanged(int index) {
		Card card = currentHand.showCard(index);
		EnzkaActions act = card.getAction();
		if(currentDiscard.matches(card)) {
			main.clearScreen();
			new PromptWindow();//makes basic prompt to pass the computer
			master.cardGiven(currentHand.getCard(index));
			if(act != EnzkaActions.NO_ACTION){
				switch(act) {
				case SKIP: master.playerSkipped();break;
				case REVERSE: master.playReversed();break;
				case DRAW_TWO: master.playerDrawCards(2);break;
				case WILD: changeDiscardPileColor();break;
				case WILD_DRAW_FOUR: changeDiscardPileColor();master.playerDrawCards(4);
				}
			}
		}
	}

	@Override
	public void deckPressed() {
		master.cardDrawn();
	}
}