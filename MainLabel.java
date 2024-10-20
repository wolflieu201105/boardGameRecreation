
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

import MainLabel.GameTitle;
import MainLabel.PlayButton;
import MainLabel.PlayerNameLabel;

public class MainLabel extends JLabel implements ActionListener {
	// the dimensions of the main label
	final private int width_ratio = 320;
	final private int height_ratio = 200;

	// the current scale
	private int scale;

	// the current width or height after resizing
	private int width;
	private int height;

	// save file name
	final private String names = "PlayerName.txt";

	// create the gameTitle instance
	private GameTitle gameTitle;

	// create the playButton
	private PlayButton playButton;

	// create name holder
	private PlayerNameLabel playerNameLabel;

	// create the constructor
	public MainLabel(int currentScale) {
		// setting the scale for the scale
		scale = currentScale;

		// set the game title for the label
		gameTitle = new GameTitle();

		// set the play Button for the label
		playButton = new PlayButton();
		playButton.addActionListener(this);

		// set the name for players
		playerNameLabel = new PlayerNameLabel();

		// adding the components to the label
		this.add(gameTitle);
		this.add(playButton);
		this.add(playerNameLabel);

		// set the bounds for the label
		ResizedFrame(scale);

		// create a border for the label
		Border border = BorderFactory.createLineBorder(Color.gray, 3);
		this.setBorder(border);

		// set color back ground and visibility of the color
		this.setBackground(new Color(123, 50, 250));
		this.setOpaque(true);
	}

	// resizing the frame as the window is resized
	public void ResizedFrame(int frameWidth, int frameHeight) {
		// determine the location of the label
		int x_start = (frameWidth - width) / 2;
		int y_start = (frameHeight - height) / 2;
		// relocate the label
		this.setBounds(x_start, y_start, width, height);
	}

	// resizing when the scale is changed
	public void ResizedFrame(int newScale) {
		// setting the new starting point for the main label
		int x_start = (this.getX() - width_ratio * (newScale - scale) / 2);
		int y_start = (this.getY() - height_ratio * (newScale - scale) / 2);

		// change the scale to the new scale
		scale = newScale;

		// setting the initial height and width of the label
		width = width_ratio * scale;
		height = height_ratio * scale;

		// setting the new bounds for the label
		this.setBounds(x_start, y_start, width, height);

		// setting the new scale for all the components
		gameTitle.resizedFrame(newScale);
		playButton.resizedFrame(newScale);
		playerNameLabel.ResizedFrame(newScale);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == playButton) {
			startGame();
		}
	}

	GameLabel gameLabel;

	public void startGame() {
		try {
			FileWriter myWriter = new FileWriter(names);
			for (int i = 0; i < playerNameLabel.textFields.length; i++) {
				myWriter.write("Player " + (i+1) + "\t");
				if (playerNameLabel.textFields[i].getText().equals("")) {
					myWriter.write("Player " + (i+1) + "\n");
				}
				else {
					myWriter.write(playerNameLabel.textFields[i].getText() + "\n");
				}
			}
			myWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		gameLabel = new GameLabel(scale);
		this.removeAll();
		this.add(gameLabel);
		repaint();
	}
}