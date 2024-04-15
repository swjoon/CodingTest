package CodingTest.programmers.Lv1.달리기경주;


import java.util.*;


// map 과 array 사용 해결
public class run {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new String[] { "mumu", "soe", "poe", "kai", "mine" },
                new String[] { "kai", "kai", "mine", "mine" })));
    }

    public static String[] solution(String[] players, String[] callings) {
        HashMap<String, Integer> map = new HashMap<>();

        for (int i = 0 ; i < players.length; i++) {
            map.put(players[i], i);
        }

     
        for(int i = 0; i < callings.length; i++){
            int num = map.get(callings[i]);
            String fname = players[num-1];
            map.put(fname, num);
            map.put(callings[i],num-1);
            players[num] = fname;
            players[num-1] = callings[i];
        }

        return players;
    }
}
