public class CardDeck {

    private int card_count; // 남은 카드 수
    private Card[] deck = new Card[20];
    // Invariant: deck[0], .., decl[card_count-1] 에 카드가 있다.

    public CardDeck() {
        for(int i = 1; i <= 10; i++) {
            for (int j = 0; j < 2; j++) {
                deck[card_count] = new Card(i, j);
                card_count = card_count + 1;
            }
        }
    }

    public Card newCard() {
        int index = (int)(Math.random() * card_count);
        Card card_to_deal = deck[index];
        for (int i = index+1; i < card_count; i++)
            deck[i-1] = deck[i];
        card_count = card_count - 1;
        return card_to_deal;
    }
}
