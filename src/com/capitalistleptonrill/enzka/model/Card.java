package com.capitalistleptonrill.enzka.model;

public class Card {
	
	private String color;
	private int value;
	
	public Card(String color, int value) {
		String[] possibleColors = {"Red","Blue","Yellow","Green","Wild"};
		this.value = (value <= 13 && value >= 0) ? value : null;
		for(int i = 0; i < possibleColors.length; i++) {
			String temp = possibleColors[i];
			if(color.equals(temp)) {
				this.color = color;
				break;
			}
		}
	}
	
	public boolean matches(Card card) {
		if (color.equals(card.getColor()) || card.getColor().equals("Wild")) {
			return true;
		} else {
			return value == card.getValue();
		}
	}
	
	public int getValue() {
		return value;
	}
	
	public String getColor() {
		return color;
	}
	
	public EnzkaActions getAction() {
		if(color != "Wild") {
			switch(value) {
			case 10: return EnzkaActions.SKIP;
			case 11: return EnzkaActions.REVERSE;
			case 12: return EnzkaActions.DRAW_TWO;
			default: return EnzkaActions.NO_ACTION;
			}
		} else {
			switch(value) {
			case 0: return EnzkaActions.WILD;
			case 1: return EnzkaActions.WILD_DRAW_FOUR;
			default: return EnzkaActions.NO_ACTION;
			}
		}
	}
	
	public String toString() {
		return color + "_" + value;
	}
}