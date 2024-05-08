

import javax.swing.JLabel;

public abstract class Bosses extends JLabel {
	// the name of the boss
	String Name;

	// the source of the image to navigate
	String ImageSource;

	// the skill of the boss
	abstract void Skill();

	// the health bar of the boss
	BossHealthBar healthBar;

	// the position, width, height of the boss
	// initial position
	float x;
	float y;
	// width
	float dx;
	// height
	float dy;

	// when hovering over the boss, the info about the boss
	// should appear along with how it attack
	abstract void HoverForInfo();
}
