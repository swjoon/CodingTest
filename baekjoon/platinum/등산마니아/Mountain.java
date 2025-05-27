package baekjoon.platinum.등산마니아;

import java.util.*;
import java.io.*;

public class Mountain {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Map<Integer, List<Integer>> routeMap = new HashMap<>();

        int N = Integer.parseInt(br.readLine());

        for (int i = 1; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            routeMap.putIfAbsent(n1, new ArrayList<>());
            routeMap.putIfAbsent(n2, new ArrayList<>());

            routeMap.get(n1).add(n2);
            routeMap.get(n2).add(n1);
        }

        long[][] nodeInfo = new long[N + 1][2];

        boolean[] visited = new boolean[N + 1];

        visited[1] = true;

        dfs(1, 0, nodeInfo, visited, routeMap);

        long answer = findCount(nodeInfo, N);

        System.out.println(answer);

        br.close();
    }

    private static int dfs(int now, int dep, long[][] nodeInfo, boolean[] visited,
            Map<Integer, List<Integer>> routeMap) {

        int nodeCount = 0;

        for (int next : routeMap.get(now)) {
            if (visited[next]) {
                continue;
            }

            visited[next] = true;

            nodeCount += (dfs(next, dep + 1, nodeInfo, visited, routeMap) + 1);
        }

        nodeInfo[now][0] = dep;
        nodeInfo[now][1] = nodeCount;

        return nodeCount;
    }

    private static long findCount(long[][] nodeInfo, int N) {
        long total = 0;
        long dup = 0;

        long node = N - 1;

        for (int i = 2; i <= N; i++) {
            total += (nodeInfo[i][0] * node);

            if (nodeInfo[i][1] > 0) {
                dup += ((nodeInfo[i][1] + 1) * nodeInfo[i][1] / 2);
            }
        }

        return total - dup;
    }
}