package 베스트앨범;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Best {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new String[] { "classic", "pop", "classic", "classic", "pop" },
                new int[] { 500, 600, 500, 800, 2500 })));

    }

    // 1차 test 오류
    public static int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        HashMap<String, ArrayList<int[]>> map = new HashMap<>();
        HashMap<String, Integer> maxValue = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            if (!map.containsKey(genres[i])) {
                map.put(genres[i], new ArrayList<>());
                maxValue.put(genres[i], plays[i]);
            }
            map.get(genres[i]).add(new int[] { i, plays[i] });
            maxValue.put(genres[i], maxValue.get(genres[i]) + plays[i]);
        }

        List<String> lists = new ArrayList<>(maxValue.keySet());
        Collections.sort(lists, (o1, o2) -> maxValue.get(o2) - maxValue.get(o1));
        List<Integer> answers = new ArrayList<>();

        for (String list : lists) {
            List<int[]> genre = map.get(list);
            Collections.sort(genre, (o1, o2) -> o2[1] - o1[1]);
            if (genre.size() > 2 && genre.get(1) == genre.get(2)) {
                genre.get(1)[0] = (genre.get(1)[0] < genre.get(2)[0]) ? genre.get(1)[0] : genre.get(2)[0];
            }
            for (int i = 0; i < Math.min(2, genre.size()); i++) {
                answers.add(genre.get(i)[0]);
            }
        }
        answer = new int[answers.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = answers.get(i);
        }
        return answer;
    }

}
