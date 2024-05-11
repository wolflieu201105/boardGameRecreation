package InGame.Cards;


import javax.swing.JLabel;

public class Cards extends JLabel {
	// the name of the card
	String name;

	// the source of the image to navigate
	String imageSource;

	// the stamina cost of the card
	int staminaCost;

	// description
	String description;

	public Cards(String Name, int StaminaCost, String ImageSource, String Description) {
		name = Name;
		staminaCost = StaminaCost;
		imageSource = ImageSource;
		description = Description;
	}
}
