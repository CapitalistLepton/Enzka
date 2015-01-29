package com.capitalistleptonrill.enzka.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.capitalistleptonrill.enzka.controller.ViewController;


public class EnzkaWindow extends JFrame implements MouseListener {
	
	private static final long serialVersionUID = -7778149466213338281L;
	
	private ArrayList<CardButton> cardButtons;
	private CardButton discard;
	private int selectedButton;
	private Rectangle deck = new Rectangle(400, 100, 120, 180);//deck rectangle
	private Rectangle leftSideButton = new Rectangle(900, 200, 80, 100);//button to expand view
	private Rectangle rightSideButton = new Rectangle(20, 200, 80, 100);//button to expand view
	private int viewingIndex = 0;
	
	private ViewController master;
	
	public EnzkaWindow(int x, int y, int width, int height, ViewController creator) {
		super("Enzka");
		this.setBounds(x, y, width, height);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Container pane = this.getContentPane();
		pane.setBackground(new Color(18, 167, 11));//green felt color
		addMouseListener(this);
		
		cardButtons = new ArrayList<CardButton>();
		selectedButton = -1;
		
		master = creator;
	}
	
	public void drawCardButton(int x, int y, int w, int h, String name) {
		CardButton button = new CardButton(x, y, w, h, name);
		cardButtons.add(button);
		repaint();
	}
	
	public void drawDiscardPile(int x, int y, int w, int h, String name) {
		discard = new CardButton(x, y, w, h, name);
		repaint();
	}
	
	public void clearScreen() {
		cardButtons.clear();
		discard = new CardButton(0, 0, 0, 0, "");
		repaint();
	}
	
	@Override
	public void paint(Graphics g) {
	  super.paint(g);
	  BufferedImage img = null;
	  try {
		    img = ImageIO.read(new File("/Users/zanelittrell/Developer/GitHub/Enzka/src/com/capitalistleptonrill/enzka/images/" + discard.name));
	  } catch (IOException e) {
	  }
	  if(img != null) {
		  g.drawImage(img, discard.x, discard.y, discard.w, discard.h, this);
	  } else {
		  g.setColor(Color.BLACK);
		  g.drawRect(discard.x, discard.y, discard.w, discard.h);
		  g.setColor(Color.RED);
		  g.fillRect(discard.x, discard.y, discard.w, discard.h);
	  }
	  try {
		    img = ImageIO.read(new File("/Users/zanelittrell/Developer/GitHub/Enzka/src/com/capitalistleptonrill/enzka/images/Enzka.png"));
	  } catch (IOException e) {
	  }
	  //draw deck
	  if(img != null) {
		  g.drawImage(img, deck.x, deck.y, deck.width, deck.height, this);
	  } else {
		  g.setColor(Color.BLACK);
		  g.drawRect(deck.x, deck.y, deck.width, deck.height);
		  g.setColor(Color.RED);
		  g.fillRect(deck.x, deck.y, deck.width, deck.height);
	  }
	  if(cardButtons.size() < 8) {
		  for (int i = 0; i < cardButtons.size(); i++) {
			  	CardButton button = cardButtons.get(i);
				try {
				    img = ImageIO.read(new File("/Users/zanelittrell/Developer/GitHub/Enzka/src/com/capitalistleptonrill/enzka/images/" + button.name));
				} catch (IOException e) {
				}
				if(img != null) {
					g.drawImage(img, button.x, button.y, button.w, button.h, this);
				} else {
					g.setColor(Color.BLACK);
					g.drawRect(button.x, button.y, button.w, button.h);
					g.setColor(Color.RED);
					g.fillRect(button.x, button.y, button.w, button.h);
				}
		  }
	  } else {
		  for (int i = 0; i < 7; i++) {
			  if(viewingIndex + i > cardButtons.size() - 1) {
				  break;
			  }
			  CardButton button = cardButtons.get(viewingIndex + i);
			  try {
				  img = ImageIO.read(new File("/Users/zanelittrell/Developer/GitHub/Enzka/src/com/capitalistleptonrill/enzka/images/" + button.name));
			  } catch (IOException e) {
			  }
			  if(img != null) {
				  g.drawImage(img, (120+10)*i+50, 350, 120, 180, this);
			  } else {
				  g.setColor(Color.BLACK);
				  g.drawRect((120+10)*i+50, 350, 120, 180);
				  g.setColor(Color.RED);
				  g.fillRect((120+10)*i+50, 350, 120, 180);
			  }
		  }
		  g.setColor(Color.BLACK);
		  g.fillArc(leftSideButton.x, leftSideButton.y, leftSideButton.width, leftSideButton.height, 270, 180);
		  if(viewingIndex > 0) {
			  g.fillArc(rightSideButton.x, rightSideButton.y, rightSideButton.width, rightSideButton.height, 90, 180);
		  }
	  }
	}
	
	public int getSelectedButton() {
		int temp = selectedButton;
		selectedButton = -1;
		return temp;
	}

	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}

	//Touch up inside equivalent
	@Override
	public void mouseReleased(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		if(deck.contains(x, y)) {
			master.deckPressed();
			cardButtons.clear();
			repaint();
		} else if(leftSideButton.contains(x, y)) {
			viewingIndex += 7;
			repaint();
		} else if(rightSideButton.contains(x, y)) {
			viewingIndex -= 7;
			repaint();
		} else {
			for (int i = 0; i < cardButtons.size(); i++) {
				CardButton button = cardButtons.get(i);
				if(button.contains(x, y)) {
					selectedButton = i + viewingIndex;
					System.out.println(selectedButton);
					if(selectedButton != -1){
						master.valueChanged(selectedButton);
					}
					break;
				}
			}
		}
	}

}