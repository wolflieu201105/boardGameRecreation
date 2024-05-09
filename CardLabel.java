import javax.swing.JLabel;

public class CardLabel extends JLabel{
	// the dimensions of the main label
	final private int width_ratio = 200;
	final private int height_ratio = 100;
	final private int x_start_ratio = 50;
	final private int y_start_ratio = 100;
    
	// the current scale
	private int scale;
    
	// the current width or height after resizing
	private int width;
	private int height;
    private int x_start;
    private int y_start;

    public CardLabel(int newScale) {
        scale = newScale;
        width = width_ratio * scale;
        height = height_ratio * scale;
        x_start = x_start_ratio * scale;
        y_start = y_start_ratio * scale;

		// setting the new bounds for the label
		this.setBounds(x_start, y_start, width, height);

		// set the visibility of the color
		this.setOpaque(true);
    }
    
}
