package baekjoon.gold.내선물을받아줘;

import java.util.*;
import java.io.*;

public class Gift {
    static int ans;
    static int N, M;
    static char[][] map;
    static int[][] visited;

    static int[] dirX = { 1, -1, 0, 0 };
    static int[] dirY = { 0, 0, 1, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        visited = new int[N][M];

        for (int y = 0; y < N; y++) {
            map[y] = br.readLine().toCharArray();
        }

        ans = 0;

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if (visited[y][x] == 0) {
                    DFS(x, y);
                }
            }
        }

        System.out.println(ans);

    }

    static void DFS(int x, int y) {
        int type = type(map[y][x]);
        int nextX = x + dirX[type];
        int nextY = y + dirY[type];
        
        visited[y][x] = 1;

        if (visited[nextY][nextX] == 0) {
            DFS(nextX, nextY);
        } else if (visited[nextY][nextX] == 1) {// 새로운 루프 발견
            ans++;
        }
        
        // 탐색완료
        visited[y][x] = 2;
    }

    // 동서남북 타입 변환
    static int type(char dir) {
        switch (dir) {
            case 'E':
                return 0;
            case 'W':
                return 1;
            case 'S':
                return 2;
            default:
                return 3;
        }
    }
}
