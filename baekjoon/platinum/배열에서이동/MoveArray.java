package baekjoon.platinum.배열에서이동;

import java.util.*;
import java.io.*;

public class MoveArray {
    private static final int[] DIR_X = { 0, 0, 1, -1 };
    private static final int[] DIR_Y = { 1, -1, 0, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int size = Integer.parseInt(br.readLine());

        int[][] map = new int[size][size];

        StringTokenizer st;

        int max = 0;

        Set<Integer> set = new HashSet<>();

        for (int y = 0; y < size; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < size; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
                max = Math.max(max, map[y][x]);
                set.add(map[y][x]);
            }
        }

        int[] hList = new int[set.size()];

        int i = 0;

        for (int s : set) {
            hList[i++] = s;
        }

        Arrays.sort(hList);

        int minDist = findMinDist(hList, max, size, map);

        System.out.println(minDist);

        br.close();

    }

    private static int findMinDist(int[] hList, int max, int size, int[][] map) {

        int dist = max;

        for (int i = 0; i < hList.length; i++) {
            dist = binarySearch(hList[i], dist, size, map);
        }

        return dist;
    }

    private static int binarySearch(int left, int dist, int size, int[][] map) {

        int l = left;
        int r = left + dist;

        int mid = 0;

        int minDist = -1;

        while (l < r) {

            mid = (l + r) / 2;

            if (bfs(left, mid, size, map)) {
                r = mid;
                minDist = mid - left;
            } else {
                l = mid;
                l++;
            }
        }

        return minDist == -1 ? dist : minDist;
    }

    private static boolean bfs(int min, int max, int size, int[][] map) {
        Deque<Point> queue = new ArrayDeque<>();

        boolean[][] visited = new boolean[size][size];

        if (map[0][0] < min || map[0][0] > max) {
            return false;
        }

        queue.add(new Point(0, 0));

        visited[0][0] = true;

        while (!queue.isEmpty()) {
            Point now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nX = now.x + DIR_X[i];
                int nY = now.y + DIR_Y[i];

                if (nX < 0 || nY < 0 || nX >= size || nY >= size || visited[nY][nX]) {
                    continue;
                }

                if (map[nY][nX] < min || map[nY][nX] > max) {
                    continue;
                }

                visited[nY][nX] = true;

                if (nX == size - 1 && nY == size - 1) {
                    return true;
                }

                queue.add(new Point(nX, nY));
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