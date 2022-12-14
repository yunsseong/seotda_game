public class Printer {

    String[] rank_name = new String[]{"삼 팔 광 땡", "일 삼 광 땡", "일 팔 광 땡", "장   땡", "구   땡", "팔   땡", "칠   땡", "육   땡", "오   땡", "사   땡", "삼   땡", "이   땡", "일   땡",
    "알   리", "독   사", "구   삥", "장   삥", "장   사", "세   륙", "아 홉 끗", "여 덟 끗", "일 곱 끗", "여 섯 끗", "다 섯 끗", " 네   끗 ", "세   끗", "두   끗", "한   끗", "망   통",
    "멍 텅 구 리", "구   사", "땡 잡 이", "암 행 어 사"};

    public void print(String message){
        System.out.println(message);
    }

    public String print_rank(int rank){
        System.out.println(rank_name[rank-1]);
        return rank_name[rank-1];
    }
}
