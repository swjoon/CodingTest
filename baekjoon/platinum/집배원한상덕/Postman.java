package baekjoon.platinum.집배원한상덕;

import java.util.*;
import java.io.*;

public class Postman {
    private static final int[] DIRX = { 0, 1, 1, 1, 0, -1, -1, -1 };
    private static final int[] DIRY = { -1, -1, 0, 1, 1, 1, 0, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        char[][] map = new char[N][N];
        int[][] hMap = new int[N][N];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        Set<Integer> set = new HashSet<>();

        int visitCount = 0;

        int maxH = 0;

        Point startPoint = new Point(0, 0);

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                hMap[i][j] = Integer.parseInt(st.nextToken());
                set.add(hMap[i][j]);

                if (map[i][j] == '.') {
                    continue;
                }

                if (map[i][j] == 'P') {
                    startPoint.x = j;
                    startPoint.y = i;
                } else {
                    visitCount++;
                }

                maxH = Math.max(maxH, hMap[i][j]);
            }
        }

        int[] sortedH = set.stream().mapToInt(Integer::intValue).toArray();

        Arrays.sort(sortedH);

        int answer = twoPointer(startPoint, visitCount, sortedH, maxH, N, map, hMap);

        System.out.println(answer);

        br.close();
    }

    private static int twoPointer(Point startPoint, int visitCount, int[] sortedH, int maxH, int size,
            char[][] map,
            int[][] hMap) {
        int l = 0;
        int r = 0;

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < sortedH.length; i++) {
            if (sortedH[i] == maxH) {
                r = i;
                break;
            }
        }

        int hSize = sortedH.length;

        while (r < hSize && l < hSize) {
            if (bfs(startPoint, visitCount, sortedH[l], sortedH[r], size, map, hMap)) {
                ans = Math.min(ans, sortedH[r] - sortedH[l]);
                l++;
            } else {
                r++;
            }
        }

        return ans;
    }

    private static boolean bfs(Point startPoint, int visitCount, int minH, int maxH, int size, char[][] map,
            int[][] hMap) {
        ArrayDeque<Point> queue = new ArrayDeque<>();

        boolean[][] visited = new boolean[size][size];

        queue.add(startPoint);

        int count = 0;

        if (hMap[startPoint.y][startPoint.x] < minH) {
            return false;
        }

        visited[startPoint.y][startPoint.x] = true;

        while (!queue.isEmpty()) {
            Point now = queue.poll();

            for (int i = 0; i < 8; i++) {
                int nextX = now.x + DIRX[i];
                int nextY = now.y + DIRY[i];

                if (nextX < 0 || nextY < 0 || nextX >= size || nextY >= size || visited[nextY][nextX]) {
                    continue;
                }

                if (hMap[nextY][nextX] < minH || maxH < hMap[nextY][nextX]) {
                    continue;
                }

                visited[nextY][nextX] = true;

                if (map[nextY][nextX] == 'K') {
                    count++;
                }

                if (count == visitCount) {
                    return true;
                }

                queue.add(new Point(nextX, nextY));
            }
        }

        return false;
    }
}

class Point {
    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}