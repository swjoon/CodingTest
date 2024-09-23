package baekjoon.gold.악덕영주혜유;

import java.util.*;
import java.io.*;

public class BadLord {
    static int N, K;
    static int total, max;
    static int[] parent;
    static boolean[] visited;
    static List<Node> list = new ArrayList<>();
    static List<List<int[]>> route = new ArrayList<>();

    static class Node {
        int start;
        int end;
        int cost;

        Node(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        parent = new int[N];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < N; i++) {
            route.add(new ArrayList<>());
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            list.add(new Node(start, end, cost));
        }

        Collections.sort(list, (o1, o2) -> o1.cost - o2.cost);

        total = 0;

        for (Node n : list) {
            int start = n.start;
            int end = n.end;
            int cost = n.cost;
            if (find(start) != find(end)) {
                total += cost;
                union(start, end);
                route.get(start).add(new int[] { end, cost });
                route.get(end).add(new int[] { start, cost });
            }
        }

        max = 0;

        for (int i = 0; i < N; i++) {
            visited[i] = true;
            DFS(i, 0);
            visited[i] = false;
        }

        System.out.println(total);
        System.out.println(max);
    }

    static void DFS(int x, int sum) {

        max = Math.max(max, sum);

        for (int[] n : route.get(x)) {
            int nextNode = n[0];
            int cost = n[1];
            if (visited[nextNode]) {
                continue;
            }
            visited[nextNode] = true;
            DFS(nextNode, sum + cost);
            visited[nextNode] = false;
        }
    }

    static int find(int x) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y) {
        int pX = find(x);
        int pY = find(y);
        if (pX < pY) {
            parent[pY] = pX;
        } else {
            parent[pX] = pY;
        }
    }
}
