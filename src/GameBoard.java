import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameBoard extends JPanel {


    private ImageIcon[][] images = new ImageIcon[2][15];

    // card1, 2 = computer's cards
    private Card card1 = new Card(0, 0);
    private Card card2 = new Card(0, 0);

    // card3, 4 = user's cards
    private Card card3 = new Card(0, 0);
    private Card card4 = new Card(0, 0);

    public String answer = "";


    private int markW;
    private int markL;
    private int strW;
    private int strL;
    public JFrame j = new JFrame();
    private JButton half = new JButton("하프");
    private JButton call = new JButton("콜");
    private JButton die = new JButton("다이");
    private JButton playerName = new JButton("");
    private JButton playerMoney = new JButton("");
    private JButton playerRank = new JButton("");
    private JButton computerName = new JButton("Computer");
    private JButton computerMoney = new JButton("");
    private JButton computerRank = new JButton("");
    private JButton gameMoney = new JButton("");




    public GameBoard(ActionListener action_half, ActionListener action_call, ActionListener action_die) {
        images[0][0] = new ImageIcon("images/0.png");
        images[1][0] = new ImageIcon("images/0.png");
        images[0][1] = new ImageIcon("images/1_0.png");
        images[1][1] = new ImageIcon("images/1_1.png");
        images[0][2] = new ImageIcon("images/2_0.png");
        images[1][2] = new ImageIcon("images/2_1.png");
        images[0][3] = new ImageIcon("images/3_0.png");
        images[1][3] = new ImageIcon("images/3_1.png");
        images[0][4] = new ImageIcon("images/4_0.png");
        images[1][4] = new ImageIcon("images/4_1.png");
        images[0][5] = new ImageIcon("images/5_0.png");
        images[1][5] = new ImageIcon("images/5_1.png");
        images[0][6] = new ImageIcon("images/6_0.png");
        images[1][6] = new ImageIcon("images/6_1.png");
        images[0][7] = new ImageIcon("images/7_0.png");
        images[1][7] = new ImageIcon("images/7_1.png");
        images[0][8] = new ImageIcon("images/8_0.png");
        images[1][8] = new ImageIcon("images/8_1.png");
        images[0][9] = new ImageIcon("images/9_0.png");
        images[1][9] = new ImageIcon("images/9_1.png");
        images[0][10] = new ImageIcon("images/10_0.png");
        images[1][10] = new ImageIcon("images/10_1.png");

        gameMoney.setBounds(175,375,450,50);
        gameMoney.setOpaque(true);
        gameMoney.setBackground(new Color(33,66,00));
        gameMoney.setForeground(Color.WHITE);
        gameMoney.setBorderPainted(false);
        gameMoney.setFocusPainted(false);
        gameMoney.setFont(new Font("tmp", Font.BOLD, 40));

        playerName.setBounds(30,720,200,40);
        playerName.setOpaque(true);
        playerName.setBackground(new Color(33,66,00));
        playerName.setForeground(Color.WHITE);
        playerName.setBorderPainted(false);
        playerName.setFocusPainted(false);
        playerName.setFont(new Font("tmp", Font.BOLD, 30));

        playerMoney.setBounds(275,740,250,40);
        playerMoney.setOpaque(true);
        playerMoney.setBackground(new Color(33,66,00));
        playerMoney.setForeground(Color.ORANGE);
        playerMoney.setBorderPainted(false);
        playerMoney.setFocusPainted(false);
        playerMoney.setFont(new Font("tmp", Font.BOLD, 20));

        playerRank.setBounds(580,630,200,40);
        playerRank.setOpaque(true);
        playerRank.setBackground(new Color(33,66,00));
        playerRank.setForeground(Color.WHITE);
        playerRank.setBorderPainted(false);
        playerRank.setFocusPainted(false);
        playerRank.setFont(new Font("tmp", Font.BOLD, 30));

        computerName.setBounds(30,30,200,40);
        computerName.setOpaque(true);
        computerName.setBackground(new Color(33,66,00));
        computerName.setForeground(Color.WHITE);
        computerName.setBorderPainted(false);
        computerName.setFocusPainted(false);
        computerName.setFont(new Font("tmp", Font.BOLD, 30));

        computerMoney.setBounds(275,20,250,40);
        computerMoney.setOpaque(true);
        computerMoney.setBackground(new Color(33,66,00));
        computerMoney.setForeground(Color.ORANGE);
        computerMoney.setBorderPainted(false);
        computerMoney.setFocusPainted(false);
        computerMoney.setFont(new Font("tmp", Font.BOLD, 20));

        computerRank.setBounds(580,120,200,40);
        computerRank.setOpaque(true);
        computerRank.setBackground(new Color(33,66,00));
        computerRank.setForeground(Color.WHITE);
        computerRank.setBorderPainted(false);
        computerRank.setFocusPainted(false);
        computerRank.setFont(new Font("tmp", Font.BOLD, 30));


        half.setBounds(285,710,70,20);
        half.addActionListener(action_half);
        call.setBounds(365,710,70,20);
        call.addActionListener(action_call);
        die.setBounds(445,710,70,20);
        die.addActionListener(action_die);
        printWinnerMark(1);
        j.add(gameMoney);
        j.add(playerName);
        j.add(playerMoney);
        j.add(playerRank);
        j.add(computerName);
        j.add(computerMoney);
        j.add(computerRank);
        j.add(half);
        j.add(call);
        j.add(die);
        j.add(this);
        j.setSize(800, 820);
        j.setTitle("섯다!");
        j.setLocationRelativeTo(null);
        j.setVisible(true);

        j.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    protected void paintComponent(Graphics g) {
        Color backColor = new Color(33, 66, 00);
        g.setColor(backColor);
        g.fillRect(0, 0, 800, 800);
        g.setColor(Color.ORANGE);
//        g.drawRect(275,740,250,40);
//        g.drawRect(275,20,250,40);
        g.setColor(Color.WHITE);
//        g.drawRect(175,375,450,50);
        g.drawRect(580,630,200,40);
        g.drawRect(580,120,200,40);
        g.setColor(Color.blue);
        g.fillOval(80,markW,100,100);
        g.setColor(Color.RED);
        g.fillOval(80,markL,100,100);
        g.setColor(Color.WHITE);
        g.setFont(new Font("tmp", Font.BOLD, 50));
        g.drawString("승",110,strW);
        g.drawString("패",110,strL);
//        g.drawRect(30,720,200,40);
//        g.drawRect(30,30,200,40);
        g.drawImage(images[card1.getNotation()][card1.getNumber()].getImage(), 390 - images[card1.getNotation()][card1.getNumber()].getIconWidth(), 70, null);
        g.drawImage(images[card2.getNotation()][card2.getNumber()].getImage(), 410, 70, null);
        g.drawImage(images[card3.getNotation()][card3.getNumber()].getImage(), 390 - images[card3.getNotation()][card3.getNumber()].getIconWidth(), 700 - images[card3.getNotation()][card3.getNumber()].getIconHeight(), null);
        g.drawImage(images[card4.getNotation()][card4.getNumber()].getImage(), 410, 700 - images[card4.getNotation()][card4.getNumber()].getIconHeight(), null);
    }


    /**
     * card 분배 테스트용 코드
     */
    public void printCard(){

        System.out.println("card1 notation : " + card1.getNotation());
        System.out.println("card1 number : " + card1.getNumber());
        System.out.println("card2 notation : " + card2.getNotation());
        System.out.println("card2 number : " + card2.getNumber());
        System.out.println("card3 notation : " + card3.getNotation());
        System.out.println("card3 number : " + card3.getNumber());
        System.out.println("card4 notation : " + card4.getNotation());
        System.out.println("card4 number : " + card4.getNumber());

    }



    public void printWinnerMark(int x){ //1==player, 2==computer
        if(x==1){
            markW = 580;
            markL = 100;
            strW = 645;
            strL = 165;
        } else if(x==2){
            markW = 100;
            markL = 580;
            strW = 165;
            strL = 645;
        }
    }
    public void setCards(int idx, Card card) {
        if (idx == 1) card1 = card;
        else if (idx == 2) card2 = card;
        else if (idx == 3) card3 = card;
        else if (idx == 4) card4 = card;
    }

    public void setPlayerMoney(String str) {
        playerMoney.setText(str);
    }

    public void setPlayerRank(String str) {
        playerRank.setText(str);
    }

    public void setComputerMoney(String str) {
        computerMoney.setText(str);
    }

    public void setComputerRank(String str) {
        computerRank.setText(str);
    }

    public void setPlayerName(String str) {
        playerName.setText(str);
    }

    public void setGameMoney(String str) {
        gameMoney.setText(str);
    }

}
