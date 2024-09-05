package baekjoon.gold.욕심쟁이판다;

import java.util.*;
import java.io.*;

public class GreedyPanda {
    static int N;
    static int ans = 0;
    static int[][] map;
    static int[][] DP;
    static int[] dirX = { 0, 0, -1, 1 };
    static int[] dirY = { 1, -1, 0, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        DP = new int[N][N];
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(DP[i], 1);
        }

        for (int y = 0; y < N; y++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                ans = Math.max(ans ,DFS(x, y));
            }
        }

        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(DP[i]));
        }

        System.out.println(ans);
    }

    static int DFS(int x, int y) {

        if (DP[y][x] != 1) {
            return DP[y][x];
        }

        for (int i = 0; i < 4; i++) {
            int nextx = x + dirX[i];
            int nexty = y + dirY[i];
            int max = 1;

            if (nextx < 0 || nexty < 0 || nextx >= N || nexty >= N || map[y][x] >= map[nexty][nextx]) {
                continue;
            }

            max += DFS(nextx, nexty);

            DP[y][x] = Math.max(DP[y][x], max);
        }

        return DP[y][x];
    }
}
