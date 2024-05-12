package 단어변환;

import java.util.*;

public class Word {
    public static void main(String[] args) {
        System.out.println(solution("hit", "cog", new String[] { "hot", "dot", "dog", "lot", "log", "cog" }));
        System.out.println(solution("hit", "cog", new String[] { "hot", "dot", "dog", "lot", "log" }));
    }

    public static int solution(String begin, String target, String[] words) {
        int answer = 0;
        answer = BFS(begin, target, words);     
        return answer;
    }
    
    // BFS 탐색
    public static int BFS(String input, String end, String[] words) {
        HashMap<String, Integer> map = new HashMap<>();
        Queue<Point> queue = new LinkedList<>();
        map.put(input, 0);
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], 0);
        }

        queue.add(new Point(input, 0));
        
        while (!queue.isEmpty()) {
            Point now = queue.poll();
            if (now.now.equals(end)) {
                return now.cnt;
            }
            if (map.get(now.now) == 0) {
                map.put(now.now, 1);
                for (int i = 0; i < words.length; i++) {
                    if (checking(now.now, words[i])) {
                        queue.add(new Point(words[i], now.cnt + 1));
                    }
                }
            }
        }
        return 0;
    }

    // 비교값 체크
    public static boolean checking(String start, String end) {
        String[][] compare = new String[2][start.length()];
        int count = 0;
        compare[0] = start.split("");
        compare[1] = end.split("");

        for (int i = 0; i < start.length(); i++) {
            if (!compare[0][i].equals(compare[1][i]))
                count++;
        }
        // 변환가능 여부
        if (count == 1)
            return true;

        return false;
    }
}

class Point {
    String now;
    int cnt;
    Point(String now, int cnt) {
        this.now = now;
        this.cnt = cnt;
    }
}