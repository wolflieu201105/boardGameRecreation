import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class MainPanel extends JPanel{
	MainPanel(int width, int height){
		Border border = BorderFactory.createLineBorder(Color.gray,3);
		this.setBackground(new Color(123,50,250));
		this.setBorder(border);
		this.setBounds(0, 0, width, height);
	}
}
