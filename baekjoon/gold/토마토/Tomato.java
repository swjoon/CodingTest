package baekjoon.gold.토마토;

import java.io.*;
import java.util.*;

public class Tomato {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());

        int[][] map = new int[Y][X];

        Queue<Point> queue = new ArrayDeque<>();

        int freshTomato = 0;

        // Map 생성
        for (int y = 0; y < Y; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < X; x++) {
                int i = Integer.parseInt(st.nextToken());
                map[y][x] = i;
                if (i == 1){
                    queue.add(new Point(x, y, 0));
                }else if(i == 0){
                    freshTomato++;
                }
            }
        }

        if(freshTomato == 0){
            System.out.println(0);
            return;
        }

        System.out.println(BFS(X, Y, freshTomato, queue, map));
    }

    private static int BFS(int X, int Y, int freshTomato, Queue<Point> queue, int[][] map) {
        int[] dirX = new int[] { 1, -1, 0, 0 };
        int[] dirY = new int[] { 0, 0, -1, 1 };

        int days = 0;

        int count = 0;

        while (!queue.isEmpty()) {
            Point now = queue.poll();

            for(int dir = 0; dir < 4; dir++){
                int nextX = now.x + dirX[dir];
                int nextY = now.y + dirY[dir];

                if(nextX < 0 || nextY < 0 || nextX >= X || nextY >= Y || map[nextY][nextX] == -1 || map[nextY][nextX] == 1){
                    days = Math.max(days, now.cnt);
                    continue;
                }

                count ++;

                map[nextY][nextX] = 1;

                queue.add(new Point(nextX, nextY, now.cnt + 1));
            }
        }

        return count == freshTomato? days : -1;
    }

    static class Point {
        int x;
        int y;
        int cnt;

        public Point(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}
