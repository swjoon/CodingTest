package baekjoon.platinum.버스갈아타기;

import java.io.*;
import java.util.*;

public class BusTransfer {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        int busC = Integer.parseInt(br.readLine());

        int[][] busInfo = new int[busC + 1][5];

        for (int i = 0; i < busC; i++) {
            st = new StringTokenizer(br.readLine());

            int busNum = Integer.parseInt(st.nextToken());

            busInfo[busNum][0] = busNum;

            for (int j = 1; j < 5; j++) {
                busInfo[busNum][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());

        int sX = Integer.parseInt(st.nextToken());
        int sY = Integer.parseInt(st.nextToken());
        int eX = Integer.parseInt(st.nextToken());
        int eY = Integer.parseInt(st.nextToken());

        List<Integer> startList = new ArrayList<>();
        List<Integer> endList = new ArrayList<>();

        boolean oneWay = false;

        for (int i = 1; i <= busC; i++) {
            int count = 0;

            if (meet(busInfo[i][1], busInfo[i][2], busInfo[i][3], busInfo[i][4], sX, sY)) {
                startList.add(busInfo[i][0]);
                count++;
            }

            if (meet(busInfo[i][1], busInfo[i][2], busInfo[i][3], busInfo[i][4], eX, eY)) {
                endList.add(busInfo[i][0]);
                count++;
            }

            if (count == 2) {
                oneWay = true;
            }
        }

        int ans = 1;

        if (!oneWay) {
            ans = bfs(startList, endList, busInfo, busC);
        }

        System.out.println(ans);

        br.close();
    }

    private static boolean checkCanTransfer(int ax, int ay, int bx, int by, int cx, int cy, int dx, int dy) {
        long ab_c = cross(ax, ay, bx, by, cx, cy);
        long ab_d = cross(ax, ay, bx, by, dx, dy);
        long cd_a = cross(cx, cy, dx, dy, ax, ay);
        long cd_b = cross(cx, cy, dx, dy, bx, by);

        if ((ab_c > 0 && ab_d < 0 || ab_c < 0 && ab_d > 0)
                && (cd_a > 0 && cd_b < 0 || cd_a < 0 && cd_b > 0)) {
            return true;
        }

        if (ab_c == 0 && meet(ax, ay, bx, by, cx, cy)) {
            return true;
        }

        if (ab_d == 0 && meet(ax, ay, bx, by, dx, dy)) {
            return true;
        }

        if (cd_a == 0 && meet(cx, cy, dx, dy, ax, ay)) {
            return true;
        }

        if (cd_b == 0 && meet(cx, cy, dx, dy, bx, by)) {
            return true;
        }

        return false;
    }

    private static int bfs(List<Integer> startList, List<Integer> endList, int[][] busInfo, int busC) {
        Deque<Point> queue = new ArrayDeque<>();

        boolean[] visited = new boolean[busC + 1];

        for (int n : startList) {
            queue.add(new Point(n, 1));
            visited[n] = true;
        }

        while (!queue.isEmpty()) {
            Point now = queue.poll();

            for (int i = 1; i <= busC; i++) {

                int nextBusNum = busInfo[i][0];

                if (!checkCanTransfer(
                        busInfo[now.busN][1], busInfo[now.busN][2], busInfo[now.busN][3], busInfo[now.busN][4],
                        busInfo[nextBusNum][1], busInfo[nextBusNum][2], busInfo[nextBusNum][3], busInfo[nextBusNum][4])) {
                    continue;
                }

                if (visited[nextBusNum]) {
                    continue;
                }

                visited[nextBusNum] = true;

                if (endList.contains(nextBusNum)) {
                    return now.busChangeCount + 1;
                }

                queue.add(new Point(nextBusNum, now.busChangeCount + 1));
            }
        }

        return 0;
    }

    private static long cross(int ax, int ay, int bx, int by, int px, int py) {
        return (long) (bx - ax) * (py - ay) - (by - ay) * (px - ax);
    }

    private static boolean meet(int ax, int ay, int bx, int by, int px, int py) {
        long minx = ax < bx ? ax : bx, maxx = ax > bx ? ax : bx;
        long miny = ay < by ? ay : by, maxy = ay > by ? ay : by;

        return minx <= px && px <= maxx && miny <= py && py <= maxy;
    }

}

class Point {

    int busN;
    int busChangeCount;

    public Point(int busN, int busChangeCount) {
        this.busN = busN;
        this.busChangeCount = busChangeCount;
    }
}
