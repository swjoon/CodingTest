package baekjoon.gold.아기상어;

import java.util.*;
import java.io.*;

public class Shark {
    static int N;
    static int fish;
    static int[] start;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        fish = 0;
        start = new int[2];

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];

        for (int y = 0; y < N; y++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
                if (map[y][x] == 9) {
                    start[0] = x;
                    start[1] = y;
                } else if (map[y][x] != 0) {
                    fish++;
                }
            }
        }

        if (fish == 0) {
            System.out.println(0);
            return;
        }

        
    }

    static void BFS() {
        Queue<Point> q = new LinkedList<>();
    }
}

class Point {

    int x, y;
    int cnt;

    Point(int x, int y, int cnt) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }
}