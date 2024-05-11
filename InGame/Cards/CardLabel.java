package InGame.Cards;

import javax.swing.JLabel;

import InGame.Players.PlayerCards;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CardLabel extends JLabel {
	// the dimensions of the main label
	final private int width_ratio = 250;
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

	// playing cards file
	private String assetFile = "Assets/PlayingCards.txt";
	private String assets = "Assets/";

	// Different types of cards and number of cards remaining
	int cardNum;
	Cards[] cardTypes;
	int[] remaining;

	public CardLabel(int newScale) {
		scale = newScale;
		width = width_ratio * scale;
		height = height_ratio * scale;
		x_start = x_start_ratio * scale;
		y_start = y_start_ratio * scale;

		// adding the players
		try {
			// get the txt file and read it
			File savedSetting = new File(assetFile);
			Scanner myReader = new Scanner(savedSetting);
			String data = myReader.nextLine();
			cardNum = Integer.parseInt(data.split("\t")[1]);
			cardTypes = new Cards[cardNum];
			remaining = new int[cardNum];
			String[] thisLine;
			int i = 0;
			while (true) {
				try {
					data = myReader.nextLine();
					thisLine = data.split("\t");
					cardTypes[i] = new Cards(thisLine[0], Integer.parseInt(thisLine[1]), assets + thisLine[2], thisLine[4]);
					remaining[i] = Integer.parseInt(thisLine[3]);
				} catch (Exception e) {
					break;
				}
			}

			// closing the reader
			myReader.close();
		}
		// returning error if there is no setting saved file
		catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		// setting the new bounds for the label
		this.setBounds(x_start, y_start, width, height);

		// set the visibility of the color
		this.setBackground(new Color(255, 70, 255));
		this.setOpaque(true);
	}

}
