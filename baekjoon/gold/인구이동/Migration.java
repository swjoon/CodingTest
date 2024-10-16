package baekjoon.gold.인구이동;

import java.util.*;
import java.io.*;

public class Migration {
    static int days;
    static int N, L, R;
    static int[][] map;
    static int migrate;
    static boolean[][] visited;
    static int[] dirX = { 0, 0, -1, 1 };
    static int[] dirY = { -1, 1, 0, 0 };

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            visited = new boolean[N][N];
            migrate = 0;

            for (int y = 0; y < N; y++) {
                for (int x = 0; x < N; x++) {
                    if (!visited[y][x]) {
                        visited[y][x] = true;
                        BFS(new Point(x, y));
                    }
                }
            }

            // break 조건 : 더이상의 migration이 없으면 탈출
            if (migrate == 0) {
                break;
            }

            days++;
        }

        System.out.println(days);

    }

    static void BFS(Point point) {
        Queue<Point> q = new LinkedList<>();
        List<Point> list = new ArrayList<>();

        q.add(point);
        list.add(point);

        int cnt = 1;
        int sum = map[point.y][point.x];

        while (!q.isEmpty()) {
            Point p = q.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = p.x + dirX[i];
                int nextY = p.y + dirY[i];

                if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N || visited[nextY][nextX]
                        || Math.abs(map[p.y][p.x] - map[nextY][nextX]) < L
                        || Math.abs(map[p.y][p.x] - map[nextY][nextX]) > R) {
                    continue;
                }

                visited[nextY][nextX] = true;
                sum += map[nextY][nextX];
                list.add(new Point(nextX, nextY));
                cnt++;
                migrate++;
                q.add(new Point(nextX, nextY));
            }
        }

        // 연합간 이동
        if (cnt > 1) {
            int newNum = sum / cnt;
            for (Point l : list) {
                map[l.y][l.x] = newNum;
            }
        }
    }
}