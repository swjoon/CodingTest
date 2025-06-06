package baekjoon.platinum.주차장;

import java.util.*;
import java.io.*;

public class ParkingLot {
    private static final int INF = Integer.MAX_VALUE;
    private static final int[] DIRX = { 0, 0, -1, 1 };
    private static final int[] DIRY = { -1, 1, 0, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int Y = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        int[][] parkMap = new int[Y][X];

        int pCount = 0;

        List<Point> cars = new ArrayList<>();

        for (int y = 0; y < Y; y++) {
            char[] c = br.readLine().toCharArray();

            for (int x = 0; x < c.length; x++) {
                switch (c[x]) {
                    case 'C':
                        parkMap[y][x] = 0;
                        cars.add(new Point(x, y, 0));
                        break;
                    case 'P':
                        parkMap[y][x] = ++pCount;
                        break;
                    case 'X':
                        parkMap[y][x] = -1;
                        break;
                    default:
                        parkMap[y][x] = 0;
                        break;
                }
            }
        }

        int cCount = cars.size();

        int ans = cCount == 0 ? 0 : -1;

        if (cCount <= pCount && pCount != 0 && cCount != 0) {
            int[][] distInfo = new int[cCount][pCount];

            for (int[] dist : distInfo) {
                Arrays.fill(dist, INF);
            }

            for (int i = 0; i < cCount; i++) {
                bfs(cars.get(i), X, Y, parkMap, i, distInfo);
            }

            ans = binarySearch(distInfo);
        }

        System.out.println(ans);

        br.close();
    }

    private static void bfs(Point start, int X, int Y, int[][] map, int carNum, int[][] distInfo) {
        boolean[][] visited = new boolean[Y][X];
        Deque<Point> queue = new ArrayDeque<>();

        visited[start.y][start.x] = true;

        queue.add(start);

        while (!queue.isEmpty()) {
            Point now = queue.poll();

            int nextCnt = now.cnt + 1;

            for (int i = 0; i < 4; i++) {
                int nextX = now.x + DIRX[i];
                int nextY = now.y + DIRY[i];

                if (nextX < 0 || nextY < 0 || nextX >= X || nextY >= Y ||
                        map[nextY][nextX] == -1 || visited[nextY][nextX]) {
                    continue;
                }

                if (map[nextY][nextX] >= 1) {
                    distInfo[carNum][map[nextY][nextX] - 1] = nextCnt;
                }

                visited[nextY][nextX] = true;

                queue.add(new Point(nextX, nextY, nextCnt));
            }
        }
    }

    private static int binarySearch(int[][] distInfo) {
        int ans = -1;

        int l = 0;
        int r = findMaxDist(distInfo);

        while (l <= r) {
            int mid = (l + r) / 2;

            if (canParking(distInfo, findParkUnderMid(mid, distInfo))) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return ans;
    }

    private static Map<Integer, List<Integer>> findParkUnderMid(int mid, int[][] dist) {
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < dist.length; i++) {
            map.putIfAbsent(i, new ArrayList<>());
            for (int j = 0; j < dist[i].length; j++) {
                if (dist[i][j] <= mid) {
                    map.get(i).add(j);
                }
            }
        }

        return map;
    }

    private static boolean canParking(int[][] distInfo, Map<Integer, List<Integer>> canPark) {
        int cCount = distInfo.length;
        int pCount = distInfo[0].length;

        int[] parkInfo = new int[pCount];

        Arrays.fill(parkInfo, -1);

        int parkingCount = 0;

        for (int car = 0; car < canPark.size(); car++) {
            boolean[] visited = new boolean[pCount];

            if (change(car, canPark, visited, parkInfo)) {
                parkingCount++;
            }
        }

        return parkingCount == cCount;
    }

    private static boolean change(int car, Map<Integer, List<Integer>> canPark, boolean[] visited, int[] parkInfo) {

        for (int park : canPark.get(car)) {

            if (visited[park]) {
                continue;
            }

            visited[park] = true;

            if (parkInfo[park] == -1 || change(parkInfo[park], canPark, visited, parkInfo)) {

                parkInfo[park] = car;

                return true;
            }
        }

        return false;
    }

    private static int findMaxDist(int[][] distInfo) {
        int max = 0;

        for (int[] dist : distInfo) {
            for (int d : dist) {
                max = Math.max(max, d == INF ? 0 : d);
            }
        }

        return max;
    }
}

class Point {

    int x, y, cnt;

    public Point(int x, int y, int cnt) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }
}