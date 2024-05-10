import javax.swing.JLabel;
import java.awt.Color;

public class PlayerLabel extends JLabel{
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

	// players
	final private int player_width = 50;
	final private int player_height = 50;
	private PlayerCards player1;
	private PlayerCards player2;
	private PlayerCards player3;
	private PlayerCards player4;
	private PlayerCards[] players = {player1, player2, player3, player4};
    
    public PlayerLabel(int newScale) {
        scale = newScale;
        width = width_ratio * scale;
        height = height_ratio * scale;
        x_start = x_start_ratio * scale;
        y_start = y_start_ratio * scale;

		// adding the players
		for (int i = 0; i < players.length; i++) {
			players[i] = new PlayerCards(0, i * player_height, player_width, player_height, newScale);
			this.add(players[i]);
		}

		// setting the new bounds for the label
		this.setBounds(x_start, y_start, width, height);
        
		// set the visibility of the color
		this.setBackground(new Color(70, 255, 255));
		this.setOpaque(true);
    }
}
