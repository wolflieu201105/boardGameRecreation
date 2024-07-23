import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Font;

import InGame.Boss.BossLabel;
import InGame.Cards.CardLabel;
import InGame.Players.PlayerLabel;

public class GameLabel extends JLabel implements Runnable{
	// the dimensions of the main label
	final private int width_ratio = 320;
	final private int height_ratio = 200;

	// the current scale
	private int scale;

	// the current width or height after resizing
	private int width;
	private int height;

	// different Labels for better management
	PlayerLabel playerLabel;
	CardLabel cardLabel;
	BossLabel bossLabel;

	// next turn button
	String nextTurnString = "End turn";
	JButton nextButton = new JButton(nextTurnString);
	int button_x_start_ratio = 270;
	int button_y_start_ratio = 10;
	int button_width_ratio = 50;
	int button_heigth_ratio = 10;
	int text_size_ratio = 8;

	// turn of players
	int turn = 0;

	public GameLabel(int newScale) {
		scale = newScale;
		width = width_ratio * scale;
		height = height_ratio * scale;

		// adding it's own label
		playerLabel = new PlayerLabel(scale);
		cardLabel = new CardLabel(scale);
		bossLabel = new BossLabel(scale);
		this.add(playerLabel);
		this.add(cardLabel);
		this.add(bossLabel);

		// setting bounds for button
		nextButton.setBounds(button_x_start_ratio * scale, button_y_start_ratio * scale, button_width_ratio * scale, button_heigth_ratio * scale);
		nextButton.setFont(new Font("Arial", Font.PLAIN, text_size_ratio * scale));
		nextButton.setBackground(new Color(255,255,255));
		this.add(nextButton);
		nextButton.addActionListener(e -> {
			turn++;
			if (turn == 5){
				turn = 0;
			}
			System.out.println(turn);
		});

		// setting the new bounds for the label
		this.setBounds(0, 0, width, height);

		// set color back ground and visibility of the color
		this.setBackground(new Color(255, 248, 178));
		this.setOpaque(true);
		startGameThread();
	}
	// FPS implementation

	final private int FPS = 200;

	Thread gameThread;

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
		cardLabel.start();
	}

	@Override
	public void run() {
		double drawInterval = 1000000000 / FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		while (gameThread != null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;
			if (delta >= 1) {
				update();
				repaint();
				delta--;
			}
		}
	}

	public void update() {
		playerLabel.update();
		cardLabel.update(FPS);
		bossLabel.update(FPS);
	}
}
