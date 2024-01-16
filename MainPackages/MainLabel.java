package MainPackages;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import MainPackages.MainMenu.GameTitle;

public class MainLabel extends JLabel {
	// the dimensions of the main label
	final private int width_ratio = 300;
	final private int height_ratio = 200;
	
	// the current scale
	private int scale;
	
	// the current width or height after resizing
	private int width;
	private int height;
	
	// create the gameTitle instance
	private GameTitle gameTitle;
	
	// create the constructor
	public MainLabel(int currentScale) {
		// setting the scale for the scale
		scale = currentScale;
		
		// set the game title for the label
		gameTitle = new GameTitle(scale, width_ratio, height_ratio);
		
		// set the bounds for the label
		ResizedFrame(scale);
		
		// create a border for the label
		Border border = BorderFactory.createLineBorder(Color.gray, 3);
		this.setBorder(border);
		
		// set color back ground and visibility of the color
		this.setBackground(new Color(123, 50, 250));
		this.setOpaque(true);
		
		// adding the components to the label
		this.add(gameTitle);
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
		int x_start = (this.getX() - width_ratio*(newScale - scale)/2);
		int y_start = (this.getY() - height_ratio*(newScale - scale)/2);
		
		// change the scale to the new scale
		scale = newScale;
		
		// setting the initial height and width of the label
		width = width_ratio * scale;
		height = height_ratio * scale;
		
		// setting the new bounds for the label
		this.setBounds(x_start, y_start, width, height);
		
		// setting the new scale for all the components
		gameTitle.resizedFrame(newScale);
	}
}
