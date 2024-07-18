package InGame.Cards;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CardLabel extends JLabel {
	// the dimensions of the main label
	final private int width_ratio = 280;
	final private int height_ratio = 120;
	final private int x_start_ratio = 50;
	final private int y_start_ratio = 80;

	// the current scale
	private int scale;

	// the current width or height after resizing
	private int width;
	private int height;
	private int x_start;
	private int y_start;

	// card label to display cards
	private int card_width_ratio = 40;
	private int card_height_ratio = 60;
	private CardInPlay[] cardsInPlay = new CardInPlay[8];

	// playing cards file
	private String assetFile = "Assets/PlayingCards.txt";
	private String assets = "Assets/PlayingCards/";

	// Different types of cards and number of cards remaining
	int cardNum;
	CardTypes[] cardTypes;
	int[] remaining;

	public CardLabel(int newScale) {
		scale = newScale;
		width = width_ratio * scale;
		height = height_ratio * scale;
		x_start = x_start_ratio * scale;
		y_start = y_start_ratio * scale;

		// adding the playing cards
		try {
			// get the txt file and read it
			File savedSetting = new File(assetFile);
			Scanner myReader = new Scanner(savedSetting);
			String data = myReader.nextLine();
			cardNum = Integer.parseInt(data.split("\t")[1]);
			cardTypes = new CardTypes[cardNum];
			remaining = new int[cardNum];
			String[] thisLine;
			int i = 0;
			while (true) {
				try {
					data = myReader.nextLine();
					thisLine = data.split("\t");
					ImageIcon newIcon = new ImageIcon(assets + thisLine[2]);
					Image cardImage = newIcon.getImage();
					cardImage = cardImage.getScaledInstance((int)card_width_ratio * scale, (int)card_height_ratio * scale, Image.SCALE_SMOOTH);
					cardTypes[i] = new CardTypes(thisLine[0], Integer.parseInt(thisLine[1]), cardImage, thisLine[4]);
					remaining[i] = Integer.parseInt(thisLine[3]);
				} catch (Exception e) {
					break;
				}
				i++;
			}

			// closing the reader
			myReader.close();
		}

		// returning error if there is no setting saved file
		catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		for (int i = 0; i < cardsInPlay.length; i++) {
			cardsInPlay[i] = new CardInPlay(card_width_ratio * scale, card_height_ratio * scale);
			this.add(cardsInPlay[i]);
		}

		// setting the new bounds for the label
		this.setBounds(x_start, y_start, width, height);

		// set the visibility of the color

		this.setBackground(new Color(255, 70, 255));
		this.setOpaque(true);
	}

	private int cardMarginHeight = 55;
	private int initialMargin = 15;
	private int numberOfCards = 6;
	public void start() {
		int marginWidth = (this.width_ratio - this.card_width_ratio*numberOfCards - initialMargin*2)/(numberOfCards - 1);
		for (int i = 0; i < numberOfCards; i++) {
			cardsInPlay[i].cardTypes = cardTypes[i];
			cardsInPlay[i].viewAble(initialMargin + (marginWidth + card_width_ratio) * i,cardMarginHeight,scale);
			cardsInPlay[i].choosable = true;
			this.add(cardsInPlay[i]);
		} 
	}

	public void update(int FPS) {
		Thread[] cardsThread = new Thread[numberOfCards];
		for (int i = 0; i < numberOfCards; i++) {
			int num = i;
			cardsThread[num] = new Thread(() ->{
				cardsInPlay[num].update(FPS);
			});
			cardsThread[num].start();
		}
	}

	// Overiding in order to change the choosable in every card
	public void removeAll() {
		for (int i = 0; i < numberOfCards; i++) {
			cardsInPlay[i].choosable = false;
		}
	}
}
