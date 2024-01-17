package MainPackages.MainMenu;

import java.awt.Font;

import javax.swing.JTextPane;

public class GameTitle extends JTextPane {
	// the dimensions of the text label
	final private int width_ratio = 206;
	final private int height_ratio = 26;
	
	// the size of the text
	final private int text_size_ratio = 20;

	// the current scale
	private int scale;

	// the current width or height after resizing
	private int width;
	private int height;

	// the current text size
	private int text_size;

	// the name of the game
	private String name = "Vạn Đại Trường Chinh";

	// create the constructor
	public GameTitle() {
		// setting the text for the pane
		this.setText(name);

		// setting font and size of the text
		this.setFont(new Font("Arial", Font.PLAIN, text_size));

		// set editable to false so user can not delete the name
		this.setEditable(false);

		// set the background to transparent
		this.setOpaque(false);
	}

	// the function will find the upper center place of the main panel
	// and put the pane there
	public void setCenterBound() {
		// get the main panel width minus the pane width then divide by 2
		// to get the starting point of the pane
		System.out.println();
		int x_start = (this.getParent().getWidth() - width)/2;
		int y_start = 0;

		// set bounds for the pane
		this.setBounds(x_start, y_start, width, height);
	}

	// the function will set the scale to the new scale and set font, bounds
	// the function is also called when first construct in mainLabel
	public void resizedFrame(int newScale) {

		// change the scale to the new scale
		scale = newScale;

		// setting the initial height and width of the text pane
		width = width_ratio * scale;
		height = height_ratio * scale;

		// setting the initial text size of the text pane
		text_size = text_size_ratio * scale;

		// setting font and size of the text
		this.setFont(new Font("Arial", Font.PLAIN, text_size));
		
		// set bounds for the pane
		this.setCenterBound();
	}
}
