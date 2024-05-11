package MainLabel;

import java.awt.Font;

import javax.swing.JTextPane;

@SuppressWarnings("serial")
public class GameTitle extends JTextPane {
	// the dimensions of the text label
	final private int width_ratio = 206;
	final private int height_ratio = 26;
	
	// the size of the text
	final private int text_size_ratio = 20;

	// the name of the game
	private String name = "Vạn Đại Trường Chinh";

	// create the constructor
	public GameTitle() {
		// setting the text for the pane
		this.setText(name);

		// set editable to false so user can not delete the name
		this.setEditable(false);

		// set the background to transparent
		this.setOpaque(false);
	}

	// the function will set the scale to the new scale and set font, bounds
	// the function is also called when first construct in mainLabel
	public void resizedFrame(int newScale) {
		// setting the initial height and width of the text pane
		int width = width_ratio * newScale;
		int height = height_ratio * newScale;

		// setting the initial text size of the text pane
		int text_size = text_size_ratio * newScale;

		// setting font and size of the text
		this.setFont(new Font("Arial", Font.PLAIN, text_size));
		
		// find the start of the text to put in
		// get the main panel width minus the pane width then divide by 2
		// to get the starting point of the pane
		int x_start = (this.getParent().getWidth() - width)/2;
		int y_start = 0;

		// set bounds for the pane
		this.setBounds(x_start, y_start, width, height);
	}
}
