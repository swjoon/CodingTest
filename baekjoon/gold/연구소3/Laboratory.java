package baekjoon.gold.연구소3;

import java.util.*;
import java.io.*;

public class Laboratory {
    static int N, M;
    static char[][] map;
    static boolean[][] visited;
    static int[] dirX = { 0, 0, -1, 1 };
    static int[] dirY = { -1, 1, 0, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][N];
        visited = new boolean[N][N];

        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                int num = Integer.parseInt(st.nextToken());
                switch (num) {
                    case 0:
                        map[y][x] = '.';
                        break;
                    case 1:
                        map[y][x] = '-';
                        break;
                    default:
                        map[y][x] = '*';
                        break;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
    }

    static void BFS() {

    }

    static boolean check() {
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (map[y][x] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
