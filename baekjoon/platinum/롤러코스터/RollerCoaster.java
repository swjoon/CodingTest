package baekjoon.platinum.롤러코스터;

import java.util.*;
import java.io.*;

public class RollerCoaster {
    static int R, C;
    static int[][] map;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // BFS(map[0][0]);

        System.out.println();
    }

    static String BFS(int start) {
        Queue<Point> queue = new LinkedList<>();

        return "    ";
    }
}

class Point {
    int x, y;
    String route;

    Point(int x, int y, String route) {
        this.x = x;
        this.y = y;
        this.route = route;
    }

}

// 1. R or C 가 홀수인 경우 전부 돌 수 있음.
// 2. R and C 가 짝수인 경우 행과 열의 합이 홀수인 부분을 지정해서 피한후 나머지를 전부 돌 수 있음.