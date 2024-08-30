package baekjoon.gold.색종이붙이기;

import java.util.*;
import java.io.*;

public class Paper {
    static int N = 10;
    static int ans = Integer.MAX_VALUE;
    static int[] dirX = { 1, 0 };
    static int[] dirY = { 0, 1 };
    static int[][] map = new int[N][N];
    static int[] remain = new int[6];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Arrays.fill(remain, 5);

        for (int y = 0; y < N; y++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        DFS(0, 0, 0);

        // ans에 변화가 없을시 == 불가능
        if (ans == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }

        System.out.println(ans);
    }

    static void DFS(int x, int y, int cnt) {

        if (x > 9 && y >= 9) {
            ans = Math.min(ans, cnt);
            return;
        }

        if (ans <= cnt) {
            return;
        }

        if (x > 9) {
            DFS(0, y + 1, cnt);
            return;
        }

        if (map[y][x] == 1) {
            // 큰 면적의 종이부터 추적
            for (int i = 5; i > 0; i--) {
                if (x + i <= N && y + i <= N && remain[i] > 0 && check(x, y, i)) {
                    // 백트래킹 활용
                    change(x, y, i, 0);
                    remain[i]--;
                    DFS(x + 1, y, cnt + 1);
                    change(x, y, i, 1);
                    remain[i]++;
                }
            }
        } else {
            DFS(x + 1, y, cnt);
        }
    }

    // 가능여부 체크
    static boolean check(int x, int y, int side) {
        for (int i = y; i < y + side; i++) {
            for (int j = x; j < x + side; j++) {
                if (map[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    // 종이 붙임 여부에 따라 상태 변경
    static void change(int x, int y, int side, int state) {
        for (int i = y; i < y + side; i++) {
            for (int j = x; j < x + side; j++) {
                map[i][j] = state;
            }
        }
    }
}
