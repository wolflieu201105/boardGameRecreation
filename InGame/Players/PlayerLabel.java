package InGame.Players;

import javax.swing.JLabel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PlayerLabel extends JLabel {
	// the dimensions of the main label
	final private int width_ratio = 50;
	final private int height_ratio = 200;
	final private int x_start_ratio = 0;
	final private int y_start_ratio = 0;

	// the current scale
	private int scale;

	// the current width or height after resizing
	private int width;
	private int height;
	private int x_start;
	private int y_start;

	// save file name
	final private String setting = "PlayerName.txt";

	// players
	final private int player_width = 50;
	final private int player_height = 50;
	private PlayerCards[] players = new PlayerCards[4];

	public PlayerLabel(int newScale) {
		scale = newScale;
		width = width_ratio * scale;
		height = height_ratio * scale;
		x_start = x_start_ratio * scale;
		y_start = y_start_ratio * scale;

		// adding the players
		try {
			// get the txt file and read it
			File savedSetting = new File(setting);
			Scanner myReader = new Scanner(savedSetting);

			for (int i = 0; i < players.length; i++) {
				String data = myReader.nextLine();
				players[i] = new PlayerCards(0, i * player_height, player_width, player_height, newScale,
						data.split("\t")[1]);
				this.add(players[i]);
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
		this.setOpaque(false);
	}

	public void update() {
		players[0].health--;
		players[1].health -= 2;
		for (PlayerCards playerCards : players) {
			playerCards.update();
		}
	}
}
