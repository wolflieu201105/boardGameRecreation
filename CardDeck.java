import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CardDeck {
    int numberOfCards = 0;
    Random randomNumbers = new Random();
    List<CardTypes> cardList = new ArrayList<CardTypes>();
    CardTypes cardQueue = null;
    public CardDeck() {}
    
    public void insertCard(CardTypes cardName) {
        cardQueue = cardName;
    }

    public void removeCardQueue() {
        cardQueue = null;
    }

    public void putInDeck() {
        if (cardQueue != null) {
            numberOfCards++;
            cardList.add(randomNumbers.nextInt(numberOfCards), cardQueue);
        }
    }

    public CardTypes drawCard() {
        if (numberOfCards == 0){
            return null;
        }
        numberOfCards--;
        CardTypes card = cardList.get(0);
        cardList.remove(0);
        return card;
    }
}
