package baekjoon.platinum.Run;

import java.util.*;
import java.io.*;

public class Run {
    private static final int INF = Integer.MAX_VALUE;
    private static final int[] DIRX = { 0, 0, -1, 1 };
    private static final int[] DIRY = { -1, 1, 0, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int Y = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        char[][] map = new char[Y][X];

        for (int y = 0; y < Y; y++) {
            map[y] = br.readLine().toCharArray();
        }

        st = new StringTokenizer(br.readLine());

        Point[] point = new Point[2];

        for (int i = 0; i < 2; i++) {
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            point[i] = new Point(x, y, 0);
        }

        int ans = bfs(point, K, X, Y, map);

        System.out.println(ans);

        br.close();
    }

    private static int bfs(Point[] point, int K, int X, int Y, char[][] map) {
        Deque<Point> queue = new ArrayDeque<>();

        int[][] dist = new int[Y][X];

        queue.add(point[0]);

        for (int[] distance : dist) {
            Arrays.fill(distance, INF);
        }

        dist[point[0].y][point[0].x] = 0;

        while (!queue.isEmpty()) {
            Point now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nextCnt = now.cnt + 1;

                for (int k = 1; k <= K; k++) {
                    int nextX = now.x + DIRX[i] * k;
                    int nextY = now.y + DIRY[i] * k;

                    if (nextX < 0 || nextY < 0 || nextX >= X || nextY >= Y) {
                        break;
                    }

                    if (map[nextY][nextX] == '#') {
                        break;
                    }

                    if (dist[nextY][nextX] < nextCnt) {
                        break;
                    }

                    if (dist[nextY][nextX] > nextCnt) {

                        dist[nextY][nextX] = nextCnt;
                        queue.add(new Point(nextX, nextY, nextCnt));
                    }

                    if (point[1].x == nextX && point[1].y == nextY) {
                        break;
                    }
                }
            }
        }

        int ans = dist[point[1].y][point[1].x];

        return ans == INF ? -1 : ans;
    }
}

class Point {
    int x, y, cnt;

    public Point(int x, int y, int cnt) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }
}