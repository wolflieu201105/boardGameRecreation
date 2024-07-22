package InGame.Players;
import javax.swing.JLabel;
import javax.swing.JTextPane;

import java.awt.Color;
import java.awt.Font;

public class PlayerCards extends JLabel {
	// the dimensions of the main label
	private int width_ratio;
	private int height_ratio;
	private int x_ratio;
	private int y_ratio;

	// the name of the Player
	private String name;
	private JTextPane nameTextPane;
	private int nameHeight = 13;
	private int text_size = 10;

	// the health bar of the player
	JTextPane healthBar;

	// health
	public int health = 25;
	public int maxHealth = 25;

	// the maximum stamina of the player
	public int maxStamina = 3;

	// the maximum cards the player can have
	public int cardsNextTurn = 3;

	// the number of buffs (can be different) that the player have currently
	int[] buffs;

	public PlayerCards(int x_start, int y_start, int width, int height, int newScale, String playerName) {
		x_ratio = x_start;
		y_ratio = y_start;
		width_ratio = width;
		height_ratio = height;
		name = playerName;

		// setting the new bounds for the label
		this.setBounds(x_ratio * newScale, y_ratio * newScale, width_ratio * newScale, height_ratio * newScale);

		// set the player's name
		nameTextPane = new JTextPane();
		nameTextPane.setBounds(0, 0, width_ratio*newScale, nameHeight*newScale);
		nameTextPane.setFont(new Font("Arial", Font.PLAIN, text_size*newScale));
		nameTextPane.setText(name);
		nameTextPane.setEditable(false);
		nameTextPane.setBackground(new Color(0, 250, 0, 150));
		nameTextPane.setOpaque(true);
		this.add(nameTextPane);

		// set the health bar
		healthBar = new JTextPane();
		healthBar.setBounds(0, nameHeight*newScale, width_ratio*newScale, nameHeight*newScale);
		healthBar.setFont(new Font("Arial", Font.PLAIN, text_size*newScale));
		healthBar.setText(health + "/" + maxHealth);
		healthBar.setEditable(false);
		healthBar.setOpaque(false);
		this.add(healthBar);

		// set the visibility of the color
		this.setOpaque(false);
	}
	
	public void update() {
		if (health < 0) {
			health = 0;
		}
		if (health != Integer.parseInt(healthBar.getText().split("/")[0])) {
			healthBar.setText(health + "/" + maxHealth);
			nameTextPane.setBackground(new Color(250 - health*10, health*10, 0, 150));
		}
	}

	// when hovering over the player, it should show the player name, status and 
	// buffs
}
