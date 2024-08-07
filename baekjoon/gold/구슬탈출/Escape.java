package baekjoon.gold.구슬탈출;

import java.util.*;
import java.io.*;

// 완전탐색 + dp 메모 활용 방법
public class Escape {

    static int N, M;
    static String[][] map;
    static boolean[][][][] visited;
    static int[] X = { 0, 0, -1, 1 };
    static int[] Y = { 1, -1, 0, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new String[N][M];
        visited = new boolean[N][M][N][M];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().split("");
        }

        int rx = 0, ry = 0, bx = 0, by = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j].equals("R")) {
                    rx = j;
                    ry = i;
                }
                if (map[i][j].equals("B")) {
                    bx = j;
                    by = i;
                }
            }
        }

        BFS(rx, ry, bx, by);

    }

    static void BFS(int rx, int ry, int bx, int by) {
        Queue<Point> queue = new LinkedList<>();

        queue.add(new Point(rx, ry, bx, by, 0));

        while (!queue.isEmpty()) {
            Point point = queue.poll();

            if (visited[point.R_Y][point.R_X][point.B_Y][point.B_X])
                continue;

            if (point.cnt > 10) {
                System.out.println(-1);
                return;
            }

            for (int i = 0; i < 4; i++) {


                
                queue.add(new Point(rx, ry, bx, by, point.cnt + 1));
            }
        }
    }
}

class Point {

    int R_X, R_Y, B_X, B_Y, cnt;

    Point(int R_X, int R_Y, int B_X, int B_Y, int cnt) {
        this.R_X = R_X;
        this.R_Y = R_Y;
        this.B_X = B_X;
        this.B_Y = B_Y;
        this.cnt = cnt;
    }
}
