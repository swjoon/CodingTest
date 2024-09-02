package baekjoon.gold.미친로봇;

import java.util.*;
import java.io.*;

public class Robot {
    static int T;
    static double ans;
    static double[] dir;
    static boolean[][] visited;
    static int[] dirX = { 1, -1, 0, 0 };
    static int[] dirY = { 0, 0, 1, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        dir = new double[4];

        T = Integer.parseInt(st.nextToken());
        for (int i = 0; i < 4; i++) {
            dir[i] = Double.parseDouble(st.nextToken()) * 0.01;
        }

        visited = new boolean[2 * T + 1][2 * T + 1];
        visited[T][T] = true;

        ans = 0;

        DFS(T, T, 1, 0);

        System.out.println(ans);
    }

    static void DFS(int x, int y, double p, int cnt) {

        if (cnt == T) {
            ans += p;
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nextx = x + dirX[i];
            int nexty = y + dirY[i];
            if (visited[nexty][nextx]) {
                continue;
            }
            visited[nexty][nextx] = true;
            DFS(nextx, nexty, p * dir[i], cnt + 1);
            visited[nexty][nextx] = false;
        }
    }
}
