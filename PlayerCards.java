import javax.swing.JLabel;

public class PlayerCards extends JLabel {
	// the dimensions of the main label
	private int width_ratio;
	private int height_ratio;
	private int x_ratio;
	private int y_ratio;

	// the name of the boss
	String Name;

	// the health bar of the player
	PlayerHealthBar healthBar;

	// the maximum stamina of the player
	int maxStamina;

	// the number of buffs (can be different) that the player have currently
	int[] buffs;

	public PlayerCards(int x_start, int y_start, int width, int height, int newScale) {
		x_ratio = x_start;
		y_ratio = y_start;
		width_ratio = width;
		height_ratio = height;

		// setting the new bounds for the label
		this.setBounds(x_ratio * newScale, y_ratio * newScale, width * newScale, height * newScale);

		// set the visibility of the color
		this.setOpaque(true);
	}
	
	// when hovering over the player, it should show the player name, status and 
	// buffs
	
}
