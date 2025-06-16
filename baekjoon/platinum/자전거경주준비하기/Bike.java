package baekjoon.platinum.자전거경주준비하기;

import java.util.*;
import java.io.*;

public class Bike {
    private static final long MOD = 1000000000L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, List<Integer>> reverseGraph = new HashMap<>();
        Map<Integer, List<Integer>> newGraph = new HashMap<>();

        for (int i = 1; i <= N; i++) {
            graph.put(i, new ArrayList<>());
            reverseGraph.put(i, new ArrayList<>());
            newGraph.put(i, new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph.get(u).add(v);
            reverseGraph.get(v).add(u);
        }

        boolean[] from = new boolean[N + 1];
        dfs(1, from, graph);

        boolean[] to = new boolean[N + 1];
        dfs(2, to, reverseGraph);

        boolean[] valid = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            valid[i] = from[i] && to[i];
        }

        int[] indegree = new int[N + 1];

        for (int u = 1; u <= N; u++) {
            if (!valid[u])
                continue;
            for (int v : graph.get(u)) {
                if (!valid[v]) {
                    continue;
                }

                newGraph.get(u).add(v);
                indegree[v]++;
            }
        }

        long[] dp = new long[N + 1];
        dp[1] = 1;

        Deque<Integer> queue = new ArrayDeque<>();

        for (int i = 1; i <= N; i++) {
            if (valid[i] && indegree[i] == 0) {
                queue.offer(i);
            }
        }

        int processed = 0;
        int totalValid = 0;

        for (boolean b : valid) {
            if (b) {
                totalValid++;
            }
        }

        boolean overflow = false;

        while (!queue.isEmpty()) {
            int now = queue.poll();

            processed++;

            for (int next : newGraph.get(now)) {
                dp[next] += dp[now];

                if (dp[next] >= MOD) {
                    overflow = true;
                    dp[next] %= MOD;
                }

                if (--indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        if (processed < totalValid) {
            System.out.println("inf");
        } else {
            System.out.println(overflow ? String.format("%09d", dp[2]) : dp[2]);
        }
    }

    static void dfs(int now, boolean[] visited, Map<Integer, List<Integer>> graph) {
        if (visited[now]) {
            return;
        }

        visited[now] = true;

        for (int next : graph.get(now)) {
            dfs(next, visited, graph);
        }
    }
}