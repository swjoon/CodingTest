package baekjoon.gold.치즈;

import java.util.*;
import java.io.*;

public class Cheese {
    static int N, M;
    static int move;
    static int[][][] map;
    static int[] dirX = { 1, -1, 0, 0 };
    static int[] dirY = { 0, 0, 1, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M][1000];

        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < M; x++) {
                map[y][x][0] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;

        while (!(move == N * M)) {
            move = 0;
            DFS(0, 0, ans + 1);
            ans++;
        }

        System.out.println(ans - 1);

    }

    static void DFS(int x, int y, int seq) {

        for (int i = 0; i < 4; i++) {
            int nextX = x + dirX[i];
            int nextY = y + dirY[i];

            if (nextX < 0 || nextY < 0 || nextX >= M || nextY >= N) {
                continue;
            }

            if (map[nextY][nextX][0] == 1) {
                if (map[nextY][nextX][seq] == 1) {
                    map[nextY][nextX][0] = 0;
                } else {
                    map[nextY][nextX][seq] = 1;
                }
                continue;
            }

            if (map[nextY][nextX][seq] == 1) {
                continue;
            }

            move++;

            map[nextY][nextX][seq] = 1;
            DFS(nextX, nextY, seq);
        }
    }
}
