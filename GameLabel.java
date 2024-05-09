import java.awt.Color;

import javax.swing.JLabel;

public class GameLabel extends JLabel{
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

    public GameLabel(int newScale) {
        scale = newScale;
        width = width_ratio * scale;
        height = height_ratio * scale;

		// adding it's own label
		playerLabel = new PlayerLabel(scale);
		cardLabel = new CardLabel(scale);
		this.add(playerLabel);
		this.add(cardLabel);

		// setting the new bounds for the label
		this.setBounds(0, 0, width, height);
        
		// set color back ground and visibility of the color
		this.setBackground(new Color(255, 248, 178));
		this.setOpaque(true);
    }

    // FPS implementation
    private int FRAMES_PER_SECOND = 25;
    private int SKIP_TICKS = 1000 / FRAMES_PER_SECOND;

    
}
