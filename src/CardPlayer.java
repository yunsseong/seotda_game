public class CardPlayer {

    String name = "Computer";
    long chips = 1000000000;
    public Card[] hand; // 갖고 있는 카드
    private int card_count; // 갖고 있는 카드의 장 수

    public CardPlayer(int max_cards) {
        hand = new Card[max_cards];
        card_count = 0;
    }

    public boolean receiveCard(Card c) {
        if (card_count < hand.length) {
            hand[card_count] = c;
            card_count += 1;
            return true;
        }
        else
            return false;
    }

    public String isGwang(int i){
        if(hand[i].getNotation() == 1) {
            if (hand[i].getNumber() == 1 || hand[i].getNumber() == 3 || hand[i].getNumber() == 8)
                return "광";
            else if (hand[i].getNumber() == 4 || hand[i].getNumber() == 7 || hand[i].getNumber() == 9)
                return "끗";

            else return "";
        }
        else return "";
    }
    public void printCards(){
        String notation;
        System.out.printf(name + " : ");
        for (int i = 0; i < 2; i++) {
            notation = isGwang(i);
            System.out.printf("[ " + hand[i].getNumber() + notation + " ] ");
        }
    }

    public void printOneCard(){
        String notation;
        System.out.printf(name + " : ");
        for (int i = 0; i < 1; i++) {
            notation = isGwang(i);
            System.out.printf("[ " + hand[i].getNumber() + notation + " ] ");
        }
    }

    public void update_chips(long chip) { chips = chip; }
}

