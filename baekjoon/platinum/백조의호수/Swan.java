package baekjoon.platinum.백조의호수;

import java.io.*;
import java.util.*;

// 문제 풀이 순서
// ICE 지역 각 칸의 두께 측정
// 오리간 ICE를 거치는 경로 중 ICE의 두께가 최소인 경로 찾기

public class Swan {
    static int X, Y;
    static char[][] map;
    static int[][] iceMap;
    static boolean[][] mapVisited;
    static int[] dirX = { 0, 0, 1, -1 };
    static int[] dirY = { 1, -1, 0, 0 };

    static class Point implements Comparable<Point> {
        int x, y, iceCnt;

        public Point(int x, int y, int iceCnt) {
            this.x = x;
            this.y = y;
            this.iceCnt = iceCnt;
        }

        @Override
        public int compareTo(Point o) {
            return Integer.compare(this.iceCnt, o.iceCnt);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Y = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        map = new char[Y][X];
        iceMap = new int[Y][X];
        mapVisited = new boolean[Y][X];

        for (int y = 0; y < Y; y++) {
            map[y] = br.readLine().toCharArray();
        }

        Queue<Point> ice = new LinkedList<>();
        Point startPoint = new Point(0, 0, 0);

        for (int y = 0; y < Y; y++) {
            for (int x = 0; x < X; x++) {
                if (map[y][x] == 'L') {
                    startPoint.x = x;
                    startPoint.y = y;
                }
                if ((map[y][x] == '.' || map[y][x] == 'L') && !mapVisited[y][x]) {
                    mapVisited[y][x] = true;
                    ice.addAll(findIce(new Point(x, y, 0)));
                }
            }
        }

        makeIceMap(ice);

        System.out.println(findRoute(startPoint));
    }

    // 얼음 경계선 찾기
    public static Queue<Point> findIce(Point input) {
        Queue<Point> water = new LinkedList<>();
        Queue<Point> ice = new LinkedList<>();
        water.add(input);

        while (!water.isEmpty()) {
            Point now = water.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dirX[i];
                int nextY = now.y + dirY[i];

                if (nextX >= 0 && nextY >= 0 && nextX < X && nextY < Y && !mapVisited[nextY][nextX]) {
                    mapVisited[nextY][nextX] = true;
                    if (map[nextY][nextX] == 'X') {
                        iceMap[nextY][nextX] = 1;
                        ice.add(new Point(nextX, nextY, 1));
                        continue;
                    }
                    water.add(new Point(nextX, nextY, 0));
                }
            }
        }
        return ice;
    }

    // 얼음섬 두께 적용 맵 생성
    public static void makeIceMap(Queue<Point> ice) {
        while (!ice.isEmpty()) {
            Point now = ice.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dirX[i];
                int nextY = now.y + dirY[i];
                int nextCnt = now.iceCnt + 1;

                if (nextX >= 0 && nextY >= 0 && nextX < X && nextY < Y && !mapVisited[nextY][nextX]
                        && map[nextY][nextX] == 'X') {
                    mapVisited[nextY][nextX] = true;
                    iceMap[nextY][nextX] = nextCnt;
                    ice.add(new Point(nextX, nextY, nextCnt));
                }
            }
        }
    }

    // 길찾기
    public static int findRoute(Point startPoint) {
        PriorityQueue<Point> point = new PriorityQueue<>();
        boolean[][] visited = new boolean[Y][X];
        int[][] route = new int[Y][X];
        int day = Integer.MAX_VALUE;

        for (int i = 0; i < Y; i++) {
            Arrays.fill(route[i], Integer.MAX_VALUE);
        }

        route[startPoint.y][startPoint.x] = 0;

        point.add(startPoint);

        while (!point.isEmpty()) {
            Point now = point.poll();

            if (visited[now.y][now.x]) {
                continue;
            }

            visited[now.y][now.x] = true;

            if (map[now.y][now.x] == 'L' && (now.x != startPoint.x || now.y != startPoint.y)) {
                day = now.iceCnt;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dirX[i];
                int nextY = now.y + dirY[i];

                if (nextX < 0 || nextY < 0 || nextX >= X || nextY >= Y) {
                    continue;
                }

                int nextIceCnt = Math.max(now.iceCnt, iceMap[nextY][nextX]);

                if (nextIceCnt < route[nextY][nextX]) {
                    route[nextY][nextX] = nextIceCnt;
                    point.add(new Point(nextX, nextY, nextIceCnt));
                }
            }

        }
        return day;
    }
}
