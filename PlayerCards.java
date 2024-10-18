import javax.swing.JLabel;
import javax.swing.JTextPane;

import java.awt.Color;
import java.awt.Font;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PlayerCards extends JLabel implements MouseListener{
	// the dimensions of the main label
	public int width_ratio;
	public int height_ratio;
	public int x_ratio;
	public int y_ratio;

	// the name of the Player
	public int id;
	private String name;
	private JTextPane nameTextPane;
	private int nameHeight = 13;
	private int text_size = 10;

	// the health bar of the player
	JTextPane healthBar;

	// health
	public int health = 25;
	public int maxHealth = 25;
	public boolean isDead = false;

	// the maximum stamina of the player
	public int maxStamina = 3;

	// the maximum cards the player can have
	public int cardsNextTurn = 3;

	// the number of buffs (can be different) that the player have currently
	int[] buffs;

	// get access to player label
	PlayerLabel parent;

	public PlayerCards(int x_start, int y_start, int width, int height, int newScale, String playerName, int ID, PlayerLabel Parent) {
		x_ratio = x_start;
		y_ratio = y_start;
		width_ratio = width;
		height_ratio = height;
		name = playerName;
		id = ID;
		parent = Parent;

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
		this.addMouseListener(this);
	}

	public void loseHP(int hp) {
		health -= hp;
		if (health > maxHealth) {
			health = 25;
		}
		if (health < 0) {
			this.isDead = true;
			health = 0;
		}
		healthBar.setText(health + "/" + maxHealth);
		nameTextPane.setBackground(new Color(250 - health*10, health*10, 0, 150));
	}
	
	public void update() {
	}

	// when left clicked on the player: 
	// check on what the buff is => change accordingly
    public void mouseClicked(MouseEvent e) {
		if(parent.clickable || isDead) {
			return;
		}
		if(parent.notClickablePlayer != id){
			switch(parent.cardName) {
				case 2:
					this.loseHP(-2);
					parent.afterCardFuntion();
					break;
				case 5:
					parent.swapPlayers(id, parent.notClickablePlayer);
					parent.afterCardFuntion();
					break;
				default:
			}
		}
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }
}
