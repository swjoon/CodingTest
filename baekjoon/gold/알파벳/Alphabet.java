package baekjoon.gold.알파벳;

import java.util.*;
import java.io.*;

public class Alphabet {
    static int max;
    static int X, Y;
    static boolean found;
    static String[][] map;
    static int[] dirX = { 0, 0, 1, -1 };
    static int[] dirY = { 1, -1, 0, 0 };
    static Map<String, Integer> check = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Y = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        map = new String[Y][X];

        for (int y = 0; y < Y; y++) {
            map[y] = br.readLine().split("");
            for (int x = 0; x < X; x++) {
                if (!check.containsKey(map[y][x])) {
                    check.put(map[y][x], 0);
                }
            }
        }

        check.replace(map[0][0], 1);

        DFS(0, 0, 1);

        System.out.println(max);
    }

    static void DFS(int x, int y, int dep) {

        max = Math.max(max, dep);

        if (max == check.size() || found) {
            found = true;
            return;
        }

        for (int dir = 0; dir < 4; dir++) {
            int nextX = x + dirX[dir];
            int nextY = y + dirY[dir];

            if (found) {
                return;
            }

            if (nextX >= 0 && nextY >= 0 && nextX < X && nextY < Y && check.get(map[nextY][nextX]) == 0) {
                check.replace(map[nextY][nextX], 1);
                DFS(nextX, nextY, dep + 1);
                check.replace(map[nextY][nextX], 0);
            }
        }
    }
}
