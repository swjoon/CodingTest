package baekjoon.gold.네트워크복구;

import java.util.*;
import java.io.*;

public class Network {

    public static int n, m;
    public static int[] distance, connect;
    public static int INF = Integer.MAX_VALUE;

    public static class Node implements Comparable<Node> {
        int n;
        int dis;

        public Node(int n, int dis) {
            this.n = n;
            this.dis = dis;
        }

        @Override
        public int compareTo(Node o) {
            return this.dis - o.dis;
        }
    }

    public static ArrayList<Node>[] list;
    public static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        list = new ArrayList[n + 1];
        distance = new int[n + 1];
        connect = new int[n + 1];

        for (int i = 0; i < n + 1; i++) {
            list[i] = new ArrayList<Node>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int dis = Integer.parseInt(st.nextToken());

            list[a].add(new Node(b, dis));
            list[b].add(new Node(a, dis));
        }

        Arrays.fill(distance, INF);
        distance[1] = 0;
        
        dijkstra();

        for (int i = 2; i <= n; i++) {
            if (connect[i] == 0)
                continue;
            cnt++;
            sb.append(i + " " + connect[i] + "\n");
        }
        System.out.println(cnt);
        System.out.println(sb.toString());
    }

    static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));
        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if (node.dis > distance[node.n])
                continue;

            // 1까지의 거리가 더 짧은 경우에 connect[n] 갱신.
            for (Node nd : list[node.n]) {
                if (distance[nd.n] > nd.dis + node.dis) {
                    distance[nd.n] = nd.dis + node.dis;
                    connect[nd.n] = node.n;
                    pq.add(new Node(nd.n, distance[nd.n]));
                }
            }
        }
    }
}