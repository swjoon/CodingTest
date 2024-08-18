package baekjoon.gold.최소비용구하기2;

import java.util.*;
import java.io.*;

public class MinValue {
    static int n, m;
    static int sPoint, ePoint;
    static int[] connect;
    static int[] nodevalue;
    static ArrayList<Node>[] list;

    static public class Node implements Comparable<Node> {
        int n;
        int value;

        public Node(int n, int value) {
            this.n = n;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return this.value - o.value;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        list = new ArrayList[n + 1];
        connect = new int[n + 1];
        nodevalue = new int[n + 1];

        for (int i = 0; i < n + 1; i++) {
            list[i] = new ArrayList<Node>();
        }

        Arrays.fill(nodevalue, Integer.MAX_VALUE);

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            list[start].add(new Node(end, value));
        }

        st = new StringTokenizer(br.readLine());

        sPoint = Integer.parseInt(st.nextToken());
        ePoint = Integer.parseInt(st.nextToken());

        nodevalue[sPoint] = 0;

        dijkstra(sPoint);

        ArrayList<Integer> answer = new ArrayList<>();

        int next = ePoint;
        answer.add(next);
        while (next != sPoint) {
            next = connect[next];
            answer.add(next);
        }

        System.out.println(nodevalue[ePoint]);
        System.out.println(answer.size());
        for (int i = answer.size() - 1; i >= 0; i--) {
            System.out.print(answer.get(i) + " ");
        }

    }

    static void dijkstra(int sPoint) {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.add(new Node(sPoint, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if (node.value > nodevalue[node.n])
                continue;

            for (Node nd : list[node.n]) {
                if (nodevalue[nd.n] > node.value + nd.value) {
                    nodevalue[nd.n] = node.value + nd.value;
                    connect[nd.n] = node.n;
                    pq.add(new Node(nd.n, nodevalue[nd.n]));
                }
            }
        }
    }
}
