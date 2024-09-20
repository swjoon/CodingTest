package baekjoon.gold.달이차오른다가자;

import java.util.*;
import java.io.*;

public class Moon {
    static int N, M;
    static char[][] map;
    static int[][][] DP;
    static int[] dirX = { 0, 0, -1, 1 };
    static int[] dirY = { 1, -1, 0, 0 };

    static class Point {
        int x, y;
        int cnt;
        int key;

        Point(int x, int y, int cnt, int key) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.key = key;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];

        for (int y = 0; y < N; y++) {
            map[y] = br.readLine().toCharArray();
        }

        DP = new int[N][M][1 << 7];

        for (int[][] twoDArray : DP) {
            for (int[] oneDArray : twoDArray) {
                Arrays.fill(oneDArray, Integer.MAX_VALUE);
            }
        }

        // 현위치 찾으면 탐색시작
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if (map[y][x] == '0') {
                    System.out.println(BFS(new Point(x, y, 0, 0)));
                    return;
                }
            }
        }
    }

    static int BFS(Point start) {
        Queue<Point> q = new LinkedList<>();
        DP[start.y][start.x][start.key] = 0;

        q.add(start);
        
        while (!q.isEmpty()) {
            Point p = q.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = p.x + dirX[i];
                int nextY = p.y + dirY[i];
                int newKey = p.key;
                int nextcnt = p.cnt + 1;

                // 벽을 만났을때
                if (nextX < 0 || nextY < 0 || nextX >= M || nextY >= N || map[nextY][nextX] == '#') {
                    continue;
                }

                char nextT = map[nextY][nextX];

                // 다음이 출구일때
                if (nextT == '1') {
                    return nextcnt;
                }
                // 문이나 열쇠를 만났을때
                if (nextT != '.' && nextT != '0') {
                    // 문을 만났을때
                    if (Character.isUpperCase(nextT)) {
                        int type = type(Character.toLowerCase(nextT));
                        // 맞는 열쇠가 없을때 false
                        if (((1 << type) & p.key) == 0) {
                            continue;
                        }
                    // 열쇠 찾음
                    } else if (Character.isLowerCase(nextT)) {
                        int type = type(nextT);
                        newKey = newKey | (1 << type);
                    }
                }

                // 이미 같은 키를 가지고 방문했던곳
                if (DP[nextY][nextX][newKey] <= nextcnt) {
                    continue;
                }

                DP[nextY][nextX][newKey] = nextcnt;
                q.add(new Point(nextX, nextY, nextcnt, newKey));
            }
        }
        // 도달 못할때
        return -1;
    }

    static int type(char key) {
        switch (key) {
            case 'a':
                return 1;
            case 'b':
                return 2;
            case 'c':
                return 3;
            case 'd':
                return 4;
            case 'e':
                return 5;
            default:
                return 6;
        }
    }
}
