package 경주로건설;

import java.util.*;

public class Road {
    public static void main(String[] args) {
        // solution(new int[][] { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } });
        // solution(new int[][] { { 0, 0, 0, 0, 0, 0, 0, 1 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 1, 0, 0 },
        //         { 0, 0, 0, 0, 1, 0, 0, 0 }, { 0, 0, 0, 1, 0, 0, 0, 1 }, { 0, 0, 1, 0, 0, 0, 1, 0 },
        //         { 0, 1, 0, 0, 0, 1, 0, 0 }, { 1, 0, 0, 0, 0, 0, 0, 0 } });
        solution(new int[][] {{0,0,0,0,0,0},{0,1,1,1,1,0},{0,0,1,0,0,0},{1,0,0,1,0,1},{0,1,0,0,0,1},{0,0,0,0,0,0}});
    }

    public static int solution(int[][] board) {
        int answer = 0;
        List<int[]> list = new ArrayList<>();
        BFS(board, list);

        System.out.println(Arrays.deepToString(list.toArray()));

        return answer;
    }

    public static void BFS(int[][] board, List<int[]> list) {
        Queue<Point> queue = new LinkedList<>();
        int maxx = board.length;
        int maxy = board.length;
        board[0][0] = 1;
        queue.add(new Point(0, 0, 0, 0, 0));

        while (!queue.isEmpty()) {
            Point point = queue.poll();

            if (point.x == maxx - 1 && point.y == maxy - 1) {
                list.add(new int[] { point.x , point.y, point.StraightCnt, point.turnCnt -1 });
                continue;
            }

            board[point.y][point.x] = 1;

            if (point.x + 1 < maxx && board[point.y][point.x + 1] != 1) {
                if (point.before != 1)
                    queue.add(new Point(point.x + 1, point.y, 1, point.StraightCnt + 1, point.turnCnt + 1));
                else
                    queue.add(new Point(point.x + 1, point.y, 1, point.StraightCnt + 1, point.turnCnt));
            }

            if (point.x - 1 > -1 && board[point.y][point.x - 1] != 1) {
                if (point.before != 2)
                    queue.add(new Point(point.x - 1, point.y, 2, point.StraightCnt + 1, point.turnCnt + 1));
                else
                    queue.add(new Point(point.x - 1, point.y, 2, point.StraightCnt + 1, point.turnCnt));
            }

            if (point.y + 1 < maxy && board[point.y + 1][point.x] != 1) {
                if (point.before != 3)
                    queue.add(new Point(point.x, point.y + 1, 3, point.StraightCnt + 1, point.turnCnt + 1));
                else
                    queue.add(new Point(point.x, point.y + 1, 3, point.StraightCnt + 1, point.turnCnt));
            }

            if (point.y - 1 > -1 && board[point.y - 1][point.x] != 1) {
                if (point.before != 4)
                    queue.add(new Point(point.x, point.y - 1, 4, point.StraightCnt + 1, point.turnCnt + 1));
                else
                    queue.add(new Point(point.x, point.y - 1, 4, point.StraightCnt + 1, point.turnCnt));
            }
        }

    }
}

class Point {

    int x;
    int y;
    int before;
    int StraightCnt;
    int turnCnt;

    Point(int x, int y, int before, int StraightCnt, int turnCnt) {
        this.x = x;
        this.y = y;
        this.before = before;
        this.StraightCnt = StraightCnt;
        this.turnCnt = turnCnt;
    }
}