import java.awt.Color;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyFrame extends JFrame implements ComponentListener{
	private int width_ratio = 3;
	private int height_ratio = 2;
	private int scale = 500; 
	public int width = this.width_ratio * scale;
	public int height = this.height_ratio * scale;
	
	MyFrame(){
		// create the mainPanel where the game occurs
		MainPanel mainPanel = new MainPanel(width, height);
		// add label to detect resize events
		getContentPane().add(mainPanel);
		getContentPane().addComponentListener(this);
		
		this.setTitle("new frame");// set titles of the frame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// exit out of application
		this.setResizable(true);
		this.setSize(width, height); // sets the x-dimension, and y-dimension of the frame
		this.setLayout(null);
		this.setVisible(true); // make frame visible
		
		ImageIcon image = new ImageIcon("src\\Icon.png");
		this.setIconImage(image.getImage());
		this.getContentPane().setBackground(new Color(0, 0, 0));
		System.out.println(this.getWidth() + " and " + this.getHeight());
	}
	
	// these functions result from the ComponentListener but there are no uses yet
	public void componentMoved(ComponentEvent e) {}
	public void componentShown(ComponentEvent e) {}
	public void componentHidden(ComponentEvent e) {}
	
	// this function to relocate the main panel
	public void componentResized(ComponentEvent e) {}
}
