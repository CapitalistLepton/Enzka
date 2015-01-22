package com.capitalistleptonrill.enzka.model;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	
	private static final int NUMBER_OF_CARDS_IN_NEW_DECK = 108;
	private static final int CARDS_PER_COLOR = 13;
	
	private ArrayList<Card> contents = new ArrayList<Card>();
	
	/** Creates a deck that is either full of cards or empty 
	 * 	
	 * @param isFull -true of false of whether the deck is full of cards already
	 */
	public Deck(boolean isFull) {
		if(isFull) {
			//number of cards divided by 2 because each card in the deck repeats twice
			for(int i = 0; i < NUMBER_OF_CARDS_IN_NEW_DECK/2; i++) { 
				int val = i % CARDS_PER_COLOR;
				//decides which color the card is
				String color = (i < CARDS_PER_COLOR) ? "Red" :
					(i < CARDS_PER_COLOR * 2) ? "Yellow":
					(i < CARDS_PER_COLOR * 3) ? "Green":
					(i < CARDS_PER_COLOR * 4) ? "Blue": "Wild";
				//add the card in twice since there is a repeat
				contents.add(new Card(color,val));
				contents.add(new Card(color,val));
			}
			shuffle();
		}
	}
	
	/** Returns the number of cards in the deck
	 * 
	 * 	@return length of the deck
	 */
	public int getLength() {
		return contents.size();
	}
	
	/** REMOVES and returns the card at the index 
	 * 
	 * 	@param index -the index of the card to get
	 * 	@return the card at the specified index
	 */
	public Card getCard(int index) {
		return contents.remove(index);
	}
	
	/** REMOVES and returns the card on top 
	 * 
	 * @return the card on top (index 0)
	 */
	public Card getCard() {
		return contents.remove(0);
	}
	
	/** Returns the card at the index 
	 * 
	 * @param index - the index of the card to show
	 * @return the card at the given index
	 */
	public Card showCard(int index) {
		return contents.get(index);
	}
	
	/** Adds card to the bottom of the deck 
	 * 
	 * @param card -the card to add
	 */
	public void addCard(Card card) {
		contents.add(card);
	}
	
	/** Shuffles the deck 
	 * 
	 */
	public void shuffle() {
		Collections.shuffle(contents);
	}

}
