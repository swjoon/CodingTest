package baekjoon.gold.Easy2048;

import java.util.*;
import java.io.*;

public class Easy {
    static int N;
    static int Max;
    static int[][] map;

    // static int[][][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        // map = new int[N][N][6];

        for (int y = 0; y < N; y++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }
    }

    public static void DFS(int dep) {
        if (dep == 5) {
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < N; x++) {
                    Max = Math.max(Max, map[y][x]);
                }
            }
            return;
        }

        for (int i = 0; i < 4; i++) {
            // move

            DFS(dep + 1);

            // move return
        }
    }
}
