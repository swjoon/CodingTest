package baekjoon.gold.NQueen;

import java.io.*;

public class Queen {
    static int N;
    static int ans = 0;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        DFS(0, 1);

        System.out.println(ans);
    }

    static void DFS(int y, int cnt) {

        if (cnt > N) {
            ans++;
            return;
        }

        if (y >= N) {
            return;
        }

        for (int x = 0; x < N; x++) {
            if (map[y][x] == 0) {
                changeBoard(x, y, 0, cnt);
                DFS(y + 1, cnt + 1);
                changeBoard(x, y, cnt, 0);
            }
        }

    }

    static void changeBoard(int x, int y, int state, int change) {
        for (int i = 0; i < N; i++) {
            if (map[y][i] == state) {
                map[y][i] = change;
            }
            if (map[i][x] == state) {
                map[i][x] = change;
            }
        }
        for (int i = 0; i < N; i++) {
            if (x + i >= N || y + i >= N) {
                break;
            }
            if (map[y + i][x + i] == state) {
                map[y + i][x + i] = change;
            }
        }
        for (int i = 0; i > -N; i--) {
            if (x + i < 0 || y + i < 0) {
                break;
            }
            if (map[y + i][x + i] == state) {
                map[y + i][x + i] = change;
            }
        }
        for (int i = 0; i < N; i++) {
            if (x - i < 0 || y + i >= N) {
                break;
            }
            if (map[y + i][x - i] == state) {
                map[y + i][x - i] = change;
            }
        }
        for (int i = 0; i > -N; i--) {
            if (x - i >= N || y + i < 0) {
                break;
            }
            if (map[y + i][x - i] == state) {
                map[y + i][x - i] = change;
            }
        }
    }
}
