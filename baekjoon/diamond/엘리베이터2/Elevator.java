package baekjoon.diamond.엘리베이터2;

import java.util.*;
import java.io.*;

public class Elevator {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int H = Integer.parseInt(st.nextToken());

        int elevatorC = Integer.parseInt(st.nextToken());

        int[][] elevatorList = new int[elevatorC + 1][2];

        for (int e = 1; e <= elevatorC; e++) {
            st = new StringTokenizer(br.readLine());

            elevatorList[e][0] = Integer.parseInt(st.nextToken());
            elevatorList[e][1] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());

        int startH = Integer.parseInt(st.nextToken());
        int endH = Integer.parseInt(st.nextToken());

        Map<Integer, List<Integer>> map = makeMap(elevatorList, startH, H, elevatorC);

        Route route = findRoute(map, elevatorList, endH, elevatorC);

        StringBuilder sb = new StringBuilder();

        if (route == null || endH > H || startH > H || H == 1) {
            sb.append(-1);
        } else {
            sb.append(route.cnt).append(route.route);
        }

        System.out.println(sb.toString());

        br.close();
    }

    private static Route findRoute(Map<Integer, List<Integer>> map, int[][] elevatorList, int endH, int elevatorC) {
        Deque<Route> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[elevatorC + 1];

        queue.add(new Route(0, 0, ""));

        while (!queue.isEmpty()) {
            Route now = queue.poll();

            for (int e : map.get(now.point)) {
                if (!visited[e]) {
                    visited[e] = true;

                    String nextRoute = String.format("%s\n%d", now.route, e);

                    Route route = new Route(e, now.cnt + 1, nextRoute);

                    if (endH >= elevatorList[e][0] && (endH - elevatorList[e][0]) % elevatorList[e][1] == 0) {
                        return route;
                    }

                    queue.add(route);
                }
            }
        }

        return null;
    }

    private static Map<Integer, List<Integer>> makeMap(int[][] elevatorList, int startH, int H, int elevatorC) {
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i <= elevatorC; i++) {
            map.put(i, new ArrayList<>());
        }

        for (int a = 1; a <= elevatorC; a++) {
            if (startH >= elevatorList[a][0] && (startH - elevatorList[a][0]) % elevatorList[a][1] == 0) {
                map.get(0).add(a);
            }

            for (int b = a + 1; b <= elevatorC; b++) {
                if (canMove(elevatorList[a], elevatorList[b], H)) {
                    map.get(a).add(b);
                    map.get(b).add(a);
                }
            }
        }

        return map;
    }

    private static boolean canMove(int[] a, int[] b, int H) {
        int diff = b[0] - a[0];

        EG eg = getGcd(a[1], b[1]);

        if (diff % eg.g == 0) {
            long a0 = (long) eg.x * (diff / eg.g);

            long c0 = a[0] + a[1] * a0;

            long L = (long) (a[1] / eg.g) * b[1];

            long k = (-c0 + (L - 1)) / L;

            long cMin = c0 + k * L;

            if (a[0] <= cMin && b[0] <= cMin && cMin <= H) {
                return true;
            }
        }

        return false;
    }

    private static EG getGcd(long a, long b) {

        if (b == 0) {
            return new EG(1, 0, a);
        }

        EG sub = getGcd(b, a % b);

        return new EG(sub.y, sub.x - (a / b) * sub.y, sub.g);
    }
}

class Route {

    int point, cnt;
    String route;

    public Route(int point, int cnt, String route) {
        this.point = point;
        this.cnt = cnt;
        this.route = route;
    }

}

class EG {
    long x, y, g;

    public EG(long x, long y, long g) {
        this.x = x;
        this.y = y;
        this.g = g;
    }
}