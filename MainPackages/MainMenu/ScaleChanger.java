package MainPackages.MainMenu;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ScaleChanger extends JLabel implements ChangeListener {
	// the dimensions of the text label
	final private int width_ratio = 300;
	final private int height_ratio = 30;

	// the current width or height after resizing
	private int width;
	private int height;

	// the dimensions of the slider
	final private int slider_width_ratio = 200;
	final private int slider_height_ratio = 15;

	// the current width and height after resizing
	private int slider_width;
	private int slider_height;

	// the max min value of the slider
	final private int slider_min_value = 1;
	final private int slider_max_value = 7;

	// the current value of the slider - the scale of the label
	private int slider_current_value;

	// a slider to determine the label scale
	private JSlider slider = new JSlider(slider_min_value, slider_max_value);

	// the dimensions of the text label
	final private int scaleText_width_ratio = 200;
	final private int scaleText_height_ratio = 15;

	// the size of the text
	final private int scaleText_size_ratio = 12;

	// the current width or height after resizing
	private int scaleText_width;
	private int scaleText_height;

	// the current text size
	private int text_size;

	// the string to represent scaling
	final private String scale_text = "The scale that you are trying to set to is: ";

	// the scale integer
	private int scale;

	// a text that represent the value of the scale before applying
	private JTextPane scaleText = new JTextPane();

	// the dimensions of the button
	final private int button_width_ratio = 100;
	final private int button_height_ratio = 30;

	// the current width or height of the button
	private int button_width;
	private int button_height;

	// a button to make event of making a new scale
	private JButton applyButton = new JButton();

	public ScaleChanger() {
		this.add(slider);
		this.add(scaleText);
		this.add(applyButton);
	}

	@Override
	public void stateChanged(ChangeEvent e) {
	}

	public void resizedFrame(int newScale) {
		width = width_ratio * newScale;
		height = height_ratio * newScale;
	}

}