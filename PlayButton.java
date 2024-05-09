import javax.swing.JButton;

import java.awt.Font;

public class PlayButton extends JButton {

    // the dimensions of the play button
    final private int width_ratio = 100;
    final private int height_ratio = 25;

    // text for the button
    final private String text = "Start";
	final private int text_size_ratio = 20;

    public PlayButton() {
        this.setText(text);
        this.setOpaque(true);
    }

    public void resizedFrame(int newScale) {
		// setting the initial height and width of the text pane
		int width = width_ratio * newScale;
		int height = height_ratio * newScale;

		// setting the initial text size of the text pane
		int text_size = text_size_ratio * newScale;

		// setting font and size of the text
		this.setFont(new Font("Arial", Font.PLAIN, text_size));

        int x_start = (this.getParent().getWidth() - width)/2;
		int y_start = this.getParent().getHeight() / 5*2;

		// set bounds for the pane
		this.setBounds(x_start, y_start, width, height);
    }
}
