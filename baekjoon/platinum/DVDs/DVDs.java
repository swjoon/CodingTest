package baekjoon.platinum.DVDs;

import java.util.*;
import java.io.*;

public class DVDs {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int testCase = 0; testCase < T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int dvdC = Integer.parseInt(st.nextToken());
            int event = Integer.parseInt(st.nextToken());

            int size = 1;

            while (size < dvdC) {
                size <<= 1;
            }

            int[][] tree = new int[2][size * 2];

            for (int i = 0; i < dvdC; i++) {
                tree[0][size + i] = i;
                tree[1][size + i] = i;
            }

            for (int i = size - 1; i > 0; i--) {
                tree[0][i] = Math.min(tree[0][i << 1], tree[0][i << 1 | 1]);
                tree[1][i] = Math.max(tree[1][i << 1], tree[1][i << 1 | 1]);
            }

            for (int e = 0; e < event; e++) {
                st = new StringTokenizer(br.readLine());

                int type = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if (type == 1) {
                    String ans = find(a, b, size, tree);
                    sb.append(ans).append("\n");
                } else {
                    update(a, b, size, tree);
                }
            }

        }

        System.out.println(sb.toString());

        br.close();
    }

    private static String find(int left, int right, int size, int[][] tree) {
        int l = size + left;
        int r = size + right;

        int max = 0;
        int min = Integer.MAX_VALUE;

        while (l <= r) {
            if ((l & 1) == 1) {
                min = Math.min(min, tree[0][l]);
                max = Math.max(max, tree[1][l]);

                l++;
            }

            if ((r & 1) == 0) {
                min = Math.min(min, tree[0][r]);
                max = Math.max(max, tree[1][r]);

                r--;
            }

            l >>= 1;
            r >>= 1;
        }

        return min < left || right < max ? "NO" : "YES";
    }

    private static void update(int a, int b, int size, int[][] tree) {
        int A = size + a;
        int B = size + b;

        int v = tree[0][B];

        tree[0][B] = tree[0][A];
        tree[1][B] = tree[1][A];

        tree[0][A] = v;
        tree[1][A] = v;

        A >>= 1;
        B >>= 1;

        while (0 < A) {
            tree[0][A] = Math.min(tree[0][A << 1], tree[0][A << 1 | 1]);
            tree[1][A] = Math.max(tree[1][A << 1], tree[1][A << 1 | 1]);
            tree[0][B] = Math.min(tree[0][B << 1], tree[0][B << 1 | 1]);
            tree[1][B] = Math.max(tree[1][B << 1], tree[1][B << 1 | 1]);

            A >>= 1;
            B >>= 1;
        }
    }
}