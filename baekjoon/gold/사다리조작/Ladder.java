package baekjoon.gold.사다리조작;

import java.util.*;
import java.io.*;

public class Ladder {
    static int min = 4;
    static int N, M, H;
    static int[][] map;
    static List<int[]> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H + 1][N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a][b] = b + 1;
            map[a][b + 1] = b;
        }

        for (int y = 1; y <= H; y++) {
            for (int x = 1; x < N; x++) {
                if (map[y][x] == 0 && map[y][x + 1] == 0) {
                    list.add(new int[] { x, y });
                }
            }
        }

        DFS(0, 0);

        if (min == 4) {
            System.out.println(-1);
            return;
        }

        System.out.println(min);
    }

    // 사다리 빈공간 체크
    static void DFS(int now, int add) {

        if (find()) {
            min = Math.min(min, add);
            return;
        }

        if (add == 3) {
            return;
        }

        for (int i = now; i < list.size(); i++) {
            int[] point = list.get(i);
            if (map[point[1]][point[0]] == 0 && map[point[1]][point[0] + 1] == 0) {
                map[point[1]][point[0]] = point[0] + 1;
                map[point[1]][point[0] + 1] = point[0];
                DFS(i + 1, add + 1);
                map[point[1]][point[0]] = 0;
                map[point[1]][point[0] + 1] = 0;
            }
        }
    }

    // 조건에 일치하는지 체크
    static boolean find() {
        for (int line = 1; line <= N; line++) {
            int x = line;
            for (int y = 0; y <= H; y++) {
                if (map[y][x] != 0) {
                    x = map[y][x];
                }
            }
            if (line != x) {
                return false;
            }
        }
        return true;
    }
}