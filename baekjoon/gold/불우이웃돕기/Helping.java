package baekjoon.gold.불우이웃돕기;

import java.util.*;
import java.io.*;

public class Helping {
    static class LANCable implements Comparable<LANCable> {
        int start, end, dist;

        public LANCable(int start, int end, int dist) {
            this.start = start;
            this.end = end;
            this.dist = dist;
        }

        @Override
        public int compareTo(LANCable o1) {
            return this.dist - o1.dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<LANCable> cableList = new PriorityQueue<>();

        for (int s = 1; s <= N; s++) {
            char[] endPoint = br.readLine().toCharArray();
            for (int e = 1; e <= N; e++) {
                if (endPoint[e - 1] != '0') {
                    cableList.add(new LANCable(s, e, parseValue(endPoint[e - 1])));
                }
            }
        }

        int[] parent = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        int ans = 0;

        while (!cableList.isEmpty()) {
            LANCable cable = cableList.poll();

            if (!union(cable.start, cable.end, parent)) {
                ans += cable.dist;
            }
        }

        for (int i = 1; i <= N; i++) {
            if (findParent(i, parent) != 1) {
                System.out.println(-1);
                return;
            }
        }

        System.out.println(ans);
    }

    private static int findParent(int n, int[] parent) {
        if (n != parent[n]) {
            parent[n] = findParent(parent[n], parent);
        }
        return parent[n];
    }

    private static boolean union(int a, int b, int[] parent) {
        int parentA = findParent(a, parent);
        int parentB = findParent(b, parent);

        if (parentA == parentB) {
            return false;
        }

        if (parentA > parentB) {
            parent[parentA] = parentB;
        } else {
            parent[parentB] = parentA;
        }

        return true;
    }

    private static int parseValue(char input) {
        int value = (int) input;
        return input >= 97 ? value - 96 : value - 38;
    }
}