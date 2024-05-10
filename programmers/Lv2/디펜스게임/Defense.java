package 디펜스게임;

import java.util.Collections;
import java.util.PriorityQueue;

public class Defense {
    public static void main(String[] args) {
        System.out.println("답: " + solution(7, 3, new int[] { 4, 2, 4, 5, 3, 3, 1 }));
    }

    public static int solution(int n, int k, int[] enemy) {
        int answer = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());

        if (k >= enemy.length)
            return enemy.length;

        for (int i = 0; i < enemy.length; i++) {
            answer = i;
            queue.add(enemy[i]);
            if (n >= enemy[i]) n -= enemy[i];
            else {
                while (n < enemy[i]) {
                    if (k > 0) {
                        n += queue.poll();
                        k--;
                    } else {
                        return answer;
                    }
                }
                n-=enemy[i];
            }
        }
        return answer + 1;
    }
}
