import javax.swing.*;

public class Reader {
    String[] buttons = {"콜", "다이"};

    public String readName(String message) { // 입력 오류 처리 생략
        String input = JOptionPane.showInputDialog(message);
        return input;
    }
    public String readString(String message) { // 입력 오류 처리 생략
        String input = JOptionPane.showInputDialog(message);
        return input;
    }

//    public String readCallDie(String message){
//        int num = JOptionPane.showOptionDialog(null, message, "콜/다이", JOptionPane.YES_NO_CANCEL_OPTION,
//                JOptionPane.QUESTION_MESSAGE, null, buttons, "콜");
//
//    }
//
//    int num = JOptionPane.showOptionDialog(null, "사용자 버튼이 여러 개입니다.", "제목 표시줄입니다.",
//            JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, buttons, "두 번째값");

}