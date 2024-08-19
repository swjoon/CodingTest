package baekjoon.platinum.백조의호수;

import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Swan {
    static int R, C;
    static char[][] map;
    static int[] dirX = { 0, 0, 1, -1 };
    static int[] dirY = { 1, -1, 0, 0 };
    static boolean[][] watervisited;
    static Queue<Point> water = new LinkedList<>();
    static Queue<Point> meltice = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        watervisited = new boolean[R][C];

        Point start = null;

        for (int y = 0; y < R; y++) {
            String line = br.readLine();
            map[y] = line.toCharArray();
            for (int x = 0; x < C; x++) {
                if (map[y][x] == 'L' && start == null) {
                    start = new Point(x, y);
                }
                if (map[y][x] == 'L' || map[y][x] == '.') {
                    water.add(new Point(x, y));
                    watervisited[y][x] = true;
                }
            }
        }

        int day = 0;

        while (!meet(start)) {
            melt();
            day++;
        }

        System.out.println(day);

    }

    static boolean meet(Point start) {
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[R][C];
        visited[start.y][start.x] = true;

        q.add(start);
        while (!q.isEmpty()) {
            Point p = q.poll();

            for (int i = 0; i < 4; i++) {
                int nextx = p.x + dirX[i];
                int nexty = p.y + dirY[i];

                if (nextx < 0 || nexty < 0 || nextx >= C || nexty >= R || visited[nexty][nextx]
                        || map[nexty][nextx] == 'X') {
                    continue;
                }

                if (map[nexty][nextx] == 'L') {
                    return true;
                }

                visited[nexty][nextx] = true;

                q.add(new Point(nextx, nexty));
            }
        }
        return false;
    }

    static void melt() {
        while (!water.isEmpty()) {
            Point p = water.poll();

            for (int i = 0; i < 4; i++) {
                int nextx = p.x + dirX[i];
                int nexty = p.y + dirY[i];

                if (nextx < 0 || nexty < 0 || nextx >= C || nexty >= R || watervisited[nexty][nextx]) {
                    continue;
                }

                if (map[nexty][nextx] == 'X') {
                    map[nexty][nextx] = '.';
                    meltice.add(new Point(nextx, nexty));
                }

                watervisited[nexty][nextx] = true;
            }
            
        }
        Queue<Point> temp = water;
        water = meltice;
        meltice = temp;
    }
}

class Point {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
