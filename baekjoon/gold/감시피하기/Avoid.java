package baekjoon.gold.감시피하기;

import java.util.*;
import java.io.*;

public class Avoid {
    static int N;
    static int count = 0;
    static String[][] map;
    static int[][] visited;
    static int[] X = { 0, 0, -1, 1 };
    static int[] Y = { 1, -1, 0, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<int[]> list = new ArrayList<>();
        N = Integer.parseInt(br.readLine());

        map = new String[N][N];
        visited = new int[N][N];

        for (int y = 0; y < N; y++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String before = "X";
            for (int x = 0; x < N; x++) {
                map[y][x] = st.nextToken();
                if (map[y][x].equals("T")) {
                    list.add(new int[] { x, y });
                }
                if (check(before, map[y][x])) {
                    System.out.println("NO");
                    return;
                }
                if (y > 0) {
                    if (check(map[y - 1][x], map[y][x])) {
                        System.out.println("NO");
                        return;
                    }
                }
                before = map[y][x];
            }
        }
        // 선생 마다 경로 체크
        for (int[] xy : list) {
            for (int i = 0; i < 4; i++) {
                BFS(xy[0], xy[1], i);
            }
        }
        // 공통 부분 == 장애물 하나로 해결 가능하므로 count - 1
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (visited[y][x] > 1) {
                    --count;
                }
            }
        }

        System.out.println(count > 3? "NO":"YES");
    }

    static void BFS(int x, int y, int dir) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(x, y, dir, new ArrayList<>()));

        while (!queue.isEmpty()) {
            Point point = queue.poll();
            List<int[]> newRoute = new ArrayList<>(point.route);

            int nextX = point.x + X[point.dir];
            int nextY = point.y + Y[point.dir];

            if (nextY < 0 || nextY >= N || nextX >= N || nextX < 0)
                return;
            if (map[nextY][nextX].equals("S")) {
                for (int[] xy : newRoute) {
                    visited[xy[1]][xy[0]]++;
                }
                count++;
                return;
            }
            if (map[nextY][nextX].equals("T")) {
                return;
            }

            newRoute.add(new int[] { nextX, nextY });

            queue.add(new Point(nextX, nextY, dir, newRoute));

        }
    }

    static boolean check(String before, String now) {
        if ((before.equals("S") && now.equals("T")) || (before.equals("T") && now.equals("S"))) {
            return true;
        }
        return false;
    }
}

class Point {

    int x;
    int y;
    int dir;
    List<int[]> route;

    Point(int x, int y, int dir, List<int[]> route) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.route = route;
    }

}
