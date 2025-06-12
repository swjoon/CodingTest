package baekjoon.platinum.나무위의구슬;

import java.util.*;
import java.io.*;

public class Bead {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        while (true) {
            int n = Integer.parseInt(br.readLine());

            if (n == 0) {
                break;
            }

            StringTokenizer st;

            Map<Integer, List<Integer>> map = new HashMap<>();

            int[][] node = new int[n + 1][2];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());

                int nodeN = Integer.parseInt(st.nextToken());

                int beadC = Integer.parseInt(st.nextToken());

                int childC = Integer.parseInt(st.nextToken());
                
                node[nodeN][1] = beadC;

                map.putIfAbsent(nodeN, new ArrayList<>());

                for (int c = 0; c < childC; c++) {
                    int child = Integer.parseInt(st.nextToken());

                    map.putIfAbsent(child, new ArrayList<>());
                    map.get(nodeN).add(child);
                    map.get(child).add(nodeN);
                }
            }

            int ans = findMoveCount(n, node, map);

            sb.append(ans).append("\n");
        }

        System.out.println(sb.toString());

        br.close();
    }

    private static int findMoveCount(int n, int[][] node, Map<Integer, List<Integer>> map) {
        boolean[] visited = new boolean[n + 1];

        makeUp(1, visited, node, map);

        int value = moveBead(node, map);

        return value;
    }

    private static void makeUp(int now, boolean[] visited, int[][] node, Map<Integer, List<Integer>> map) {
        visited[now] = true;

        node[now][0] += 1;

        for (int n : map.get(now)) {
            if (!visited[n]) {

                makeUp(n, visited, node, map);

                node[now][0] += node[n][0];
                node[now][1] += node[n][1];
            }
        }
    }

    private static int moveBead(int[][] node, Map<Integer, List<Integer>> map) {
        Deque<Integer> queue = new ArrayDeque<>();

        boolean[] visited = new boolean[node.length];

        visited[1] = true;

        queue.add(1);

        int moveValue = 0;

        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (int next : map.get(now)) {
                if (!visited[next]) {
                    visited[next] = true;

                    moveValue += Math.abs(node[next][1] - node[next][0]);

                    queue.add(next);
                }
            }
        }

        return moveValue;
    }
}