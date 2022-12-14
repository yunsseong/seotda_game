public class Card {
    public static final int SIZE_OF_ONE_SUIT = 2;

    private int number;
    private int notation;
    public Card(int nb, int nt) {
        number = nb;
        notation = nt;
    }

    public int getNumber() {
        return number;
    }

    public int getNotation() {
        return notation;
    }
}
