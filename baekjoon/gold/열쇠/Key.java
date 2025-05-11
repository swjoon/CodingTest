package baekjoon.gold.열쇠;

import java.util.*;
import java.io.*;

public class Key {
    private static final int[] dirX = { 0, 0, 1, -1 };
    private static final int[] dirY = { -1, 1, 0, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        for (int testCase = 0; testCase < N; testCase++) {
            testCase(br);
        }

        br.close();
    }

    private static void testCase(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        char[][] map = new char[H][W];

        for (int h = 0; h < H; h++) {
            map[h] = br.readLine().toCharArray();
        }

        char[] keys = br.readLine().toCharArray();

        Set<Character> keySet = new HashSet<>();

        for (char key : keys) {
            keySet.add(key);
        }

        System.out.println(stolenDocs(keySet, H, W, map));
    }

    private static int stolenDocs(Set<Character> keySet, int H, int W, char[][] map) {
        Map<Character, Deque<Point>> stopPoints = new HashMap<>();
        Set<Character> stopDoors = new HashSet<>();
        Deque<Point> points = new ArrayDeque<>();

        boolean[][] visited = new boolean[H][W];

        int docCount = 0;

        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                if ((x == 0 || x == W - 1 || y == 0 || y == H - 1) && map[y][x] != '*') {
                    visited[y][x] = true;
                    char startpoint = map[y][x];

                    if (startpoint == '.') {
                        points.add(new Point(x, y));
                    } else if (startpoint == '$') {
                        docCount++;
                        points.add(new Point(x, y));
                    } else if (Character.isLowerCase(startpoint)) {
                        keySet.add(startpoint);
                        points.add(new Point(x, y));
                    } else if (keySet.contains(Character.toLowerCase(startpoint))) {
                        points.add(new Point(x, y));
                    } else {
                        stopDoors.add(startpoint);
                        stopPoints.putIfAbsent(startpoint, new ArrayDeque<>());
                        stopPoints.get(startpoint).add(new Point(x, y));
                    }
                }
            }
        }

        while (!points.isEmpty()) {
            Point point = points.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = point.x + dirX[i];
                int nextY = point.y + dirY[i];

                if (nextX < 0 || nextX >= W || nextY < 0 || nextY >= H || visited[nextY][nextX]
                        || map[nextY][nextX] == '*') {
                    continue;
                }

                visited[nextY][nextX] = true;

                char next = map[nextY][nextX];

                if (next == '.') {
                    points.add(new Point(nextX, nextY));
                    continue;
                }

                if (next == '$') {
                    docCount++;
                    points.add(new Point(nextX, nextY));
                    continue;
                }

                if (Character.isLowerCase(next)) {
                    System.out.println("new Key : " + next);
                    keySet.add(next);
                    points.add(new Point(nextX, nextY));
                    continue;
                }

                char key = Character.toUpperCase(next);

                if (keySet.contains(key)) {
                    points.add(new Point(nextX, nextY));
                    continue;
                }

                stopDoors.add(next);
                stopPoints.putIfAbsent(next, new ArrayDeque<>());
                stopPoints.get(next).add(new Point(nextX, nextY));
            }

            if (points.isEmpty()) {
                findOpenDoor(points, stopDoors, stopPoints, keySet);
            }
        }

        return docCount;
    }

    private static void findOpenDoor(Deque<Point> points, Set<Character> stopDoors,
            Map<Character, Deque<Point>> stopPoints, Set<Character> keySet) {

        stopDoors.stream().forEach(door -> {
            if (keySet.contains(Character.toLowerCase(door))) {
                Deque<Point> stopPoint = stopPoints.get(door);
                while (!stopPoint.isEmpty()) {
                    points.add(stopPoint.poll());
                }
            }
        });
    }
}

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}