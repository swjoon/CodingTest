package baekjoon.gold.불;

import java.util.*;
import java.io.*;

public class Fire {
    private static int[] dirX = new int[] { 0, 0, -1, 1 };
    private static int[] dirY = new int[] { -1, 1, 0, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int Y = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        int[][] map = new int[Y][X];

        Deque<FirePoint> fireQueue = new ArrayDeque<>();
        Deque<MovePoint> moveQueue = new ArrayDeque<>();

        for (int y = 0; y < Y; y++) {
            char[] row = br.readLine().toCharArray();

            for (int x = 0; x < X; x++) {
                switch (row[x]) {
                    case '#':
                        map[y][x] = -1;
                        break;
                    case 'F':
                        fireQueue.add(new FirePoint(x, y, 1));
                        map[y][x] = 1;
                        break;
                    case 'J':
                        moveQueue.add(new MovePoint(x, y, 1));
                        map[y][x] = 0;
                        break;
                    default:
                        map[y][x] = 0;
                        break;
                }
            }
        }

        print(map, X, Y);

        applyFireToMap(fireQueue, map, X, Y);

        print(map, X, Y);

        int time = findRoute(moveQueue, map, X, Y);

        System.out.println(time == -1 ? "IMPOSSIBLE" : time);

    }

    private static int findRoute(Deque<MovePoint> moveQueue, int[][] map, int X, int Y) {
        boolean[][] visited = new boolean[Y][X];

        MovePoint start = moveQueue.peek();

        visited[start.y][start.x] = true;

        while (!moveQueue.isEmpty()) {
            MovePoint now = moveQueue.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dirX[i];
                int nextY = now.y + dirY[i];
                int count = now.count;

                if (nextX < 0 || nextX >= X || nextY < 0 || nextY >= Y) {
                    return count;
                }

                // 벽 or 불 or 방문
                if (map[nextY][nextX] == -1 || visited[nextY][nextX]) {
                    continue;
                }

                if (map[nextY][nextX] > count || map[nextY][nextX] == 0) {

                    visited[nextY][nextX] = true;

                    moveQueue.add(new MovePoint(nextX, nextY, count + 1));
                }

            }
        }

        return -1;
    }

    private static void applyFireToMap(Deque<FirePoint> fireQueue, int[][] map, int X, int Y) {

        while (!fireQueue.isEmpty()) {
            FirePoint now = fireQueue.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dirX[i];
                int nextY = now.y + dirY[i];
                int count = now.count;

                if (nextX < 0 || nextX >= X || nextY < 0 || nextY >= Y || map[nextY][nextX] != 0) {
                    continue;
                }

                map[nextY][nextX] = count;

                fireQueue.add(new FirePoint(nextX, nextY, count + 1));
            }

        }
    }

    private static void print(int[][] map, int X, int Y) {
        for (int y = 0; y < Y; y++) {
            System.out.println(Arrays.toString(map[y]));
        }

        System.out.println();
    }
}

class FirePoint {
    int x;
    int y;
    int count;

    public FirePoint(int x, int y, int count) {
        this.x = x;
        this.y = y;
        this.count = count;
    }
}

class MovePoint {
    int x;
    int y;
    int count;

    public MovePoint(int x, int y, int count) {
        this.x = x;
        this.y = y;
        this.count = count;
    }
}