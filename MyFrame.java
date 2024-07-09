import java.awt.Color;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MyFrame extends JFrame implements ComponentListener {
	// the initial frame size
	final private int width_ratio = 300;
	final private int height_ratio = 200;

	// the current scale
	private int scale;

	// the current width or height of the frame
	private int width;
	private int height;

	// the source of the icon for the frame
	final private String iconSource = "Assets/Icon.png";

	// the source of frame settings
	final private String setting = "Savings.txt";

	// create the Label for the Frame
	MainLabel mainLabel;

	// the constructor of the frame
	public MyFrame() {
		try {
			// get the txt file and read it 
			File savedSetting = new File(setting);
			Scanner myReader = new Scanner(savedSetting);
			
			// the data now have the first line of the txt file for scale
			String data = myReader.nextLine();
			
			// set the scale as the value after the tab
			scale = Integer.parseInt(data.split("\t")[1]);
			
			// closing the reader
			myReader.close();
		} 
		// returning error if there is no setting saved file
		catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		// set the dimensions for the frame
		width = width_ratio * scale;
		height = height_ratio * scale;

		// create the mainPanel where the game occurs
		mainLabel = new MainLabel(scale);

		// add label to detect resize events
		getContentPane().add(mainLabel);
		getContentPane().addComponentListener(this);

		this.setTitle("new frame");// set titles of the frame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// exit out of application
		this.setResizable(true);
		this.setSize(width * 11/10, height * 11/10); // sets the x-dimension, and y-dimension of the frame
		this.setLayout(null);
		this.setVisible(true); // make frame visible

		// set icon for the frame
		ImageIcon image = new ImageIcon(iconSource);
		this.setIconImage(image.getImage());

		// set the the background for the frame
		this.getContentPane().setBackground(new Color(40, 40, 40));

		// adding the main label of the frame
		this.add(mainLabel);
	}

	// these functions result from the ComponentListener but there are no uses yet
	public void componentMoved(ComponentEvent e) {
	}

	public void componentShown(ComponentEvent e) {
	}

	public void componentHidden(ComponentEvent e) {
	}

	// this function to relocate the main panel
	public void componentResized(ComponentEvent e) {
		// setting the placement after the frame have been resized
		mainLabel.ResizedFrame(this.getWidth(), this.getHeight());
	}
}
