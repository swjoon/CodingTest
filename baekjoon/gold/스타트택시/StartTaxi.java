package baekjoon.gold.스타트택시;

import java.util.*;
import java.io.*;

public class StartTaxi {
    private static final int DIRSIZE = 4;
    private static final int[] DX = { 0, -1, 0, 1 };
    private static final int[] DY = { 1, 0, -1, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int gas = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][N];

        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());

        int startY = Integer.parseInt(st.nextToken()) - 1;
        int startX = Integer.parseInt(st.nextToken()) - 1;

        TaxiPoint start = new TaxiPoint(startX, startY, 0, gas);

        Map<Integer, int[]> targetMap = new HashMap<>();

        for (int target = 2; target < M + 2; target++) {
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = target;
            int ey = Integer.parseInt(st.nextToken()) - 1;
            int ex = Integer.parseInt(st.nextToken()) - 1;
            targetMap.put(target, new int[] { ex, ey });
        }

        int remainGas = workTaxi(start, M, map, targetMap);

        System.out.println(remainGas);

        br.close();
    }

    private static int workTaxi(TaxiPoint taxi, int targetCount, int[][] map, Map<Integer, int[]> targetMap) {

        while (targetCount-- > 0) {
            taxi = findTarget(taxi, map);

            if (taxi == null) {
                return -1;
            }

            taxi = moveEndPoint(taxi, map, targetMap);

            if (taxi == null) {
                return -1;
            }
        }

        return taxi.remainGas;
    }

    private static TaxiPoint findTarget(TaxiPoint taxi, int[][] map) {
        if (map[taxi.y][taxi.x] > 1) {
            return new TaxiPoint(taxi.x, taxi.y, 0, taxi.remainGas);
        }

        int mapSize = map.length;

        boolean[][] visited = new boolean[mapSize][mapSize];

        visited[taxi.y][taxi.x] = true;

        Deque<TaxiPoint> queue = new ArrayDeque<>();

        queue.add(taxi);

        int minCount = Integer.MAX_VALUE;

        TaxiPoint returnPoint = null;

        while (!queue.isEmpty()) {
            TaxiPoint now = queue.poll();

            if (now.remainGas == 0) {
                continue;
            }

            if (minCount == now.count) {
                return returnPoint;
            }

            for (int i = 0; i < DIRSIZE; i++) {
                int nextX = now.x + DX[i];
                int nextY = now.y + DY[i];
                int nextCount = now.count + 1;
                int nextRemainGas = now.remainGas - 1;

                if (nextX < 0 || nextY < 0 || nextX >= mapSize || nextY >= mapSize || map[nextY][nextX] == 1
                        || visited[nextY][nextX]) {
                    continue;
                }

                if (map[nextY][nextX] > 1) {
                    minCount = nextCount;
                    if (returnPoint == null) {
                        returnPoint = new TaxiPoint(nextX, nextY, 0, nextRemainGas);
                    } else if (returnPoint.y > nextY || (returnPoint.y == nextY && returnPoint.x > nextX)) {
                        returnPoint.x = nextX;
                        returnPoint.y = nextY;
                    }
                    continue;
                }

                visited[nextY][nextX] = true;

                queue.add(new TaxiPoint(nextX, nextY, nextCount, nextRemainGas));
            }

        }

        return returnPoint;
    }

    private static TaxiPoint moveEndPoint(TaxiPoint taxi, int[][] map, Map<Integer, int[]> targetMap) {
        int[] endPoint = targetMap.get(map[taxi.y][taxi.x]);

        map[taxi.y][taxi.x] = 0;

        int mapSize = map.length;

        boolean[][] visited = new boolean[mapSize][mapSize];

        visited[taxi.y][taxi.x] = true;

        Deque<TaxiPoint> queue = new ArrayDeque<>();

        queue.add(taxi);

        while (!queue.isEmpty()) {
            TaxiPoint now = queue.poll();

            if (now.remainGas == 0) {
                continue;
            }

            for (int i = 0; i < DIRSIZE; i++) {
                int nextX = now.x + DX[i];
                int nextY = now.y + DY[i];
                int nextRemainGas = now.remainGas - 1;
                int nextCount = now.count + 1;

                if (nextX < 0 || nextY < 0 || nextX >= mapSize || nextY >= mapSize || map[nextY][nextX] == 1
                        || visited[nextY][nextX]) {
                    continue;
                }

                if (nextX == endPoint[0] && nextY == endPoint[1]) {
                    return new TaxiPoint(nextX, nextY, 0, nextRemainGas + nextCount * 2);
                }

                visited[nextY][nextX] = true;

                queue.add(new TaxiPoint(nextX, nextY, nextCount, nextRemainGas));
            }
        }

        return null;
    }
}

class TaxiPoint {
    int x, y, count, remainGas;

    public TaxiPoint(int x, int y, int count, int remainGas) {
        this.x = x;
        this.y = y;
        this.count = count;
        this.remainGas = remainGas;
    }
}