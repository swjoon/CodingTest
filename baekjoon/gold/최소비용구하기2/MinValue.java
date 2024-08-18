package baekjoon.gold.최소비용구하기2;

import java.util.*;
import java.io.*;

public class MinValue {
    static int n, m;
    static int sPoint, ePoint;
    static int[] parent;
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

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());

        sPoint = Integer.parseInt(st.nextToken());
        ePoint = Integer.parseInt(st.nextToken());

    }

    static void dijkstra() {

    }
}
