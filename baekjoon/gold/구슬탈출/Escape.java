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
    static int redx, redy, bluex, bluey, cnt;
    static boolean goalR, goalB;

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
                    map[i][j] = ".";
                    rx = j;
                    ry = i;
                }
                if (map[i][j].equals("B")) {
                    bx = j;
                    by = i;
                    map[i][j] = ".";
                }
            }
        }

        // 10회 이내로 클리어 못할시 -1 출력
        if (!BFS(rx, ry, bx, by)) {
            System.out.println(-1);
        }

    }

    static boolean BFS(int rx, int ry, int bx, int by) {
        Queue<Point> queue = new LinkedList<>();

        queue.add(new Point(rx, ry, bx, by, 0));

        while (!queue.isEmpty()) {
            Point point = queue.poll();

            visited[point.R_Y][point.R_X][point.B_Y][point.B_X] = true;

            if (point.cnt >= 10) {
                return false;
            }

            for (int i = 0; i < 4; i++) {
                redx = point.R_X;
                redy = point.R_Y;
                bluex = point.B_X;
                bluey = point.B_Y;
                cnt = point.cnt + 1;
                goalR = false;
                goalB = false;

                // 상황제어 -> 상황에 맞춰 먼저움직여야할 색의 구슬부터 이동.
                if (redx == bluex && (i == 0 || i == 1)) {
                    if (i == 0) { // 상
                        if (redy > bluey) {
                            moveball("red", i);
                            moveball("blue", i);
                        } else {
                            moveball("blue", i);
                            moveball("red", i);
                        }
                    } else { // 하
                        if (redy > bluey) {
                            moveball("blue", i);
                            moveball("red", i);
                        } else {
                            moveball("red", i);
                            moveball("blue", i);
                        }
                    }
                } else if (redy == bluey && (i == 2 || i == 3)) {
                    if (i == 2) { // 좌
                        if (redx > bluex) {
                            moveball("blue", i);
                            moveball("red", i);
                        } else {
                            moveball("red", i);
                            moveball("blue", i);
                        }
                    } else { // 우
                        if (redx > bluex) {
                            moveball("red", i);
                            moveball("blue", i);
                        } else {
                            moveball("blue", i);
                            moveball("red", i);
                        }
                    }
                } else {
                    moveball("blue", i);
                    moveball("red", i);
                }

                if (goalR && !goalB) {
                    System.out.println(cnt);
                    return true;
                } else if (goalB)
                    continue;

                if (visited[redy][redx][bluey][bluex])
                    continue;

                queue.add(new Point(redx, redy, bluex, bluey, cnt));
            }
        }
        return false;
    }

    // 구슬 이동
    static void moveball(String color, int i) {
        if (color.equals("blue")) {
            while (true) {
                int nextX = bluex + X[i];
                int nextY = bluey + Y[i];
                if (!map[nextY][nextX].equals(".") || (nextX == redx && nextY == redy)) {
                    if (map[nextY][nextX].equals("O")) {
                        bluex = 0;
                        bluey = 0;
                        goalB = true;
                    }
                    break;
                }
                bluex = nextX;
                bluey = nextY;
            }
        } else {
            while (true) {
                int nextX = redx + X[i];
                int nextY = redy + Y[i];
                if (!map[nextY][nextX].equals(".") || (nextX == bluex && nextY == bluey)) {
                    if (map[nextY][nextX].equals("O")) {
                        redx = 0;
                        redy = 0;
                        goalR = true;
                    }
                    break;
                }
                redx = nextX;
                redy = nextY;
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