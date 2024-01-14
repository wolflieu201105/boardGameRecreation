package Cards;

import javax.swing.JLabel;

public abstract class Cards extends JLabel {
	// the name of the card
	String Name;

	// the source of the image to navigate
	String ImageSource;

	// the stamina cost of the card
	int StaminaCost;

	// the skill of the card
	abstract void Skill();

	// the position, width, height and the velocity of the card
	// initial position
	float x;
	float y;
	// width
	float dx;
	// height
	float dy;
	// x velocity
	float vx;
	// y velocity
	float vy;

	// Make the card move towards the center point as the card played
	abstract void MoveToCenter();

	// When right clicking over the card, the card should move forward for
	// a bit and show a black box filling with the card information
	abstract void RightClickForInfo();
}
