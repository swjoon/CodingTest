package baekjoon.platinum.섬;

import java.util.*;
import java.io.*;

public class Island {
    private static final int[] DIR_X = { 0, 1, 1, 1, 0, -1, -1, -1 };
    private static final int[] DIR_Y = { -1, -1, 0, 1, 1, 1, 0, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        char[][] islandMap = new char[h + 2][w + 2];

        int[][] islandH = new int[h + 2][w + 2];

        boolean[][] waterVisited = new boolean[h + 2][w + 2];

        for (int i = 0; i < h + 2; i++) {
            Arrays.fill(islandMap[i], '.');
            Arrays.fill(islandH[i], -1);
        }

        for (int y = 1; y <= h; y++) {
            char[] s = br.readLine().toCharArray();
            for (int x = 1; x <= w; x++) {
                islandMap[y][x] = s[x - 1];
            }
        }

        Map<Integer, List<Integer>> info = new HashMap<>();

        info.put(0, new ArrayList<>());

        getIslandH(islandMap, islandH, waterVisited, info);

        int maxH = getMaxH(islandH);

        System.out.println(info.size() == 1 ? -1 : getAnswer(maxH + 1, info));

        br.close();
    }

    private static void getIslandH(char[][] map, int[][] islandH, boolean[][] waterVisited,
            Map<Integer, List<Integer>> info) {
        Deque<Point> waterQ = new ArrayDeque<>();
        Deque<Point> innerQ = new ArrayDeque<>();

        int w = map[0].length;
        int h = map.length;

        waterVisited[0][0] = true;

        waterQ.add(new Point(0, 0, 0));

        int height = 0;

        int islandNumber = 1;

        while (!waterQ.isEmpty()) {

            while (!waterQ.isEmpty()) {
                innerQ.add(waterQ.poll());
            }

            while (!innerQ.isEmpty()) {
                Point now = innerQ.poll();

                for (int i = 0; i < 8; i += 2) {
                    int nextX = now.x + DIR_X[i];
                    int nextY = now.y + DIR_Y[i];

                    if (nextX < 0 || nextY < 0 || nextX >= w || nextY >= h || waterVisited[nextY][nextX]) {
                        continue;
                    }

                    if (map[nextY][nextX] == 'x') {
                        info.get(now.parent).add(islandNumber);
                        info.putIfAbsent(islandNumber, new ArrayList<>());
                        checkIsland(new Point(nextX, nextY, now.parent), height, islandNumber++, map, islandH,
                                waterVisited, waterQ);
                        continue;
                    }

                    waterVisited[nextY][nextX] = true;

                    innerQ.add(new Point(nextX, nextY, now.parent));
                }
            }
            height++;
        }
    }

    private static void checkIsland(Point p, int height, int child, char[][] map, int[][] islandH,
            boolean[][] waterVisited, Deque<Point> waterQ) {
        Deque<Point> stack = new ArrayDeque<>();

        int w = map[0].length;
        int h = map.length;

        waterVisited[p.y][p.x] = true;
        map[p.y][p.x] = '.';
        islandH[p.y][p.x] = height;

        stack.push(p);

        while (!stack.isEmpty()) {
            Point now = stack.pop();

            for (int i = 0; i < 8; i++) {
                int nextX = now.x + DIR_X[i];
                int nextY = now.y + DIR_Y[i];

                if (nextX < 0 || nextY < 0 || nextX >= w || nextY >= h || map[nextY][nextX] == '.') {
                    continue;
                }

                waterVisited[nextY][nextX] = true;

                map[nextY][nextX] = '.';

                islandH[nextY][nextX] = height;

                waterQ.add(new Point(nextX, nextY, child));
                stack.push(new Point(nextX, nextY, now.parent));
            }
        }
    }

    private static int getMaxH(int[][] islandH) {
        int max = 0;

        for (int[] height : islandH) {
            for (int h : height) {
                max = Math.max(max, h);
            }
        }

        return max;
    }

    private static String getAnswer(int maxH, Map<Integer, List<Integer>> info) {
        int islandCount = info.size();

        int[] heightCount = new int[maxH];

        int[] hInfo = new int[islandCount];

        boolean[] visited = new boolean[islandCount];

        for (int island : info.get(0)) {
            dfs(island, info, visited, hInfo, heightCount);
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < heightCount.length; i++) {
            sb.append(heightCount[i]).append(" ");
        }

        return sb.toString();
    }

    private static int dfs(int now, Map<Integer, List<Integer>> info, boolean[] visited, int[] hInfo,
            int[] heightCount) {

        int maxH = 0;

        for (int next : info.get(now)) {
            if (visited[next]) {
                maxH = Math.max(maxH, hInfo[next]);
                continue;
            }

            visited[next] = true;

            maxH = Math.max(maxH, dfs(next, info, visited, hInfo, heightCount));
        }

        heightCount[maxH]++;

        return maxH + 1;
    }
}

class Point {
    int x, y, parent;

    public Point(int x, int y, int parent) {
        this.x = x;
        this.y = y;
        this.parent = parent;
    }
}