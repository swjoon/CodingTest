package baekjoon.gold.소문난칠공주;

import java.io.*;
import java.util.*;

public class SevenPrincess {
    static int ans = 0;
    static int[] dirX = { 0, 0, 1, -1 };
    static int[] dirY = { 1, -1, 0, 0 };
    static char[][] map = new char[5][5];
    static List<int[]> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int y = 0; y < 5; y++) {
            map[y] = br.readLine().toCharArray();
        }

        Comb(0, 0);

        System.out.println(ans);
    }

    static void Comb(int number, int def) {

        if (def == 7) {
            if (find()) {
                ans++;
            }
            return;
        }

        for (int i = number; i < 25; i++) {
            list.add(new int[] { i % 5, i / 5 });
            Comb(i + 1, def + 1);
            list.remove(list.size() - 1);
        }
    }

    static boolean find() {
        Queue<int[]> q = new LinkedList<>();
        boolean[] visited = new boolean[25];
        int cnt = 1;
        int cntS = 0;

        int startX = list.get(0)[0];
        int startY = list.get(0)[1];

        visited[startY * 5 + startX] = true;

        q.add(new int[] { startX, startY });

        while (!q.isEmpty()) {
            int[] point = q.poll();

            if (map[point[1]][point[0]] == 'S') {
                cntS++;
            }

            for (int i = 0; i < 4; i++) {
                int nextX = point[0] + dirX[i];
                int nextY = point[1] + dirY[i];

                if (nextX >= 0 && nextY >= 0 && nextX < 5 && nextY < 5 && !visited[nextY * 5 + nextX]
                        && list.stream().anyMatch(arr -> arr[0] == nextX && arr[1] == nextY)) {
                    visited[nextY * 5 + nextX] = true;
                    q.add(new int[] { nextX, nextY });
                    cnt++;
                }
            }
        }
        
        return cnt == 7 && cntS >= 4;
    }
}
