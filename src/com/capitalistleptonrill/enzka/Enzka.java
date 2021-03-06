package com.capitalistleptonrill.enzka;

import com.capitalistleptonrill.enzka.controller.CardListener;
import com.capitalistleptonrill.enzka.controller.ViewController;
import com.capitalistleptonrill.enzka.model.Card;
import com.capitalistleptonrill.enzka.model.Deck;

public class Enzka implements CardListener{

	private static final int NUMBER_OF_PLAYERS = 3;
	private static final int CARDS_IN_A_HAND = 7;

	private Deck gameDeck;
	private Deck discardPile;
	private Deck[] players;
	private ViewController display;
	private int currentPlayer;
	private boolean cardPlayed;
	private boolean deckPressed;
	private boolean skipNext;
	private int playDirection;
	
	public static void main(String[] args) {
		Enzka enzka = new Enzka();
		enzka.currentPlayer = 0;
		enzka.cardPlayed = false;
		enzka.deckPressed = false;
		enzka.playDirection = 1;
		while(true) {
			enzka.display.displayHand(enzka.players[enzka.currentPlayer], enzka.discardPile.showCard(enzka.discardPile.getLength()-1));
			while(!enzka.cardPlayed){
				try {
					Thread.sleep(50);
				}catch(InterruptedException ex) {}
				if(enzka.deckPressed) {
					enzka.display.displayHand(enzka.players[enzka.currentPlayer], enzka.discardPile.showCard(enzka.discardPile.getLength()-1));
					enzka.deckPressed = false;
				}
			}
			enzka.cardPlayed = false;
			enzka.currentPlayer = Math.abs((enzka.skipNext) ? (enzka.currentPlayer + enzka.playDirection) % NUMBER_OF_PLAYERS : (enzka.currentPlayer + enzka.playDirection*2) % NUMBER_OF_PLAYERS);
		}
	}
	
	/**
	 * Initializes the game with a given number of players 
	 * 
	 * @param numberOfPlayers -the number of players involved in the game
	 */
	public Enzka() {
		gameDeck = new Deck(true);
		discardPile = new Deck(false);
		players = new Deck[NUMBER_OF_PLAYERS];
		display = new ViewController(this);
		
		//initialize each player
		for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
			players[i] = new Deck(false);
		}
		
		//deal out the cards
		for (int i = 0; i < NUMBER_OF_PLAYERS * CARDS_IN_A_HAND; i++) {
			players[i%NUMBER_OF_PLAYERS].addCard(gameDeck.getCard());
		}
		discardPile.addCard(gameDeck.getCard());
	}

	@Override
	public void cardGiven(Card card) {
		discardPile.addCard(card);
		cardPlayed = true;
	}

	@Override
	public void cardDrawn() {
		players[currentPlayer].addCard(gameDeck.getCard());
		deckPressed = true;
	}

	@Override
	public void playerSkipped() {
		skipNext = true;
	}

	@Override
	public void playReversed() {
		playDirection = -(playDirection);
	}

	@Override
	public void playerDrawCards(int numCards) {
		for(int i = 0; i < numCards; i++) {
			players[currentPlayer + playDirection].addCard(gameDeck.getCard());
		}
	}
}