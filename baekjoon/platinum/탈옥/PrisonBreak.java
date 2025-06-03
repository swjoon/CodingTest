package baekjoon.platinum.탈옥;

import java.util.*;
import java.io.*;

public class PrisonBreak {
    private static final int[] DIRX = { 0, 0, -1, 1 };
    private static final int[] DIRY = { -1, 1, 0, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());

        StringTokenizer st;

        StringBuilder sb = new StringBuilder();

        for (int test = 0; test < testCase; test++) {
            st = new StringTokenizer(br.readLine());

            int Y = Integer.parseInt(st.nextToken());
            int X = Integer.parseInt(st.nextToken());

            char[][] map = new char[Y][X];

            for (int y = 0; y < Y; y++) {
                map[y] = br.readLine().toCharArray();
            }

            sb.append(prisonBreak(X, Y, map)).append("\n");
        }

        System.out.println(sb.toString());

        br.close();
    }

    private static int prisonBreak(int X, int Y, char[][] oldMap) {
        char[][] newMap = makeMap(X + 2, Y + 2, oldMap);
        Point[] starPoints = findStartPoints(X + 2, Y + 2, newMap);

        int[][][] bfsMap = new int[Y + 2][X + 2][3];

        for (int y = 0; y < Y + 2; y++) {
            for (int x = 0; x < X + 2; x++) {
                Arrays.fill(bfsMap[y][x], Integer.MAX_VALUE);
            }
        }

        for (int i = 0; i < 3; i++) {
            bfs(starPoints[i], i, newMap, bfsMap, X + 2, Y + 2);
        }

        return findMinUnLock(newMap, bfsMap, X + 2, Y + 2);
    }

    private static void bfs(Point startPoint, int type, char[][] map, int[][][] bfsMap, int X, int Y) {
        Deque<Point> queue = new ArrayDeque<>();

        bfsMap[startPoint.y][startPoint.x][type] = 0;

        queue.add(startPoint);

        while (!queue.isEmpty()) {
            Point now = queue.pollFirst();

            for (int i = 0; i < 4; i++) {
                int nextX = now.x + DIRX[i];
                int nextY = now.y + DIRY[i];

                if (nextX < 0 || nextY < 0 || nextX >= X || nextY >= Y || map[nextY][nextX] == '*') {
                    continue;
                }

                int nextUnLock = now.unLock + (map[nextY][nextX] == '#' ? 1 : 0);

                if (bfsMap[nextY][nextX][type] <= nextUnLock) {
                    continue;
                }

                bfsMap[nextY][nextX][type] = nextUnLock;

                if (map[nextY][nextX] == '#') {
                    queue.addLast(new Point(nextX, nextY, nextUnLock));
                } else {
                    queue.addFirst(new Point(nextX, nextY, nextUnLock));
                }
            }
        }
    }

    private static char[][] makeMap(int X, int Y, char[][] oldMap) {
        char[][] newMap = new char[Y][X];

        for (int y = 0; y < Y; y++) {
            Arrays.fill(newMap[y], '.');
        }

        for (int y = 0; y < Y - 2; y++) {
            for (int x = 0; x < X - 2; x++) {
                newMap[y + 1][x + 1] = oldMap[y][x];
            }
        }

        return newMap;
    }

    private static Point[] findStartPoints(int X, int Y, char[][] map) {
        Point[] startP = new Point[3];

        startP[0] = new Point(0, 0, 0);

        int type = 1;

        for (int y = 0; y < Y; y++) {
            for (int x = 0; x < X; x++) {
                if (map[y][x] == '$') {
                    startP[type] = new Point(x, y, 0);
                    type++;
                }
            }
        }

        return startP;
    }

    private static int findMinUnLock(char[][] map, int[][][] bfsMap, int X, int Y) {
        int unLockCnt = Integer.MAX_VALUE;

        for (int y = 0; y < Y; y++) {
            for (int x = 0; x < X; x++) {
                if (map[y][x] == '*') {
                    continue;
                }

                unLockCnt = Math.min(unLockCnt,
                        bfsMap[y][x][0] + bfsMap[y][x][1] + bfsMap[y][x][2] - (map[y][x] == '#' ? 2 : 0));
            }
        }

        return unLockCnt;
    }
}

class Point {
    int x, y, unLock;

    public Point(int x, int y, int unLock) {
        this.x = x;
        this.y = y;
        this.unLock = unLock;
    }
}