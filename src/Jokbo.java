public class Jokbo {
    private CardPlayer player;
    private int hand_0_num;
    private int hand_0_not;
    private int hand_1_num;
    private int hand_1_not;
    private int hand_num_sum;
    private int hand_not_sum;
    private int hand_sum;
    private boolean same_num;

    public int JokboTrans(CardPlayer p) {

        same_num = false;

        player = p;

        hand_0_num = player.hand[0].getNumber();
        hand_0_not = player.hand[0].getNotation();
        hand_1_num = player.hand[1].getNumber();
        hand_1_not = player.hand[1].getNotation();

        hand_num_sum = hand_0_num + hand_1_num;
        hand_not_sum = hand_0_not + hand_1_not;

        hand_sum = (hand_0_num + hand_1_num) % 10;

        if (hand_0_num == hand_1_num)
            same_num = true;


        // 특수족보
        if ((hand_0_num == 4 || hand_0_num == 9) && (hand_1_num == 4 || hand_1_num == 9) && hand_not_sum == 2 && hand_0_num != hand_1_num) return 30;
        if ((hand_0_num == 4 || hand_0_num == 9) && (hand_1_num == 4 || hand_1_num == 9) && hand_0_num != hand_1_num) return 31;
        if ((hand_0_num == 3 || hand_0_num == 7) && (hand_1_num == 3 || hand_1_num == 7) && hand_not_sum == 2) return 32;
        if ((hand_0_num == 4 || hand_0_num == 7) && (hand_1_num == 4 || hand_1_num == 7) && hand_not_sum == 2) return 33;

        // 광땡
        if (hand_not_sum == 2 && (hand_0_num == 3 || hand_0_num == 8) && (hand_1_num == 3 || hand_1_num == 8)) return 1;
        if (hand_not_sum == 2 && (hand_0_num == 1 || hand_0_num == 3) && (hand_1_num == 1 || hand_1_num == 3)) return 2;
        if (hand_not_sum == 2 && (hand_0_num == 1 || hand_0_num == 8) && (hand_1_num == 1 || hand_1_num == 8)) return 3;


        // 땡
        if (same_num && hand_num_sum == 20) return 4;
        if (same_num && hand_num_sum == 18) return 5;
        if (same_num && hand_num_sum == 16) return 6;
        if (same_num && hand_num_sum == 14) return 7;
        if (same_num && hand_num_sum == 12) return 8;
        if (same_num && hand_num_sum == 10) return 9;
        if (same_num && hand_num_sum == 8) return 10;
        if (same_num && hand_num_sum == 6) return 11;
        if (same_num && hand_num_sum == 4) return 12;
        if (same_num && hand_num_sum == 2) return 13;

        // 중간족보
        if ((hand_0_num == 1 || hand_0_num == 2) && (hand_1_num == 1 || hand_1_num == 2)) return 14;
        if ((hand_0_num == 1 || hand_0_num == 4) && (hand_1_num == 1 || hand_1_num == 4)) return 15;
        if ((hand_0_num == 1 || hand_0_num == 9) && (hand_1_num == 1 || hand_1_num == 9)) return 16;
        if ((hand_0_num == 1 || hand_0_num == 10) && (hand_1_num == 1 || hand_1_num == 10)) return 17;
        if ((hand_0_num == 4 || hand_0_num == 10) && (hand_1_num == 4 || hand_1_num == 10)) return 18;
        if ((hand_0_num == 4 || hand_0_num == 6) && (hand_1_num == 4 || hand_1_num == 6)) return 19;

        // 끗
        if (hand_sum == 9) return 20;
        if (hand_sum == 8) return 21;
        if (hand_sum == 7) return 22;
        if (hand_sum == 6) return 23;
        if (hand_sum == 5) return 24;
        if (hand_sum == 4) return 25;
        if (hand_sum == 3) return 26;
        if (hand_sum == 2) return 27;
        if (hand_sum == 1) return 28;
        if (hand_sum == 0) return 29;

        return 0;
    }
}
