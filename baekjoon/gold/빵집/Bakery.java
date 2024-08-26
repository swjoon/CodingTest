package baekjoon.gold.빵집;

import java.util.*;
import java.io.*;

public class Bakery {
    static int H, W;
    static int ans;
    static char[][] map;
    static boolean[][] visited;
    // 상 중 하 로 진행
    static int[] dirY = { -1, 0, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        map = new char[H][W];
        visited = new boolean[H][W];

        for (int i = 0; i < H; i++) {
            map[i] = br.readLine().toCharArray();
        }

        ans = 0;
        
        // 각 지점마다 체크하고 목표지점 도달시 횟수 1회 증가
        for (int i = 0; i < H; i++) {
            if (DFS(0, i)) {
                ans++;
            }
        }

        System.out.println(ans);

    }

    static boolean DFS(int x, int y) {
        // 방문처리
        visited[y][x] = true;

        if (x == W - 1) {
            return true;
        }

        // 오른쪽 대각선 포함 진행
        for (int i = 0; i < 3; i++) {
            int nextx = x + 1;
            int nexty = y + dirY[i];
            if (nexty < 0 || nexty >= H || visited[nexty][nextx] || map[nexty][nextx] == 'x') {
                continue;
            }
            // 목표지점 도착시 탐색 종료
            if (DFS(nextx, nexty)) {
                return true;
            }
        }

        return false;
    }
}
