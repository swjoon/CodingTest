package baekjoon.gold.내리막길;

import java.util.*;
import java.io.*;

public class Downhill {
    static int M, N;
    static int[][] map;
    static int[][] dp;
    static int[] dirX = { 0, 0, -1, 1 };
    static int[] dirY = { -1, 1, 0, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        dp = new int[M][N];

        for (int y = 0; y < M; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
                dp[y][x] = -1;
            }
        }

        System.out.println(DFS(0, 0));
    }

    static int DFS(int x, int y) {
        if (x == N - 1 && y == M - 1) {
            return 1;
        }

        if (dp[y][x] != -1) {
            return dp[y][x];
        }

        dp[y][x] = 0;

        for (int i = 0; i < 4; i++) {
            int nextx = x + dirX[i];
            int nexty = y + dirY[i];
            if (nextx < 0 || nexty < 0 || nextx >= N || nexty >= M || map[nexty][nextx] >= map[y][x]) {
                continue;
            }
            dp[y][x] += DFS(nextx, nexty);
        }

        return dp[y][x];
    }
}
