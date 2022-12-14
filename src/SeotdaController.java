import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class SeotdaController {

    private CardPlayer computer_player;
    private CardPlayer player;
    private Dealer dealer;
    private Jokbo jokbo;
    private Reader reader = new Reader();
    private Printer print = new Printer();
    private int rank_player;
    private int rank_computer;

    private long player_chips;
    private long computer_chips;
    private long betting_default = 25000000; // 기본적으로 게임 시작할 때 배팅하는 금액을 정의

    private long stake = betting_default*2;

    private boolean player_allin;
    private boolean computer_allin;
    private boolean player_call;
    private boolean computer_call;

    private boolean player_die;

    private boolean computer_die;

    private boolean player_win = true;
    private boolean draw;

    private GameBoard board;
    private String user_input;
    private String player_input;



    public SeotdaController(Dealer d){
        dealer = d;
        jokbo = new Jokbo();
        user_input = reader.readName("이름을 입력하세요");
        player = new CardPlayer(2);
        player.name = user_input;
        computer_player = new CardPlayer(2);

        ActionListener action_half = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonClickHandler("하프");
            }
        };

        ActionListener action_call = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonClickHandler("콜");
            }
        };

        ActionListener action_die = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonClickHandler("다이");
            }
        };

        board = new GameBoard(action_half, action_call, action_die);
    }

    public void manageSeotda() {
        board.setPlayerName(player.name);
        board.setPlayerMoney(moneyMessage(player.chips));
        board.setComputerMoney(moneyMessage(computer_player.chips));
        board.setGameMoney(moneyMessage(stake));

        for (int i = 0; i < 2; i++) {
            board.setCards(i+3,dealer.dealOneTo(player));
            board.setCards(i+1,dealer.dealOneTo(computer_player));
        }
        board.setCards(2,new Card(0,0));
        rank_player = jokbo.JokboTrans(player);
        rank_computer = jokbo.JokboTrans(computer_player);

        player.printCards();
        board.setPlayerRank(print.print_rank(rank_player));

        computer_player.printOneCard();

        //새 게임을 위해 기본 값 초기화
        player_call = false;
        computer_call = false;
        player_die = false;
        computer_die = false;
        if (!draw) {
            computer_chips -= betting_default;
            player_chips -= betting_default;
        } else {
            draw = false;
        }

        board.j.repaint();
        System.out.println("\n");
    }

    private void buttonClickHandler(String answer){
        player_input = answer;
        Betting();
        check();
    }

    public String moneyMessage(long money){
        String msg= "";
        if(money==0){
            msg = "0 원";
            return msg;
        }
        //사용가능 최대금액 = 9999억원.
        int divide = 100000000;
        String m[] = {"억 ", "만 ", "원"};
        if(money>=divide){
            msg = (money/divide)+m[0];
            money%=divide;
        }
        divide/=10000;
        if(money>=divide){
            msg = msg + (money/divide)+m[1];
            money%=divide;
        }
        divide/=10000;
        if(money>=1){
            msg = msg + (money/divide) + m[2];
        }
        else msg = msg + m[2];
        return msg;
    }

    public void Betting(){

        // player chip 클래스에서 가져오기
        player_chips = player.chips;
        computer_chips = computer_player.chips;

        // player 턴

        // 둘 다 콜인 경우 -> check 실행해야함
        // 둘다 콜 아닌 경우 -> 콜, 하프, 다이 선택 반복
            System.out.println("***********내 차례***********\n"
                    + "현재 판돈 : " + moneyMessage(stake) + "\n"
                    + "현재 배팅 금액 : " + moneyMessage(betting_default) + "\n"
                    + player.name + " 잔액 : " + moneyMessage(player_chips) + "\n"
                    + computer_player.name + " 잔액 : " + moneyMessage(computer_chips));

            // 컴퓨터가 올인이 아닌 경우 - 하프, 콜, 다이 고를 수 있음

            if (!computer_allin) {
                if (player_input.equals("콜")) {
                    player_input = "";
                    player_call = true;
                    call_player();
                } else if (player_input.equals("하프")) {
                    player_input = "";
                    player_call = false;
                    half_player();
                } else if (player_input.equals("다이")) {
                    player_input = "";
                    player_die = true;

                }
            }

            // 컴퓨터가 올인인 경우 - 상대가 올인이기 때문에 콜, 다이에서만 고른다
            // 콜, 다이 중 어떤 걸 고르더라도 이번 턴 게임은 종료

            else {

                String player_input = reader.readString("상대가 올인입니다. 어떻게 할까요? (콜, 다이)");
                if (player_input.equals("콜")) {
                    call_player();
                    player_call = true;

                }
                else if (player_input.equals("다이")) {
                    player_die = true;

                }

            }

            // computer 턴

            System.out.println("***********상대 차례*************\n"
                    + "현재 판돈 : " + moneyMessage(stake) + "\n"
                    + "현재 배팅 금액 : " + moneyMessage(betting_default) + "\n"
                    + player.name + " 잔액 : " + moneyMessage(player_chips) + "\n"
                    + computer_player.name + " 잔액 : " + moneyMessage(computer_chips));

            // computer_determination에 앞선 경우의 수 구현
            // player가 올인한 경우 컴퓨터는 그냥 콜 하는걸로 구현해서 확률로 구현하면 수정 필요

            String computer_input = computer_determination();

            if (computer_input.equals("콜")) {
                call_computer();
                computer_call = true;
                System.out.println("computer 의 선택 : 콜");
            }
            else if (computer_input.equals("하프")) {
                half_computer();
                computer_call = false;
                System.out.println("computer 의 선택 : 하프");
            }
            else if (computer_input.equals("다이")) {
                computer_die = true;
                System.out.println("computer 의 선택 : 다이");

            }

        board.j.repaint();
        System.out.println( "***********최종 결과*************\n"
                + "현재 판돈 : " + moneyMessage(stake) + "\n"
                + "현재 배팅 금액 : " + moneyMessage(betting_default) + "\n"
                + player.name + " 잔액 : " + moneyMessage(player_chips) + "\n"
                + computer_player.name + " 잔액 : " + moneyMessage(computer_chips));

    }

    public void check(){

        // 무승부, 승부 값 초기화
        draw = false;
        player_win = true;

        if (!(computer_die || player_die)) {

            // 일삼광땡, 일팔광땡에서 지는 경우 : 암행어사, 삼팔광땡

            if ((rank_player == 2 || rank_player == 3) && (rank_computer == 33 || rank_computer == 1))
                player_win = false;

                // 장땡에서 지는 경우 : 광땡

            else if (rank_player == 4 && rank_computer < 4)
                player_win = false;

                // 땡에서 지거나 비기는 경우

            else if (rank_player >= 5 && rank_player <= 13) {

                // 비기는 경우 : 멍구사 (땡은 겹칠 수 없음)
                if (rank_computer == 30) draw = true;

                    // 지는 경우 땡잡이거나 높은 족보인경우
                else if (rank_computer == 32 || (rank_player > rank_computer)) player_win = false;

            }

            // 알리~망통

            else if (rank_player >= 14 && rank_player <= 29) {

                // 비기는 경우 : 멍구사, 구사, 족보 겹침
                if (rank_computer == 30 || rank_computer == 31 || rank_player == rank_computer) draw = true;

                // 지는 경우 : 높은 족보인경우
                if (rank_player > rank_computer) player_win = false;

            }

            // 특수패를 player가 가지는 경우

            else {
                //구사
                if (rank_player == 31) {
                    // 비기는 경우 : 알리 이하
                    // 지는 경우 : 알리 이상
                    if (rank_computer >= 14) draw = true;
                    else player_win = false;
                }

                //멍텅구리구사
                if (rank_player == 30) {
                    // 비기는 경우 : 땡 이하
                    // 지는 경우 : 땡 이상
                    if (rank_computer >= 5) draw = true;
                    else player_win = false;
                }

                // 땡잡이
                if (rank_player == 32) {

                    // 비기는 경우 : 망통
                    if (rank_computer == 29) draw = true;
                        // 지는 경우 : 땡이 아닐 때
                    else if (!(rank_computer >= 5 && rank_computer <= 13)) player_win = false;

                }

                // 암행어사
                if (rank_player == 33) {

                    // 비기는 경우 : 한끗일때
                    if (rank_computer == 28) draw = true;
                        // 지는 경우 : !(이기는 경우:일팔광땡, 일상광땡이거나 망통, 땡잡이)
                    else if (!(rank_computer == 2 || rank_computer == 3 || rank_computer == 29)) player_win = false;

                }

            }

        }

        // 비기면 판돈 그대로 해서 다시 시작
        if(computer_die) {
            player_win();
        }
        else if(player_die) {
            player_lose();
        }
        else if(!player_call || !computer_call) {
            return;
        }
        else if(draw) draw();
        else if(player_win) player_win();
        else player_lose();

        board.setPlayerMoney(moneyMessage(player.chips));
        board.setComputerMoney(moneyMessage(computer_player.chips));
        board.setCards(2,new Card(computer_player.hand[1].getNumber(),computer_player.hand[1].getNotation()));
        board.j.repaint();

        if (JOptionPane.showConfirmDialog(null, "계속 하시겠습니까?", "WARNING",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

            // 무승부인경우 판돈, 배팅 그대로
            // 무승부 아닌 경우 배팅 초기화

            if (!draw) {
                betting_default = 25000000;
                stake = betting_default*2;
            }

            reGame();
        } else {
            System.exit(0);
        }
    }
    public String computer_determination(){

        Random random = new Random();
        double decision = random.nextDouble();
        // 플레이어가 올인이 아닌 경우 콜, 다이, 하프 중 알아서 고름
        if (!player_allin) {
            if (decision < 0.1) return "다이";
            else if (decision > 0.7) return "하프";
            else return "콜";
        } // 플레이어가 올인인 경우 무조건 콜
        else return "콜";

    }
    public void reGame(){

        // 모두 올인이 아닌 경우 + draw에만 경기 재개

        if(!(player_allin || computer_allin) || (draw)){
            board.setComputerRank("");
            dealer = new Dealer();

            // 컴퓨터, 플레이어 칩 모두 업데이트

            long pre_player_chips = player.chips;
            long pre_computer_chips = computer_player.chips;
            player = new CardPlayer(2);
            player.chips = pre_player_chips;
            player.name = user_input;
            computer_player = new CardPlayer(2);
            computer_player.chips = pre_computer_chips;
            manageSeotda();

        }

        // 둘중 하나라도 올인 상태로 게임이 끝나면 게임 종료

        else {
            System.out.println("게임이 종료되었습니다.");
        }
    }

    public void draw(){
        player.update_chips(player_chips);
        computer_player.update_chips(computer_chips);
        player.printCards();
        board.setPlayerRank(print.print_rank(rank_player));
        computer_player.printCards();
        board.setComputerRank(print.print_rank(rank_computer));
        print.print("무승부 입니다");
        print.print("게임을 다시 진행합니다.");
        //reGame();
    }

    public void player_win(){

        // 올인했는데 플레이어가 이긴 경우 올인 초기화 -> 게임 계속 진행
        if (player_allin) player_allin = false;

        player.update_chips(player_chips+stake);
        computer_player.update_chips(computer_chips);
        player.printCards();
        board.setPlayerRank(print.print_rank(rank_player));
        computer_player.printCards();
        board.setComputerRank(print.print_rank(rank_computer));
        print.print(player.name + "님이 이겼습니다.");
        print.print("["+ player.name + "] 현재 총 "+ moneyMessage(player.chips) + "이 있습니다.");
        print.print("[Computer] 현재 총 "+ moneyMessage(computer_player.chips) + "이 있습니다.");
        board.printWinnerMark(1);
        board.j.repaint();
        //reGame();
    }

    public void player_lose(){

        // 컴퓨터가 올인했는데 컴퓨터가 이긴 경우 컴퓨터 올인 초기화
        if (computer_allin) computer_allin = false;

        player.update_chips(player_chips);
        computer_player.update_chips(computer_chips+stake);
        player.printCards();
        board.setPlayerRank(print.print_rank(rank_player));
        computer_player.printCards();
        board.setComputerRank(print.print_rank(rank_computer));
        print.print(player.name + "님이 졌습니다.");
        print.print("["+ player.name + "] 현재 총 "+ moneyMessage(player.chips) + "이 있습니다.");
        print.print("[Computer] 현재 총 "+ moneyMessage(computer_player.chips) + "이 있습니다.");
        board.printWinnerMark(2);
        board.j.repaint();
        //reGame();
    }

    public void show_result(){
        JOptionPane.showMessageDialog(null, "결과를 보여줍니다.");
    }

    public void half_player(){

        long updating_general = stake/2;

        if (player_chips - updating_general < 0) {
            stake += player_chips;
            player_chips = 0;
            player_allin = true;
        }

        else {
            player_chips -= updating_general;
            stake += updating_general;
            betting_default = updating_general;
        }
        board.setGameMoney(moneyMessage(stake));
        board.setPlayerMoney(moneyMessage(player_chips));
        board.j.repaint();
    }

    public void half_computer(){

        long updating_general = stake / 2;

        if (computer_chips - updating_general < 0) {
            stake += computer_chips;
            computer_chips = 0;
            computer_allin = true;
        }

        else {
            computer_chips -= updating_general;
            stake += updating_general;
            betting_default = updating_general;
        }
        board.setGameMoney(moneyMessage(stake));
        board.setComputerMoney(moneyMessage(computer_chips));
        board.j.repaint();

    }

    public void call_player(){

        if (player_chips - betting_default < 0) {
            player_allin = true;
            stake += player_chips;
            player_chips = 0;
        }
        else {
            player_chips -= betting_default;
            stake += betting_default;
        }
        board.setGameMoney(moneyMessage(stake));
        board.setPlayerMoney(moneyMessage(player_chips));
        board.j.repaint();
    }

    public void call_computer(){

        if(computer_chips - betting_default < 0){
            computer_allin = true;
            stake += computer_chips;
            computer_chips = 0;
        }
        else{
            computer_chips -= betting_default;
            stake += betting_default;
        }
        board.setGameMoney(moneyMessage(stake));
        board.setComputerMoney(moneyMessage(computer_chips));
        board.j.repaint();
    }

}