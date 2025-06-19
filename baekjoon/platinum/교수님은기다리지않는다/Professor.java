package baekjoon.platinum.교수님은기다리지않는다;

import java.io.*;
import java.util.*;

public class Professor {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int sampleC = Integer.parseInt(st.nextToken());
            int actionC = Integer.parseInt(st.nextToken());

            if (sampleC == 0 && actionC == 0) {
                break;
            }

            int[] parent = new int[sampleC + 1];

            for (int i = 0; i <= sampleC; i++) {
                parent[i] = i;
            }

            int[] weight = new int[sampleC + 1];

            for (int i = 0; i < actionC; i++) {
                st = new StringTokenizer(br.readLine());

                String type = st.nextToken();

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if (type.equals("!")) {
                    int w = Integer.parseInt(st.nextToken());
                    union(a, b, w, parent, weight);
                    continue;
                }

                if (find(a, parent, weight) != find(b, parent, weight)) {
                    sb.append("UNKNOWN").append("\n");
                    continue;
                }

                int ans = weight[b] - weight[a];

                sb.append(ans).append("\n");
            }
        }

        System.out.println(sb.toString());

        br.close();
    }

    private static void union(int a, int b, int w, int[] parent, int[] weight) {
        int pA = find(a, parent, weight);
        int pB = find(b, parent, weight);

        if (pA == pB) {
            return;
        }

        parent[pB] = pA;
        weight[pB] = weight[a] - weight[b] + w;
    }

    private static int find(int a, int[] parent, int[] weight) {
        if (parent[a] == a) {
            return a;
        }

        int pA = parent[a];
        int root = find(pA, parent, weight);

        weight[a] += weight[pA];
        parent[a] = root;

        return root;
    }
}