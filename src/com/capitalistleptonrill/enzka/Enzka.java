package com.capitalistleptonrill.enzka;

import com.capitalistleptonrill.enzka.controller.ViewController;
import com.capitalistleptonrill.enzka.model.Card;
import com.capitalistleptonrill.enzka.model.Deck;

public class Enzka {

	private static final int NUMBER_OF_PLAYERS = 3;
	private static final int CARDS_IN_A_HAND = 7;

	private Deck gameDeck;
	private Deck discardPile;
	private Deck[] players;
	private ViewController display;
	
	public static void main(String[] args) {
		Enzka enzka = new Enzka();
		int currentPlayer = 0;
		while(true) {
			enzka.playTurn(currentPlayer);
			currentPlayer = (currentPlayer == NUMBER_OF_PLAYERS - 1) ? 0 : currentPlayer + 1; 
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
		display = new ViewController();
		
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
	
	/**
	 * Plays a turn of the game
	 */
	public void playTurn(int player) {
		Card card = display.displayHand(players[player], discardPile.showCard(discardPile.getLength()-1));
		while(card == null) {
			players[player].addCard(gameDeck.getCard());
			card = display.displayHand(players[player], discardPile.showCard(discardPile.getLength()-1));
		}
		discardPile.addCard(card);
		System.out.println(discardPile.showCard(discardPile.getLength()-1));
	}
}