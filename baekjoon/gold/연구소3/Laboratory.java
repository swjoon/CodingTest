package baekjoon.gold.연구소3;

import java.util.*;
import java.io.*;

public class Laboratory {
    static int N, M;
    static int uninfect = 0;
    static int ans = Integer.MAX_VALUE;
    static char[][] map;
    static int[] dirX = { 0, 0, -1, 1 };
    static int[] dirY = { -1, 1, 0, 0 };
    static List<int[]> list = new ArrayList<>();

    static class Point {
        int x, y;
        int cnt;

        Point(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][N];

        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                int num = Integer.parseInt(st.nextToken());
                switch (num) {
                    case 0:
                        map[y][x] = '.';
                        ++uninfect;
                        break;
                    case 1:
                        map[y][x] = '-';
                        break;
                    default:
                        list.add(new int[] { x, y });
                        map[y][x] = '*';
                        break;
                }
            }
        }

        if (uninfect == 0) {
            System.out.println(0);
            return;
        }

        List<List<int[]>> trylist = comb();

        for (List<int[]> tryL : trylist) {
            BFS(tryL);
        }

        if (ans == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }

        System.out.println(ans);
    }

    static void BFS(List<int[]> tryL) {
        boolean[][] visited = new boolean[N][N];
        Queue<Point> q = new LinkedList<>();

        int infect = 0;

        for (int[] i : tryL) {
            q.add(new Point(i[0], i[1], 0));
            visited[i[1]][i[0]] = true;
        }

        while (!q.isEmpty()) {
            Point p = q.poll();

            for (int i = 0; i < 4; i++) {
                int nextx = p.x + dirX[i];
                int nexty = p.y + dirY[i];

                if (nextx < 0 || nexty < 0 || nextx >= N || nexty >= N || map[nexty][nextx] == '-'
                        || visited[nexty][nextx]) {
                    continue;
                }

                visited[nexty][nextx] = true;

                q.add(new Point(nextx, nexty, p.cnt + 1));

                if (map[nexty][nextx] == '.') {
                    ++infect;
                }

                if (uninfect == infect) {
                    ans = Math.min(ans, p.cnt + 1);
                    return;
                }
            }
        }
    }

    static void combination(int start, int depth, List<List<int[]>> L, List<int[]> currentCombination) {
        if (depth == M) {
            L.add(new ArrayList<>(currentCombination));
            return;
        }

        for (int i = start; i < list.size(); i++) {
            currentCombination.add(list.get(i));
            combination(i + 1, depth + 1, L, currentCombination);
            currentCombination.remove(currentCombination.size() - 1);
        }
    }

    static List<List<int[]>> comb() {
        List<List<int[]>> L = new ArrayList<>();
        combination(0, 0, L, new ArrayList<>());
        return L;
    }
}
