package 당구연습;

import java.util.*;

public class ball {
    public static void main(String[] args) {
        solution(10, 10, 3, 7, new int[][] { { 7, 7 }, { 2, 7 }, { 7, 3 } });
    }

    public static int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        ArrayList<Integer> list = new ArrayList<>();
        int[] answer = new int[balls.length];

        for (int i = 0; i < balls.length; i++) {
            int xlen;
            int ylen;
            // 좌우
            if (balls[i][1] != startY) {
                // 좌변
                ylen = Math.abs(balls[i][1] - startY);
                xlen = balls[i][0] + startX;
                list.add((int) (Math.pow(xlen, 2) + Math.pow(ylen, 2)));
                // 우변
                xlen = 2 * m - xlen;
                list.add((int) (Math.pow(xlen, 2) + Math.pow(ylen, 2)));
            } else {
                // y 같을때
                if (balls[i][0] > startX) {
                    list.add((int) Math.pow(balls[i][0] + startX, 2));
                } else {
                    list.add((int) Math.pow(2 * m - (balls[i][0] + startX), 2));
                }
            }
            // 상하
            if (balls[i][0] != startX) {
                // 밑변
                xlen = Math.abs(balls[i][0] - startX);
                ylen = balls[i][1] + startY;
                list.add((int) (Math.pow(xlen, 2) + Math.pow(ylen, 2)));
                // 윗변
                ylen = 2 * n - ylen;
                list.add((int) (Math.pow(xlen, 2) + Math.pow(ylen, 2)));
            } else {
                // x같을때
                if (balls[i][1] > startY) {
                    list.add((int) Math.pow(balls[i][1] + startY, 2));
                } else {
                    list.add((int) Math.pow(2 * n - (balls[i][1] + startY), 2));
                }
            }
            int min = Collections.min(list);
            answer[i] = min;
            list.clear();
        }

        return answer;
    }
}
