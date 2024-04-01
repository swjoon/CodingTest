package CodingTest.programmers.Lv1.달리기경주;


import java.util.*;

public class run {
    // 시간초과
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new String[] { "mumu", "soe", "poe", "kai", "mine" },
                new String[] { "kai", "kai", "mine", "mine" })));
    }

    public static String[] solution(String[] players, String[] callings) {
        HashMap<Integer, String> map = new HashMap<>();

        for (int i = 0; i < players.length; i++) {
            map.put(i, players[i]);
        }

        for (int i = 0; i < callings.length; i++) {

            for (int j = 0; j < players.length; j++) {
                if (players[j].equals(callings[i])) {
                    String change = players[j - 1];
                    players[j - 1] = players[j];
                    players[j] = change;
                    break;
                }
            }

        }

        return players;
    }
}
