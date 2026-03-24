package baekjoon.gold.Monkey;

import java.io.*;
import java.util.*;

public class Monkey {

    private static final int INF = Integer.MAX_VALUE;
    private static final int[][] DIR = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private static final int[][] HORSEDIR = {{2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}};

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));) {
            int K = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());

            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());

            int[][] map = new int[Y][X];
            int[][][] visited = new int[Y][X][K + 1];

            for (int y = 0; y < Y; y++) {
                for (int x = 0; x < X; x++) {
                    Arrays.fill(visited[y][x], INF);
                }
            }

            visited[0][0][K] = 0;

            for (int y = 0; y < Y; y++) {
                st = new StringTokenizer(br.readLine());

                for (int x = 0; x < X; x++) {
                    map[y][x] = Integer.parseInt(st.nextToken());
                }
            }

            Point start = new Point(0, 0, K, 0);

            int answer = findMinMoveCount(start, X, Y, map, visited);

            System.out.println(answer);

        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    private static int findMinMoveCount(Point start, int X, int Y, int[][] map, int[][][] visited) {
        Queue<Point> q = new LinkedList<>();

        q.add(start);

        while (!q.isEmpty()) {
            Point now = q.poll();

            if (now.k > 0) {
                for (int i = 0; i < 8; i++) {
                    int nextX = now.x + HORSEDIR[i][0];
                    int nextY = now.y + HORSEDIR[i][1];
                    int nextK = now.k - 1;
                    int nextCnt = now.cnt + 1;

                    if (nextX < 0 || nextY < 0 || nextX >= X || nextY >= Y || map[nextY][nextX] == 1 || visited[nextY][nextX][nextK] < nextCnt) {
                        continue;
                    }

                    visited[nextY][nextX][nextK] = nextCnt;

                    if (nextX == X - 1 && nextY == Y - 1) {
                        continue;
                    }

                    q.add(new Point(nextX, nextY, nextK, nextCnt));
                }
            }

            for (int i = 0; i < 4; i++) {
                int nextX = now.x + DIR[i][0];
                int nextY = now.y + DIR[i][1];
                int nextCnt = now.cnt + 1;

                if (nextX < 0 || nextY < 0 || nextX >= X || nextY >= Y || map[nextY][nextX] == 1 || visited[nextY][nextX][now.k] <= nextCnt) {
                    continue;
                }

                visited[nextY][nextX][now.k] = nextCnt;

                if (nextX == X - 1 && nextY == Y - 1) {
                    continue;
                }

                q.add(new Point(nextX, nextY, now.k, nextCnt));
            }
        }

        int minValue = Arrays.stream(visited[Y - 1][X - 1]).min().getAsInt();

        return minValue == INF ? -1 : minValue;
    }
}

class Point {

    int x, y, k, cnt;

    public Point(int x, int y, int k, int cnt) {
        this.x = x;
        this.y = y;
        this.k = k;
        this.cnt = cnt;
    }
}
