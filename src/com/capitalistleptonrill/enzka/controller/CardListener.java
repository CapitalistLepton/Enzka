package com.capitalistleptonrill.enzka.controller;

import com.capitalistleptonrill.enzka.model.Card;

public interface CardListener {
	
	public void cardGiven(Card card);
	
	public void cardDrawn();
	
	public void playerSkipped();
	
	public void playReversed();
	
	public void playerDrawCards(int numCards);
}