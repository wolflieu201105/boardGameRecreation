package Bosses;

import javax.swing.JPanel;
import java.awt.Color;

public abstract class BossHealthBar extends JPanel {

	// the position, height, width of the health bar
	// initial position
	float x;
	float y;
	// width
	float dx;
	// height
	float dy;

	// set the color for the health bar
	Color backgroundColor;
}
