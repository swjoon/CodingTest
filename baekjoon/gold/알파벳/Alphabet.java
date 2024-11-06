package baekjoon.gold.알파벳;

import java.util.*;
import java.io.*;

public class Alphabet {
    static int max;
    static int X, Y;
    static String[][] map;
    static int[] dirX = { 0, 0, 1, -1 };
    static int[] dirY = { 1, -1, 0, 0 };
    static Stack<String> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Y = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        map = new String[Y][X];

        for (int y = 0; y < Y; y++) {
            map[y] = br.readLine().split("");
        }

        stack.push(map[0][0]);

        DFS(0, 0, 1);

        System.out.println(max);
    }

    static void DFS(int x, int y, int dep) {

        for (int dir = 0; dir < 4; dir++) {
            int nextX = x + dirX[dir];
            int nextY = y + dirY[dir];

            if (nextX < 0 || nextY < 0 || nextX >= X || nextY >= Y || stack.contains(map[nextY][nextX])) {
                max = Math.max(max, dep);
                continue;
            }

            stack.push(map[nextY][nextX]);

            DFS(nextX, nextY, dep + 1);

            stack.pop();
        }
    }
}
