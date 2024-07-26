import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CardDeck {
    int numberOfCards = 0;
    Random randomNumbers = new Random();
    List<CardTypes> cardList = new ArrayList<CardTypes>();
    public CardDeck() {}
    
    public void insertCard(CardTypes cardName) {
        numberOfCards ++;
        cardList.add(randomNumbers.nextInt(numberOfCards), cardName);
    }

    public CardTypes drawCard() {
        numberOfCards--;
        return cardList.remove(0);
    }
}
