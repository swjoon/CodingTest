package 경주로건설;

import java.util.*;

public class Road {
    public static void main(String[] args) {
        solution(new int[][] { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } });
        solution(new int[][] { { 0, 0, 1, 0 }, { 0, 0, 0, 0 }, { 0, 1, 0, 1 }, { 1,
        0, 0, 0 } });
    }

    public static int solution(int[][] board) {
        int answer = 0;

        Queue<Point> queue = new LinkedList<>();

        int[][] costBoard = new int[board.length][board.length];

        for (int i = 0; i < costBoard.length; i++) {
            for (int j = 0; j < costBoard.length; j++) {
                costBoard[i][j] = Integer.MAX_VALUE - 600;
            }
        }

        queue.add(new Point(0, 0, 5, 0));

        int[] dx = { 0, 0, -1, 1 };
        int[] dy = { 1, -1, 0, 0 };

        while (!queue.isEmpty()) {
            Point point = queue.poll();

            for (int i = 0; i < 4; i++) {
                int x = point.x + dx[i];
                int y = point.y + dy[i];
                int cost = point.cost;
                if (point.before == i || point.before == 5)
                    cost += 100;
                else
                    cost += 600;

                if (x > -1 && y > -1 && x < board.length && y < board.length && board[y][x] != 1
                        && costBoard[y][x] + 500 > cost) {
                    if (costBoard[y][x] > cost) {
                        costBoard[y][x] = cost;
                    }
                    queue.add(new Point(x, y, i, cost));
                }
            }
        }

        answer = costBoard[board.length - 1][board.length - 1];
        System.out.println(answer);
        return answer;
    }

}

class Point {

    int x;
    int y;
    int before;
    int cost;

    Point(int x, int y, int before, int cost) {
        this.x = x;
        this.y = y;
        this.before = before;
        this.cost = cost;
    }
}