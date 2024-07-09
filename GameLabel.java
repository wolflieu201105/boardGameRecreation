import java.awt.Color;

import javax.swing.JLabel;

import InGame.Boss.BossLabel;
import InGame.Cards.CardLabel;
import InGame.Players.PlayerLabel;

public class GameLabel extends JLabel implements Runnable{
	// the dimensions of the main label
	final private int width_ratio = 300;
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

		// setting the new bounds for the label
		this.setBounds(0, 0, width, height);
        
		// set color back ground and visibility of the color
		this.setBackground(new Color(255, 248, 178));
		this.setOpaque(true);
		startGameThread();
    }

    // FPS implementation

	final private int FPS = 60;

	Thread gameThread;
    public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		double drawInterval = 1000000000/FPS;
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
	}
}
