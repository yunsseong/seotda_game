public class Dealer {

    CardDeck deck;

    public Dealer() {
        deck = new CardDeck();
    }

    public Card dealOneTo(CardPlayer p) {
        Card c = deck.newCard();
        p.receiveCard(c);
        return c;
    }
}