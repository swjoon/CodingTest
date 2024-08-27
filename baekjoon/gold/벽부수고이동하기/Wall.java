package baekjoon.gold.벽부수고이동하기;

import java.util.*;
import java.io.*;

public class Wall {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static boolean[][] usevisited;
    static int[] dirX = { 0, 0, -1, 1 };
    static int[] dirY = { -1, 1, 0, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];
        usevisited = new boolean[N][M];

        for (int y = 0; y < N; y++) {
            String input = br.readLine();
            String[] token = input.split("");
            for (int x = 0; x < M; x++) {
                map[y][x] = Integer.parseInt(token[x]);
            }
        }

        System.out.println(BFS(new Point(0, 0, 1, false)));
    }

    static int BFS(Point start) {
        Queue<Point> q = new LinkedList<>();
        visited[start.y][start.x] = true;
        q.add(start);

        while (!q.isEmpty()) {
            Point p = q.poll();

            if (p.y == N - 1 && p.x == M - 1) {
                return p.cnt;
            }

            if (p.use) {
                for (int i = 0; i < 4; i++) {
                    int nextx = p.x + dirX[i];
                    int nexty = p.y + dirY[i];

                    if (nextx < 0 || nexty < 0 || nextx >= M || nexty >= N || visited[nexty][nextx]
                            || usevisited[nexty][nextx] || map[nexty][nextx] == 1) {
                        continue;
                    }

                    usevisited[nexty][nextx] = true;

                    q.add(new Point(nextx, nexty, p.cnt + 1, true));
                }
            } else {
                for (int i = 0; i < 4; i++) {
                    int nextx = p.x + dirX[i];
                    int nexty = p.y + dirY[i];

                    if (nextx < 0 || nexty < 0 || nextx >= M || nexty >= N || visited[nexty][nextx]) {
                        continue;
                    }

                    if (map[nexty][nextx] == 1 && !usevisited[nexty][nextx]) {
                        q.add(new Point(nextx, nexty, p.cnt + 1, true));
                        continue;
                    }

                    if (map[nexty][nextx] == 1) {
                        continue;
                    }

                    visited[nexty][nextx] = true;

                    q.add(new Point(nextx, nexty, p.cnt + 1, false));
                }
            }
        }
        return -1;
    }
}

class Point {
    int x, y;
    int cnt;
    boolean use;

    Point(int x, int y, int cnt, boolean use) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
        this.use = use;
    }
}
