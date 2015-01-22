package com.capitalistleptonrill.enzka.view;

public class CardButton {
	
	public int x;
	public int y;
	public int w;
	public int h;
	public String name;
	
	public CardButton(int x, int y, int width, int height, String name) {
		this.x = x;
		this.y = y;
		this.w = width;
		this.h = height;
		this.name = name;
	}
	
	public boolean contains(int x, int y) {
		if(x >= this.x && x <= (this.w + this.x)) {
			return (y >= this.y && y <= (this.h + this.y));
		} else {
			return false;
		}
	}

}
