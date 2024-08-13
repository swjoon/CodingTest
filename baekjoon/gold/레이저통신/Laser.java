package baekjoon.gold.레이저통신;

import java.util.*;
import java.io.*;

public class Laser {
    static int[] X = { 0, -1, 0, 1 };
    static int[] Y = { -1, 0, 1, 0 };
    static int W, H;
    static int[][][] record;
    static String[][] map;
    static int[][] stpoint = new int[2][2];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new String[H][W];
        record = new int[H][W][4];

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                Arrays.fill(record[i][j], 10000);
            }
        }
        int n = 0;
        for (int i = 0; i < H; i++) {
            map[i] = br.readLine().split("");
            for (int j = 0; j < W; j++) {
                if (map[i][j].equals("C")) {
                    stpoint[n][0] = j;
                    stpoint[n++][1] = i;
                }
            }
        }
        System.out.println(BFS(stpoint[0]));
    }

    static int BFS(int[] start) {
        PriorityQueue<Point> queue = new PriorityQueue<>((o1, o2) -> o1.cnt - o2.cnt);
        int min = Integer.MAX_VALUE;
        int x = start[0];
        int y = start[1];

        for (int i = 0; i < 4; i++) {
            queue.add(new Point(x, y, i, 0));
            record[y][x][i] = 0;
        }

        while (!queue.isEmpty()) {
            Point point = queue.poll();
            int dir = point.dir;
            int cnt = point.cnt;

            if (point.x == stpoint[1][0] && point.y == stpoint[1][1]) {
                min = Math.min(min, point.cnt);
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nextX = point.x + X[i];
                int nextY = point.y + Y[i];
                int nextCnt = cnt + (i != dir ? 1 : 0);

                if (nextX >= W || nextY >= H || nextX < 0 || nextY < 0 || map[nextY][nextX].equals("*")
                        || ((int) Math.abs(dir - i) == 2)) {
                    continue;
                }

                if (record[nextY][nextX][i] > nextCnt) {
                    record[nextY][nextX][i] = nextCnt;
                    queue.add(new Point(nextX, nextY, i, nextCnt));
                }

            }
        }
        return min;
    }
}

class Point {
    int x, y;
    int dir;
    int cnt;

    Point(int x, int y, int dir, int cnt) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.cnt = cnt;
    }
}
