import javax.swing.JLabel;

public class PlayerCards extends JLabel {
	// the dimensions of the main label
	final private int width_ratio = 50;
	final private int height_ratio = 50;
	private int x_ratio;
	private int y_ratio;

	// the position, height, width of the player
	// initial position
	int width;
	int height;

	// the name of the boss
	String Name;

	// the health bar of the player
	PlayerHealthBar healthBar;

	// the maximum stamina of the player
	int maxStamina;

	// the number of buffs (can be different) that the player have currently
	int[] buffs;

	public PlayerCards(int x_start, int y_start, int newScale) {
		x_ratio = x_start;
		y_ratio = y_start;
		width = width_ratio*newScale;
		height = height_ratio*newScale;

		// setting the new bounds for the label
		this.setBounds(x_ratio * newScale, y_ratio * newScale, width, height);

		// set the visibility of the color
		this.setOpaque(true);
	}
	
	// when hovering over the player, it should show the player name, status and 
	// buffs
	
}
