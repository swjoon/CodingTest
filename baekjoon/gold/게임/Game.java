package baekjoon.gold.게임;

import java.util.*;
import java.io.*;

public class Game {
    static int ans;
    static int N, M;
    static int[][] DP;
    static char[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        DP = new int[N][M];
        map = new char[N][M];
        visited = new boolean[N][M];

        for (int y = 0; y < N; y++) {
            map[y] = br.readLine().toCharArray();
        }

        visited[0][0] = true;
        DFS(0, 0, 0);

        System.out.println((ans == Integer.MAX_VALUE) ? -1 : ans);
    }

    static void DFS(int x, int y, int cnt) {
        if (ans == Integer.MAX_VALUE) {
            return;
        }
        int move = Integer.parseInt(Character.toString(map[y][x]));
        // 각 칸의 움직임
        int[] dirX = new int[] { move, -move, 0, 0 };
        int[] dirY = new int[] { 0, 0, move, -move };

        // 네가지 방향
        for (int i = 0; i < 4; i++) {
            int nextX = x + dirX[i];
            int nextY = y + dirY[i];
            int nextcnt = cnt + 1;

            // out 조건시 움직인 횟수 저장
            if (nextX < 0 || nextY < 0 || nextX >= M || nextY >= N || map[nextY][nextX] == 'H') {
                ans = Math.max(ans, nextcnt);
                continue;
            }

            // 루프 발견
            if (visited[nextY][nextX]) {
                ans = Integer.MAX_VALUE;
                return;
            }
            
            // 같은칸에 도달했을대 움직인 횟수가 적을시 pass
            if (DP[nextY][nextX] >= nextcnt) {
                continue;
            }
            
            DP[nextY][nextX] = nextcnt;

            // 백트래킹
            visited[nextY][nextX] = true;
            DFS(nextX, nextY, nextcnt);
            visited[nextY][nextX] = false;
        }
    }
}