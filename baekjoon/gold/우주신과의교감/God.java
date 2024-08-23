package baekjoon.gold.우주신과의교감;

import java.util.*;
import java.io.*;

public class God {
    static int N, M;
    static int[] parent;
    static int[][] godxy;

    static class Bridge implements Comparable<Bridge> {
        int a, b;
        double value;

        Bridge(int a, int b, double value) {
            this.a = a;
            this.b = b;
            this.value = value;
        }

        @Override
        public int compareTo(Bridge b) {
            return Double.compare(this.value, b.value);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        godxy = new int[N + 1][2];

        for (int i = 0; i < N + 1; i++) {
            parent[i] = i;
        }

        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            godxy[i][0] = Integer.parseInt(st.nextToken());
            godxy[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }

        PriorityQueue<Bridge> pq = new PriorityQueue<>();

        for (int i = 1; i < N; i++) {
            for (int j = i + 1; j < N + 1; j++) {
                double d = Math.sqrt(
                        Math.pow(godxy[i][0] - godxy[j][0], 2) + Math.pow(godxy[i][1] - godxy[j][1], 2));
                pq.add(new Bridge(i, j, d));
            }
        }

        double answer = 0;

        while (!pq.isEmpty()) {
            Bridge bridge = pq.poll();
            if (find(bridge.a) != find(bridge.b)) {
                answer += bridge.value;
                union(bridge.a, bridge.b);
            }
        }

        System.out.printf("%.2f\n", answer);
    }

    static int find(int node) {
        if (node != parent[node]) {
            parent[node] = find(parent[node]);
        }
        return parent[node];
    }

    static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if (pa != pb) {
            parent[pa] = pb;
        }
    }
}
