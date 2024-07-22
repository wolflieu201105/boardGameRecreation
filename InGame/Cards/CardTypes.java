package InGame.Cards;

import java.awt.Image;

public class CardTypes{
	// the name of the card
	String name;

	// the source of the image to navigate
	Image image;

	// the stamina cost of the card
	int staminaCost;

	// description
	String description;

	public CardTypes(String Name, int StaminaCost, Image cardImage, String Description) {
		name = Name;
		staminaCost = StaminaCost;
		image = cardImage;
		description = Description;
	}
}
