package 혼자서하는틱택토;

public class tictactoe {
    public static void main(String[] args) {
        System.out.println(solution(new String[] { "O.X", ".O.", "..X" }));
        System.out.println(solution(new String[] { "O.X", "O.X", "O.." }));
        System.out.println(solution(new String[] { "XOX", "OXO", "XOO" }));
        System.out.println(solution(new String[] { "OOO", "O..", "XXX" }));
    }

    public static int solution(String[] board) {
        String[][] boards = new String[3][3];
        int answer = 0;
        int Onum = 0;
        int Xnum = 0;
        int Obingo = 0;
        int Xbingo = 0;

        for (int i = 0; i < 3; i++) {
            boards[i] = board[i].split("");
            for (int j = 0; j < 3; j++) {
                if (boards[i][j].equals("O"))
                    Onum++;
                else if (boards[i][j].equals("X"))
                    Xnum++;
            }
        }

        if (Onum < Xnum || Onum - Xnum > 1)
            return answer;

        for (int i = 0; i < 3; i++) {
            if (Xbingo(i, boards, "O"))
                Obingo++;
            if (Xbingo(i, boards, "X"))
                Xbingo++;
        }
        for (int i = 0; i < 3; i++) {
            if (Ybingo(i, boards, "O"))
                Obingo++;
            if (Ybingo(i, boards, "X"))
                Xbingo++;
        }
        if (boards[0][0].equals("O") && boards[1][1].equals("O") && boards[2][2].equals("O"))
            Obingo++;
        if (boards[0][2].equals("O") && boards[1][1].equals("O") && boards[2][0].equals("O"))
            Obingo++;
        if (boards[0][0].equals("X") && boards[1][1].equals("X") && boards[2][2].equals("X"))
            Xbingo++;
        if (boards[0][2].equals("X") && boards[1][1].equals("X") && boards[2][0].equals("X"))
            Xbingo++;

        if ((Obingo > 0 && Xbingo > 0)) return answer;

        if ((Obingo >= 1 && Onum == Xnum) || (Xbingo >= 1 && Onum > Xnum)) return answer;
        return 1;
    }

    public static boolean Xbingo(int x, String[][] boards, String OX) {
        return boards[x][0].equals(OX) && boards[x][1].equals(OX) && boards[x][2].equals(OX);
    }

    public static boolean Ybingo(int y, String[][] boards, String OX) {
        return boards[0][y].equals(OX) && boards[1][y].equals(OX) && boards[2][y].equals(OX);
    }
}
