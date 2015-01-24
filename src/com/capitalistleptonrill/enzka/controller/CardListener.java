package com.capitalistleptonrill.enzka.controller;

import com.capitalistleptonrill.enzka.model.Card;

public interface CardListener {
	
	public void cardGiven(Card card);
	
	public void cardDrawn();
}
