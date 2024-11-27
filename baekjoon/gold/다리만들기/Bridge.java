package baekjoon.gold.다리만들기;

import java.util.*;
import java.io.*;

public class Bridge {
    static int size;
    static int[][] map;
    static boolean[][] visited;
    static int[] dirX = { 0, 0, -1, 1 };
    static int[] dirY = { 1, -1, 0, 0 };
    static Queue<Point> q = new LinkedList<>();
    static Map<Integer, Point> m = new HashMap<>();

    static class Point {
        int x, y, cnt, type;

        public Point(int x, int y, int cnt, int type) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.type = type;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        size = Integer.parseInt(br.readLine());
        map = new int[size][size];
        visited = new boolean[size][size];

        for (int y = 0; y < size; y++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int x = 0; x < size; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        // 섬 종류
        int type = 1;

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if (!visited[y][x] && map[y][x] == 1) {
                    visited[y][x] = true;
                    findCoastline(new Point(x, y, 0, type));
                    type++;
                }
            }
        }

        System.out.println(makingBridge());

    }

    // 섬마다 해안선 찾기 (다리 지을 시작점을 찾아서 q에 집어넣는다)
    static void findCoastline(Point point) {
        Queue<Point> list = new LinkedList<>();

        list.add(point);

        while (!list.isEmpty()) {
            boolean check = false;
            Point now = list.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dirX[i];
                int nextY = now.y + dirY[i];
                if (nextX < 0 || nextY < 0 || nextX >= size || nextY >= size) {
                    continue;
                }

                if (map[nextY][nextX] == 0) {
                    if (check) {
                        continue;
                    }
                    check = true;
                    q.add(new Point(now.x, now.y, 0, now.type));
                    continue;
                }

                if (visited[nextY][nextX]) {
                    continue;
                }

                visited[nextY][nextX] = true;

                list.add(new Point(nextX, nextY, now.cnt, now.type));
            }
        }
    }

    // 다리 놓기
    static int makingBridge() {
        int count = 0;
        boolean check = false;
        int min = Integer.MAX_VALUE;

        while (!q.isEmpty()) {
            Point now = q.poll();

            if (check && count != now.cnt) {
                return min;
            }

            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dirX[i];
                int nextY = now.y + dirY[i];

                if (nextX < 0 || nextY < 0 || nextX >= size || nextY >= size) {
                    continue;
                }

                if (visited[nextY][nextX]) {
                    Point p = m.containsKey(nextY * size + nextX) ? m.get(nextY * size + nextX) : null;
                    if (p == null) {
                        continue;
                    }
                    if (p.type != now.type) {
                        min = Math.min(min, now.cnt + p.cnt);
                        count = now.cnt;
                        check = true;
                    }
                    continue;
                }

                visited[nextY][nextX] = true;

                q.add(new Point(nextX, nextY, now.cnt + 1, now.type));
                m.put(nextY * size + nextX, new Point(nextX, nextY, now.cnt + 1, now.type));
            }
        }
        return 0;
    }
}