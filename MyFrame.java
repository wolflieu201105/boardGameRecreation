import java.awt.Color;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MyFrame extends JFrame implements ComponentListener{
	private int width_ratio = 3;
	private int height_ratio = 2;
	private int scale = 300; 
	public int width = this.width_ratio * scale;
	public int height = this.height_ratio * scale;
	private String iconSource = "src\\Icon.png";
	JLabel mainLabel;
	
	MyFrame(){
		// create the mainPanel where the game occurs
		mainLabel = new MainLabel(width, height);
		// add label to detect resize events
		getContentPane().add(mainLabel);
		getContentPane().addComponentListener(this);
		
		this.setTitle("new frame");// set titles of the frame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// exit out of application
		this.setResizable(true);
		this.setSize(width, height); // sets the x-dimension, and y-dimension of the frame
		this.setLayout(null);
		this.setVisible(true); // make frame visible
		
		// set icon for the frame
		ImageIcon image = new ImageIcon(iconSource);
		this.setIconImage(image.getImage());
		
		// set the the background for the frame
		this.getContentPane().setBackground(new Color(0, 0, 125));
		
		// adding the main label of the frame
		this.add(mainLabel);
	}
	
	// these functions result from the ComponentListener but there are no uses yet
	public void componentMoved(ComponentEvent e) {}
	public void componentShown(ComponentEvent e) {}
	public void componentHidden(ComponentEvent e) {}
	
	// this function to relocate the main panel
	public void componentResized(ComponentEvent e) {
		
		// finding the placement for the main frame
		int x_start = (this.getWidth() - width)/2; 
		int y_start = (this.getHeight() - height)/2;
		
		// setting the placement after the frame have been resized
		mainLabel.setBounds(x_start, y_start, width, height);
	}
}
