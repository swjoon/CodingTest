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

        Map<Integer, List<Integer>> busConnection = getBusConnection(busInfo);

        List<Integer> startList = new ArrayList<>();
        List<Integer> endList = new ArrayList<>();

        boolean oneWay = false;

        for (int i = 0; i < busC; i++) {
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
            ans = bfs(startList, endList, busConnection, busC);
        }

        System.out.println(ans);
    }

    private static Map<Integer, List<Integer>> getBusConnection(int[][] busInfo) {
        Map<Integer, List<Integer>> busConnection = new HashMap<>();

        for (int i = 0; i < busInfo.length; i++) {
            int sBusNum = busInfo[i][0];

            busConnection.putIfAbsent(sBusNum, new ArrayList<>());

            for (int j = i + 1; j < busInfo.length; j++) {
                int eBusNum = busInfo[j][0];

                busConnection.putIfAbsent(eBusNum, new ArrayList<>());

                if (checkCanTransfer(
                        busInfo[i][1], busInfo[i][2], busInfo[i][3], busInfo[i][4],
                        busInfo[j][1], busInfo[j][2], busInfo[j][3], busInfo[j][4])) {
                    busConnection.get(sBusNum).add(eBusNum);
                    busConnection.get(eBusNum).add(sBusNum);
                }
            }
        }

        return busConnection;
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

    private static int bfs(List<Integer> startList, List<Integer> endList, Map<Integer, List<Integer>> busConnection, int busC) {
        Deque<Point> queue = new ArrayDeque<>();

        boolean[] visited = new boolean[busC + 1];

        for (int n : startList) {
            queue.add(new Point(n, 1));
            visited[n] = true;
        }

        while (!queue.isEmpty()) {
            Point now = queue.poll();

            for (int nextBusNum : busConnection.get(now.busN)) {

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
