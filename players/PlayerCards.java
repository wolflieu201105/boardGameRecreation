package players;

import javax.swing.JLabel;

public abstract class PlayerCards extends JLabel {
	// the name of the boss
	String Name;

	// the health bar of the player
	PlayerHealthBar healthBar;

	// the maximum stamina of the player
	int maxStamina;

	// the number of buffs (can be different) that the player have currently
	int[] buffs;

	// the position, height, width of the player
	// initial position
	float x;
	float y;
	// width
	float dx;
	// height
	float dy;
	
	// when hovering over the player, it should show the player name, status and 
	// buffs
	
}
