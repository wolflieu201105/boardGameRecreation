import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.Border;

public class MainLabel extends JLabel{
	MainLabel(int width, int height){
		Border border = BorderFactory.createLineBorder(Color.gray,3);
		this.setBackground(new Color(123,50,250));
		this.setOpaque(true);
		this.setBorder(border);
		this.setBounds(0, 0, width, height);
	}
}
