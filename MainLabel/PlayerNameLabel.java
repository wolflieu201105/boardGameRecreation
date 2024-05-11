package MainLabel;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Font;

public class PlayerNameLabel extends JLabel{
	// the dimensions of the main label
	final private int width_ratio = 300;
	final private int height_ratio = 60;
    final private int x_start = 0;
    final private int y_start = 120;
    
    // save file name
    final private String setting = "PlayerName.txt";
    
    private int marginLeft = 10;
    private int marginTop = 35;
    private int width = 55;
    private int height = 20;
    private int text_size = 10;
    JTextField textField1 = new JTextField();
    JTextField textField2 = new JTextField();
    JTextField textField3 = new JTextField();
    JTextField textField4 = new JTextField();
    public JTextField[] textFields = {textField1, textField2, textField3, textField4};

    public PlayerNameLabel() {
		try {
			// get the txt file and read it 
			File savedSetting = new File(setting);
			Scanner myReader = new Scanner(savedSetting);
			
			for (int i = 0; i < textFields.length; i++) {
                String data = myReader.nextLine();
                textFields[i].setText(data.split("\t")[1]);
                textFields[i].setBackground(new Color(255, 248, 178));
            }
			
			// closing the reader
			myReader.close();
		} 
		// returning error if there is no setting saved file
		catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		this.setOpaque(false);
    }
    
	public void ResizedFrame(int newScale) {
        for (int i = 0; i < textFields.length; i++) {
            textFields[i].setBounds(((marginLeft * 2 + width) * i + marginLeft) * newScale, marginTop * newScale, width * newScale, height * newScale);
		    // setting font and size of the text
		    textFields[i].setFont(new Font("Arial", Font.PLAIN, text_size * newScale));
            this.add(textFields[i]);
        }
        this.setBounds(x_start * newScale, y_start * newScale, width_ratio * newScale, height_ratio * newScale);
    }
}
