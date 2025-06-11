package baekjoon.platinum.트리와경로개수쿼리;

import java.util.*;
import java.io.*;

public class RouteCount {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int size = Integer.parseInt(br.readLine());

        int[] colorInfo = new int[size + 1];

        boolean[] visited = new boolean[size + 1];

        int[][] dp = new int[size + 1][2];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int n = 1; n <= size; n++) {
            colorInfo[n] = Integer.parseInt(st.nextToken());
        }

        for (int n = 1; n <= size; n++) {
            if (colorInfo[n] == 1) {
                dp[0][1]++;
            } else {
                dp[0][0]++;
            }
        }

        if (size == 1) {
            System.out.println(0);
            return;
        }

        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 1; i < size; i++) {
            st = new StringTokenizer(br.readLine());

            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            map.putIfAbsent(n1, new ArrayList<>());
            map.putIfAbsent(n2, new ArrayList<>());

            map.get(n1).add(n2);
            map.get(n2).add(n1);
        }

        visited[1] = true;

        makeUpInfo(1, colorInfo, visited, dp, map);

        int query = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        long[] savedResult = new long[size + 1];

        for (int q = 0; q < query; q++) {
            int node = Integer.parseInt(br.readLine());

            if (map.get(node).size() == 1) {
                sb.append(0);
            } else {
                long route = savedResult[node] == 0 ? findRouteCount(node, dp, colorInfo, map) : savedResult[node];
                savedResult[node] = route;
                sb.append(route);
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());

        br.close();
    }

    private static void makeUpInfo(int node, int[] colorInfo, boolean[] visited, int[][] dp,
            Map<Integer, List<Integer>> map) {

        for (int nextNode : map.get(node)) {

            if (visited[nextNode]) {
                continue;
            }

            visited[nextNode] = true;

            makeUpInfo(nextNode, colorInfo, visited, dp, map);

            dp[node][0] += dp[nextNode][0];
            dp[node][1] += dp[nextNode][1];
        }

        if (colorInfo[node] == 0) {
            dp[node][0]++;
        } else {
            dp[node][1]++;
        }
    }

    private static long findRouteCount(int node, int[][] dp, int[] colorInfo, Map<Integer, List<Integer>> map) {
        long ans = 0;

        int nodeCount = map.get(node).size();

        int[][] info = new int[nodeCount][2];

        int now = dp[node][0] + dp[node][1];

        int[] nodeColor = colorInfo[node] == 0 ? new int[] { 1, 0 } : new int[] { 0, 1 };

        int i = 0;

        for (int nextNode : map.get(node)) {
            int next = dp[nextNode][0] + dp[nextNode][1];
            if (now > next) {
                info[i][0] = dp[nextNode][0];
                info[i][1] = dp[nextNode][1];
            } else {
                info[i][0] = dp[0][0] - dp[node][0];
                info[i][1] = dp[0][1] - dp[node][1];
            }
            i++;
        }

        int useZ = 0, useO = 0;

        for (int n = 0; n < nodeCount; n++) {
            int nowZ = info[n][0], nowO = info[n][1];

            ans += ((long) nowZ * (dp[0][1] - nodeColor[1] - useO - nowO)
                    + (long) nowO * (dp[0][0] - nodeColor[0] - useZ - nowZ));
            useZ += nowZ;
            useO += nowO;
        }

        return ans;
    }
}