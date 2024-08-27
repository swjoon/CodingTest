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

            // 벽을 1회 부쉈는가 아닌가 체크여부 use
            if (p.use) {
                for (int i = 0; i < 4; i++) {
                    int nextx = p.x + dirX[i];
                    int nexty = p.y + dirY[i];
                    // 중복방지를 위해 visited와 usevisited를 둘다 체크. 벽을 만났을때 더이상 부술수 없으므로 탐색 불가
                    if (nextx < 0 || nexty < 0 || nextx >= M || nexty >= N || visited[nexty][nextx]
                            || usevisited[nexty][nextx] || map[nexty][nextx] == 1) {
                        continue;
                    }
                    // queue에 넣기전 미리 체크. 메모리 절약
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
