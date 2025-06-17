package baekjoon.platinum.임계경로;

import java.util.*;
import java.io.*;

public class Route {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int cityC = Integer.parseInt(br.readLine());
        int routeC = Integer.parseInt(br.readLine());

        List<int[]>[] routeInfo = new List[cityC + 1];

        for (int i = 1; i <= cityC; i++) {
            routeInfo[i] = new ArrayList<>();
        }

        for (int i = 0; i < routeC; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            routeInfo[s].add(new int[] { e, t });
        }

        StringTokenizer st = new StringTokenizer(br.readLine());

        int startP = Integer.parseInt(st.nextToken());
        int endP = Integer.parseInt(st.nextToken());

        int[] info = new int[cityC + 1];
        boolean[] visited = new boolean[cityC + 1];

        findRoute(startP, info, visited, routeInfo);

        StringBuilder sb = new StringBuilder();

        int routeCount = findRouteCount(info, routeInfo, startP, cityC);

        sb.append(info[startP]).append("\n").append(routeCount);

        System.out.println(sb.toString());

        br.close();
    }

    private static void findRoute(int now, int[] info, boolean[] visited, List<int[]>[] routeInfo) {
        for (int[] n : routeInfo[now]) {
            int next = n[0];

            if (!visited[next]) {
                visited[next] = true;

                findRoute(next, info, visited, routeInfo);
            }

            info[now] = Math.max(info[now], info[next] + n[1]);
        }
    }

    private static int findRouteCount(int[] info, List<int[]>[] routeInfo, int startP, int cityC) {
        Deque<Integer> queue = new ArrayDeque<>();

        boolean[] visited = new boolean[cityC + 1];

        queue.add(startP);

        visited[startP] = true;

        int routeCount = 0;

        while (!queue.isEmpty()) {
            int now = queue.poll();
            int maxTime = info[now];

            for (int[] n : routeInfo[now]) {
                int next = n[0];
                int usetime = n[1];
                int nextTime = info[next];

                if (maxTime == nextTime + usetime) {
                    routeCount++;

                    if (!visited[next]) {
                        visited[next] = true;
                        queue.add(next);
                    }
                }
            }
        }

        return routeCount;
    }
}