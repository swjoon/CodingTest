package baekjoon.gold.인터넷설치;

import java.util.*;
import java.io.*;

public class Internet {
    static int N, P, K;
    static int[] parent;
    static ArrayList<Node>[] list;

    static public class Node implements Comparable<Node> {
        int a;
        int value;

        Node(int a, int value) {
            this.a = a;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return this.value - o.value;
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        list = new ArrayList[N + 1];

        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            list[a].add(new Node(b, value));
            list[b].add(new Node(a, value));
        }

        dijkstra();
    }

     static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));
        while (!pq.isEmpty()) {
            Node node = pq.poll();

            
            for (Node n : list[node.a]) {
                
            }
        }
    }
}
// bfs?
